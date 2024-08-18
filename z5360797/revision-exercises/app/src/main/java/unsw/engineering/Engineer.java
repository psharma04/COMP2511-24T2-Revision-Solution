package unsw.engineering;

public class Engineer extends Employee {

    private double bonus;

    public Engineer(String title, String firstName, String lastName, double bonus) {
        super(title, firstName, lastName);
        this.bonus = bonus;
    }

    public double calculateSalary() {
        double base = super.calculateSalary();
        return base + bonus;
    }
}
