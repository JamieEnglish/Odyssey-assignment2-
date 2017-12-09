package controllers;

import java.util.Collection;

import com.google.common.base.Optional;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import utils.CSVLoader;
import utils.FileLogger;
import utils.Serializer;
import models.Movie;
import models.Rating;
import models.User;


public class OdysseyAPI 
{	
	private static Map<Long, User> userIndex = new HashMap<>();
	private static Map<Long, Movie> movieIndex = new HashMap<>();
	private static Map<Long, Rating> ratingIndex = new HashMap<>();
	private static Map<String, Movie> movieTitleIndex = new HashMap<>();
	private static Map<String, User> usernameIndex = new HashMap<>();
	private Serializer serializer;
	Optional<User> currentuser;
	
	public OdysseyAPI()
	  {}
	
	public OdysseyAPI(Serializer serializer)
	{
		this.serializer = serializer;
	}
	
	public void prime() throws Exception
	{
		CSVLoader loader = new CSVLoader();
		
		List <User> users = loader.loadUsers("moviedata_small/users5.dat");
		for(User user: users)
		{
			userIndex.put(user.UserID, user);
		}
		
		List<Movie> movies = loader.loadMovies("moviedata_small/items5.dat");
		for(Movie movie: movies)
		{
			movieIndex.put(movie.MovieID, movie);
		}
		
		List<Rating> ratings = loader.loadRatings("moviedata_small/ratings5.dat");
		for(Rating rating: ratings)
		{
			ratingIndex.put(rating.UserID, rating);
		}
	}
	
	@SuppressWarnings("unchecked")
	  public void load() throws Exception
	  {
	    serializer.read();
	    userIndex = (Map<Long, User>) serializer.pop();
	    usernameIndex = (Map<String, User>) serializer.pop();
	    movieIndex = (Map<Long, Movie>) serializer.pop();
	    ratingIndex = (Map<Long, Rating>) serializer.pop();
	    User.counter = (Long) serializer.pop();
	    Movie.counter = (Long) serializer.pop();
	    
	  }
	
	void store() throws Exception
	  {
		serializer.push(User.counter);
		serializer.push(Movie.counter);
		serializer.push(userIndex);
	    serializer.push(movieIndex);
	    serializer.push(ratingIndex);
	    serializer.push(usernameIndex);
	    serializer.write(); 
	  }
	
	
	public User addUser(String firstName, String lastName, int age, String gender, String occupation, String zipCode, String username, String password, String role)
	{
		User user = new User (firstName, lastName, age, gender, occupation, zipCode, username, password, role);
		userIndex.put(user.UserID, user);
		usernameIndex.put(username, user);
		return user;
	}
	
	public void removeUser(long UserID)
	{
		User user = userIndex.remove(UserID);
		usernameIndex.remove(user.username);
	}
	
	public static User getUser(long UserID)
	{
		return userIndex.get(UserID);
	}
	
	public User getUserByUsername(String username) 
	  {
	    return usernameIndex.get(username);
	  }
	
	public Collection<User> getUsers ()
	  {
	    return userIndex.values();
	  }
	
	public Movie addMovie(String title, String releaseDate, String URL)
	{
		Movie movie = new Movie(title, releaseDate, URL);
		movieIndex.put(movie.MovieID, movie);
		movieTitleIndex.put(title, movie);
		return movie;
	}
	
	public void removeMovie(Long MovieID)
	{
		movieIndex.remove(MovieID);
	}
	
	public static Movie getMovie(Long MovieID)
	{
		return movieIndex.get(MovieID);
	}
	
	public Collection<Movie> getMovies()
	{
		return movieIndex.values();
	}
	
	
	public Rating addRating(Long UserID, Long ItemID, int rating, int timestamp)
	  {
		  Rating thisRating = null;
		    Optional<User> user = Optional.fromNullable(userIndex.get(UserID));
		    if (user.isPresent())
		    {
		      thisRating = new Rating(UserID, ItemID, rating, timestamp);
		      user.get().ratings.put(thisRating.UserID, thisRating);
		      ratingIndex.put(thisRating.UserID, thisRating);
		    }
		    return thisRating;
	  }
	
	public Rating getRating(Long UserID)
	{
		return ratingIndex.get(UserID);
		
	}
	
	public static Movie getMoviesByTitle(String title)
	{
		return movieTitleIndex.get(title);
	}
	
	public static Movie getMovieByYear(String title)
	{
		return null;
	}
	
	public boolean login(String username, String password) {
	    Optional<User> user = Optional.fromNullable(usernameIndex.get(username));
	    if (user.isPresent() && user.get().password.equals(password)) {
	      currentuser = user;
	      FileLogger.getLogger().log(currentuser.get().username + " logged in...");
	      return true;
	    }
	    return false;
	  }
	
	public void logout() {
	    Optional<User> user = currentuser;
	    if (user.isPresent()) {
	      FileLogger.getLogger().log(currentuser.get().firstName + " logged out...");
	      currentuser = Optional.absent();
	    }
	  }
	
}
