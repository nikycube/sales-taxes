package org.lastminute.salestaxes.renderer;

import org.lastminute.salestaxes.model.cart.ShopCart;

public interface Renderer {

    /**
     * Render the invoice for a ShopCart
     *
     * @param shopCart - given shopCart
     */
    void renderInvoice(ShopCart shopCart);
}
