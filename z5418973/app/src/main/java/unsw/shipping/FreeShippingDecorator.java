package unsw.shipping;

public class FreeShippingDecorator extends Product {

    private Product product;
    private int weight;
    private int price;

    public FreeShippingDecorator(Product product, int price, int weight) {
        this.product = product;
        this.price = price;
        this.weight = weight;
    }

    public int getWeight() {
        return product.getWeight();
    }

    public double getPrice() {
        return product.getPrice();
    }

    public double getShippingCost() {
        if (product.getPrice() > price && product.getWeight() < weight) {
            return 0;
        }

        return product.getShippingCost();
    }

}
