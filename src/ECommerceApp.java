import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Price: $" + price;
    }
}

class ShoppingCart {
    private ArrayList<Product> cartItems;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    public void addProduct(Product product) {
        cartItems.add(product);
        System.out.println(product.getName() + " has been added to the cart.");
    }

    public void removeProduct(int productId) {
        for (Product product : cartItems) {
            if (product.getId() == productId) {
                cartItems.remove(product);
                System.out.println(product.getName() + " has been removed from the cart.");
                return;
            }
        }
        System.out.println("Product not found in the cart.");
    }

    public void viewCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("Cart Items:");
        for (Product product : cartItems) {
            System.out.println(product);
        }
    }

    public void checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty. Add items before checkout.");
            return;
        }
        double total = 0;
        System.out.println("Checkout Summary:");
        for (Product product : cartItems) {
            System.out.println(product);
            total += product.getPrice();
        }
        System.out.println("Total Amount: $" + total);
        cartItems.clear();
        System.out.println("Thank you for your purchase!");
    }
}

public class ECommerceApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();
        ShoppingCart cart = new ShoppingCart();

        // Sample Products
        products.add(new Product(1, "Laptop", 899.99));
        products.add(new Product(2, "Smartphone", 499.99));
        products.add(new Product(3, "Headphones", 49.99));
        products.add(new Product(4, "Keyboard", 29.99));

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Remove Product from Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Available Products:");
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;
                case 2:
                    System.out.print("Enter Product ID to add to cart: ");
                    int addId = scanner.nextInt();
                    Product productToAdd = products.stream().filter(p -> p.getId() == addId).findFirst().orElse(null);
                    if (productToAdd != null) {
                        cart.addProduct(productToAdd);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 3:
                    cart.viewCart();
                    break;
                case 4:
                    System.out.print("Enter Product ID to remove from cart: ");
                    int removeId = scanner.nextInt();
                    cart.removeProduct(removeId);
                    break;
                case 5:
                    cart.checkout();
                    break;
                case 6:
                    System.out.println("Exiting application. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
