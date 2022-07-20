package org.lastminute.salestaxes.tax;

import org.lastminute.salestaxes.model.cart.Item;
import org.lastminute.salestaxes.model.config.Config;
import org.lastminute.salestaxes.util.MathUtils;

import java.math.BigDecimal;
/**
 * Concrete strategy. Implements ImportTax calculateTax and isEligible methods.
 */
public class ImportTaxCalculator implements TaxCalculator {

    private static final Integer IMPORT_TAX_RATE = 5;

    public ImportTaxCalculator() {
    }

    /**
     * Calculate import tax using a basic rate for an item
     * @param item - used for getting the information required in implementation
     * @param config - used for getting the configs for a tax calculator
     * @return
     */
    @Override
    public BigDecimal calculateTax(final Item item, final Config config) {
        return MathUtils.roundUpTax(item.getPrice().multiply(BigDecimal.valueOf(IMPORT_TAX_RATE)).divide(new BigDecimal(100)));
    }
    /**
     * An item is eligible for applying import tax if the category of the item is not in exception list and is imported
     * @param item - used for getting the information required in implementation
     * @param config- used for getting the configs for a tax calculator
     * @return
     */
    @Override
    public boolean isEligible(final Item item, final Config config) {
        return item.isImported() && !config.getImportTaxExceptions().contains(item.getCategory());
    }
}
