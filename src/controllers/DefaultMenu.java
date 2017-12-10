package controllers;

import java.util.Collection;

import asg.cliche.Command;
import asg.cliche.Param;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import models.Movie;
import models.User;

public class DefaultMenu {
	private String name;
	private User user;
	private OdysseyAPI Odyssey;
	
	public DefaultMenu(OdysseyAPI Odyssey, User user)
	{
		this.Odyssey = Odyssey;
		this.setName(user.firstName);
		this.setUser(user);
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
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
