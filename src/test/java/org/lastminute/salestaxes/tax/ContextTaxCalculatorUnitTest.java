package org.lastminute.salestaxes.tax;

import org.junit.Before;
import org.junit.Test;
import org.lastminute.salestaxes.TestData;
import org.lastminute.salestaxes.model.cart.Item;
import org.lastminute.salestaxes.model.config.Config;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ContextTaxCalculatorUnitTest {

    private BasicTaxCalculator basicTaxCalculator;

    private ImportTaxCalculator importTaxCalculator;
    private Config config;

    @Before
    public void setup() {
        basicTaxCalculator = new BasicTaxCalculator();
        importTaxCalculator = new ImportTaxCalculator();
        config = TestData.DEFAULT_CONFIG;
    }

    @Test
    public void test_calculateTax_Basic() {
        //given
        ContextTaxCalculator contextTaxCalculator = new ContextTaxCalculator(basicTaxCalculator);
        Item bookItem = TestData.DEFAULT_BOOK_ITEM_1_SCENARIO;
        //when

        BigDecimal result = contextTaxCalculator.calculateTax(bookItem, config);

        //then
        assertThat(result).isEqualTo(new BigDecimal("1.25"));
    }

    @Test
    public void test_calculateTax_Import() {
        //given
        ContextTaxCalculator contextTaxCalculator = new ContextTaxCalculator(importTaxCalculator);
        Item item = TestData.DEFAULT_IMPORTED_CHOCOLATE_ITEM_2_SCENARIO;
        //when

        BigDecimal result = contextTaxCalculator.calculateTax(item, config);

        //then
        assertThat(result).isEqualTo(new BigDecimal("0.50"));
    }
}
