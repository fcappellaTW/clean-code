package com.d.tdd;

public class NewGame {

    private int[] rolls = new int[21];
    private int currentRoll = 0;

    public void roll(int pinsKnocked) {
        rolls[currentRoll++] = pinsKnocked;
    }

    public int score() {
        int score = 0;
        int rollIndex = 0;

        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(rollIndex))
                score += 10 + strikeBonus(rollIndex);
            else if (isSpare(rollIndex))
                score += 10 + spareBonus(rollIndex);
            else
                score += rolls[rollIndex] + rolls[rollIndex + 1];

            rollIndex += 2;
        }
        return score;
    }

    public boolean isSpare(int rollIndex) {
        return rolls[rollIndex] + rolls[rollIndex + 1] == 10;
    }

    public int spareBonus(int rollIndex) {
        return rolls[rollIndex + 2];
    }

    public boolean isStrike(int rollIndex) {
        return rolls[rollIndex] == 10;
    }

    public int strikeBonus(int rollIndex) {
        return rolls[rollIndex + 1] + rolls[rollIndex + 2];
    }
}
