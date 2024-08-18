package unsw.shipping;

public class FreeShippingDecorator extends Decorator  {

    private double cost;
    private int weight;

    public FreeShippingDecorator(Product product, double cost, int weight) {
        super(product);
        this.cost = cost;
        this.weight = weight;
    }

    @Override
    public double getShippingCost() {
        if (super.getPrice() > cost && super.getWeight() < weight){
            return 0;
        }
        return super.getShippingCost();
    }

}
