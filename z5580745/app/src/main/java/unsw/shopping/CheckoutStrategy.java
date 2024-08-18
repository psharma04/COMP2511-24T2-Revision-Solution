package unsw.shopping;

import java.util.List;

public abstract class CheckoutStrategy {
    public abstract void checkout(List<Item> items, String paymentMethod, int paymentAmount, boolean receipt);
    public abstract  int scanItems(List<Item> items);
    public abstract void printReceipt(List<Item> items,  boolean receipt);
    public void printPayment(String paymentMethod, int paymentAmount, int amountPurchased){
        // similarlly, added method lead to added if condition
        // This can be extracted to payment strategy,
        if (paymentMethod.equals("cash")) {
            System.out.println("Paid $" + paymentAmount + " with $" + (paymentAmount - amountPurchased) + " change.");
        } else {
            paymentAmount = amountPurchased;
            System.out.println("Paid $" + paymentAmount + ".");
        }
    }
}
