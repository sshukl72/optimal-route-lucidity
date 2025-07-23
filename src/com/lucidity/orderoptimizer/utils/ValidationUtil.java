package com.lucidity.orderoptimizer.utils;

import static com.lucidity.orderoptimizer.utils.Constants.*;

public class ValidationUtil {

    /*
     * returns true if the value is null, infinite  or NAN condition satisfy  */
    public static void isValidDoubleValue(Double value, String name) {
        if (value == null) {
            throw new NullPointerException(String.format(OBJECT_PROVIDED_IS_NULL, name));
        } else if (Double.isNaN(value)) {
            throw new IllegalArgumentException(String.format(VALUE_IS_NAN, name));
        } else if (Double.isInfinite(value)) {
            throw new IllegalArgumentException(String.format(VALUE_IS_INFINITE, name));
        }
    }

    /*
     * returns true if the value is null, blank  or empty condition satisfy  */
    public static void isValidString(String value, String name) {
        if (value == null) {
            throw new NullPointerException(String.format(OBJECT_PROVIDED_IS_NULL, name));
        } else if (value.isEmpty()) {
            throw new IllegalArgumentException(String.format(VALUE_IS_EMPTY, name));
        } else if (value.isBlank()) {
            throw new IllegalArgumentException(String.format(VALUE_IS_BLANK, name));
        }
    }

    public static <T> void objectNotNull(T object, String name) {

        if (object == null) {
            throw new NullPointerException(String.format(OBJECT_PROVIDED_IS_NULL, name));
        }
    }

}
