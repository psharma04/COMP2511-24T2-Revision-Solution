package unsw.shipping;

public class DiscountDecorator extends Decorator {

    private double discount;

    public DiscountDecorator(Product product, int discount) {
        super(product);
        this.discount = discount;
    }

    @Override
    public double getPrice(){
        double original = super.getPrice();
        double finalDiscount = (100-discount)/100;
        return original * finalDiscount;
    }

    

}
