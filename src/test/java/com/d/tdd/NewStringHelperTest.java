package com.d.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NewStringHelperTest {

    private NewStringHelper stringHelper = new NewStringHelper();

    @Test
    public void replaceAInFirst2Positions() {
        assertEquals("BCD", stringHelper.replaceAInFirst2Positions("ABCD"));
        assertEquals("CD", stringHelper.replaceAInFirst2Positions("AACD"));
        assertEquals("BCD", stringHelper.replaceAInFirst2Positions("BACD"));
        assertEquals("AA", stringHelper.replaceAInFirst2Positions("AAAA"));
        assertEquals("MNAA", stringHelper.replaceAInFirst2Positions("MNAA"));
        assertEquals("", stringHelper.replaceAInFirst2Positions(""));
        assertEquals("", stringHelper.replaceAInFirst2Positions("A"));
        assertEquals("", stringHelper.replaceAInFirst2Positions("AA"));
        assertEquals("B", stringHelper.replaceAInFirst2Positions("B"));
        assertEquals("BC", stringHelper.replaceAInFirst2Positions("BC"));
    }

    @Test
    public void areFirstAndLastTwoCharactersTheSame() {
        assertEquals(false, stringHelper.areFirstAndLastTwoCharactersTheSame(""));
        assertEquals(false, stringHelper.areFirstAndLastTwoCharactersTheSame("A"));
        assertEquals(true, stringHelper.areFirstAndLastTwoCharactersTheSame("AB"));
        assertEquals(false, stringHelper.areFirstAndLastTwoCharactersTheSame("ABC"));
        assertEquals(true, stringHelper.areFirstAndLastTwoCharactersTheSame("AAA"));
        assertEquals(true, stringHelper.areFirstAndLastTwoCharactersTheSame("ABCAB"));
        assertEquals(false, stringHelper.areFirstAndLastTwoCharactersTheSame("ABCDEBA"));
    }
}
