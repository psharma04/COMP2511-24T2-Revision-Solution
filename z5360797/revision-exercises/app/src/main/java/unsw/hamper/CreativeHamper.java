package unsw.hamper;

public class CreativeHamper extends ArrayListItemHamper<Item> {
    /**
     * invariant: if hamper contains 5 or more items, it must contain at least 2 toy cars (at least
     * 1 must be premium) and at least 2 fruits. Otherwise, adding an item should do nothing
     * creative hamper has a price surcharge of $10
     */
    private static final int FRUIT_COUNT = 2;
    private static final int CAR_PREM_COUNT = 1;
    private static final int TOY_CAR_COUNT = 2;
    private static final int SURCHARGE = 10;

    @Override
    public int getPrice() {
        return super.getPrice() + SURCHARGE;
    }

    @Override
    public void add(Item e, int n) {
        if (size() < 5) {
            super.add(e, n);
            return;
        }

        int c = e.getClass() == ToyCar.class ? 1 : 0;
        if (countType(ToyCar.class) + c < TOY_CAR_COUNT)
            return;
        c = e.getClass() == Fruit.class ? 1 : 0;
        if (countType(Fruit.class) + c < FRUIT_COUNT)
            return;
        c = e.getClass() == PremiumToyCar.class ? 1 : 0;
        if (countType(PremiumToyCar.class) + c < CAR_PREM_COUNT)
            return;
        super.add(e, n);
    }
}
