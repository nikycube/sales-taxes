package org.lastminute.salestaxes.tax;

import org.lastminute.salestaxes.TestData;
import org.lastminute.salestaxes.model.cart.Item;
import org.lastminute.salestaxes.model.config.Config;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicTaxCalculatorUnitTest {
    private BasicTaxCalculator basicTaxCalculator;
    private Config config;

    @Before
    public void setup() {
        basicTaxCalculator = new BasicTaxCalculator();
        config = TestData.DEFAULT_CONFIG;
    }

    @Test
    public void test_calculateTax_ShouldReturnCalculatedTax() {
        //given
        Item bookItem = TestData.DEFAULT_BOOK_ITEM_1_SCENARIO;

        //when
        BigDecimal result = basicTaxCalculator.calculateTax(bookItem, config);

        //then
        assertThat(result).isEqualTo(new BigDecimal("1.25"));
    }

    @Test
    public void test_isEligible_ShouldReturnFalse() {
        //given
        Item bookItem = TestData.DEFAULT_BOOK_ITEM_1_SCENARIO;

        //when
        boolean result = basicTaxCalculator.isEligible(bookItem, config);

        //then
        assertThat(result).isFalse();
    }

    @Test
    public void test_isEligible_ShouldReturnTrue() {
        //given
        Item musicItem = TestData.DEFAULT_MUSIC_ITEM_1_SCENARIO;

        //when
        boolean result = basicTaxCalculator.isEligible(musicItem, config);

        //then
        assertThat(result).isTrue();
    }
}
