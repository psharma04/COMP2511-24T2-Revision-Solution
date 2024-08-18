package unsw.shopping;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class CheckoutSystem {
    // use strategy pattern
    private String supermarket;
    private int amountPurchased;
    private CheckoutStrategy strategy;

    private CheckoutSystem(String supermarket) {
        this.supermarket = supermarket;
        if (supermarket.equals("Coles")) {
            strategy = new ColesCheckoutStrategy();
        } else if (supermarket.equals("Woolies")) {
            strategy = new WooliesCheckoutStrategy();
        }
    }

    public static CheckoutSystem instance(String supermarket) {
        return new CheckoutSystem(supermarket);

    }

    public void checkout(List<Item> items, String paymentMethod, int paymentAmount, boolean receipt) {
        // Welcome the user
        strategy.checkout(items, paymentMethod, paymentAmount, receipt);
        amountPurchased = strategy.scanItems(items);
        // Take the user's payment
        strategy.printPayment(paymentMethod, paymentAmount, amountPurchased);

        // Print the receipt
        if (receipt) {
        strategy.printReceipt(items, receipt);
        }
    }

    // public void scanItems(List<Item> items) {
    //     // Supermarkets have restrictions on the number of items allowed
    //     if (supermarket.equals("Coles")) {
    //         if (items.size() > 20) {
    //             System.out.println("Too many items.");
    //         }
    //     } else if (supermarket.equals("Woolies")) {
    //         if (items.size() >= 55) {
    //             System.out.println("Sorry, that's more than we can handle in a single order!");
    //         }
    //     }

    //     if (items.size() == 0) {
    //         System.out.println("You do not have any items to purchase.");
    //         return;
    //     }

    //     for (Item item : items) {
    //         amountPurchased += item.getPrice();
    //     }
    // }

    public static void main(String[] args) {
        List<Item> items = new ArrayList<Item>(Arrays.asList(
            new Item("Apple", 1),
            new Item("Orange", 1),
            new Item("Avocado", 5)
        ));

        CheckoutSystem checkout = new CheckoutSystem("Woolies");
        checkout.checkout(items, "cash", 200, true);
    }

}