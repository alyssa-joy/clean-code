package com.b.simple.design.business.text;

public class MyTextHelperRefactored {

	public String swapLastTwoCharacters(String str) {

		if (str.length() <= 1)
			return str;

		char[] charArray = str.toCharArray();

		int secondToLastCharacterIndex = charArray.length - 2;
		int LastCharacterIndex = charArray.length - 1;

		return swapCharacters(charArray, secondToLastCharacterIndex, LastCharacterIndex);
	}

	private String swapCharacters(char[] charArray, int index1, int index2) {

		char characterToSwap = charArray[index1];
		charArray[index1] = charArray[index2];
		charArray[index2] = characterToSwap;

		return new String(charArray);
	}

	public String truncateAInFirst2Positions(String str) {

		if (str.length() <= 1)
			return str.replaceAll("A", "");

		return deleteAInFirst2Positions(str);
	}

	private String deleteAInFirst2Positions(String str) {

		StringBuilder sb = new StringBuilder(str);

		for (int index = 0, counter = 0; counter < 2; ++index, ++counter) {
			if (sb.charAt(index) == 'A') {
				sb.deleteCharAt(index);
				--index;
			}
		}

		return sb.toString();
	}
}
