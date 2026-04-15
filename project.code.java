class Product {
    private String productName;
    private int quantity;
    private int threshold;

    public Product(String productName, int quantity, int threshold) {
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
        System.out.println("Product: " + productName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Threshold: " + threshold);

        if (isLowStock()) {
            System.out.println("Status: Low Stock");
        } else {
            System.out.println("Status: Stock OK");
        }
        System.out.println("---------------------------");
    }
}

class InventoryManager {
    private Product[] products;

    public InventoryManager(Product[] products) {
        this.products = products;
    }

    public void checkLowStock() {
        System.out.println("Checking Inventory...\n");

        for (Product product : products) {
            if (product.isLowStock()) {
                System.out.println("ALERT: " + product.getProductName() + " is low on stock!");
            } else {
                System.out.println(product.getProductName() + " - Stock OK");
            }
        }
        System.out.println();
    }

    public void displayInventory() {
        System.out.println("Inventory Details:\n");

        for (Product product : products) {
            product.displayProduct();
        }
    }
}

public class Main {
    public static void main(String[] args) {

        Product p1 = new Product("Laptop", 10, 5);
        Product p2 = new Product("Mouse", 2, 5);
        Product p3 = new Product("Keyboard", 7, 3);

        Product[] productList = { p1, p2, p3 };

        InventoryManager manager = new InventoryManager(productList);

        manager.checkLowStock();
        manager.displayInventory();
    }
}
