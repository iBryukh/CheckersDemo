package com.sheremet.checkers.client.units;

import checkers.pojo.checker.Position;

/**
 * Created by Oleh on 14.12.2016.
 */
public class ValidationUtil {

    private ValidationUtil() {

    }

    public static boolean validPosition(Position p) {
        return p.getLetter() != null && p.getNumber() != null;
    }

}
