package models;

public class Fixtures 
{
	public static User[] users =
		  {
		    new User("jamie", "english", 22, "M", "Student" , "00010", "jayo", "secret"),
		    new User ("Aaron", "Cosulich", 22, "M", "Agent" , "11101", "arrow", "secret"),
		    new User("Alanas", "Jakonis", 20, "F", "Trainer" , "10101", "small", "secret"),
		    new User("Mozeeb", "Abdula", 18, "M", "Programmer" , "01010", "dope", "secret"),
		    new User ("Kamil", "Bigos", 18, "M", "Hacker", "33333", "surelad", "secret")
			}; 
	
	public static Movie[] movies =
		{
			new Movie("Instellar", "2014", "Https//www.imdb.com/instellar"),
			new Movie("Dunkirk", "2017", "Https//www.imdb.com/Dunkirk")
		};
	

}
