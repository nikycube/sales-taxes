package org.lastminute.salestaxes.renderer;

import org.lastminute.salestaxes.model.cart.ShopCart;

public class PrintLineRenderer implements Renderer {

    /**
     * Render invoice in PrintLine
     *
     * @param shopCart - given shopCart
     */
    @Override
    public void renderInvoice(ShopCart shopCart) {
        StringBuilder sb = new StringBuilder();
        shopCart.getShopCartItems().forEach(shopCartItem -> {
            sb.append(shopCartItem.getQuantity()).append(" ");
            sb.append(shopCartItem.getItem().getName()).append(" : ");
            sb.append(shopCartItem.getCost());
            sb.append("\n");
        });
        sb.append("Sales Taxes: ").append(shopCart.getTotalTax()).append("\n");
        sb.append("Total: ").append(shopCart.getTotalCost());
        System.out.println(sb);
    }
}
