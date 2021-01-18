	package application;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class Information {
	private String name;
	private String email;
	private String phone_nr;
	private LocalDate birthday;
	private String sex;
	private ArrayList<Language> languages;
	class Language {
		String language;
		String level;
		String[] levels = new String[]{"BEGINNER","ADVANCED","EXPERIENCED"};
		public Language(){
			language = "";
			level = "";
		}
		public Language(String language, String level) {
			this.language = language;
			this.level = level;
		}
		public String toString() {
			return language + " level: "+ level;
		}
	}
	public Information() {
		name="";
		email="";
		phone_nr="";
		birthday = LocalDate.now();
		sex = "";
		languages = new ArrayList<Language>();
	}
	public Information(String name, String email, String phone, String date_of_birth,ArrayList<Language> languages) {
		setName(name);
		setEmail(email);
		setPhoneNumber(phone);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		birthday = LocalDate.parse(date_of_birth, formatter);
		this.languages = languages;
	}
	//gettere
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	public String getPhoneNumber() {
		return phone_nr;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public String getSex() {
		return sex;
	}
	public ArrayList<Language> getLanguages(){
		return languages;
	}
	//settere
	public void setName(String name) {
		this.name = name;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhoneNumber(String phone_nr) {
		this.phone_nr = phone_nr;
	}
	public void setBirthday(int year, int month, int day) {
		
		birthday = LocalDate.of(year, month, day);
	}
	public void setBirthday(String birthday) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		this.birthday = LocalDate.parse(birthday, formatter);
	}
	public void addLanguage(String languageName, String lvl ) {
		Language lan = new Language();
		lan.language = languageName;
		lan.level = lvl;
		languages.add(lan);
	}
}
