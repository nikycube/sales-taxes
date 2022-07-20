package org.lastminute.salestaxes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lastminute.salestaxes.model.cart.Item;
import org.lastminute.salestaxes.model.config.Config;
import org.lastminute.salestaxes.util.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

public class TestData {

    public final static Config DEFAULT_CONFIG = generateConfig();

    //first scenario
    public final static Item DEFAULT_BOOK_ITEM_1_SCENARIO = new Item("book", "books", new BigDecimal("12.49"), false);

    public final static Item DEFAULT_MUSIC_ITEM_1_SCENARIO = new Item("music CD", "other", new BigDecimal("14.99"), false);

    public final static Item DEFAULT_CHOCOLATE_ITEM_1_SCENARIO = new Item("chocolate bar", "food", new BigDecimal("0.85"), false);

    //second scenario
    public final static Item DEFAULT_IMPORTED_CHOCOLATE_ITEM_2_SCENARIO = new Item("imported box of chocolates", "food", new BigDecimal("10.00"), true);

    public final static Item DEFAULT_IMPORTED_PERFUME_ITEM_2_SCENARIO = new Item("imported bottle of perfume", "other", new BigDecimal("47.50"), true);

    //third scenario
    public final static Item DEFAULT_IMPORTED_PERFUME_ITEM_3_SCENARIO = new Item("imported bottle of perfume", "other", new BigDecimal("27.99"), true);

    public final static Item DEFAULT_PERFUME_ITEM_3_SCENARIO = new Item("bottle of perfume", "other", new BigDecimal("18.99"), false);

    public final static Item DEFAULT_PILLS_ITEM_3_SCENARIO = new Item("packet of headache pills", "medical products", new BigDecimal("9.75"), false);

    public final static Item DEFAULT_IMPORTED_CHOCOLATE_ITEM_3_SCENARIO = new Item("box of imported chocolates", "food", new BigDecimal("11.25"), true);

    public static Config generateConfig() {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = FileUtils.getFileAsIOStream("/config.json");
        Config config;
        try {
            config = mapper.readValue(is, Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return config;
    }

}
