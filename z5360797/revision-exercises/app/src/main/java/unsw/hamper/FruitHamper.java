package unsw.hamper;

public class FruitHamper extends ArrayListItemHamper<Fruit> {
    /**
     * invariant: FruitHamper must have at least 2 apples and 2 avocados when have >= 6 fruits.
     * Otherwise, adding an item should do nothing fruit hamper has price surcharge of 25%, rounded
     * up to nearest integer
     */

    private static final int APPLE_COUNT = 2;
    private static final int AVO_COUNT = 1;
    private static final double SURCHARGE = 1.25;

    @Override
    public int getPrice() {
        System.out.println(super.getPrice());
        return (int) Math.ceil(super.getPrice() * SURCHARGE);
    }

    @Override
    public void add(Fruit e, int n) {
        if (size() < 5) {
            super.add(e, n);
            return;
        }
        int c = e.getClass() == Apple.class ? 1 : 0;
        if (countType(Apple.class) + c < 2)
            return;
        c = e.getClass() == Avocado.class ? 1 : 0;
        if (countType(Avocado.class) + c < 2)
            return;
        super.add(e, n);
    }
}
