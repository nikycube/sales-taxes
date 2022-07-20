package org.lastminute.salestaxes.tax;

import org.lastminute.salestaxes.model.cart.Item;
import org.lastminute.salestaxes.model.config.Config;
import org.lastminute.salestaxes.util.MathUtils;

import java.math.BigDecimal;

/**
 * Concrete strategy. Implements BasicTax calculateTax and isEligible methods.
 */
public class BasicTaxCalculator implements TaxCalculator {

    private static final Integer BASIC_TAX_RATE = 10;

    public BasicTaxCalculator() {
    }

    /**
     * Calculate basic tax using a basic rate for an item
     *
     * @param item   - used for getting the information required in implementation
     * @param config - used for getting the configs for a tax calculator
     * @return
     */
    @Override
    public BigDecimal calculateTax(final Item item, final Config config) {
        return MathUtils.roundUpTax(item.getPrice().multiply(BigDecimal.valueOf(BASIC_TAX_RATE)).divide(new BigDecimal(100)));
    }

    /**
     * An item is eligible for applying basic tax if the category of the item is not in exception list
     *
     * @param item    - used for getting the information required in implementation
     * @param config- used for getting the configs for a tax calculator
     * @return
     */
    @Override
    public boolean isEligible(final Item item, final Config config) {
        return !config.getBaseTaxExceptions().contains(item.getCategory());
    }
}
