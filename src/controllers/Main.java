package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import models.Movie;
import models.User;
import utils.Serializer;
import utils.XMLSerializer;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import asg.cliche.ShellDependent;

public class Main implements ShellDependent
{
	private static final String ADMIN = "admin";
	public OdysseyAPI Odyssey;
	private Shell theShell;
	
	
	public Main() throws Exception
	{
		File datastore = new File("datastore.xml");
		Serializer serializer = new XMLSerializer(datastore);
		
		Odyssey = new OdysseyAPI(serializer);
	
		if(datastore.isFile())
		{
			Odyssey.load();
		}else {
			Odyssey.prime();
		}
		
	}
	
	public void cliSetShell(Shell theShell)
	{
		this.theShell = theShell;
	}
	  
	@Command(description = "Log in")
	  public void logIn(
			  @Param(name = "user name") String username, 
			  @Param(name = "password") String password)
	      throws IOException {

	    if (Odyssey.login(username, password) && Odyssey.currentuser.isPresent()) {
	      User user = Odyssey.currentuser.get();
	      System.out.println("You are logged in as " + user.username);
	      if (user.role!=null && user.role.equals(ADMIN)) {
	        AdminMenu adminMenu = new AdminMenu(Odyssey, user.username);
	        ShellFactory.createSubshell(user.username, theShell, "Admin", adminMenu).commandLoop();
	      } else {
	        DefaultMenu defaultMenu = new DefaultMenu(Odyssey, user);
	        ShellFactory.createSubshell(user.username, theShell, "Default", defaultMenu).commandLoop();
	      }
	    } else
	      System.out.println("Unknown username/password.");
	  } 
	
	@Command(description = "Add a new User")
    public void addUser(
    	@Param(name = "First Name") String firstName,
        @Param(name= "Last Name") String lastName,
        @Param(name = "Age") int age,
        @Param(name= "Gender") String gender,
        @Param(name= "Occupation") String occupation,
        @Param(name= "zipCode") String zipCode,
        @Param(name= "Username") String userName,
        @Param(name= "Password") String password,
        @Param(name= "role") String role){
        Odyssey.addUser(firstName, lastName, age, gender, occupation, zipCode, userName, password, role);}
	
	@Command(description="Get all users details")
	public void getUsers()
	{
		Collection<User> users = Odyssey.getUsers();
	    System.out.println(users);
	}
	
	public static void main(String [] args) throws Exception
	{
		Main main = new Main();
		
		Shell shell = ShellFactory.createConsoleShell("od", "Welcome to Odyssey - ?help for instructions", main);
		shell.commandLoop();
		
		main.Odyssey.store();
		
		Movie result = OdysseyAPI.getMovie(Long.valueOf(3));
        
        System.out.println(result);
	}
}
