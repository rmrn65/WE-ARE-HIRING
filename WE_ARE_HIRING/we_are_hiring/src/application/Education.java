package application;
import java.time.*;
public class Education implements Comparable<Education>{
		LocalDate startTime;
		LocalDate finishTime;
		String institutionName;
		String educationLevel;
		String[] educationLevels = new String[]{"high","licenta","master","doctorat"};
		double meanGPA;
		int valid;
		
		public Education() {
			startTime = null;
			finishTime = null;
			institutionName = "";
			educationLevel = "";
			meanGPA = 0;
			valid = 0;
		}
		public Education(LocalDate startTime, LocalDate finishTime,
				String institutionName, String educationLevel, double meanGPA){
			try {
				setDates(startTime, finishTime);
				this.institutionName = institutionName;
				this.educationLevel = educationLevel;
				this.meanGPA = meanGPA;
			}
			catch (InvalidDatesException e) {
				System.out.println(e);
			}
		}
		public void setDates(LocalDate startTime, LocalDate finishTime) throws InvalidDatesException{
			if(finishTime != null) {
				if(startTime == null) {
					valid = 0;
					throw new InvalidDatesException("Starting date can't be null");
				}
				else if(startTime.compareTo(finishTime) >= 0) {
					valid = 0;
					throw new InvalidDatesException("The dates are invalid! Finishing date must be greater than Starting date");
				}
				else{
					this.startTime = startTime;
					this.finishTime = finishTime;
					valid = 1;
				}
			}
			else {
				this.startTime = startTime;
				valid = 1;
			}
		}
		public int compareTo(Education e){
			if(finishTime == null)
				return -1;
			if(finishTime.compareTo(e.finishTime) == 0)
			{
				if(meanGPA == e.meanGPA)
					return 0;
				else if(meanGPA > e.meanGPA)
					return -1;
				else
					return 1;		
			}
			return -finishTime.compareTo(e.finishTime);
		}
		public String toString() {
			return "Start date: "+ startTime+"\nFinised date: "+ finishTime+"\nInstitution name: "
		+institutionName+"\nLevel of education: "+ educationLevel+"\nMean GPA: "+meanGPA;
		}
}
//throw exception
//
class InvalidDatesException extends Exception{

	private static final long serialVersionUID = 1L;

	InvalidDatesException(String msg){
		super(msg);
	}
}
