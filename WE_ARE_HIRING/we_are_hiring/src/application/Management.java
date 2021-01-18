package application;

public class Management extends Departament {
	public double getTotalSalaryBudget() {
		double totalBudget = 0;
		for(Employee emp: this.getEmployees()) {
				totalBudget += emp.salary + 0.16 * emp.salary;
		}
		return totalBudget;
	}
	public String toString() {
		return "Management";
	}
}
