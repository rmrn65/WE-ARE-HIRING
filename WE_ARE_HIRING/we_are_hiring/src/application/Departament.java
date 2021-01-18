package application;
import java.util.*;
public abstract class Departament {
	ArrayList<Employee> allEmployees;
	ArrayList<Job> availableJobs;
	public abstract double getTotalSalaryBudget();
	public Departament() {
		allEmployees = null;
		availableJobs = null;
	}
	public ArrayList<Job> getJobs(){
		return availableJobs;
	}
	public ArrayList<Employee> getEmployees(){
		return allEmployees;
	}
	public void add (Employee employee) {
		if(allEmployees == null) {
			allEmployees = new ArrayList<Employee>();
		}
		allEmployees.add(employee);
	}
	public void remove(Employee employee) {
		if(allEmployees.contains(employee) == false) {
			System.out.println("The employee \"" + employee + "\" is not in the Departament");
			return;
		}
		allEmployees.remove(employee);
	}
	public void add (Job job) {
		if(availableJobs == null) {
			availableJobs = new ArrayList<Job>();
		}
		availableJobs.add(job);
	}
}
