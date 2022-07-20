package org.lastminute.salestaxes.parser;

import org.lastminute.salestaxes.model.cart.ShopCartItem;

import java.util.List;

public interface Parser {

    /**
     * Import shopCart from a given fileName. If the file is not found an exception is thrown.
     * A line is corespondent of {@ref ShopCartItem}
     * If a line is not in a pattern an exception is thrown.
     *
     * @param fileName - the file name of a file
     * @return a list of {@ref ShopCartItem}
     */
    List<ShopCartItem> importCart(String fileName);
}
