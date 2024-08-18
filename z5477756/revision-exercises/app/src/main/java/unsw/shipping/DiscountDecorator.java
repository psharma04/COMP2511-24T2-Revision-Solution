package unsw.shipping;

public class DiscountDecorator extends Decorator {
    private Product product;
    double discount;

    public DiscountDecorator(Product product, int discount) {
        super(product);

        this.product = product;
        this.discount = (double) (discount) / 100.0;
    }

    @Override
    public double getPrice() {
        return (1 - discount) * product.getPrice();
    }

    @Override
    public int getWeight() {
        return product.getWeight();
    }

    @Override
    public double getShippingCost() {
        return product.getShippingCost();
    }

}
