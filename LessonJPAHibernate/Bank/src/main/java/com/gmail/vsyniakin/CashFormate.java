package com.gmail.vsyniakin;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CashFormate {

    public static double formatter(double cash) {
        return new BigDecimal(cash).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
