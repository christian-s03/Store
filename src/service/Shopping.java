package service;

import Exceptions.NotAvailableException;
import Exceptions.ProcessingException;
import model.*;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.*;

public class Shopping implements CommandLine {
    private ProductManager productManager;
    private Cart cart;
    private OrderProcessor orderProcessor;
    private Scanner scanner;
    private PcComponents pcComponents;
    private List<Product> products;

    public Shopping(ProductManager productManager, Cart cart, OrderProcessor orderProcessor, Scanner scanner, PcComponents pcComponents) {
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

        UUID productId;
        try {
            productId = UUID.fromString(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID format");
            return;
        }

        Optional<Product> optionalProduct = productManager.findById(productId);

        if (optionalProduct.isEmpty()) {
            System.out.println("Product ID not found");
            return;
        }

        Product foundProduct = optionalProduct.get();

        System.out.println("How many products do you want to add to cart: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        if (foundProduct.getQuantityAvailable() < quantity) {
            System.out.println("There is not enough products to add to cart");
            return;
        }

        products.addAll(Collections.nCopies(quantity, foundProduct));

        productManager.removeProduct(foundProduct.getId(), quantity);

        System.out.println("Product added to cart");
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

            Computer computer = createBaseComputer();
            BigDecimal totalCost = BigDecimal.valueOf(2000.00);

            totalCost = totalCost.add(chooseProcessor(computer));
            totalCost = totalCost.add(chooseRam(computer));
            totalCost = totalCost.add(chooseSsd(computer));
            totalCost = totalCost.add(chooseCharger(computer));

            askToAddPcToCart(computer, totalCost);

            command = waitForReturnToMenu();

        } while (command != 0);
    }

    private Computer createBaseComputer() {
        return new Computer(
                UUID.randomUUID(),
                "Pc",
                BigDecimal.ZERO,
                10,
                null, null, null, null
        );
    }

    private BigDecimal chooseProcessor(Computer computer) {
        System.out.println("Pick Processor: ");
        displayComponents(pcComponents.getProcessors());

        int choice = scanner.nextInt();
        scanner.nextLine();
        PcComponents.Component c = pcComponents.getProcessors().get(choice - 1);

        System.out.println("You picked: " + c.getProductName());
        computer.setProcessor(c.getProductName());

        return c.getPrice();
    }

    private BigDecimal chooseRam(Computer computer) {
        System.out.println("Pick Ram: ");
        displayComponents(pcComponents.getRam());

        int choice = scanner.nextInt();
        scanner.nextLine();
        PcComponents.Component c = pcComponents.getRam().get(choice - 1);

        computer.setRam(c.getProductName());
        return c.getPrice();
    }

    private BigDecimal chooseSsd(Computer computer) {
        System.out.println("Pick Ssd: ");
        displayComponents(pcComponents.getSsd());

        int choice = scanner.nextInt();
        scanner.nextLine();
        PcComponents.Component c = pcComponents.getSsd().get(choice - 1);

        computer.setSsd(c.getProductName());
        return c.getPrice();
    }

    private BigDecimal chooseCharger(Computer computer) {
        System.out.println("Pick charger: ");
        displayComponents(pcComponents.getChargers());

        int choice = scanner.nextInt();
        scanner.nextLine();
        PcComponents.Component c = pcComponents.getChargers().get(choice - 1);

        computer.setCharger(c.getProductName());
        return c.getPrice();
    }

