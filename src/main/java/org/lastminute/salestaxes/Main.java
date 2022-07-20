package org.lastminute.salestaxes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lastminute.salestaxes.invoice.InvoiceService;
import org.lastminute.salestaxes.invoice.InvoiceServiceImpl;
import org.lastminute.salestaxes.model.cart.ShopCart;
import org.lastminute.salestaxes.model.config.Config;
import org.lastminute.salestaxes.parser.TxtFileParser;
import org.lastminute.salestaxes.renderer.PrintLineRenderer;
import org.lastminute.salestaxes.util.FileUtils;

import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String args[]) {

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = FileUtils.getFileAsIOStream("/config.json");
        Config config;
        try {
            config = mapper.readValue(is, Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InvoiceService invoiceService = new InvoiceServiceImpl(config);
        TxtFileParser txtFileParser = new TxtFileParser(config);
        PrintLineRenderer printLineRenderer = new PrintLineRenderer();
        ShopCart shopCart1 = new ShopCart(txtFileParser.importCart("/test1.txt"));
        invoiceService.prepareInvoice(shopCart1);
        printLineRenderer.renderInvoice(shopCart1);
        System.out.println(" ");
        ShopCart shopCart2 = new ShopCart(txtFileParser.importCart("/test2.txt"));
        invoiceService.prepareInvoice(shopCart2);
        printLineRenderer.renderInvoice(shopCart2);
        System.out.println(" ");
        ShopCart shopCart3 = new ShopCart(txtFileParser.importCart("/test3.txt"));
        invoiceService.prepareInvoice(shopCart3);
        printLineRenderer.renderInvoice(shopCart3);
    }


}
