package application;

public class DepartamentFactory {
	public Departament getDepartament(String departamentName) {
		if(departamentName.equalsIgnoreCase("IT"))
			return new IT();
		if(departamentName.equalsIgnoreCase("Management"))
			return new Management();
		if(departamentName.equalsIgnoreCase("Finance"))
			return new Finance();
		if(departamentName.equalsIgnoreCase("Marketing"))
			return new Marketing();
		return null;
	}
	public Departament getDepartament(String departamentName, String companyName) {
		Application app = Application.getInstance();
		for(Departament dep: app.getCompany(companyName).departaments) {
			if(departamentName.equalsIgnoreCase(dep.toString()))
				return dep;
		}
		return null;
	}
}
