package application;

public class IT extends Departament{
	public double getTotalSalaryBudget() {
		double totalBudget = 0;
		for(Employee emp: this.getEmployees()) {
			totalBudget += emp.salary;
		}
		return totalBudget;// no taxes => Net = Gross
	}
	public String toString() {
		return "IT";
	}
}
