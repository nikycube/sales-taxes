package org.lastminute.salestaxes.tax;

import org.junit.Before;
import org.junit.Test;
import org.lastminute.salestaxes.TestData;
import org.lastminute.salestaxes.model.cart.Item;
import org.lastminute.salestaxes.model.config.Config;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ImportTaxCalculatorUnitTest {
    private ImportTaxCalculator importTaxCalculator;
    private Config config;

    @Before
    public void setup() {
        importTaxCalculator = new ImportTaxCalculator();
        config = TestData.DEFAULT_CONFIG;
    }

    @Test
    public void test_calculateTax_ShouldReturnCalculatedTax() {
        //given
        Item item = TestData.DEFAULT_IMPORTED_CHOCOLATE_ITEM_2_SCENARIO;

        //when
        BigDecimal result = importTaxCalculator.calculateTax(item, config);

        //then
        assertThat(result).isEqualTo(new BigDecimal("0.50"));
    }

    @Test
    public void test_isEligible_ShouldReturnFalse() {
        //given
        Item bookItem = TestData.DEFAULT_BOOK_ITEM_1_SCENARIO;

        //when
        boolean result = importTaxCalculator.isEligible(bookItem, config);

        //then
        assertThat(result).isFalse();
    }

    @Test
    public void test_isEligible_ShouldReturnTrue() {
        //given
        Item item = TestData.DEFAULT_IMPORTED_PERFUME_ITEM_2_SCENARIO;

        //when
        boolean result = importTaxCalculator.isEligible(item, config);

        //then
        assertThat(result).isTrue();
    }
}
