package org.lastminute.salestaxes.tax;

import org.lastminute.salestaxes.model.cart.Item;
import org.lastminute.salestaxes.model.config.Config;

import java.math.BigDecimal;

/**
 * Common interface for all strategies.
 */
public interface TaxCalculator {

    /**
     * calculate tax for an item
     * @param item - used for getting the information required in implementation
     * @param config - used for getting the configs for a tax calculator
     * @return {@ref BigDecimal} the amount of tax
     */
    BigDecimal calculateTax(Item item, Config config);

    /**
     * check if an item is eligible for applying a certain tax
     * @param item - used for getting the information required in implementation
     * @param config- used for getting the configs for a tax calculator
     * @return {@ref Boolean} that holds the result
     */
    boolean isEligible(Item item, Config config);
}
