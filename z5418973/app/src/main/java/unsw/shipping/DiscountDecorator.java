package unsw.shipping;

public class DiscountDecorator extends Product {

    private Product product;
    private double discount;

    public DiscountDecorator(Product product, double discount) {
        this.product = product;
        this.discount = (100 - discount)/100;
    }

    @Override
    public double getPrice() {
        return (product.getPrice() * discount);
    }

    public int getWeight() {
        return product.getWeight();
    }

    public double getShippingCost() {
        return product.getShippingCost();
    }
}
