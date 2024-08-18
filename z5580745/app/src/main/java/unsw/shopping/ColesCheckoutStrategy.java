package unsw.shopping;

import java.util.List;

public class ColesCheckoutStrategy extends CheckoutStrategy {
    private String cardName = "flybuys";

    @Override
    public void checkout(List<Item> items, String paymentMethod, int paymentAmount, boolean receipt) {
        System.out.println("Welcome! Please scan your first item. If you have a " + cardName + " card, please scan it at any time.");

    }

    @Override
    public int scanItems(List<Item> items) {
        // Supermarkets have restrictions on the number of items allowed
        
        if (items.size() > 20) {
            System.out.println("Too many items.");
        }
           

        if (items.size() == 0) {
            System.out.println("You do not have any items to purchase.");
            return 0;
        }
        int amountPurchased = 0;
        for (Item item : items) {
            amountPurchased += item.getPrice();
        }
        return amountPurchased;
    }

    @Override
    public void printReceipt(List<Item> items, boolean receipt) {
        System.out.println("Today at Coles you purchased the following:");
                
                for (Item item : items) {
                    System.out.println("- " + item.getName() + " : $" + item.getPrice());
                }
    }
    
}
