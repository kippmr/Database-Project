import java.io.*;

public class User {
	private String username;
	private boolean admin;
	private String password;

	public User(String uName) { //maybe check for valid user inside of constructor 
		this.username = uName;
		this.admin = false;
	}

	public User(String uName, boolean admin) {
		this.username = uName;
		this.admin = admin;
		try {fetchPassword();}
		catch (IOException e) {e.printStackTrace();}
	}
	
	//Returns username of user
	public String getUsername() { 
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	private void fetchPassword() throws IOException {
		File pw = new File("password.txt");
		BufferedReader reader;
		if(pw.exists() && !pw.isDirectory()) {
			reader = new BufferedReader(new FileReader("password.txt"));
			this.password = reader.readLine();
			reader.close();
		}
		else {
			this.password = "ADMIN";
			writePassword(this.password);
		}
		return;
	}

	public void writePassword(String pw) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("password.txt"));
		writer.write(pw);
		writer.close();
		return;
	}
}