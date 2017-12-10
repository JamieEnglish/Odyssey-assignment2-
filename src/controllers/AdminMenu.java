package controllers;

import java.util.Collection;

import asg.cliche.Command;
import asg.cliche.Param;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import models.Movie;
import models.User;

public class AdminMenu {
	private String username;
	private OdysseyAPI Odyssey;
	
	public AdminMenu(OdysseyAPI Odyssey, String username)
	{
		this.Odyssey = Odyssey;
		this.setName(username);
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
	
	  
	  @Command(description="delete a user")
		public void removeUser(
				@Param(name="id") Long UserID){
			Odyssey.removeUser(UserID);
		}
	  
	  @Command(description="add a movie")
		public void addMovie(
		@Param(name="title") String title,
		@Param(name="releaseDate") String releaseDate,
		@Param(name="URL") String URL){
			Odyssey.addMovie(title, releaseDate, URL);
		}
	  
	  @Command(description="add a rating")
		public void addRating(
				@Param(name="user") Long UserID,
				@Param(name="movie") Long MovieID,
				@Param(name="rating") int rating,
				@Param(name="timestamp") int timestamp){
			Odyssey.addRating(UserID, MovieID, rating, timestamp);
		}
		
		@Command(description="get a user's rating")
		public void getRating(
				@Param(name="id") Long UserID){
			Odyssey.getRating(UserID);
		}
		
		@Command(description="Get all movies")
		public void getMovies()
		{
			Collection<Movie> movies = Odyssey.getMovies();
		    System.out.println(movies);
		}
		
		@Command(description="Search a movie")
		public void searchMovies() {
			
		        StdOut.println("Enter your search:");
		        String prefix = StdIn.readString();
		        System.out.println(Odyssey.searchMovies(prefix));
		}
	  
	  public String getName() 
		{
		    return username;
		}
		  public void setName(String username) 
		  {
		    this.username = username;
		  }

}
