package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

public class Movie {
	
	public static long counter = 0l;
	public long MovieID;
	public String title;
	public String releaseDate;
	public String URL;
	
	
	public List<Rating> numRatings = new ArrayList<>();
	
	
	public Movie(String title, String releaseDate, String URL){
		this.MovieID = counter++;
		this.title = title;
		this.releaseDate = releaseDate;
		this.URL = URL;
}

	@Override
	public String toString(){
	   return toStringHelper(this).addValue(MovieID)
			   					   .addValue(title)
			   					   .addValue(releaseDate)
			   					   .addValue(URL)
			   					   .toString();
	}
	
	@Override  
	  public int hashCode()  
	  {  
	     return Objects.hashCode(this.MovieID, this.title, this.releaseDate, this.URL);  
	  }
	
	@Override
	  public boolean equals(final Object obj)
	  {
	    if (obj instanceof Movie)
	    {
	      final Movie other = (Movie) obj;
	      return Objects.equal(MovieID, other.MovieID) 
	          && Objects.equal(title,  other.title)
	          && Objects.equal(releaseDate,     other.releaseDate)
	          && Objects.equal(URL,  other.URL);
	    }
	    else
	    {
	      return false;
	    }
	  }
}