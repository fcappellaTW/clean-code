package com.c.refactoring.movie;

import java.util.Arrays;
import java.util.List;

import com.c.refactoring.StringUtils;

public class Movie {

    private static final List<String> VALID_B_RATINGS = Arrays.asList("1", "2", "3", "4");

    String rating;

    public Movie(String rating) {
        super();
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public boolean isValidRating() {
        if (rating == null)
            return false;

        String gradeType = rating.substring(0, 1);
        String gradeRating = rating.substring(1);

        switch (gradeType) {
            case "A":
                return isGradeARatingValid(gradeRating);

            case "B":
                return isGradeBRatingValid(gradeRating);

            default:
                return false;
        }
    }

    private boolean isGradeARatingValid(String gradeARating) {
        return (gradeARating.length() == 2 && StringUtils.isNumeric(gradeARating));
    }

    private boolean isGradeBRatingValid(String gradeBRating) {
        return VALID_B_RATINGS.contains(gradeBRating);
    }
}
