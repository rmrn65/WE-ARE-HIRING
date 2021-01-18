package application;
import java.time.Period;
import java.util.*;
public class Job {
	String jobTitle;
	String companyName;
	Boolean isAvailable;
	//Departament departament;
	int noPositions;
	Constraint graduationYear;
	Constraint noOfExperienceYears;
	Constraint GPA;
	List<User> candidates;
	int candidatesNeeded;
	double salary;
	
	public Job() {
		jobTitle = "";
		noPositions = 0;
		isAvailable = false;
		graduationYear = null;
		noOfExperienceYears = null;
		GPA = null;
	}
	public Job(String jobTitle, String companyName, double salary,Constraint graduation, Constraint expYears, Constraint gpa) {
		this.jobTitle = jobTitle;
		this.companyName = companyName;
		this.salary = salary;
		isAvailable = true;
		graduationYear = graduation;
		noOfExperienceYears = expYears;
		GPA = gpa;
		noPositions = 1;
	}
	//set departament based on the job
	public Job(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public void apply(User user) {
		if(meetsRequirements(user) == false) {
			System.out.println("Dear user " + user + " ,you do not meet the requirements to apply for this job " + jobTitle + " at company "+ companyName);
			return;
		}
		Company comp = Application.getInstance().getCompany(companyName) ;
		int max_grade = -1;
		Recruiter chosenRecruiter = null;
		double chosenScore = -2;
		for(Recruiter recruiter: comp.allRecruiters) {
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
		if(chosenRecruiter != null)
			chosenRecruiter.evaluate(this, user);
		else
			System.out.println("There are no recruiters in this company!");
	}
	public boolean meetsRequirements(User user) {
		if(graduationYear.inferiorLimit == null)
			graduationYear.inferiorLimit = 0;
		if(graduationYear.superiorLimit == null)
			graduationYear.superiorLimit = 3000;
		if(noOfExperienceYears.inferiorLimit == null)
			noOfExperienceYears.inferiorLimit = 0;
		if(noOfExperienceYears.superiorLimit == null)
			noOfExperienceYears.superiorLimit = 100;
		if(GPA.inferiorLimit == null)
			GPA.inferiorLimit = 0.0;
		if(GPA.superiorLimit == null)
			GPA.superiorLimit = 10.0;
		
		if((Integer)graduationYear.inferiorLimit > user.getGraduationYear() || (Integer)graduationYear.superiorLimit < user.getGraduationYear())
			return false;
		int totalYearsOfExp = 0;
		for(Experience exp: user.consumerResume.experience) {
			Period period = Period.between(exp.startDate, exp.endDate);
			totalYearsOfExp += period.getYears();
		}
		if((Integer)noOfExperienceYears.inferiorLimit > totalYearsOfExp || (Integer)noOfExperienceYears.superiorLimit <  totalYearsOfExp)
			return false;
		if((Double)GPA.inferiorLimit > user.meanGPA() || (Double)GPA.superiorLimit < user.meanGPA())
			return false;
		return true;
	}
	//develop toString
	public String toString() {
		return jobTitle;
	}
}