    private void askToAddPcToCart(Computer computer, BigDecimal totalCost) throws NotAvailableException {
        System.out.println("Would you like add your PC to cart? ( YES / NO )");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("YES")) {
            productManager.addProduct(computer);
            computer.setPrice(totalCost);
            cart.addProductToCart(computer, 1);

            System.out.println("Total PC price: " + totalCost + " zł");
        } else {
            System.out.println("PC not added to cart");
        }
    }

    private int waitForReturnToMenu() {
        System.out.println("Click 0 to go back to menu");
        while (true) {
            try {
                int command = Integer.parseInt(scanner.nextLine().trim());
                return command;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        }
    }

    @Override
    public void setUpPhone() throws NotAvailableException {
        Smartphone smartphone = createBaseSmartphone();
        BigDecimal totalCost = BigDecimal.valueOf(1000.00);

        System.out.println("Set up your phone. Starting price is " + totalCost);

        chooseColor(smartphone);
        totalCost = totalCost.add(chooseBattery(smartphone));
        totalCost = totalCost.add(chooseAccessories());

        askToAddToCart(smartphone, totalCost);
        waitForReturnToMenu();
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

    private Smartphone createBaseSmartphone() {
        return new Smartphone(UUID.randomUUID(), "Phone", BigDecimal.ZERO, 1, null, 0);
    }

    private void chooseColor(Smartphone smartphone) {
        System.out.println("Pick color of your phone: ");
        Smartphone.displayAvailableColors();

        int colorChoice = scanner.nextInt();
        scanner.nextLine();

        Smartphone.Color color = Smartphone.Color.values()[colorChoice - 1];
        smartphone.setColor(color.name());
    }

    private BigDecimal chooseBattery(Smartphone smartphone) {
        System.out.println("Pick battery: ");
        System.out.println("1 - 1500 mAh - 29.99 zł");
        System.out.println("2 - 2500 mAh - 49.99 zł");
        System.out.println("3 - 3500 mAh - 79.99 zł");

        int batteryChoice = scanner.nextInt();

        return switch (batteryChoice) {
            case 1 -> {
                smartphone.setBatteryCapacity(1500);
                yield BigDecimal.valueOf(29.99);
            }
            case 2 -> {
                smartphone.setBatteryCapacity(2500);
                yield BigDecimal.valueOf(49.99);
            }
            case 3 -> {
                smartphone.setBatteryCapacity(3500);
                yield BigDecimal.valueOf(79.99);
            }
            default -> {
                System.out.println("Invalid battery choice");
                yield BigDecimal.ZERO;
            }
        };
    }

    private BigDecimal chooseAccessories() {
        BigDecimal cost = BigDecimal.ZERO;

        System.out.println("Pick accessories to your phone: ");

        while (true) {
            System.out.println("1 - Powerbank - 399.99 zł");
            System.out.println("2 - Case - 29.99 zł");
            System.out.println("3 - Charger - 99.99 zł");
            System.out.println("0 - No accessories");

            int accessoriesChoice = scanner.nextInt();
            scanner.nextLine();

            switch (accessoriesChoice) {
                case 0 -> {
                    return cost;
                }
                case 1 -> cost = cost.add(BigDecimal.valueOf(399.99));
                case 2 -> cost = cost.add(BigDecimal.valueOf(29.99));
                case 3 -> cost = cost.add(BigDecimal.valueOf(99.99));
                default -> System.out.println("Invalid accessories choice");
            }
        }
    }

    private void askToAddToCart(Smartphone smartphone, BigDecimal totalCost) throws NotAvailableException {
        System.out.println("Would you like to add your Smartphone to cart? ( YES / NO )");
        scanner.nextLine();
        String addToCartChoice = scanner.nextLine();

        if (addToCartChoice.equalsIgnoreCase("YES")) {
            productManager.addProduct(smartphone);
            smartphone.setPrice(totalCost);
            cart.addProductToCart(smartphone, 1);
            System.out.println("Total smartphone price: " + totalCost + " zł");
        } else {
            System.out.println("Smartphone not added to cart");
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
        Order order = new Order(orderId, customer, cartItems, totalAmount, orderTime);
        for (Product product : cartItems) {
            product.decreaseQuantityProduct(1);
        }
        System.out.println("Order has been placed. Thank you!");
    }

    private int generateOrderId() {
        Random random = new Random();
        return random.nextInt(10000);
    }
}
