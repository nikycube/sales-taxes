package org.lastminute.salestaxes.model.cart;

import java.math.BigDecimal;
import java.util.List;

public class ShopCart {

    private final List<ShopCartItem> shopCartItems;

    private BigDecimal totalTax;

    private BigDecimal totalCost;

    public ShopCart(final List<ShopCartItem> shopCartItems) {
        this.shopCartItems = shopCartItems;
        this.totalCost = BigDecimal.ZERO;
        this.totalTax = BigDecimal.ZERO;
    }

    public List<ShopCartItem> getShopCartItems() {
        return shopCartItems;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
