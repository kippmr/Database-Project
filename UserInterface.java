import java.io.*;
import java.lang.*;
import java.util.*;

public class UserInterface {
	BufferedReader stung = new BufferedReader(new InputStreamReader(System.in)); //object for user's input

	private int currentPage, quantity; //page number, quantity
	private String cID = ""; //confirmation ID
	private String sNo = ""; //serial number
	private String currentUser = ""; //current user
	private String choose = ""; //stores user input
	private ArrayList<String> audioProducts = new  ArrayList<String>(); //ArrayList for audio
	private ArrayList<String> readables = new  ArrayList<String>(); //ArrayList for readables
	private ArrayList<String> users = new  ArrayList<String>(); //ArrayList for readables

	//TODO: initialize user object, currently doing it through user class
	User userO; //object for the user

	boolean boolOption = false; //check if the entered option is valid
	boolean boolSNo = false; //check if the entered sNo is valid
	boolean boolQuantity = false; //check if the quantity is valid

	public UserInterface() {
		currentPage = 1;

		getReadables();
		getAudioProducts();
		getUsers();
	}

	public static void mainLoop() {
		/**function to loop and use a switch statment to change the page to whichever page the user is on**/
		while(true) { //is forever really such a good idea?
			if (currentPage == 1) {
				p1();
			} else if (currentPage == 2) {
				p2();
			} else if (currentPage == 3) {
				p3();
			} else if (currentPage == 4) {
				p4();
			} else if (currentPage == 5) {
				p5();
			} else if (currentPage == 6) {
				p6();
			} else if (currentPage == 7) {
				p7();
			} else if (currentPage == 8) {
				p8();
			} else if (currentPage == 9) {
				p9();
			} else if (currentPage == 10) {
				p10();
			} else if (currentPage == 11) {
				p11();
			} else { 
				p1();
			}
		}
	}

	private void p1() {
		//**Page No. 1! Where a user either (1) signs in or (2) signs up**//
		System.out.println("1.Sign in"
				+ "\n2.Sign up"
				+ "\n\nChoose your Option: "); //display options to user
		choose = userInput(); //get the selected option
		if (choose.equals("1")) { //the user chooses 1
			System.out.println("Enter your username: "); //ask for a username
			currentUser = userInput(); //get the username 
			

			//TODO: check if the username is exists, currently doing it through user class
			if (User.checkUser(currentUser)) { //check if the username exists

				//TODO: create user object using getUser in user class
				userO = User.getUser(currentUser);

				currentPage = 3; //proceed to page 4
			} else { //if the username does not exist
				currentPage = 4; //proceed to page 4
			}
			}
		else if (choose.equals("2")) { //the user chooses 2
			currentPage = 2; //proceed to page 2
		} else { //the user does not choose a valid option
			currentPage = 1; //repeat page 1
		}
	}

	private void p2() {
		//**Page No. 2! User types in a Username and check for validity**//
		System.out.println("Choose your username: ");
		currentUser = userInput();

		//TODO: check if username is not taken and add it, currently doing it through user class
		if (User.checkUser(currentUser)) { //check for already existing username

			//TODO: add user to user.txt, currerntly doing it through user class
			User.addUser(currentUser);
			users.add(currentUser);

			System.out.println("Username successfully added."); //tell user input was successful
			currentPage = 1; //go back to page 1
		} else { //username already exists
			System.out.println("Username not added"); //tell user input was unsuccessful
			currentPage = 2; //repeat page 2
		}
	}

	private void p3() {
		//**Page No. 3! A friendly greeting**//
		System.out.println("Hello Mr. " + currentUser + "\n"); //greet the user
		currentPage = 5; //proceed to page 5
	}

	private void p4() {
		//**Page No. 4! Seems like you didn't make the cut**//
		System.out.println("No Access\n"); //tell user no access
		currentPage = 1; //go back to page 1
	}

	private void p5() {
		//**Page No. 5! User can (1) view items (2) view cart or (3) sign out**//
			System.out.println("1.View Items By Catagory"
					+ "\n2.View Shopping Cart"
					+ "\n3.Sign Out"
					+ "\n\nChoose your option: "); //display options to user
			choose = userInput(); //get the selected option

			if (choose.equals("1")) { //user chooses 1
				currentPage = 6; //proceed to page 6
			} else if (choose.equals("2")) { //user chooses 2
				currentPage = 7; //proceed to page 7
			} else if (choose.equals("3")) { //user chooses 3
				currentPage = 1; //go back to page 1
			} else { //user does not choose a valid option
				currentPage = 5; //repeat page 5
			}
	}

	private void p6() {
		//**Page No. 6! User can (1) view readables (2) view audio or (-1) return**//
		System.out.println("1.Readables"
				+ "\n2.Audio"
				+ "\nPress -1 to return to previous menu"
				+ "\n\nChoose your option: "); //display options to user
		choose = userInput(); //get the selected option

		if (choose.equals("1")) { //user chooses 1
			currentPage = 8; //proceed to page 8
		} else if (choose.equals("2")) { //user chooses 2
			currentPage = 9; //proceed to page 9
		} else if (choose.equals("-1")) { //user chooses -1
			currentPage = 5; //go back to page 5
		} else { //user does not choose a valid option
			currentPage = 6; //repeat page 6
		}
	}

