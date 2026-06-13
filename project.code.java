import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private final String productName;
    private int quantity;
    private int threshold;

    public Product(String productName, int quantity, int threshold) {
        if (quantity < 0 || threshold < 0) {
            throw new IllegalArgumentException("Quantity and Threshold cannot be negative.");
        }

        this.productName = productName;
        this.quantity = quantity;
        this.threshold = threshold;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public boolean isLowStock() {
        return quantity < threshold;
    }

    public void displayProduct() {
        System.out.println("--------------------------------");
        System.out.println("Product Name : " + productName);
        System.out.println("Quantity     : " + quantity);
        System.out.println("Threshold    : " + threshold);
        System.out.println("Status       : " + (isLowStock() ? "LOW STOCK" : "STOCK OK"));
        System.out.println("--------------------------------");
    }
}

class InventoryManager {
    private ArrayList<Product> products;

    public InventoryManager() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void checkLowStock() {
        System.out.println("\n===== LOW STOCK REPORT =====");

        boolean lowStockFound = false;

        for (Product product : products) {
            if (product.isLowStock()) {
                System.out.println("ALERT: " + product.getProductName()
                        + " is low on stock! Current Quantity = "
                        + product.getQuantity());
                lowStockFound = true;
            }
        }

        if (!lowStockFound) {
            System.out.println("All products have sufficient stock.");
        }
    }

    public void displayInventory() {
        System.out.println("\n===== INVENTORY DETAILS =====");

        for (Product product : products) {
            product.displayProduct();
        }
    }

    public int countLowStockProducts() {
        int count = 0;

        for (Product product : products) {
            if (product.isLowStock()) {
                count++;
            }
        }

        return count;
    }

    public int getTotalProducts() {
        return products.size();
    }

    public void displaySummary() {
        System.out.println("\n===== INVENTORY SUMMARY =====");
        System.out.println("Total Products      : " + getTotalProducts());
        System.out.println("Low Stock Products  : " + countLowStockProducts());
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();

        System.out.println("===== INVENTORY MANAGEMENT SYSTEM =====");

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {

            System.out.println("\nEnter details for Product " + i);

            System.out.print("Product Name: ");
            String name = sc.nextLine();

            int quantity;
            do {
                System.out.print("Quantity: ");
                quantity = sc.nextInt();

                if (quantity < 0) {
                    System.out.println("Quantity cannot be negative. Try again.");
                }
            } while (quantity < 0);

            int threshold;
            do {
                System.out.print("Threshold: ");
                threshold = sc.nextInt();

                if (threshold < 0) {
                    System.out.println("Threshold cannot be negative. Try again.");
                }
            } while (threshold < 0);

            sc.nextLine();

            manager.addProduct(new Product(name, quantity, threshold));
        }

        manager.checkLowStock();
        manager.displayInventory();
        manager.displaySummary();

        sc.close();
    }
}
