package com.c.refactoring.movie;

import com.c.refactoring.StringUtils;

public class MyMovieRefactored {

    String rating;

    public MyMovieRefactored(String rating) {
        super();
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public boolean isValidRating() {
        if (this.getRating() != null) {
            return isValidARating() || isValidBRating();
        }
        return false;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    private boolean isValidARating() {
        String firstCharacter = this.getRating().substring(0, 1);

        if (firstCharacter.equalsIgnoreCase("A")
            && this.getRating().length() == 3
            && StringUtils.isNumeric(this.getRating().substring(1, 3))) {
            return true;
        }

        return false;
    }

    private boolean isValidBRating() {
        String firstCharacter = this.getRating().substring(0, 1);

        if (firstCharacter.equalsIgnoreCase("B")
            && this.getRating().length() == 2) {

            String secondCharacter = this.getRating().substring(1, 2);

            if (StringUtils.isNumeric(secondCharacter)
                && Integer.parseInt(secondCharacter) > 0
                && Integer.parseInt(secondCharacter) < 5)
                return true;
        }

        return false;
    }
}
