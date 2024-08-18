package unsw.engineering;

public class SalesPerson extends Employee {

	private float commission;

	public SalesPerson(String title, String firstName, String lastName, int quota) {
		super(title, firstName, lastName, quota);
	}

	public double calculateSalary() {
		double totalSal;
		totalSal = super.calculateSalary() + commission * super.getSalesAchieved();
		         
		return totalSal;
	}

}
