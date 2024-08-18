package unsw.engineering;

public class SalesPerson extends Employee {

	private float commission;
	private double salesTarget;
	private double salesAchieved;

	public SalesPerson(String title, String firstName, String lastName, int quota) {
		super(title, firstName, lastName);
		this.salesTarget = quota;
	}

	public double calculateSalary() {
		double base = super.calculateSalary();
		return base + commission * getSalesAchieved();
	}

	public double getSalesTarget() {
		return salesTarget;
	}

	public double getSalesAchieved() {
		return salesAchieved;
	}

	public String getSalesSummary() {
		return getFirstName() + getLastName() + "Sales Target: " + getSalesTarget() + "$\n"
				+ "Sales to date: " + getSalesAchieved() + "$";
	}


}
