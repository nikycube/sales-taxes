package org.lastminute.salestaxes.parser;

import org.lastminute.salestaxes.model.cart.Item;
import org.lastminute.salestaxes.model.cart.ShopCartItem;
import org.lastminute.salestaxes.model.config.CategoryMapping;
import org.lastminute.salestaxes.model.config.Config;
import org.lastminute.salestaxes.util.FileUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TxtFileParser implements Parser {

    private static final String IMPORTED_KEYWORD = "imported";

    private static final Set<String> IGNORED_KEYWORDS = new HashSet<>(Arrays.asList(IMPORTED_KEYWORD));

    private static final String ITEM_LINE_REGEX = "(\\d+) ([\\w\\s]* )at (\\d+.\\d{2})";

    private static final Pattern PATTERN = Pattern.compile(ITEM_LINE_REGEX);

    private final Config config;

    public TxtFileParser(Config config) {
        this.config = config;
    }

    /**
     * Import shopCartItems from txt file. If the file is not found an exception is thrown.
     * A line should match a regex pattern.
     * If a line is not in a pattern an exception is thrown.
     *
     * @param fileName - the file name of a file
     * @return a list of {@ref ShopCartItem}
     */
    @Override
    public List<ShopCartItem> importCart(String fileName) {
        return createShopCartItems(FileUtils.getFileAsIOStream(fileName));
    }


    /**
     * create shopCartItems for each line of the line
     *
     * @param is - the inputStream of file
     * @return a list of {@ref ShopCartItem}
     */
    private List<ShopCartItem> createShopCartItems(final InputStream is) {
        List<ShopCartItem> shopCartItems = new ArrayList<>();
        try (InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr);) {
            String line;
            while ((line = br.readLine()) != null) {
                shopCartItems.add(createShopCartItem(line));
            }
            is.close();
        } catch (IOException e) {
            throw new RuntimeException("Unable to read the file", e);
        }
        return shopCartItems;
    }

    /**
     * Create ShopCartItem from a line if the line matches the regex pattern.
     * It can throw exception for invalid line or not founding the category of the product
     *
     * @param line - given line
     * @return a {@ref ShopCartItem}
     */
    private ShopCartItem createShopCartItem(final String line) {
        boolean imported = false;
        String category = "";
        Matcher matcher = PATTERN.matcher(line);
        matcher.find();
        String inputQuantity;
        String inputName;
        String inputPrice;
        try {
            inputQuantity = matcher.group(1);
            inputName = matcher.group(2);
            inputPrice = matcher.group(3);
        } catch (Exception e) {
            throw new RuntimeException("Invalid line!");
        }

        String[] words = inputName.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            if (!imported && IMPORTED_KEYWORD.equalsIgnoreCase(word)) {
                imported = true;
                continue;
            }
            if (!IGNORED_KEYWORDS.contains(word)) {
                Optional<String> categoryOptional = findCategory(word);
                if (categoryOptional.isPresent()) {
                    category = categoryOptional.get();
                    break;
                }
            }
        }
        if ("".equalsIgnoreCase(category)) {
            throw new RuntimeException("No mapped category found for name: " + inputName);
        }
        return new ShopCartItem(new Item(inputName.trim(), category, new BigDecimal(inputPrice), imported), Integer.valueOf(inputQuantity));
    }

    /**
     * For a keyword find the category if is found
     *
     * @param keyword given keyword
     * @return {@ref Optional<String>} that holds the category if it was found
     */
    private Optional<String> findCategory(final String keyword) {
        return config.getCategoryMappings().stream()
                .filter(categoryMapping -> categoryMapping.getKeywords().contains(keyword))
                .map(CategoryMapping::getCategory)
                .findFirst();
    }
}
