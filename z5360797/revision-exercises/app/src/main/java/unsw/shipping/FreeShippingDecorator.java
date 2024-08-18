package unsw.shipping;

public class FreeShippingDecorator extends Product {

    private Product wrappee;
    private double price;
    private int weight;

    public FreeShippingDecorator(Product wrappee, double price, int weight) {
        this.wrappee = wrappee;
        this.price = price;
        this.weight = weight;
    }

    @Override
    public double getPrice() {
        return wrappee.getPrice();
    }

    @Override
    public int getWeight() {
        return wrappee.getWeight();
    }

    @Override
    public double getShippingCost() {
        if (getPrice() > price && getWeight() < weight)
            return 0;
        else
            return super.getShippingCost();
    }
}
