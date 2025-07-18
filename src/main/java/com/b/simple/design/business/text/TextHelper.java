package com.b.simple.design.business.text;

public class TextHelper {

	public String swapLastTwoCharacters(String str) {
		int stringLength = str.length();
		if (stringLength < 2)
			return str;

		char lastChar = str.charAt(stringLength - 1);
		char secondLastChar = str.charAt(stringLength - 2);

		StringBuilder sb = new StringBuilder(str);
		sb.setCharAt(stringLength - 1, secondLastChar);
		sb.setCharAt(stringLength - 2, lastChar);

		return sb.toString();
	}

	public String truncateAInFirst2Positions(String str) {
		if (str.length() < 2)
			return str.replaceAll("A", "");

		String first2Chars = str.substring(0, 2);
		String updatedFirst2Chars = first2Chars.replaceAll("A", "");
		String remainingChars = str.substring(2);

		return updatedFirst2Chars + remainingChars;
	}
}
