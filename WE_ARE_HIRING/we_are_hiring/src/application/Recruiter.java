package application;
import java.util.ArrayList;;
public class Recruiter extends Employee{
	double recruiterScore = 5.0;
	public int evaluate(Job job, User user) {
		double score = recruiterScore * user.getTotalScore();
		Request newRequest = new Request(job,user,this,recruiterScore);
		
		//cum parametrizez un key&value object
		
		//trimit requestul pe lista managerului
		// this.currentCompany.manager.requestList.add(request);
		Application app = Application.getInstance();
		Company recruitersCompany = app.getCompany(this.actualCompanyName);
		if(recruitersCompany.manager.employRequests == null)
			recruitersCompany.manager.employRequests = new ArrayList<Request>();
		recruitersCompany.manager.employRequests.add(newRequest);
		recruiterScore += 0.1;
		return (int)score;
	}
}
