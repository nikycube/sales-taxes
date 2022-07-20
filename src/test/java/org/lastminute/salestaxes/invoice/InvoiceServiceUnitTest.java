package org.lastminute.salestaxes.invoice;

import org.junit.Before;
import org.junit.Test;
import org.lastminute.salestaxes.TestData;
import org.lastminute.salestaxes.model.cart.Item;
import org.lastminute.salestaxes.model.cart.ShopCart;
import org.lastminute.salestaxes.model.cart.ShopCartItem;
import org.lastminute.salestaxes.model.config.Config;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InvoiceServiceUnitTest {

    private InvoiceService invoiceService;
    private Config config;

    @Before
    public void setup() {
        config = TestData.DEFAULT_CONFIG;
        invoiceService = new InvoiceServiceImpl(config);
    }

    @Test
    public void test_prepareInvoice_ShouldCalculateFirstScenario() {
        //given
        Item bookItem = TestData.DEFAULT_BOOK_ITEM_1_SCENARIO;
        Item musicItem = TestData.DEFAULT_MUSIC_ITEM_1_SCENARIO;
        Item chocolateItem = TestData.DEFAULT_CHOCOLATE_ITEM_1_SCENARIO;
        ShopCartItem shopCartBookItem = new ShopCartItem(bookItem, 1);
        ShopCartItem shopCartMusicItem = new ShopCartItem(musicItem, 1);
        ShopCartItem shopCartFoodItem = new ShopCartItem(chocolateItem, 1);
        ShopCart shopCart = new ShopCart(List.of(shopCartBookItem, shopCartMusicItem, shopCartFoodItem));

        //when
        invoiceService.prepareInvoice(shopCart);

        //then
        assertThat(shopCart.getTotalCost()).isEqualTo(new BigDecimal("29.83"));
        assertThat(shopCart.getTotalTax()).isEqualTo(new BigDecimal("1.50"));

        assertThat(shopCartMusicItem.getCost()).isEqualTo(new BigDecimal("16.49"));
        assertThat(shopCartMusicItem.getTax()).isEqualTo(new BigDecimal("1.50"));

        assertThat(shopCartBookItem.getTax()).isEqualTo(new BigDecimal("0"));
        assertThat(shopCartBookItem.getCost()).isEqualTo(new BigDecimal("12.49"));

        assertThat(shopCartFoodItem.getTax()).isEqualTo(new BigDecimal("0"));
        assertThat(shopCartFoodItem.getCost()).isEqualTo(new BigDecimal("0.85"));
    }

    @Test
    public void test_prepareInvoice_ShouldCalculateSecondScenario() {
        //given
        Item chocolateItem = TestData.DEFAULT_IMPORTED_CHOCOLATE_ITEM_2_SCENARIO;
        Item perfumeItem = TestData.DEFAULT_IMPORTED_PERFUME_ITEM_2_SCENARIO;
        ShopCartItem shopCartChocolateItem = new ShopCartItem(chocolateItem, 1);
        ShopCartItem shopCartPerfumeItem = new ShopCartItem(perfumeItem, 1);

        ShopCart shopCart = new ShopCart(List.of(shopCartChocolateItem, shopCartPerfumeItem));

        //when
        invoiceService.prepareInvoice(shopCart);

        //then
        assertThat(shopCart.getTotalCost()).isEqualTo(new BigDecimal("65.15"));
        assertThat(shopCart.getTotalTax()).isEqualTo(new BigDecimal("7.65"));

        assertThat(shopCartChocolateItem.getCost()).isEqualTo(new BigDecimal("10.50"));
        assertThat(shopCartChocolateItem.getTax()).isEqualTo(new BigDecimal("0.50"));

        assertThat(shopCartPerfumeItem.getCost()).isEqualTo(new BigDecimal("54.65"));
        assertThat(shopCartPerfumeItem.getTax()).isEqualTo(new BigDecimal("7.15"));
    }

    @Test
    public void test_prepareInvoice_ShouldCalculateThirdScenario() {
        //given
        Item importedPerfumeItem = TestData.DEFAULT_IMPORTED_PERFUME_ITEM_3_SCENARIO;
        Item perfumeItem = TestData.DEFAULT_PERFUME_ITEM_3_SCENARIO;
        Item pillItem = TestData.DEFAULT_PILLS_ITEM_3_SCENARIO;
        Item importedChocolateItem = TestData.DEFAULT_IMPORTED_CHOCOLATE_ITEM_3_SCENARIO;
        ShopCartItem shopCartImportedPerfumeItem = new ShopCartItem(importedPerfumeItem, 1);
        ShopCartItem shopCartPerfumeItem = new ShopCartItem(perfumeItem, 1);
        ShopCartItem shopCartPillItem = new ShopCartItem(pillItem, 1);
        ShopCartItem shopCartImportedChocolateItem = new ShopCartItem(importedChocolateItem, 1);
        ShopCart shopCart = new ShopCart(List.of(shopCartImportedPerfumeItem, shopCartPerfumeItem, shopCartPillItem, shopCartImportedChocolateItem));

        //when
        invoiceService.prepareInvoice(shopCart);

        //then
        assertThat(shopCart.getTotalCost()).isEqualTo(new BigDecimal("74.68"));
        assertThat(shopCart.getTotalTax()).isEqualTo(new BigDecimal("6.70"));

        assertThat(shopCartPerfumeItem.getCost()).isEqualTo(new BigDecimal("20.89"));
        assertThat(shopCartPerfumeItem.getTax()).isEqualTo(new BigDecimal("1.90"));

        assertThat(shopCartImportedPerfumeItem.getCost()).isEqualTo(new BigDecimal("32.19"));
        assertThat(shopCartImportedPerfumeItem.getTax()).isEqualTo(new BigDecimal("4.20"));

        assertThat(shopCartPillItem.getCost()).isEqualTo(new BigDecimal("9.75"));
        assertThat(shopCartPillItem.getTax()).isEqualTo(new BigDecimal("0"));

        assertThat(shopCartImportedChocolateItem.getCost()).isEqualTo(new BigDecimal("11.85"));
        assertThat(shopCartImportedChocolateItem.getTax()).isEqualTo(new BigDecimal("0.60"));
    }
}
