package models;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;

public class Rating {
	  
	  public Long UserID;
	  public Long ItemID;
	  public int rating;
	  public int timestamp;
	  
	  public Rating(Long userID, Long itemID, int rating, int timestamp)
	  {
		  this.UserID = userID;
		  this.ItemID = itemID;
		  this.rating = rating;
		  this.timestamp = timestamp;
	  }
	  
	  public List<Movie> movie = new ArrayList<Movie>();
	  
	  @Override
	  public int hashCode()
	  {
		  return Objects.hashCode(this.UserID, this.ItemID, this.rating, this.timestamp);
	  }
	  
	  @Override
	  public boolean equals(final Object obj)
	  {
		  if (obj instanceof Rating) 
		  {
			  final Rating other = (Rating) obj;
			  return Objects.equal(UserID, other.UserID)
					  && Objects.equal(ItemID, other.ItemID)
					  && Objects.equal(rating, other.rating)
					  && Objects.equal(timestamp, other.timestamp);
		  }
			  else
			    {
			      return false;
			    }
		 
	  }
}
