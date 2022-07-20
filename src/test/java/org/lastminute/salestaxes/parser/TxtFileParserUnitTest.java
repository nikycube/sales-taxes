package org.lastminute.salestaxes.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.lastminute.salestaxes.TestData;
import org.lastminute.salestaxes.model.cart.ShopCartItem;
import org.lastminute.salestaxes.model.config.Config;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class TxtFileParserUnitTest {
    private Config config;

    @Before
    public void setup() {
        config = TestData.DEFAULT_CONFIG;
    }

    @Test
    public void test_importCart_Test1_ShouldReturnListOfShopCartItems() {
        //given
        TxtFileParser txtFileParser = new TxtFileParser(config);

        //when
        List<ShopCartItem> result = txtFileParser.importCart("/test1.txt");

        //then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(3);
        Optional<ShopCartItem> bookItem = result.stream().filter(shopCartItem -> "books".equals(shopCartItem.getItem().getCategory())).findFirst();
        assertThat(bookItem.isPresent()).isEqualTo(true);
        assertThat(bookItem.get().getItem().getPrice()).isEqualTo(new BigDecimal("12.49"));
        assertThat(bookItem.get().getItem().getName()).isEqualTo("book");
        assertThat(bookItem.get().getItem().isImported()).isEqualTo(false);
        assertThat(bookItem.get().getQuantity()).isEqualTo(Integer.valueOf(1));

        Optional<ShopCartItem> cdItem = result.stream().filter(shopCartItem -> "other".equals(shopCartItem.getItem().getCategory())).findFirst();
        assertThat(cdItem.isPresent()).isEqualTo(true);
        assertThat(cdItem.get().getItem().getPrice()).isEqualTo(new BigDecimal("14.99"));
        assertThat(cdItem.get().getItem().getName()).isEqualTo("music CD");
        assertThat(cdItem.get().getItem().isImported()).isEqualTo(false);
        assertThat(cdItem.get().getQuantity()).isEqualTo(Integer.valueOf(1));

        Optional<ShopCartItem> chocolateItem = result.stream().filter(shopCartItem -> "food".equals(shopCartItem.getItem().getCategory())).findFirst();
        assertThat(chocolateItem.isPresent()).isEqualTo(true);
        assertThat(chocolateItem.get().getItem().getPrice()).isEqualTo(new BigDecimal("0.85"));
        assertThat(chocolateItem.get().getItem().getName()).isEqualTo("chocolate bar");
        assertThat(chocolateItem.get().getItem().isImported()).isEqualTo(false);
        assertThat(chocolateItem.get().getQuantity()).isEqualTo(Integer.valueOf(1));
    }

    @Test
    public void test_importCart_Test2_ShouldReturnListOfShopCartItems() {
        //given
        TxtFileParser txtFileParser = new TxtFileParser(config);

        //when
        List<ShopCartItem> result = txtFileParser.importCart("/test2.txt");

        //then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);

        Optional<ShopCartItem> perfumeItem = result.stream().filter(shopCartItem -> "other".equals(shopCartItem.getItem().getCategory())).findFirst();
        assertThat(perfumeItem.isPresent()).isEqualTo(true);
        assertThat(perfumeItem.get().getItem().getPrice()).isEqualTo(new BigDecimal("47.50"));
        assertThat(perfumeItem.get().getItem().getName()).isEqualTo("imported bottle of perfume");
        assertThat(perfumeItem.get().getItem().isImported()).isEqualTo(true);
        assertThat(perfumeItem.get().getQuantity()).isEqualTo(Integer.valueOf(1));

        Optional<ShopCartItem> chocolateItem = result.stream().filter(shopCartItem -> "food".equals(shopCartItem.getItem().getCategory())).findFirst();
        assertThat(chocolateItem.isPresent()).isEqualTo(true);
        assertThat(chocolateItem.get().getItem().getPrice()).isEqualTo(new BigDecimal("10.00"));
        assertThat(chocolateItem.get().getItem().getName()).isEqualTo("imported box of chocolates");
        assertThat(chocolateItem.get().getItem().isImported()).isEqualTo(true);
        assertThat(chocolateItem.get().getQuantity()).isEqualTo(Integer.valueOf(1));
    }

    @Test
    public void test_importCart_Test3_ShouldReturnListOfShopCartItems() {
        //given
        TxtFileParser txtFileParser = new TxtFileParser(config);

        //when
        List<ShopCartItem> result = txtFileParser.importCart("/test3.txt");

        //then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(4);

        Optional<ShopCartItem> importedPerfumeItem = result.stream().filter(shopCartItem -> "other".equals(shopCartItem.getItem().getCategory()) && "imported bottle of perfume".equals(shopCartItem.getItem().getName())).findFirst();
        assertThat(importedPerfumeItem.isPresent()).isEqualTo(true);
        assertThat(importedPerfumeItem.get().getItem().getPrice()).isEqualTo(new BigDecimal("27.99"));
        assertThat(importedPerfumeItem.get().getItem().isImported()).isEqualTo(true);
        assertThat(importedPerfumeItem.get().getQuantity()).isEqualTo(Integer.valueOf(1));

        Optional<ShopCartItem> perfumeItem = result.stream().filter(shopCartItem -> "other".equals(shopCartItem.getItem().getCategory()) && "bottle of perfume".equals(shopCartItem.getItem().getName())).findFirst();
        assertThat(perfumeItem.isPresent()).isEqualTo(true);
        assertThat(perfumeItem.get().getItem().getPrice()).isEqualTo(new BigDecimal("18.99"));
        assertThat(perfumeItem.get().getItem().isImported()).isEqualTo(false);
        assertThat(perfumeItem.get().getQuantity()).isEqualTo(Integer.valueOf(1));

        Optional<ShopCartItem> pillItem = result.stream().filter(shopCartItem -> "medical products".equals(shopCartItem.getItem().getCategory())).findFirst();
        assertThat(pillItem.isPresent()).isEqualTo(true);
        assertThat(pillItem.get().getItem().getPrice()).isEqualTo(new BigDecimal("9.75"));
        assertThat(pillItem.get().getItem().isImported()).isEqualTo(false);
        assertThat(pillItem.get().getQuantity()).isEqualTo(Integer.valueOf(1));

        Optional<ShopCartItem> importedChocolateItem = result.stream().filter(shopCartItem -> "food".equals(shopCartItem.getItem().getCategory()) && "box of imported chocolates".equals(shopCartItem.getItem().getName())).findFirst();
        assertThat(importedChocolateItem.isPresent()).isEqualTo(true);
        assertThat(importedChocolateItem.get().getItem().getPrice()).isEqualTo(new BigDecimal("11.25"));
        assertThat(importedChocolateItem.get().getItem().isImported()).isEqualTo(true);
        assertThat(importedChocolateItem.get().getQuantity()).isEqualTo(Integer.valueOf(1));
    }

    @Test(expected = RuntimeException.class)
    public void test_importCart_Test4_ShouldThrowInvalidFormat() {
        //given
        TxtFileParser txtFileParser = new TxtFileParser(config);

        //when
        try {
            txtFileParser.importCart("/test4-invalid-format.txt");
        } catch (Exception e) {
            //then
            assertThat(e.getMessage()).isEqualTo("Invalid line!");
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_importCart_ShouldThrowFileNotFound() {
        //given
        TxtFileParser txtFileParser = new TxtFileParser(config);

        //when
        try {
            txtFileParser.importCart("/test4-invalid-formata.txt");
        } catch (Exception e) {
            //then
            assertThat(e.getMessage()).isEqualTo("/test4-invalid-formata.txt is not found");
            throw e;
        }
    }

    @Test(expected = RuntimeException.class)
    public void test_importCart_Test5_ShouldThrowNOCategoryFound() {
        //given
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = Config.class.getResourceAsStream("/config.json");
        Config config;
        try {
            config = mapper.readValue(is, Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TxtFileParser txtFileParser = new TxtFileParser(config);

        //when
        try {
            txtFileParser.importCart("/test5-no-category.txt");
        } catch (Exception e) {
            //then
            assertThat(e.getMessage()).isEqualTo("No mapped category found for name: imported phone ");
            throw e;
        }
    }
}
