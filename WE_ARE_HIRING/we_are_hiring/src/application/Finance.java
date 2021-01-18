package application;
import java.time.*;
public class Finance extends Departament{
	public double getTotalSalaryBudget() {
		double totalBudget = 0;
		int diff = 0;
		for(Employee emp: this.getEmployees()) {
			if(emp.consumerResume.experience.first().endDate == null) {
				Period period = Period.between(emp.consumerResume.experience.first().startDate, LocalDate.now());
				diff = Math.abs(period.getYears());
			}else {
				Period period = Period.between(emp.consumerResume.experience.first().startDate, 
												emp.consumerResume.experience.first().endDate);
				diff = Math.abs(period.getYears()); 
			}
			if(diff < 1) 
				totalBudget += emp.salary + emp.salary * 0.1;
			else 
				totalBudget += emp.salary + emp.salary * 0.16;
		}
		return totalBudget;

		}
	public String toString() {
		return "Finance";
	}
}
