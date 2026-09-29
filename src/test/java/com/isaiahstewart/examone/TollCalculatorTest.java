package com.isaiahstewart.examone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class TollCalculatorTest {

    @ParameterizedTest(name = "weight={0}, ev={1}, carpool={2} -> {4}")
    @CsvSource({
            "1, false, false, 0.0",
            "3500, false, false, 0.0",
            "3501, false, true, 0.05",
            "1200, false, false, 0.0",
            "1201, false, false, 0.0",
            "2147483647, true, false, 0.05",
            "1201, true, false, .1",
            "1201, false, true, .1",
            "1201, true, true, .15",
            "3501, true, true, .25"
    })
    void testTollCalculator(int weight, boolean isEV, boolean isCarpool, double expected) {
        assertEquals(expected, new TollCalculator().calculateDiscount(weight, isEV, isCarpool));
    }
    @Test
    void testNegativeTollCalculatorTC01() {

        assertThrowsExactly(IllegalArgumentException.class, () -> new TollCalculator().calculateDiscount(-2147483648,true,true));
    }
    // TC05 (Exception Case)
    @Test
    void testNegativeTollCalculatorTC05() {

        assertThrowsExactly(IllegalArgumentException.class, () -> new TollCalculator().calculateDiscount(-1,false,false));
    }
    // TC06 (Exception Case)
    @Test
    void testNegativeTollCalculatorTC06() {

        assertThrowsExactly(IllegalArgumentException.class, () -> new TollCalculator().calculateDiscount(0,false,false));
    }
}