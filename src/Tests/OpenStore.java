package Tests;

import service.*;
import model.*;
import Exceptions.NotAvailableException;
import Exceptions.ProcessingException;

import java.util.ArrayList;
import java.util.Scanner;

public class OpenStore {
    public static void main(String[] args) {

        ProductManager productManager = new ProductManager();
        Cart cart = new Cart(new ArrayList<>(), productManager);
        OrderProcessor orderProcessor = new OrderProcessor();
        Scanner scanner = new Scanner(System.in);
        PcComponents pcComponents = new PcComponents();

        Shopping shopping = new Shopping(
                productManager,
                cart,
                orderProcessor,
                scanner,
                pcComponents
        );

        try {
            shopping.start();
        } catch (NotAvailableException | ProcessingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
