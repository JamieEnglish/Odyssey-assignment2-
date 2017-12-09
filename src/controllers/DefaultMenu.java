package controllers;

import asg.cliche.Command;
import asg.cliche.Param;
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
