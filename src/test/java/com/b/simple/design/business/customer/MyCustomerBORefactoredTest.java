package com.b.simple.design.business.customer;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;
import com.b.simple.design.model.customer.ProductImpl;
import com.b.simple.design.model.customer.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MyCustomerBORefactoredTest {

	private CustomerBO customerBO = new CustomerBOImpl();

	@Test
	public void testCustomerProductSum_TwoProductsSameCurrencies()
			throws DifferentCurrenciesException {

		Amount[] amounts = {
			createAmount(new BigDecimal("5.0"), Currency.EURO),
			createAmount(new BigDecimal("6.0"), Currency.EURO)
		};

		List<Product> products = createProductsWithAmounts(amounts);

		Amount actualAmount = customerBO.getCustomerProductsSum(products);
		Amount expectedAmount = createAmount(((Amount) createAmount(new BigDecimal("5.0"), Currency.EURO)).getValue().add(((Amount) createAmount(new BigDecimal("6.0"), Currency.EURO)).getValue()), Currency.EURO);

		assertAmount(expectedAmount, actualAmount);
	}

	@Test
	public void testCustomerProductSum_TwoProductsDifferentCurrencies() {

		Amount[] amounts = {
			createAmount(new BigDecimal("5.0"), Currency.INDIAN_RUPEE),
			createAmount(new BigDecimal("6.0"), Currency.EURO)
		};

		List<Product> products = createProductsWithAmounts(amounts);

		Assertions.assertThrows(DifferentCurrenciesException.class, ()->{
			customerBO.getCustomerProductsSum(products);
		});
	}

	@Test
	public void testCustomerProductSum_NoProductsNoCurrencies()
		throws DifferentCurrenciesException {

		List<Product> products = new ArrayList<Product>();

		Amount actualAmount = customerBO.getCustomerProductsSum(products);
		Amount expectedAmount = createAmount(BigDecimal.ZERO, Currency.EURO);

		assertAmount(expectedAmount, actualAmount);
	}

	private void assertAmount(Amount expectedAmount, Amount actualAmount) {
		assertEquals(expectedAmount.getCurrency(), actualAmount.getCurrency());
		assertEquals(expectedAmount.getValue(), actualAmount.getValue());
	}

	private AmountImpl createAmount(BigDecimal value, Currency currency) {
		return new AmountImpl(value, currency);
	}

	private Product createProduct(int id, String name, ProductType type, Amount amount) {
		return new ProductImpl(id, name, type, amount);
	}

	private List<Product> createProductsWithAmounts(Amount[] amounts) {
		List<Product> products = new ArrayList<Product>();

		for (Amount amount:amounts) {
			products.add(createProduct(100, "Product 15", ProductType.BANK_GUARANTEE, amount));
		}

		return products;
	}
}