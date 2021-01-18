package application;

//import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.*;
//daca vrei sa faci in package-uri organizat, poti da import la clase import package.class
public class User extends Consumer implements Observer{
	List<String> companiesInterested;
	public User() {
		companiesInterested = null;
	}
	//constructor cu toti parametrii
	public User(String username, List<Consumer> linkedConsumers, Resume consumerResume) {
		super(linkedConsumers, consumerResume);
	}
	public void addInterestedCompany(String companyName) {
		if(companiesInterested == null) {
			companiesInterested = new ArrayList<String>();
		}
		companiesInterested.add(companyName);
		Application app = Application.getInstance();
		app.getCompany(companyName).addObserver(this);
	}
	//??
	public Employee convert() {
		Employee e = new Employee();
		e.consumerResume = this.consumerResume;
		e.linkedConsumers = this.linkedConsumers;
		e.companiesInterested = companiesInterested;
		companiesInterested.clear();
		System.out.println(this.consumerResume.personalData.getName() + " GOT HIRED");
		return e;
	}
	public Double getTotalScore() {
		int years_of_exp = 0;
		for(Experience exp: consumerResume.experience) {
			years_of_exp += ChronoUnit.MONTHS.between(exp.startDate, exp.endDate) / 3;
		}
		return meanGPA() + 1.5 * years_of_exp;//calcul numar ani de experienta
	}
	
	public String toString() {
		return this.consumerResume.personalData.getEmail();
	}
	//for observer
	public void update(String notification) {
		companiesInterested.remove(notification);
		System.out.println("Dear user "+ this.toString() +" the company "+ notification +" has no more available positions.");
		System.out.println("We will delete this company from your list of companies you applied");
	}
}
