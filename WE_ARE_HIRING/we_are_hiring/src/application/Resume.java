package application;

import java.util.TreeSet;

public class Resume{
	Information personalData;
	TreeSet<Education> education;
	TreeSet<Experience> experience;
	public Resume(){
		personalData = new Information();
		education = new TreeSet<Education>();
		experience = new TreeSet<Experience>();
	}
	public Resume(ResumeBuilder resumeBuilder)throws ResumeIncompleteException {
		if(resumeBuilder.education == null)
			throw new ResumeIncompleteException("Your education is incomplete. You need to add entities");
		if(resumeBuilder.personalData == null)
			throw new ResumeIncompleteException("Your personal data is null");
		Information x = resumeBuilder.personalData;
		if(x.getBirthday() == null || x.getName() == "" || x.getSex()=="" || x.getEmail() =="" || x.getPhoneNumber() =="")
			throw new ResumeIncompleteException("Your personal data is incomplete");
		
		this.personalData = resumeBuilder.personalData;
		this.education = resumeBuilder.education;
		this.experience = resumeBuilder.experience;
	}
	public static class ResumeBuilder {
		Information personalData;
		TreeSet<Education> education;
		TreeSet<Experience> experience;
		public ResumeBuilder(Information personalData) {

			this.personalData = personalData;
		}
		public ResumeBuilder setEducation(TreeSet<Education> education) {
			this.education = education;
			return this;
		}
		public ResumeBuilder setExperience(TreeSet<Experience> experience) {
			this.experience = experience;
			return this;
		}
		public Resume build() {
			try {
			return new Resume(this);
			}
			catch(ResumeIncompleteException e) {
				System.out.println(e);
			}
				return null;
		}	
	}
	class ResumeIncompleteException extends Exception{

		private static final long serialVersionUID = 1L;

		ResumeIncompleteException(String msg){
			super(msg);
		}
	}
	
}