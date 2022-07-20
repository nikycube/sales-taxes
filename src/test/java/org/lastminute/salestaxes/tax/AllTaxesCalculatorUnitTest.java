package org.lastminute.salestaxes.tax;

import org.junit.Before;
import org.junit.Test;
import org.lastminute.salestaxes.TestData;
import org.lastminute.salestaxes.model.cart.Item;
import org.lastminute.salestaxes.model.config.Config;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AllTaxesCalculatorUnitTest {

    private Config config;

    private AllTaxesCalculator allTaxesCalculator;

    @Before
    public void setup() {
        BasicTaxCalculator basicTaxCalculator = new BasicTaxCalculator();
        ImportTaxCalculator importTaxCalculator = new ImportTaxCalculator();
        allTaxesCalculator = new AllTaxesCalculator(List.of(basicTaxCalculator, importTaxCalculator));
        config = TestData.DEFAULT_CONFIG;
    }

    @Test
    public void test_calculateAllTaxes_ShouldCalculateAllTaxes() {
        //given
        Item item = TestData.DEFAULT_IMPORTED_PERFUME_ITEM_2_SCENARIO;

        //when
        BigDecimal result = allTaxesCalculator.calculateAllTaxes(item, config);

        //then
        assertThat(result).isEqualTo(new BigDecimal("7.15"));
    }

    @Test
    public void test_calculateAllTaxes_ShouldCalculateBasicTax() {
        //given
        Item item = TestData.DEFAULT_MUSIC_ITEM_1_SCENARIO;

        //when
        BigDecimal result = allTaxesCalculator.calculateAllTaxes(item, config);

        //then
        assertThat(result).isEqualTo(new BigDecimal("1.50"));
    }

    @Test
    public void test_calculateAllTaxes_ShouldCalculateImportTax() {
        //given
        Item item = TestData.DEFAULT_IMPORTED_CHOCOLATE_ITEM_2_SCENARIO;

        //when
        BigDecimal result = allTaxesCalculator.calculateAllTaxes(item, config);

        //then
        assertThat(result).isEqualTo(new BigDecimal("0.50"));
    }
}
