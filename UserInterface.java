import java.io*;
import java.lang*;
import java.util*;

public class UserInterface {
	BufferedReader stung = new BufferedReader(new InputStreamReader(System.in)); //object for user's input
	User user = new User(); //object for the user
	private int currentPage, quantity; //page number, quantity
	private String currentUser = ""; //current user
	private String choose = ""; //stores user input
	private ArrayList<String> audio = new  ArrayList<String>(); //ArrayList for audio
	private ArrayList<String> readables = new  ArrayList<String>(); //ArrayList for readables

	boolean boolOption = false; //check if the entered option is valid
	boolean boolSNo = false; //check if the entered sNo is valid
	boolean boolQuantity = false; //check if the quantity is valid

	public UserInterface() {
		user.makeSC(); //make the users shopping cart
		currentPage = 1;
	}

	public void mainLoop() {
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
		System.out.println("1.Sign in
			\n2.Sign up
			\n\nChoose your Option: "); //display options to user
		choose = userInput(); //get the selected option
		if (choose.equals("1")) { //the user chooses 1
			System.out.println("Enter your username: "); //ask for a username
			user = getInput(); //get the username 
			
			if (user.checkUser(user)) { //check if the username exists
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

		if (user.addUser(currentUser)) { //check for already existing username
			System.out.println("Username successfully added."); //tell user input was successful
			currentPage = 1; //go back to page 1
		} else { //username already exists
			System.out.println("Username not added"); //tell user input was unsuccessful
			currentPage = 2; //repeat page 2
		}
	}

	private void p3() {
		//**Page No. 3! A friendly greeting**//
		System.out.println("Hello Mr. " + user + "\n"); //greet the user
		currentPage = 5; //proceed to page 5
	}

	private void p4() {
		//**Page No. 4! Seems like you didn't make the cut**//
		System.out.println("No Access\n"); //tell user 
		currentPage = 1;
	}

	private void p5() {
		//**Page No. 5! User can (1) view items (2) view cart or (3) sign out**//
			System.out.println("1.View Items By Catagory
				\n2.View Shopping Cart
				\n3.Sign Out
				\n\nChoose your option: "); //display options to user
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
		System.out.println("1.Readables
			\n2.Audio
			\nPress -1 to return to previous menu
			\n\nChoose your option: "); //display options to user
		choose = userInput(); //get the selected option

		if (choose.equals("1")) { //user chooses 1
			currentPage = 8; //proceed to page 8
		} else if (choose.equals("2")) { //user chooses 2
			currentPage = 9; //proceed to page 9
		} else if (choose.equals("-1")) { //user chooses -1
			currentPage = 5; //go back to page 5
		} else { //user does not choose a valid option
			currentPage = 6 //repeat page 6
		}
	}

	private void p7(){
		//**Page No. 7! User is viewing their cart.txt file!**//

		//open cart.txt file and list its contents, numbering each one
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
			System.out.println("Press -1 to return to previous menu.
				\nChoose your option: "); //display options to user
			choose = userInput(); //get user input

			//check for valid sNo
			if (choose.equals("-1")) { //if the user chooses -1
				currentPage = 6; //go back to page 6
				boolSNo = true; //valid option selected

			} else if (user.checkSNo(Integer.parseInt(choose))) { //check if the number enetered is a sNo
				boolSNo = true; //valid sNo selected

				boolQuantity = false; //reset for loop
				do { //loop until quantity is valid
					System.out.println("Enter quantity: "); //ask for quantity
					quantity = Integer.parseInt(userInput()); //get user input for quantity

					if (user.checkQuantity(quantity)) { //check for valid quantity
						boolQuantity = true; //valid quantity selected

						//TODO: append item to cart

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
					}
				} while (!boolQuantity); //while quantity is invalid

			} else { //selected invalid sNo
				System.out.println("Selected Seriel Number not Available.") //alert user to error
			}
		} while (!boolSNo); //while sNo is invalid (or not -1)
	}

	private void p9() {
		//**Page No. 9! User is viewing Audio. Numbers equal sNo's,**//
		//**selecting one will request a quantity. (-1) goes back a menu**//

		//TODO: copy/paste from p8()

	}

	private void p10( ) {
		//**Page No. 10! User's Billing Info, yes/no comformation and given an ID**//

		System.out.println("Billing Information:");
		//Name | *Percentages* | Quantity | Price

		System.out.println("Are you sure you want to pay? yes or no. ");
		if (userInput().toLowerCase().equals(("yes"))) {
			System.out.println("Comfirmation ID: " + comfirmID + 
				"\nItems shipped to: Mr." + currentUser);
		}
	}

	private void p11() {
		//**Page No. 11!**//

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

	public void showReadables() {
		//display all readables for browsing
	}

	public void showAudioProducts() {
		//display all audio products for browsing
	}

	public String userInput() {
		return stung.readLine(); //return what the user types into the prompt
	}
}