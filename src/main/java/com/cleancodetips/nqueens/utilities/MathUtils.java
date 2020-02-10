package com.cleancodetips.nqueens.utilities;

public class MathUtils {

    /**
     * Calculates greatest common factor of two integers
     */
    public static int GCF(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return (GCF(b, a % b));
        }
    }

}
