package controllers;

import java.util.Collection;

import asg.cliche.Command;
import asg.cliche.Param;
import models.User;

public class AdminMenu {
	private String username;
	private OdysseyAPI Odyssey;
	
	public AdminMenu(OdysseyAPI Odyssey, String username)
	{
		this.Odyssey = Odyssey;
		this.setName(username);
	}
	
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
	  
	  public String getName() 
		{
		    return username;
		}
		  public void setName(String username) 
		  {
		    this.username = username;
		  }

}