	private void p7(){
		//**Page No. 7! User is viewing their Cart_USERNAME.txt file!**//

		//TODO: open Cart_USERNAME.txt file and list its contents, comma-separated
		ShoppingCart.getContent();

		currentPage = 5;
	}

	private void p8(){
		//**Page No. 8! User is viewing readables. Numbers equal sNo's,**//
		//**selecting one will request a quantity. (-1) goes back a menu**//
		
		System.out.println("Readables:\n"); //display readables

		// S.No | Name of Book | Author | Price($) | Quantity in Store | Type
		//TODO: display list of readables to user

		boolSNo = false; //reset for loop
		do { //loop until sNo is valid, or -1
			System.out.println("Press -1 to return to previous menu."
					+ "\nChoose your option: "); //display options to user
			choose = userInput(); //get user input

			//check for valid sNo
			if (choose.equals("-1")) { //if the user chooses -1
				currentPage = 6; //go back to page 6
				boolSNo = true; //valid option selected

			//TODO: check if sNo exists, currently doing it through ui class
			} else if (checkSNo(choose)) { //check if the number enetered is a sNo

				sNo = choose; //assign sNo to variable
				boolSNo = true; //valid sNo selected

				System.out.println("Enter quantity: "); //ask for quantity
				quantity = Integer.parseInt(userInput()); //get user input for quantity

				//TODO: check is there is enough quantity of the sNo, currently doing it through shoppingcart class
				if (ShoppingCart.checkQuantity(quantity, sNo)) { //check for valid quantity

					//TODO: append item to Cart_USERNAME.txt, do it with ShoppingCart.checkQuantity ??

					boolOption = false; //reset for loop
					do { //loop until valid answer is chosen
						System.out.println("Press -2 to Continue Shopping or Press 0 to CheckOut: "); //display options to user
						choose = userInput(); //get user input

						if (choose.equals("-2")) { //user chooses option -2
							boolOption = true; //valid option selected
							currentPage = 6; //go back to page 6
						} else if (choose.equals("0")) { //user chooses option 0
							boolOption = true; //valid option selected
							currentPage = 10; //proceed to page 10
						}
					} while (!boolOption); //while the option is invalid

				} else { //selected invalid amount of product
					System.out.println("Selected Quantity not Available."); //alert user to error
					currentPage = 8 //repeat page 6
				}

			} else { //selected invalid sNo
				System.out.println("Selected Serial Number not Available."); //alert user to error
			}
		} while (!boolSNo); //while sNo is invalid (or not -1)
	}

	private void p9() {
		//**Page No. 9! User is viewing Audio. Numbers equal sNo's,**//
		//**selecting one will request a quantity. (-1) goes back a menu**//

		//TODO: copy/paste from p8() when done

	}

	private void p10( ) {
		//**Page No. 10! User's Billing Info, yes/no comformation and given an ID**//

		System.out.println("Billing Information:");
		//Name | *Percentages* | Quantity | Price

		System.out.println("Are you sure you want to pay? yes or no. ");
		choose = userInput().toLowerCase();

		if (choose.equals(("yes"))) {
			System.out.println("Comfirmation ID: " + cNo + 
				"\nItems shipped to: Mr." + currentUser);
		} else {
			System.out.println("Going back to main menu");
			currentPage = 5;
		}
	}

	private void p11() {
		//**Page No. 11!**//

		//TODO: for bonus
	}





	//### HELPER FUINCTIONS ###\\
	public int getCurrentPage() {
		return currentPage;
	}

	public int changeCurrentPage(int page) {
		return page;
	}

	public void getReadables() {
		//fetch all readables from the files and place them in the readables array

		BufferedReader reader = new BufferedReader(new FileReader("Book.txt"));

		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
			readables.add(line);
		}
		reader.close();

		reader = new BufferedReader(new FileReader("eBook.txt"));

		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
			readables.add(line);
		}
		reader.close();
	}

	public void getAudioProducts() {
		//fetch all audio products from the files and place them in the audioProducts array

		//TODO: make the array list for Item objects instead of strings
		
		BufferedReader reader = new BufferedReader(new FileReader("MP3.txt"));

		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
			audioProducts.add(line);
		}
		reader.close();

		reader = new BufferedReader(new FileReader("CD.txt"));

		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
			audioProducts.add(line);
		}
		reader.close();
	}

	public void getUsers() {
		//fetch all users from the files and place them in the users array

		//TODO: make the array list for Item objects instead of strings
		
		BufferedReader reader = new BufferedReader(new FileReader("Users.txt"));

		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
			users.add(line);
		}
		reader.close();
	}

	public void showReadables() {
		//TODO: display all readables for browsing, needs formatting: see assignment

		
	}

	public void showAudioProducts() {
		//TODO: display all audio products for browsing, needs formatting: see assignment

		
	}

	public String userInput() {
		return stung.readLine(); //return what the user types into the prompt
	}

	public boolean checkSNo(String str) {
		//TODO: check if the sNo exists, wait until audioProducts and readables ArrayLists are for Items
	}
}