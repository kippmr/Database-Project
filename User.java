public class User{
	private String username;
	
	public User(String uName) {
		this.username = uName
	}
	
	//Returns username of user
	public String getUsername(...){
		return this.username;
	}
	
	//Adds a user to the users list if their username isn't already in use
	public static boolean checkUser(String uName) {
		boolean validUser = true
		for (User u: UserInterface.userNameList) {
			if (u.getUsername().equals(userName)) {
				validUser = false
				System.out.println("Username already in use")
	
			}
		}
		return validUser
	}
	
	//Create a user 
	public static User addUser (String uName) {
		User u = new Shoppingcart(uName) 
	}
	
	
}