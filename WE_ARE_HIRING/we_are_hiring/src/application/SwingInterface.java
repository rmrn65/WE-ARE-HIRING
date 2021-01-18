package application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SwingInterface extends JFrame implements ActionListener{
	public SwingInterface(){
		//Labels
		setTitle("AdminPage");
		Application app = Application.getInstance();
		
		JLabel labelCompany = new JLabel();
		labelCompany.setBounds(120,10,100,20);
		labelCompany.setText("Companies");
		labelCompany.setVisible(false);
		
		JLabel labelUsers = new JLabel("Users",SwingConstants.CENTER);
		labelUsers.setBounds(280,10,200,20);
		labelUsers.setText("Users");
		labelUsers.setVisible(false);
		
		//Buttons
		JButton buttonShow = new JButton("Show");
		buttonShow.setBounds(10,10,80,30);
		JButton buttonHide = new JButton("Hide");
		buttonHide.setBounds(10,40,80,30);
		JButton buttonDepartament = new JButton("Departaments");
		buttonDepartament.setBounds(120,350,140,30);
		buttonDepartament.setVisible(false);
		
		//Radio Buttons
		JRadioButton radioCompanies = new JRadioButton("Companies");
		radioCompanies.setBounds(10,100,110,30);
		JRadioButton radioUsers = new JRadioButton("Users");
		radioUsers.setBounds(10,130,110,30);
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioUsers);
		bg.add(radioCompanies);
		
		//Lists
		
		//Companies
		DefaultListModel<String> companyList = new DefaultListModel<String>();
		
		for(int i = 0 ; i < app.companies.size() ; i++) {
			companyList.add(i, app.getCompanies().get(i).name);
		}
		JList<String> StringCompanyList = new JList<String>(companyList);
		StringCompanyList.setVisible(false);
		StringCompanyList.setBounds(120,50,140,300);
		
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)StringCompanyList.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);  

		//Users
		DefaultListModel<String> userList = new DefaultListModel<String>();
		for(int i = 0 ; i < app.users.size(); i++) {
			userList.add(i, app.users.get(i).toString());
		}
		JList<String> StringUserList = new JList<String>(userList);
		StringUserList.setVisible(false);
		StringUserList.setBounds(280,50,200,200);
				
		renderer =  (DefaultListCellRenderer)StringUserList.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);  

		//Button Action
		buttonShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radioCompanies.isSelected()) { 
					StringCompanyList.setVisible(true);
					labelCompany.setVisible(true);
					buttonDepartament.setVisible(true);
				}
				else if(radioUsers.isSelected())
					StringUserList.setVisible(true);
					labelUsers.setVisible(true);
			}
			
		});
		buttonHide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radioCompanies.isSelected()) 
					StringCompanyList.setVisible(false);
				else if(radioUsers.isSelected())
					StringUserList.setVisible(false);
			}
		});
		add(labelCompany);add(labelUsers);
		add(StringCompanyList);add(StringUserList);
		add(buttonShow);add(buttonHide);add(buttonDepartament);
		add(radioCompanies);add(radioUsers);
		setSize(550,550);
		setLayout(null);
		setVisible(true);		
	}
    public void actionPerformed(ActionEvent e) {
    	
    }
}
