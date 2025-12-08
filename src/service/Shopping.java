package service;

import Exceptions.NotAvailableException;
import Exceptions.ProcessingException;
import model.*;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.*;

public class Shopping implements CommandLine {
    public ProductManager productManager;
    public Cart cart;
    private OrderProcessor orderProcessor;
    private Scanner scanner;
    private PcComponents pcComponents;
    private List<Product> products;

    public Shopping(ProductManager productManager, Cart cart, OrderProcessor orderProcessor,
                    Scanner scanner, PcComponents pcComponents) {
        this.productManager = productManager;
        this.cart = cart;
        this.orderProcessor = orderProcessor;
        this.scanner = scanner;
        this.pcComponents = new PcComponents();
    }

    public void start() throws NotAvailableException, ProcessingException {
        int choice;
        System.out.println("Welcome in our Store");
        do {
            System.out.println("Choose an option: ");
            System.out.println("1 - View available products");
            System.out.println("2 - Add product to cart");
            System.out.println("3 - Remove product from cart");
            System.out.println("4 - View cart");
            System.out.println("5 - Set up Pc");
            System.out.println("6 - Set up Smartphone");
            System.out.println("7 - Checkout");
            System.out.println("8 - Exit");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> viewProducts();
                case 2 -> addProductToCart();
                case 3 -> removeProductFromCart();
                case 4 -> viewCart();
                case 5 -> setUpPc();
                case 6 -> setUpPhone();
                case 7 -> checkout();
                case 8 -> System.out.println("Good Bye!");
            }
        } while (choice != 8);
    }

    @Override
    public void viewProducts() {
        productManager.displayProducts();
    }

    public void addProductToCart() {

        System.out.println("Please enter the product ID (UUID) that you want to add to cart: ");
        UUID productId = UUID.fromString(scanner.nextLine());

        Optional<Product> optionalProduct = productManager.findById(productId);

        if (optionalProduct.isPresent()) {
            Product foundProduct = optionalProduct.get();

            System.out.println("How many products you want to add to cart: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            if (foundProduct.getQuantityAvailable() < quantity) {
                System.out.println("There is not enough products to add to cart");
                return;
            }
            for (int i = 0; i < quantity; i++) {
                products.add(foundProduct);
            }
            productManager.removeProduct(foundProduct.getId(), quantity);
            System.out.println("Product added to cart");
        } else {
            System.out.println("Product ID not found");
        }
    }

    @Override
    public void removeProductFromCart() {
        System.out.println("Please enter the product ID (UUID) that you want to remove from cart: ");
        UUID productIdToRemove = UUID.fromString(scanner.nextLine());
        Optional<Product> optionalProductToRemove = productManager.findById(productIdToRemove);

        if (optionalProductToRemove.isPresent()) {
            Product productToRemove = optionalProductToRemove.get();

            System.out.println("How many you want to remove from cart: ");
            int quantityToRemove = scanner.nextInt();
            scanner.nextLine();

            cart.removeProductFromCart(productToRemove, quantityToRemove);
        } else {
            System.out.println("Invalid product ID");
        }
    }

    public void displayAvailableProducts() {
        productManager.displayProducts();
    }

    public void displayComponents(List<PcComponents.Component> components) {
        for (int i = 0; i < components.size(); i++) {
            PcComponents.Component component = components.get(i);
            System.out.println((i + 1) + " - " + component.getProductName() + " - " + component.getPrice() + " zł");
        }
    }

    public void setUpPc() throws NotAvailableException {
        int command;
        do {
            System.out.println("Set up your new Pc. Starting price is 2000.00 zł + components");
            Computer computer = new Computer(UUID.randomUUID(), "Pc", new BigDecimal(0.00), 10,
                    null, null, null, null);
            BigDecimal totalCost = BigDecimal.valueOf(2000.00);

            System.out.println("Pick Processor: ");
            displayComponents(pcComponents.getProcessors());
            int processorChoice = scanner.nextInt();
            PcComponents.Component chosenProcessor = pcComponents.getProcessors().get(processorChoice - 1);
            System.out.println("You picked: " + chosenProcessor.getProductName());
            computer.setProcessor(chosenProcessor.getProductName());
            totalCost = totalCost.add(chosenProcessor.getPrice());

            System.out.println("Pick Ram: ");
            displayComponents(pcComponents.getRam());
            int ramChoice = scanner.nextInt();
            PcComponents.Component chosenRam = pcComponents.getRam().get(ramChoice - 1);
            computer.setRam(chosenRam.getProductName());
            totalCost = totalCost.add(chosenRam.getPrice());

            System.out.println("Pick Ssd: ");
            displayComponents(pcComponents.getSsd());
            int ssdChoice = scanner.nextInt();
            PcComponents.Component chosenSsd = pcComponents.getSsd().get(ssdChoice - 1);
            computer.setSsd(chosenSsd.getProductName());
            totalCost = totalCost.add(chosenSsd.getPrice());

            System.out.println("Pick charger: ");
            displayComponents(pcComponents.getChargers());
            int chargerChoice = scanner.nextInt();
            PcComponents.Component chosenCharger = pcComponents.getChargers().get(chargerChoice - 1);
            computer.setCharger(chosenCharger.getProductName());
            totalCost = totalCost.add(chosenCharger.getPrice());

            System.out.println("Would you like add your PC to cart? ( YES / NO )");
            String addToCartChoice = scanner.next();
            if (addToCartChoice.equalsIgnoreCase("YES")) {
                productManager.addProduct(computer);
                computer.setPrice(totalCost);
                cart.addProductToCart(computer, 1);
                System.out.println("Total PC price: " + totalCost + " zł");
            } else {
                System.out.println("PC not added to cart");
            }
            System.out.println("Click 0 to go back to menu");
            command = scanner.nextInt();
            scanner.nextLine();
        } while (command != 0);
    }

    @Override
    public void setUpPhone() throws NotAvailableException {
        int command = 0;
        Smartphone smartphone = new Smartphone(UUID.randomUUID(), "Phone", BigDecimal.ZERO,
                1, null, 0);
        BigDecimal totalCost = BigDecimal.valueOf(1000.00);
        System.out.println("Set up your phone. Starting price is " + totalCost);
        System.out.println("Pick color of your phone: ");
        Smartphone.displayAvailableColors();
        int colorChoice = scanner.nextInt();
        scanner.nextLine();
        Smartphone.Color color = Smartphone.Color.values()[colorChoice - 1];
        smartphone.setColor(String.valueOf(color));
        System.out.println("Pick battery: ");
        System.out.println("1 - 1500 mAh - 29.99 zł");
        System.out.println("2 - 2500 mAh - 49.99 zł");
        System.out.println("3 - 3500 mAh - 79.99 zł");
        int batteryChoice = scanner.nextInt();
        switch (batteryChoice) {
            case 1 -> {
                smartphone.setBatteryCapacity(1500);
                totalCost = totalCost.add(BigDecimal.valueOf(29.99));
            }
            case 2 -> {
                smartphone.setBatteryCapacity(2500);
                totalCost = totalCost.add(BigDecimal.valueOf(49.99));
            }
            case 3 -> {
                smartphone.setBatteryCapacity(3500);
                totalCost = totalCost.add(BigDecimal.valueOf(79.99));
            }
            default -> {
                System.out.println("Invalid battery choice");
                return;
            }
        }
        scanner.nextLine();
        System.out.println("Pick accessories to your phone: ");
        while (true) {
            System.out.println("1 - Powerbank - 399.99 zł");
            System.out.println("2 - Case - 29.99 zł");
            System.out.println("3 - Charger - 99.99 zł");
            System.out.println("0 - No accessories");
            int accessoriesChoice = scanner.nextInt();
            scanner.nextLine();
            if (accessoriesChoice == 0) {
                break;
            }
            switch (accessoriesChoice) {
                case 1 -> totalCost = totalCost.add(BigDecimal.valueOf(399.99));
                case 2 -> totalCost = totalCost.add(BigDecimal.valueOf(29.99));
                case 3 -> totalCost = totalCost.add(BigDecimal.valueOf(99.99));
                default -> System.out.println("Invalid accessories choice");
            }
        }
        System.out.println("Would you like add your Smartphone to cart? ( YES / NO )");
        String addToCartChoice = scanner.nextLine();
        if (addToCartChoice.equalsIgnoreCase("YES")) {
            productManager.addProduct(smartphone);
            smartphone.setPrice(totalCost);
            cart.addProductToCart(smartphone, 1);
            System.out.println("Total smartphone price: " + totalCost + " zł");
        } else {
            System.out.println("Smartphone not added to cart");
        }
        System.out.println("Click 0 to go back to menu");
        command = scanner.nextInt();
        scanner.nextLine();
    }

    @Override
    public void viewCart() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            cart.viewCartProducts();
            System.out.println("Click 0 to go back to main menu");
            int input = scanner.nextInt();
            if (input == 0) {
                break;
            }
            System.out.println("Invalid input. Try again");
        }
    }

    @Override
    public void checkout() {
        System.out.println("Customer's name:");
        String customerName = scanner.nextLine();
        System.out.println("Customer's email: :");
        String email = scanner.nextLine();
        Customer customer = new Customer(customerName, email);
        List<Product> cartItems = cart.getProductsFromCart();
        BigDecimal totalAmount = cart.totalCartPrice();
        int orderId = generateOrderId();
        ZoneId orderTime = ZoneId.systemDefault();
        Order order = new Order(orderId, customer, cartItems, totalAmount);
        for (Product product : cartItems) {
            product.decreaseQuantityProduct(1);
        }
        System.out.println("Order has been placed. Thank you!");
        orderProcessor.processOrder(order);
    }

    private int generateOrderId() {
        Random random = new Random();
        return random.nextInt(10000);
    }
}
