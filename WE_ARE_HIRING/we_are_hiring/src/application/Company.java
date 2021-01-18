package application;
import java.time.LocalDate;
import java.util.*;

public class Company implements Subject{
	String name;
	ArrayList<Job> companyJobs;
	Manager manager;
	ArrayList<Recruiter> allRecruiters;
	ArrayList<Departament> departaments;
	public Company() {
		name = "";
		companyJobs = new ArrayList<Job>();
		manager = null;
	}
	public Company(String name) {
		this.name = name;
		companyJobs = new ArrayList<Job>();
	}
	public Company(String name, ArrayList<Job> jobs, Manager manager) {
		this.name = name;
		companyJobs = jobs;
		this.manager = manager;
	}
	public void addJob(Job job) {
		if(companyJobs.contains(job))
			return;
		else
			companyJobs.add(job);
	}
	public void add(Departament departament) {
		if(departaments == null)
			departaments = new ArrayList<Departament>();
		departaments.add(departament);
	}
	public void add(Recruiter recruiter) {
		if(allRecruiters == null)
			allRecruiters = new ArrayList<Recruiter>();
		allRecruiters.add(recruiter);
	}
	public void add(Employee employee, Departament departament) {
		
		//se face convert de la user la employee (in manager class) si dupa se aplica functia add
		employee.actualCompanyName = name;
		Experience newCompanyExperience = new Experience(LocalDate.now(),null,name,departament.toString(),employee.job.jobTitle);
		employee.consumerResume.experience.add(newCompanyExperience);
		Application app = Application.getInstance();
		
		//sterg toate celelalte requesturi ale angajatului
		for(String companies: employee.companiesInterested) {
			List<Request> req= app.getCompany(companies).manager.employRequests;
			for(Request x: req) {
				User u = (User)x.getValue1();
				 if(u.consumerResume.personalData == employee.consumerResume.personalData)
				 	req.remove(x);
			}
		}
		
		departament.add(employee);
		//sterg observer - > in manager
		employee.companiesInterested.clear();
	}
	public void remove(Employee employee) {
		employee.consumerResume.experience.first().endDate = LocalDate.now();
		for(Departament dep: departaments)
			if(dep.allEmployees.contains(employee))
				dep.remove(employee);
		employee.job.isAvailable = true;
		employee.job.noPositions++;
		//app.getCompany(employee.actualCompanyName).companyJobs
		employee = null;
		}
	public void remove(Departament departament) {
		departaments.remove(departament);
	}
	public void remove(Recruiter recruiter) {
		allRecruiters.remove(recruiter);
		remove((Employee)recruiter);	
	}
	public void move(Departament source, Departament dest) {
		for(Employee emp: source.allEmployees) {
			move(emp, dest);
		}
		source = null;
	}
	public void move(Employee employee, Departament newDepartament) {
		newDepartament.add(employee);
		Application app = Application.getInstance();
		for(Departament dep: app.getCompany(name).departaments)
		{
			if(dep.allEmployees.contains(employee) == true)
				dep.remove(employee);
		}
	}
	public boolean contains(Departament departament) {
		return this.departaments.contains(departament);
	}
	public boolean contains(Employee employee) {
		for(Departament dep: this.departaments) {
			if(dep.allEmployees.contains(employee) == true)
				return true;
		}
		return false;
	}
	public boolean contains(Recruiter recruiter) {
		return this.allRecruiters.contains(recruiter);
	}
	public Recruiter getRecruiter(User user) {
		int max_grade = -1;
		Recruiter chosenRecruiter = null;
		double chosenScore = -2;
		for(Recruiter recruiter: this.allRecruiters) {
			int grade = user.getDegreeInFriendship(recruiter);
			if(grade == max_grade) {
				if(chosenScore < recruiter.recruiterScore) {
					chosenRecruiter = recruiter;
					chosenScore = recruiter.recruiterScore;
				}
			}
			if(max_grade < grade) {
				max_grade = grade;
				chosenRecruiter = recruiter;
				chosenScore = recruiter.recruiterScore;
			}
		}
		return chosenRecruiter;
	}
	public ArrayList<Job> getJobs(){
		ArrayList<Job> jobs = new ArrayList<Job>();
		for(Job job:this.companyJobs) {
			if(job.isAvailable == true)
				jobs.add(job);
		}
		return jobs;
	}
	public String getName() {
		return name;
	}
	public String toString() {
		return name;
	}
	
	
	// for Subject
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	public void removeObserver(Observer observer) {
		if(observers.contains(observer) == true)
			observers.remove(observer);
	}
	public void notifyAllObservers() {
		for (Observer observer : observers) {
	          observer.update(name);
	          removeObserver(observer);
	    }
	}
}
