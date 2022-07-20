package org.lastminute.salestaxes.model.cart;

import java.math.BigDecimal;

public class ShopCartItem {

    private final Item item;

    private final Integer quantity;

    private BigDecimal cost;

    private BigDecimal tax;

    public ShopCartItem(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
        this.cost = BigDecimal.ZERO;
        this.tax = BigDecimal.ZERO;
    }

    public Item getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
}
