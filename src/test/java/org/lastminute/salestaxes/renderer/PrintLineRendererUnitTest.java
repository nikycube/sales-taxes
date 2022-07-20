package org.lastminute.salestaxes.renderer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lastminute.salestaxes.TestData;
import org.lastminute.salestaxes.model.cart.Item;
import org.lastminute.salestaxes.model.cart.ShopCart;
import org.lastminute.salestaxes.model.cart.ShopCartItem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PrintLineRendererUnitTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private PrintLineRenderer printLineRenderer;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        printLineRenderer = new PrintLineRenderer();
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void test_renderInvoice_ShouldPrintAllData() throws UnsupportedEncodingException {
        //given
        Item bookItem = TestData.DEFAULT_BOOK_ITEM_1_SCENARIO;
        Item musicItem = TestData.DEFAULT_MUSIC_ITEM_1_SCENARIO;
        Item chocolateItem = TestData.DEFAULT_CHOCOLATE_ITEM_1_SCENARIO;
        ShopCartItem shopCartBookItem = new ShopCartItem(bookItem, 1);
        shopCartBookItem.setCost(new BigDecimal("12.49"));
        ShopCartItem shopCartMusicItem = new ShopCartItem(musicItem, 1);
        shopCartMusicItem.setCost(new BigDecimal("16.49"));
        shopCartMusicItem.setTax(new BigDecimal("1.50"));
        ShopCartItem shopCartFoodItem = new ShopCartItem(chocolateItem, 1);
        shopCartFoodItem.setCost(new BigDecimal("0.85"));
        ShopCart shopCart = new ShopCart(List.of(shopCartBookItem, shopCartMusicItem, shopCartFoodItem));
        shopCart.setTotalCost(new BigDecimal("29.83"));
        shopCart.setTotalTax(new BigDecimal("1.50"));

        //when
        printLineRenderer.renderInvoice(shopCart);
        String result = "1 book : 12.49\r\n" +
                "1 music CD : 16.49\r\n" +
                "1 chocolate bar : 0.85\r\n" +
                "Sales Taxes: 1.50\r\n" +
                "Total: 29.83\r\n";
        String resultWithoutNewLine = result.replaceAll("(\r\n|\n)","");

        //then
        String printLnResult =  new String(outContent.toByteArray(), "UTF-8").replaceAll("(\r\n|\n)","");

        assertThat(printLnResult).isEqualTo(resultWithoutNewLine);
    }
}
