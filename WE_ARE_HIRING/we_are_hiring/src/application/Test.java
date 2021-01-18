package application;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;
import java.io.*;
import org.json.JSONException;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.ArrayList;

import javax.swing.*;
public class Test {
	public Information create_info(JSONObject current_employee) {
		Information info = new Information();

		info.setName((String) current_employee.get("name"));
		info.setEmail((String) current_employee.get("email"));
		info.setPhoneNumber((String) current_employee.get("phone"));
		info.setBirthday((String) current_employee.get("date_of_birth"));
		info.setSex((String) current_employee.get("genre"));
		JSONArray languages = (JSONArray) current_employee.get("languages");
		JSONArray levels = (JSONArray) current_employee.get("languages_level");
		for(int j = 0 ; j < languages.size(); j ++)
			info.addLanguage((String)languages.get(j), (String)levels.get(j));
		return info;
	}
	
	public Education add_education(JSONObject current_education) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate start = LocalDate.parse((String) current_education.get("start_date"), formatter);
		LocalDate end;
		if((String) current_education.get("end_date") == null)
			end = null;
		else
			end = LocalDate.parse((String) current_education.get("end_date"), formatter);
		Number grade = (Number) current_education.get("grade");
		
		Education edu = new Education(start, end, (String)current_education.get("name"),
				(String)current_education.get("level"),Double.parseDouble(grade.toString()));
		return edu;
	}
	
	public Experience add_experience(JSONObject current_experience) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate start = LocalDate.parse((String) current_experience.get("start_date"), formatter);
		LocalDate end;
		if((String) current_experience.get("end_date") == null)
			end = null;
		else
			end = LocalDate.parse((String) current_experience.get("end_date"), formatter);
		String exp_company_name = (String)current_experience.get("company");
		Experience exp = new Experience(start,end,exp_company_name,(String)current_experience.get("department"),(String) current_experience.get("position"));
		//Job newJob = new Job();
		//sa adaug "pozitia" in departament sub forma de job
		//se adauga employee-ul in departament
		return exp;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		Test test = new Test();
		Application app = Application.getInstance();
		Company Google = new Company("Google");
		Company Amazon = new Company("Amazon");
		app.add(Google);
		app.add(Amazon);
		
		//Departaments
		Departament googleIT = new IT();
		Departament googleMng = new Management();
		Departament googleMark = new Marketing();
		Departament googleFin = new Finance();
		
		Google.add(googleIT);
		Google.add(googleMng);
		Google.add(googleMark);
		Google.add(googleFin);
		
		//Jobs
		Job it_job = new Job("SDE","Google",10000,new Constraint(2002,2006), new Constraint(2,6),new Constraint(8.0, null));
		googleIT.add(it_job);
		it_job = new Job("Intern SDE", "Google",5000,new Constraint(null,null), new Constraint(0,2), new Constraint(9.0,null));
		googleIT.add(it_job);
		
		
		
		Departament amazonIT = new IT();
		Departament amazonMng = new Management();
		Departament amazonMark = new Marketing();
		Departament amazonFin = new Finance();
		
		Amazon.add(amazonIT);
		Amazon.add(amazonMng);
		Amazon.add(amazonMark);
		Amazon.add(amazonFin);
		//Jobs
		it_job = new Job("SDE","Amazon",12000,new Constraint(2014,2020), new Constraint(1,null), new Constraint(9.0, null));
		amazonIT.add(it_job);
		it_job = new Job("Intern SDE","Amazon",6000, new Constraint(null,null), new Constraint(0,2), new Constraint(9.35,null));
		amazonIT.add(it_job);
		
		// reading from json
		Object obj = new JSONParser().parse(new FileReader("/home/robert/facult/an2_sem1/POO/tema/tema1/arhiva/consumers.json"));
		JSONObject jo = (JSONObject) obj;
		JSONArray employeejo = (JSONArray) jo.get("employees");
		ArrayList<Employee> myEmployees = new ArrayList<Employee>();
		Iterator<Map> iteremp = employeejo.iterator();
		while(iteremp.hasNext()) {
			JSONObject current_employee = (JSONObject) iteremp.next();
			Employee newEmployee = new Employee();

			//create information
			newEmployee.consumerResume = new Resume();
			newEmployee.consumerResume.personalData = test.create_info(current_employee);
			
			//set salary
			Long long_salary = (long)current_employee.get("salary");
			String string_salary = long_salary + "";
			newEmployee.salary = Double.parseDouble(string_salary);
			
			//add education
			JSONArray educationjo = (JSONArray) current_employee.get("education");
			Iterator iteredu = educationjo.iterator();
			
			while(iteredu.hasNext()) {
				JSONObject current_education = (JSONObject) iteredu.next();
				newEmployee.add(test.add_education(current_education));
			}
			
			//add experience
			if(current_employee.containsKey("experience") == true) {
				JSONArray experiencejo = (JSONArray) current_employee.get("experience");
				Iterator iterexp = experiencejo.iterator();
				
				while(iterexp.hasNext()) {
					JSONObject current_experience = (JSONObject) iterexp.next();
					newEmployee.add(test.add_experience(current_experience));
				}
			}
			newEmployee.actualCompanyName = newEmployee.consumerResume.experience.first().companyName;	
			DepartamentFactory dep_fact = new DepartamentFactory();

			dep_fact.getDepartament(newEmployee.consumerResume.experience.first().departament, newEmployee.actualCompanyName).add(newEmployee);
			myEmployees.add(newEmployee);
		}
		JSONArray recruiterjo = (JSONArray) jo.get("recruiters");
		Iterator<Map> iterrec = recruiterjo.iterator();
		ArrayList<Recruiter> myRecruiters = new ArrayList<Recruiter>();
		while(iterrec.hasNext()) {
			JSONObject current_recruiter = (JSONObject) iterrec.next();
			Recruiter newRecruiter = new Recruiter();
			
			newRecruiter.consumerResume = new Resume();
			newRecruiter.consumerResume.personalData = test.create_info(current_recruiter);
			System.out.println(newRecruiter.consumerResume.personalData.getName());
			
			Long long_salary = (long)current_recruiter.get("salary");
			String string_salary = long_salary + "";
			newRecruiter.salary = Double.parseDouble(string_salary);
			
			JSONArray educationjo = (JSONArray) current_recruiter.get("education");
			Iterator iteredu = educationjo.iterator();
			
			while(iteredu.hasNext()) {
				JSONObject current_education = (JSONObject) iteredu.next();
				newRecruiter.add(test.add_education(current_education));
			}
			if(current_recruiter.containsKey("experience") == true) {
				JSONArray experiencejo = (JSONArray) current_recruiter.get("experience");
				Iterator iterexp = experiencejo.iterator();
				
				while(iterexp.hasNext()) {
					JSONObject current_experience = (JSONObject) iterexp.next();
					newRecruiter.add(test.add_experience(current_experience));
				}

			}
			newRecruiter.actualCompanyName = newRecruiter.consumerResume.experience.first().companyName;	
			newRecruiter.consumerResume.experience.first().departament = "IT";
			app.getCompany(newRecruiter.actualCompanyName).add(newRecruiter);
			DepartamentFactory dep_fact = new DepartamentFactory();
			
			dep_fact.getDepartament(newRecruiter.consumerResume.experience.first().departament, newRecruiter.actualCompanyName).add(newRecruiter);
			myRecruiters.add(newRecruiter);
		}
		JSONArray managerjo = (JSONArray) jo.get("managers");
		Iterator<Map> iterman = managerjo.iterator();
		ArrayList<Manager> myManagers = new ArrayList<Manager>();
		while(iterman.hasNext()) {
			JSONObject current_manager = (JSONObject) iterman.next();
			Manager newManager = new Manager();
			
			newManager.consumerResume = new Resume();
			newManager.consumerResume.personalData = test.create_info(current_manager);
			System.out.println(newManager.consumerResume.personalData.getName());
			
			Long long_salary = (long)current_manager.get("salary");
			String string_salary = long_salary + "";
			newManager.salary = Double.parseDouble(string_salary);
			
			JSONArray educationjo = (JSONArray) current_manager.get("education");
			Iterator iteredu = educationjo.iterator();
			
			while(iteredu.hasNext()) {
				JSONObject current_education = (JSONObject) iteredu.next();
				newManager.add(test.add_education(current_education));
			}
			if(current_manager.containsKey("experience") == true) {
				JSONArray experiencejo = (JSONArray) current_manager.get("experience");
				Iterator iterexp = experiencejo.iterator();
				
				while(iterexp.hasNext()) {
					JSONObject current_experience = (JSONObject) iterexp.next();
					newManager.add(test.add_experience(current_experience));
				}
			}
			newManager.actualCompanyName = newManager.consumerResume.experience.first().companyName;	
			
			app.getCompany(newManager.actualCompanyName).manager = newManager;
			newManager.consumerResume.experience.first().departament = "Management";
			DepartamentFactory dep_fact = new DepartamentFactory();
			
			dep_fact.getDepartament(newManager.consumerResume.experience.first().departament, newManager.actualCompanyName).add(newManager);
			myManagers.add(newManager);
		}
		//Pentru users
		JSONArray userjo = (JSONArray) jo.get("users");
		Iterator<Map> iterusr = userjo.iterator();
		
		while(iterusr.hasNext()) {
			JSONObject current_user = (JSONObject) iterusr.next();
			User newUser = new User();

			newUser.consumerResume = new Resume();
			newUser.consumerResume.personalData = test.create_info(current_user);
			System.out.println("USER: " + newUser.consumerResume.personalData.getEmail());

			JSONArray educationjo = (JSONArray) current_user.get("education");
			Iterator iteredu = educationjo.iterator();
			
			while(iteredu.hasNext()) {
				JSONObject current_education = (JSONObject) iteredu.next();
				newUser.add(test.add_education(current_education));
			}
			if(current_user.containsKey("experience") == true) {
				JSONArray experiencejo = (JSONArray) current_user.get("experience");
				Iterator iterexp = experiencejo.iterator();
				
				while(iterexp.hasNext()) {
					JSONObject current_experience = (JSONObject) iterexp.next();
					newUser.add(test.add_experience(current_experience));
				}
			}
			newUser.companiesInterested = (ArrayList<String>) current_user.get("interested_companies");
			app.add(newUser);

		}

		//tree of friendship
		System.out.println(app.users);
		app.users.get(0).add(app.users.get(1));
		app.users.get(0).add(myEmployees.get(2));
		app.users.get(1).add(myRecruiters.get(0));
		app.users.get(1).add(myEmployees.get(6));
		app.users.get(2).add(myEmployees.get(2));
		app.users.get(2).add(app.users.get(3));
		app.users.get(3).add(myEmployees.get(9));
		myEmployees.get(1).add(myRecruiters.get(2));
		myEmployees.get(1).add(myEmployees.get(9));
		myEmployees.get(2).add(myEmployees.get(5));
		myEmployees.get(2).add(myRecruiters.get(1));
		myEmployees.get(5).add(myRecruiters.get(3));
		
		for(User usr : app.users) {
			for(String company: usr.companiesInterested) {
				for(Job job: app.getCompany(company).departaments.get(0).availableJobs) {
					job.apply(usr);
				}
			}
		}
		for(Manager manager: myManagers) {
			for(Job job: app.getCompany(manager.actualCompanyName).departaments.get(0).availableJobs) {
				manager.porcess(job);
			}
		}
		new SwingInterface();
	}
}
