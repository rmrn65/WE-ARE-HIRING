package application;

interface Observer {
	public Subject subject = null;
	public void update(String notification); 
}
/*
 *Application app = Application.getInsteance()
 *for(String comp: companiesInsterested){
 *	list<Requests> r = app.getCompany(comp).manager.allRequests
 *	for(Request x: r)
 *		if(r.value1.resume.personalData == employee.perosnalData)
 *			r.remove(x); 
 *} 
 * 
 * */
 