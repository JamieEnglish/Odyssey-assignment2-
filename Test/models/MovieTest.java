package models;

import static models.Fixtures.movies;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class MovieTest 
{
	Movie testMovie = new Movie("Interstellar (2014)", "01-Jan-2014", "http://www.imdb.com/title/tt0816692/?ref_=nv_sr_2");

	@Test
	public void testCreate()
	{
		assertEquals("Interstellar (2014)", testMovie.title);
		assertEquals("01-Jan-2014", testMovie.releaseDate);
		assertEquals("http://www.imdb.com/title/tt0816692/?ref_=nv_sr_2", testMovie.URL);
	}
	
	@Test
	  public void testIds()
	  {
	    Set<Long> ids = new HashSet<>();
	    for (Movie movie : movies)
	    {
	      ids.add(movie.MovieID);
	    }
	    assertEquals (movies.length, ids.size());
	  }
	
	@Test
	public void testToString() {
		assertEquals("Movie{" + testMovie.MovieID + ", Interstellar (2014), 01-Jan-2014, http://www.imdb.com/title/tt0816692/?ref_=nv_sr_2}"

		, testMovie.toString());
	}
	
	@Test
	public void testEquals()
	{
		Movie testMovie2 = new Movie("Interstellar (2014)", "01-Jan-2014", "http://www.imdb.com/title/tt0816692/?ref_=nv_sr_2");
		Movie diffMovie = new Movie("Lord of the Rings", "01-May-2001", "http://www.imdb.com/title/Lotr");
		
		assertEquals(testMovie, testMovie);
		assertEquals(testMovie, testMovie2);
		assertSame(testMovie, testMovie);
		assertNotSame(testMovie, testMovie2);
		assertNotEquals(testMovie, diffMovie);
	}

}
