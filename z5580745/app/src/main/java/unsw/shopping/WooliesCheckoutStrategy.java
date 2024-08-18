package unsw.shopping;

import java.util.List;

public class WooliesCheckoutStrategy extends CheckoutStrategy {

    private String cardName = "Everyday Rewards";

    @Override
    public void checkout(List<Item> items, String paymentMethod, int paymentAmount, boolean receipt) {
        System.out.println("Welcome! Please scan your first item. If you have a " + cardName + " card, please scan it at any time.");
    }

    @Override
    public int scanItems(List<Item> items) {
       
        if (items.size() >= 55) {
            System.out.println("Sorry, that's more than we can handle in a single order!");
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
        System.out.print("Your purchase: ");
    
                for (int i = 0; i < items.size() - 1; i++) {
                    System.out.print(items.get(i).getName() + ", ($" + items.get(i).getPrice() + "), "); // law of demeter
                }
                System.out.println(items.get(items.size() - 1).getName() + " ($" + items.get(items.size() - 1).getPrice() + ").");
    }
    
}
