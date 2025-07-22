package com.d.tdd;

public class NewStringHelper {

    public String replaceAInFirst2Positions(String str) {
        if (str.length() < 2) {
            return str.replaceAll("A", "");
        }

        String first2Chars = str.substring(0, 2);
        String remainingChars = str.substring(2);

        return first2Chars.replaceAll("A", "") + remainingChars;
    }

    public boolean areFirstAndLastTwoCharactersTheSame(String str) {
        if (str.length() < 2)
            return false;

        String first2Chars = str.substring(0, 2);
        String last2Chars = str.substring(str.length() - 2);

        return first2Chars.equals(last2Chars);
    }
}
