package application;
import java.util.*;
public class Manager extends Employee{
	List<Request> employRequests;
	public Manager() {
		return;
	}//fac din besos consumer sau schimb initializarea din employee
	public void add(Request request) {
		if(employRequests == null)
			employRequests = new ArrayList<Request>();
		employRequests.add(request);
	}
	public void porcess(Job job) {
		//sortez dupa scor
		//verifica daca meets requirements(job)
		Collections.sort(employRequests);
		Application app = Application.getInstance();
		for(int i = 0 ; i < job.noPositions && i < employRequests.size() ; i++) {
			//aici adaug un angajat
			User usr = (User)employRequests.get(i).getValue1();
			Employee newEmployee = usr.convert();
			newEmployee.actualCompanyName = job.companyName;
			newEmployee.job = job;
			Departament jobsDep = null;
			for(Departament dep: app.getCompany(job.companyName).departaments)
			{
				if(dep.availableJobs.contains(job)) {
					jobsDep = dep;
					break;
				}
			}
			app.getCompany(this.actualCompanyName).add(newEmployee,jobsDep);
			app.getCompany(actualCompanyName).removeObserver(usr);
			
		}
		if(employRequests.size() < job.noPositions)
			job.noPositions -= employRequests.size();
		else {
			job.noPositions = 0;
			job.isAvailable = false;
		}
		
		//manager.actualcompany.notifyAllObservers
		employRequests.clear();
		app.getCompany(this.actualCompanyName).notifyAllObservers();
		
	}
}
