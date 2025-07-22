package com.d.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NewGameTest {

    private NewGame game = new NewGame();

    @Test
    public void testAll0s() {
        rollMultipleTimes(0, 20);

        assertEquals(0, game.score());
    }

    @Test
    public void testAll1s() {
        rollMultipleTimes( 1, 20);

        assertEquals(20, game.score());
    }

    @Test
    public void testHalf1sAndHalf2s() {
        rollMultipleTimes(1, 10);
        rollMultipleTimes(2, 10);

        assertEquals(30, game.score());
    }

    @Test
    public void testOneSpare() {
        rollSpare();
        rollMultipleTimes( 1, 18);

        assertEquals(29, game.score());
    }

    @Test
    public void testTwoSpares() {
        rollSpare();
        rollSpare();
        rollMultipleTimes(1, 16);

        assertEquals(42, game.score());
    }

    @Test
    public void testOneStrike() {
        rollStrike();
        rollMultipleTimes(1, 19);

        assertEquals(30, game.score());
    }

    private void rollSpare() {
        rollMultipleTimes(5, 2);
    }

    private void rollStrike() {
        rollMultipleTimes(10, 1);
    }

    private void rollMultipleTimes(int pinsKnocked, int noOfTimes) {
        for (int i = 1; i <= noOfTimes; i++) {
            game.roll(pinsKnocked);
        }
    }
}
