package org.lastminute.salestaxes.model.cart;

import java.math.BigDecimal;

public class Item {

    private final String name;

    private final String category;

    private final BigDecimal price;

    private final boolean imported;


    public Item(String name, String category, BigDecimal price, boolean imported) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.imported = imported;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isImported() {
        return imported;
    }
}
