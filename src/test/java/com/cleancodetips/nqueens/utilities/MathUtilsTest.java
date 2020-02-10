package com.cleancodetips.nqueens.utilities;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MathUtilsTest {

    @Test
    public void givenTwoNumbers_whenGCF_thenCorrectNumberIsReturned() {
        // GIVEN
        int a = 12;
        int b = 9;
        int expected = 3;

        // WHEN
        int result = MathUtils.GCF(a, b);

        // THEN
        assertThat(result).isEqualTo(expected);
    }
}
