/* 
*Names: Yu You, Matthew Kipp, Sean McKay
*MacIDs: youy2, mckaysm, kippmr
*Student Number: 1419572 (Yu), 1423885 (Sean), 1303604 (Matt)
*Description: This is the Shopping Cart, used as container
for items the user might want to buy
*/

import java.io.*; // Working with input and output

public class User { // User can interact with the userinterface, purchase items etc.
	private String username; // username for the user
	private boolean admin; // Whether or not the user is an admin
	private String password; // password for admin, by default is ADMIN

	public User(String uName) { // Instantiation with given username 
		this.username = uName; // Set the user's username to the given username
		this.admin = false; // by default, user is not an admin
	}

	public User(String uName, boolean admin) { // Instantiation for admins with given username
		this.username = uName; // Set user's username to the given username
		this.admin = admin; // Set the admin property to true if the user is an admin
		try {fetchPassword();} // Attempt to get the admin password, if it exists
		catch (IOException e) {e.printStackTrace();} // Handle exception if password file does not exist
	}
	
	//Returns username of user
	public String getUsername() {  // Retuns the username of user as a string
		return this.username; // Return the username
	}

	public String getPassword() { // Returns the password of user as a string
		return this.password; // Return the password
	}

	private void fetchPassword() throws IOException { // Sets password field to the password stored in password.txt, if it exists. Otherwise sets it to "ADMIN" 
		File pw = new File("password.txt"); // Create file object
		BufferedReader reader; // To read from the file
		if(pw.exists() && !pw.isDirectory()) { // If the password file already exists
			reader = new BufferedReader(new FileReader("password.txt")); // Read from the password file
			this.password = reader.readLine(); // Read the password from the file
			reader.close(); // Close the file
		}
		else { // If the password file does not exist
			this.password = "ADMIN"; // Sets the password to "ADMIN"
			writePassword(this.password); // Writes the password to the password file
		}
		return; // exit
	}

	public void writePassword(String pw) throws IOException { // Creates a file "password.txt" and writes the given password string pw to it
		BufferedWriter writer = new BufferedWriter(new FileWriter("password.txt")); // To write to the file
		writer.write(pw); // Write the password to the file
		writer.close(); // Close the file
		return; // Exit
	}
}