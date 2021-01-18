package application;
import java.util.*;
public class Application {
	private static Application instance = null;
	
	List<Company> companies;
	List<User> users;
	private Application() {
		this.users = null;
		this.companies = null;
	}
	public ArrayList<Company> getCompanies(){
		Iterator<Company> iterator = companies.iterator();
		ArrayList<Company> allCompanies = new ArrayList<Company>();
		while(iterator.hasNext()) {
			allCompanies.add((Company) iterator.next());
		}
		return allCompanies;	
	}
	public Company getCompany(String name) {
		for(Company c: companies) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}
	public void add(Company company) {
		if(companies == null)
			companies = new ArrayList<Company>();
		companies.add(company);
	}
	public void add(User user) {
		if(users == null)
			users = new ArrayList<User>();
		users.add(user);
	}
	public boolean remove(Company company) {
		if(companies.contains(company) == false)
			return false;
		else 
			companies.remove(company);
		return true;
	}
	public boolean remove(User user) {
		if(users.contains(user) == false)
			return false;
		else
			users.remove(user);
		return true;		
	}
	public ArrayList<Job> getJobs(List<String> companies){
		ArrayList<Job> searchedJobs= new ArrayList<Job>();
		for(String names: companies) {
			Company c = getCompany(names);
			searchedJobs.addAll(c.companyJobs);
		}
		return searchedJobs;
	}
	public static Application getInstance() {
		if(instance == null)
			instance = new Application();
		return instance;
	}
}
