package ca.retrylife.jdma.util;

/**
 * Utils for working with values
 */
public class Values {

    /**
     * Convert an integer to a ulong
     * @param val Integer
     * @return Unsigned Long
     */
    public static long intToULong(int val) {
        if (val >= 0) {
            return val;
        }
        return (~0L >>> 32) & val;
    }

}