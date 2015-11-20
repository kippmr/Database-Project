public class User {
	private String username;
	
	public User(String uName) { //maybe check for valid user inside of constructor 
		this.username = uName;
	}
	
	//Returns username of user
	public String getUsername() { 
		return this.username;
	}
}