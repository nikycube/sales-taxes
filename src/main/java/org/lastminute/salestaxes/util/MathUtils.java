package org.lastminute.salestaxes.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class MathUtils {
    //utility class
    private MathUtils() {
    }
    private static final BigDecimal INCREMENT = new BigDecimal("0.05");

    /**
     * Round up a value to 0.05
     * @param value - the actual value
     * @return {@ref BigDecimal} the value rounded up
     */
    public static BigDecimal roundUpTax(BigDecimal value) {
        return round(value, INCREMENT, RoundingMode.UP);
    }

    /**
     * Round a value based on increment and roundingMode
     * @param value - the actual value
     * @param increment - how much should be incremented
     * @param roundingMode - the type of rounding
     * @return {@ref BigDecimal} the value rounded up
     */
    private static BigDecimal round(BigDecimal value, BigDecimal increment,
                                   RoundingMode roundingMode) {
        if (increment.signum() == 0) {
            // 0 increment does not make much sense, but prevent division by 0
            return value;
        } else {
            BigDecimal divided = value.divide(increment, 0, roundingMode);
            BigDecimal result = divided.multiply(increment);
            return result;
        }
    }
}
