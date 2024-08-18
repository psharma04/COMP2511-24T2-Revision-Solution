package unsw.shipping;

public class DiscountDecorator extends Product {

    Product wrappee;
    double discount;

    public DiscountDecorator(Product wrappee, double discount) {
        this.wrappee = wrappee;
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        return wrappee.getPrice() * ((100 - discount) / 100);
    }

    @Override
    public int getWeight() {
        return wrappee.getWeight();
    }

    @Override
    public double getShippingCost() {
        return wrappee.getShippingCost();
    }



}
