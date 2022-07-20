package org.lastminute.salestaxes.tax;

import org.lastminute.salestaxes.model.cart.Item;
import org.lastminute.salestaxes.model.config.Config;

import java.math.BigDecimal;
import java.util.List;

public class AllTaxesCalculator {

    private final List<TaxCalculator> taxCalculators;

    public AllTaxesCalculator(List<TaxCalculator> taxCalculators) {
        this.taxCalculators = taxCalculators;
    }

    /**
     * calculate all taxes for an item if they are eligible to be applied
     *
     * @param item   - used for getting the information required in implementation
     * @param config - used for getting the configs for a tax calculator
     * @return {@ref BigDecimal} the sum of all taxes applied
     */
    public BigDecimal calculateAllTaxes(final Item item, final Config config) {
        return taxCalculators.stream()
                .filter(taxCalculator -> taxCalculator.isEligible(item, config))
                .map(taxCalculator -> new ContextTaxCalculator(taxCalculator).calculateTax(item, config))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
