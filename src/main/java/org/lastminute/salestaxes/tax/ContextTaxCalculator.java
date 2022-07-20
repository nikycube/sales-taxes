package org.lastminute.salestaxes.tax;

import org.lastminute.salestaxes.model.cart.Item;
import org.lastminute.salestaxes.model.config.Config;

import java.math.BigDecimal;

/**
 * A context class for Strategies which delegate calculateTax
 */
public class ContextTaxCalculator {

    private final TaxCalculator taxCalculator;

    public ContextTaxCalculator(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    /**
     * Delegate calculation of a tax
     * @param item - used for getting the information required in implementation
     * @param config - used for getting the configs for a tax calculator
     * @return
     */
    public BigDecimal calculateTax(final Item item, final Config config) {
        return taxCalculator.calculateTax(item, config);
    }
}
