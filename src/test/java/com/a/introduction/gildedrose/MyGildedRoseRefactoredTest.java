package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyGildedRoseRefactoredTest {

	public static final int NOT_EXPIRED_SELL_IN = 16;
	public static final int DEFAULT_QUALITY = 4;
	public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
	public static final int EXPIRED_SELL_IN = -2;
	public static final String AGED_BRIE = "Aged Brie";
	public static final int MAX_QUALITY = 50;
	public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
	public static final int SELL_IN_GREATER_THAN_TEN_EXCLUSIVE = 15;
	public static final int SELL_IN_BETWEEN_SIX_AND_TEN_INCLUSIVE = 7;
	public static final int NOT_EXPIRED_SELL_IN_LESS_THAN_FIVE_INCLUSIVE = 4;

	@Test
	public void testUnexpiredItemQualityDecreasesByOne() {

		//setup
		GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM, NOT_EXPIRED_SELL_IN, DEFAULT_QUALITY);

		//invoke
		app.updateQuality();

		//verify/assert
		Item expectedItem = new Item(DEFAULT_ITEM, NOT_EXPIRED_SELL_IN - 1, DEFAULT_QUALITY - 1);

		assertItem(expectedItem, app.items[0]);
	}

	@Test
	public void testExpiredItemQualityDecreasesByTwo() {

		GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM, EXPIRED_SELL_IN, DEFAULT_QUALITY);

		app.updateQuality();

		Item expectedItem = new Item(DEFAULT_ITEM, EXPIRED_SELL_IN - 1, DEFAULT_QUALITY - 2);

		assertItem(expectedItem, app.items[0]);
	}

	@Test
	public void testUnexpiredAgedBrieQualityIncreasesByOne() {

		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELL_IN, DEFAULT_QUALITY);

		app.updateQuality();

		Item expectedItem = new Item(AGED_BRIE, NOT_EXPIRED_SELL_IN - 1, DEFAULT_QUALITY + 1);

		assertItem(expectedItem, app.items[0]);
	}

	@Test
	public void testExpiredAgedBrieQualityIncreasesByTwo() {

		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, EXPIRED_SELL_IN, DEFAULT_QUALITY);

		app.updateQuality();

		Item expectedItem = new Item(AGED_BRIE, EXPIRED_SELL_IN - 1, DEFAULT_QUALITY + 2);

		assertItem(expectedItem, app.items[0]);
	}

	@Test
	public void testUnexpiredAgedBrieMaxQualityStaysTheSame() {

		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELL_IN, MAX_QUALITY);

		app.updateQuality();

		Item expectedItem = new Item(AGED_BRIE, NOT_EXPIRED_SELL_IN - 1, MAX_QUALITY);

		assertItem(expectedItem, app.items[0]);
	}

	@Test
	public void testBackstagePassesBeyondTenDaysQualityIncreasesByOne() {

		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, SELL_IN_GREATER_THAN_TEN_EXCLUSIVE, DEFAULT_QUALITY);

		app.updateQuality();

		Item expectedItem = new Item(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, SELL_IN_GREATER_THAN_TEN_EXCLUSIVE - 1, DEFAULT_QUALITY + 1);

		assertItem(expectedItem, app.items[0]);
	}

	@Test
	public void testBackstagePassesBetweenFiveAndTenDaysQualityIncreasesByTwo() {

		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, SELL_IN_BETWEEN_SIX_AND_TEN_INCLUSIVE, DEFAULT_QUALITY);

		app.updateQuality();

		Item expectedItem = new Item(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, SELL_IN_BETWEEN_SIX_AND_TEN_INCLUSIVE - 1, DEFAULT_QUALITY + 2);

		assertItem(expectedItem, app.items[0]);
	}

	@Test
	public void testBackstagePassesLessThanFiveDaysQualityIncreasesByThree() {

		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, NOT_EXPIRED_SELL_IN_LESS_THAN_FIVE_INCLUSIVE, DEFAULT_QUALITY);

		app.updateQuality();

		Item expectedItem = new Item(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, NOT_EXPIRED_SELL_IN_LESS_THAN_FIVE_INCLUSIVE - 1, DEFAULT_QUALITY + 3);

		assertItem(expectedItem, app.items[0]);
	}

	private void assertItem(Item expectedItem, Item actualItem) {
		assertEquals(expectedItem.name, actualItem.name);
		assertEquals(expectedItem.sellIn, actualItem.sellIn);
		assertEquals(expectedItem.quality, actualItem.quality);
	}

	private GildedRose createGildedRoseWithOneItem(String itemType, int sellIn, int quality) {
		Item item = new Item(itemType, sellIn, quality);
		Item[] items = new Item[]{item};
		return new GildedRose(items);
	}
}