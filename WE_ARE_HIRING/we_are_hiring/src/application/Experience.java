package application;
import java.time.*;
public class Experience implements Comparable<Experience> {
	LocalDate startDate;
	LocalDate endDate;
	String companyName;
	String position;
	String departament;
	int valid;
	public Experience() {
		startDate = null;
		endDate = null;
		companyName = "";
		departament = null;
		valid = 0;
	}
	public Experience(LocalDate startDate, LocalDate endDate, String companyName, String departament,String position){
		try {
			setDates(startDate, endDate);
			this.companyName = companyName;
			this.departament = departament ;
			this.position = position;
		}
		catch (InvalidDatesException e) {
			System.out.println(e);
		}
	}
	public void setDates(LocalDate startDate, LocalDate endDate) throws InvalidDatesException{
		
		if(endDate != null) {
			if(startDate == null) {
				valid = 0;
				throw new InvalidDatesException("Starting date can't be null");
			}
			else if(startDate.compareTo(endDate) >= 0) {
				valid = 0;
				throw new InvalidDatesException("The dates are invalid! Finishing date must be greater than Starting date");
			}
			else{
				this.startDate = startDate;
				this.endDate = endDate;
				valid = 1;
			}
		}
		else {
			this.startDate = startDate;
			valid = 1;
		}
	}
	public int compareTo(Experience e) {
		if(endDate == null)
			return -1;
		if(endDate.compareTo(e.endDate) == 0)
		{
			return companyName.compareTo(e.companyName);
		}
		return -endDate.compareTo(e.endDate);
	}
	public int getYearsOfExp() {
		
		return 0;
	}
}
