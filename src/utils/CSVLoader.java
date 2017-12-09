package utils;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.introcs.In;
import models.Movie;
import models.Rating;
import models.User;

public class CSVLoader 
{
	public List<User> loadUsers(String filename) throws Exception 
	{
		File usersFile = new File(filename);
		In inUsers = new In(usersFile);
		String delims = "[|]";
		List<User> users = new ArrayList<User>();
		while (!inUsers.isEmpty()) 
		{
			String userDetails = inUsers.readLine();
			String[] userTokens = userDetails.split(delims);
			if (userTokens.length == 7) 
			{
				String firstName = userTokens[1];
				String lastName = userTokens[2];
				int age = Integer.valueOf(userTokens[3]);
				String gender = userTokens[4];
				String occupation = userTokens[5];
				String zipCode = userTokens[6];
				String username = "";
				String password = "";
				users.add(new User(firstName, lastName, age, gender, occupation, zipCode, username, password));
			} else {
				throw new Exception("Invalid member length: " + userTokens.length);
			}
		}
		return users;
   }
	
	public List<Movie> loadMovies(String filename) throws Exception
	{
		File moviesFile = new File(filename);
		In inMovies = new In(moviesFile);
		String delims = "[|]";
		List<Movie> movies = new ArrayList<>();
		while (!inMovies.isEmpty())
		{
			String movieDetails = inMovies.readLine();
			String[] movieTokens = movieDetails.split(delims);
			if(movieTokens.length == 23)
			{
				String title = movieTokens[1];
				String releaseDate = movieTokens[2];
				String URL = movieTokens[3];
				movies.add(new Movie(title, releaseDate, URL));
			}else {
				throw new Exception("Invalid member length: " + movieTokens.length);
			}
		}
		return movies;
	}
	
	public List<Rating> loadRatings(String filename) throws Exception
	{
		File ratingsFile = new File(filename);
		In inRatings = new In(ratingsFile);
		String delims = "[|]";
		List<Rating> ratings = new ArrayList<>();
		while(!inRatings.isEmpty())
		{
			String ratingDetails = inRatings.readLine();
			String[] ratingTokens = ratingDetails.split(delims);
			if(ratingTokens.length == 4)
			{
				Long UserID = Long.valueOf(ratingTokens[0]);
				Long ItemID = Long.valueOf(ratingTokens[1]);
				int rating = Integer.valueOf(ratingTokens[2]);
				int timestamp = Integer.valueOf(ratingTokens[3]);
				ratings.add(new Rating(UserID, ItemID, rating, timestamp));
			}else {
				throw new Exception("Invalid member length: " +ratingTokens.length);
			}
		}
		return ratings;
	}
}