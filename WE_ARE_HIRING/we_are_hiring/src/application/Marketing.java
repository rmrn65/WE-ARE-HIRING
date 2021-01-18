package application;

public class Marketing extends Departament{
	public double getTotalSalaryBudget() {
		double totalBudget = 0;
		for(Employee emp: this.getEmployees()) {
			if(emp.salary > 5000) 
				totalBudget += emp.salary + emp.salary *0.1;
			else if(emp.salary < 3000) 
				totalBudget += emp.salary;
			else
				totalBudget += emp.salary + 0.16 * emp.salary;
		}
		return totalBudget;
	}
	public String toString() {
		return "Marketing";
	}
}

