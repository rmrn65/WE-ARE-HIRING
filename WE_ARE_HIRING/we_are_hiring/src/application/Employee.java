package application;
import java.util.*;
public class Employee extends Consumer{
	String username;
	String actualCompanyName ="";//can get pointerNull
	Job job;
	double salary = 0;
	
	List<String> companiesInterested;
	public Employee() {
		username = "";
		actualCompanyName = ""; //consumerResume.experience.first().companyName;
		salary = 0;
		job = null;
	}
	public Employee(String actualCompanyName, double salary) {
		this.actualCompanyName = actualCompanyName;
		this.salary = salary;
	}
	public String toString() {
		return consumerResume.personalData.getName();
	}
	//aceste doua campuri se vor initializa??
}
