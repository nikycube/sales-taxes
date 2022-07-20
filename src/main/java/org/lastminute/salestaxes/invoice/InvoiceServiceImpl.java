package org.lastminute.salestaxes.invoice;

import org.lastminute.salestaxes.model.cart.ShopCart;
import org.lastminute.salestaxes.model.config.Config;
import org.lastminute.salestaxes.tax.BasicTaxCalculator;
import org.lastminute.salestaxes.tax.AllTaxesCalculator;
import org.lastminute.salestaxes.tax.ImportTaxCalculator;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceServiceImpl implements InvoiceService {

    private final Config config;

    public InvoiceServiceImpl(Config config) {
        this.config = config;
    }

    /**
     * Calculate taxes and prices for a shop cart
     *
     * @param shopCart given a shop Cart
     */
    @Override
    public void prepareInvoice(ShopCart shopCart) {
        BasicTaxCalculator taxCalculator = new BasicTaxCalculator();
        ImportTaxCalculator importTaxCalculator = new ImportTaxCalculator();
        AllTaxesCalculator allTaxesCalculator = new AllTaxesCalculator(List.of(taxCalculator, importTaxCalculator));

        shopCart.getShopCartItems().forEach(shopCartItem -> {
            shopCartItem.setTax(allTaxesCalculator.calculateAllTaxes(shopCartItem.getItem(), config));
            shopCartItem.setCost(shopCartItem.getTax().add(shopCartItem.getItem().getPrice()).multiply(BigDecimal.valueOf(shopCartItem.getQuantity())));
            shopCart.setTotalCost(shopCartItem.getCost().add(shopCart.getTotalCost()));
            shopCart.setTotalTax(shopCartItem.getTax().add(shopCart.getTotalTax()));
        });
    }
}
