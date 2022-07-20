package org.lastminute.salestaxes.invoice;

import org.lastminute.salestaxes.model.cart.ShopCart;

public interface InvoiceService {

    /**
     * prepare invoice with all necessary data such as including taxes calculation for shop cart
     *
     * @param shopCart given a shop Cart
     */
    void prepareInvoice(ShopCart shopCart);
}
