package unsw.shipping;

public class FreeShippingDecorator extends Decorator {
    private Product product;
    private double free_shipping_weight_threshold;
    private double free_shipping_price_threshold;

    public FreeShippingDecorator(Product product,
            double free_shipping_price_threshold, double free_shipping_weight_threshold) {
        super(product);
        this.product = product;
        this.free_shipping_weight_threshold = free_shipping_weight_threshold;
        this.free_shipping_price_threshold = free_shipping_price_threshold;
    }

    @Override
    public double getPrice() {
        return product.getPrice();
    }

    @Override
    public int getWeight() {
        return product.getWeight();
    }

    @Override
    public double getShippingCost() {
        double price = getPrice();
        double weight = getWeight();

        if (weight < free_shipping_weight_threshold && price > free_shipping_price_threshold) {
            return 0.0;
        }

        return product.getShippingCost();
    }

}
