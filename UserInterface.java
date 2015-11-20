import java.io.*;
import java.lang.*;
import java.util.*;

public class UserInterface {
	BufferedReader stung = new BufferedReader(new InputStreamReader(System.in)); 	//object for user's input

	private int currentPage, quantity; 											 	//page number, quantity
	
	private String cID 					= ""; 										//confirmation ID
	private int sNo 					= 0; 										//serial number
	private String currentUser  		= ""; 										//current user
	private String choice 				= ""; 										//stores user input
	private String itemInfoString 		= "";										//stores information about the current selected item
	
	private ArrayList<CD> cdlist 		= new ArrayList<CD>();
	private ArrayList<MP3> mp3list 		= new ArrayList<MP3>();
	private ArrayList<Book> booklist	= new ArrayList<Book>(); 					//ArrayList for audio
	private ArrayList<eBook> ebooklist	= new ArrayList<eBook>(); 					//ArrayList for readables
	
	private ArrayList<String> users 	= new ArrayList<String>(); 					//ArrayList for users
	private ArrayList<String> sNos 		= new ArrayList<String>();					//ArrayList for serial numbers

	User userO 		   					= null; 									//object for the user
	ShoppingCart cartO 					= null; 

	boolean validOption   				= false; 									//check if the entered option is valid
	boolean	validSNo 	  				= false; 									//check if the entered sNo is valid
	boolean validQuantity 				= false; 									//check if the quantity is valid

	public UserInterface() throws IOException {
		
		currentPage = 1;

		getReadables();
		getAudioProducts();
		getUsers();
	}

	public void mainLoop() throws IOException{
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

	private void p1() throws IOException{
		//**Page No. 1! Where a user either (1) signs in or (2) signs up**//
		System.out.println("1.Sign in"
						 + "\n2.Sign up"
						 + "\n\nChoose your Option: "); 	//display options to user
		choice = userInput(); 								//get the selected option
		
		if (choice.equals("1")) { 							//the user choices 1
			System.out.println("Enter your username: "); 	//ask for a username
			currentUser = userInput(); 						//get the username 
			
			//TODO: check if the username is exists, currently doing it through user class
			if (users.contains(currentUser)) { 				//check if the username exists

				userO = new User(currentUser);
				cartO = new ShoppingCart(currentUser);

				gotoPage(3); 								//proceed to page 3
			} 
			else											//if the username does not exist
				gotoPage(4);								//proceed to page 4
		}
		
		else if (choice.equals("2")) //the user choices 2
			gotoPage(2); 									//proceed to page 2

		else 												//the user does not choice a valid option
			gotoPage(1); 									//repeat page 1
	}

	private void p2() throws IOException{
		//**Page No. 2! User types in a Username and check for validity**//
		System.out.println("Choose your username: ");
		currentUser = userInput();

		if (!users.contains(currentUser)) { 						//check for already existing username

			addUser(currentUser);

			System.out.println("Username successfully added."); 	//tell user input was successful
			gotoPage(1); 											//go back to page 1
		} 
		else { 														//username already exists
			System.out.println("Username not added"); 				//tell user input was unsuccessful
			gotoPage(1); 											//go back to page 1
		}
	}

	private void p3() {
		//**Page No. 3! A friendly greeting**//
		System.out.println("Hello Mr. " + userO.getUsername() + "\n");  //greet the user
		gotoPage(5); 													//proceed to page 5
	}

	private void p4() {
		//**Page No. 4! Seems like you didn't make the cut**//
		System.out.println("No Access\n"); 								//tell user no access
		gotoPage(1); 													//go back to page 1
	}

	private void p5() throws IOException{
		//**Page No. 5! User can (1) view items (2) view cart or (3) sign out**//
			System.out.println("1.View Items By Catagory"
							 + "\n2.View Shopping Cart"
							 + "\n3.Sign Out"
					         + "\n\nChoose your option: "); 			//display options to user
			choice = userInput(); 										//get the selected option

			if (choice.equals("1"))  									//user selects 1
				gotoPage(6);											//proceed to page 6
			else if (choice.equals("2"))  								//user selects 2
				gotoPage(7);
							 											//proceed to page 7
			else if (choice.equals("3")) { 								//user selects 3
				cartO.writeToFile();
				userO = null;
				cartO = null;
				currentUser = "";
				gotoPage(1); 											//go back to page 1
			} 
			
			else 														//user does not choice a valid option
				gotoPage(5); 											//repeat page 5
			
	}

	private void p6() throws IOException{
		//**Page No. 6! User can (1) view readables (2) view audio or (-1) return**//
		System.out.println("1.Readables"
						 + "\n2.Audio"
				         + "\nPress -1 to return to previous menu"
				         + "\n\nChoose your option: "); 				//display options to user
		choice = userInput(); 											//get the selected option

		if (choice.equals("1"))  										//user choices 1
			gotoPage(8); 												//proceed to page 8
		else if (choice.equals("2"))  									//user choices 2
			gotoPage(9); 												//proceed to page 9
		else if (choice.equals("-1")) 									//user choices -1
			gotoPage(5); 												//go back to page 5
		else  															//user does not choice a valid option
			gotoPage(6); 												//repeat page 6
		
	}

	private void p7() throws IOException{
		//**Page No. 7! User is viewing their Cart_USERNAME.txt file!**//
		System.out.println(cartO.getContent());

		gotoPage(5);
	}

	private void p8() throws IOException{
		//**Page No. 8! User is viewing readables. Numbers equal sNo's,**//
		//**selecting one will request a quantity. (-1) goes back a menu**//

		System.out.printf("\nReadables\n\n%-12s%-20s%-14s%-18s%-25s%s",
				"S.No","Name of Book","Author","Price($)","Quantity in Store","Type"); 	//display readables
		showReadables(); 																//call show readables function

		validSNo = false; 									//reset for loop
		do { 												//loop until sNo is valid, or -1
			System.out.println("Press -1 to return to previous menu."
					         + "\nChoose your option: "); 	//display options to user
			choice = userInput(); 							//get user input
															//check for valid sNo
			if (choice.equals("-1")) { 						//if the user choices -1
				currentPage = 6; 							//go back to page 6
				validSNo = true; 							//valid option selected

			//TODO: check if sNo exists, currently doing it through ui class
			} 
			else if (checkSNo(choice)) { 					//check if the number enetered is a sNo

				sNo = Integer.parseInt(choice); 			//assign sNo to variable
				validSNo = true; 							//valid sNo selected

				itemInfoString = findCurrentItem(sNo); 		//find the string that contains the serial number
				String[] info = itemInfoString.split(",");  //split the comma-seperated file
				if (info[5].equals("Book")) { 				//check if the type is Book
					Book currentItem = new Book(info[0], info[1], info[3], info[4], info[5]); //make a new Book item

					System.out.println("Enter quantity: "); 	//ask for quantity
					quantity = Integer.parseInt(userInput()); 	//get user input for quantity

					if (cartO.addItem(currentItem, quantity)) { //check for valid quantity

						//TODO: append item to Cart_USERNAME.txt, do it with ShoppingCart.checkQuantity ??

						validOption = false; //reset for loop
						do { 				 //loop until valid answer is chosen
							System.out.println("Press -2 to Continue Shopping or Press 0 to CheckOut: "); //display options to user
							choice = userInput(); //get user input

							if (choice.equals("-2")) { //user choices option -2
								validOption = true; //valid option selected
								gotoPage(6); //go back to page 6
							} else if (choice.equals("0")) { //user choices option 0
								validOption = true; //valid option selected
								gotoPage(10); //proceed to page 10
							}
						} while (!validOption); //while the option is invalid

					} else { //selected invalid amount of product
						System.out.println("Selected Quantity not Available."); //alert user to error
						gotoPage(8);// previous code: currentPage = 8; //repeat page 6 or page 8????
					}

				} 
				else if (info[5].equals("eBook")) { //check if the type is eBook
					eBook currentItem = new eBook(info[0], info[1], info[3], info[4], info[5]); //make new eBook item


					System.out.println("Enter quantity: "); //ask for quantity
					quantity = Integer.parseInt(userInput()); //get user input for quantity

					//TODO: addItem will also check for quantity
					if (cartO.addItem(currentItem, quantity)) { //check for valid quantity

						//TODO: append item to Cart_USERNAME.txt, do it with ShoppingCart.checkQuantity ??

						validOption = false; //reset for loop
						do { //loop until valid answer is chosen
							System.out.println("Press -2 to Continue Shopping or Press 0 to CheckOut: "); //display options to user
							choice = userInput(); //get user input

							if (choice.equals("-2")) { //user choices option -2
								validOption = true; //valid option selected
								gotoPage(6); //go back to page 6
							} else if (choice.equals("0")) { //user choices option 0
								validOption = true; //valid option selected
								gotoPage(10); //proceed to page 10
							}
						} while (!validOption); //while the option is invalid

					} else { //selected invalid amount of product
						System.out.println("Selected Quantity not Available."); //alert user to error
						gotoPage(8); //repeat page 6
					}
				}

			} else { //selected invalid sNo
				System.out.println("Selected Serial Number not Available."); //alert user to error
			}
		} while (!validSNo); //while sNo is invalid (or not -1)
	}

	private void p9() throws IOException{
		//**Page No. 9! User is viewing Audio. Numbers equal sNo's,**//
		//**selecting one will request a quantity. (-1) goes back a menu**//

		System.out.printf("Audio:\n\n%-12s%-20s%-14s%-18s%-25s%s",
				          "S.No","Name","Artist","Price($)","Quantity in Store","Type"); //display readables
		showAudioProducts(); //call show readables function

		validSNo = false; //reset for loop
		do { //loop until sNo is valid, or -1
			System.out.println("Press -1 to return to previous menu."
					+ "\nChoose your option: "); //display options to user
			choice = userInput(); //get user input

			//check for valid sNo
			if (choice.equals("-1")) { //if the user choices -1
				gotoPage(6); //go back to page 6
				validSNo = true; //valid option selected

			//TODO: check if sNo exists, currently doing it through ui class
			} else if (checkSNo(choice)) { //check if the number enetered is a sNo

				sNo = Integer.parseInt(choice); //assign sNo to variable
				validSNo = true; //valid sNo selected

				itemInfoString = findCurrentItem(sNo); //find the string that contains the serial number
				String[] info = itemInfoString.split(","); //split the comma-seperated file
				if (info[5].equals("MP3")) { //check if the type is MP3
					MP3 currentItem = new MP3(info[0], info[1], info[3], info[4], info[5]); //make a new MP3 item

					System.out.println("Enter quantity: "); //ask for quantity
					quantity = Integer.parseInt(userInput()); //get user input for quantity

					//TODO: addItem will also check for quantity
					if (cartO.addItem(currentItem, quantity)) { //check for valid quantity

						//TODO: append item to Cart_USERNAME.txt, do it with ShoppingCart.checkQuantity ??

						validOption = false; //reset for loop
						do { //loop until valid answer is chosen
							System.out.println("Press -2 to Continue Shopping or Press 0 to CheckOut: "); //display options to user
							choice = userInput(); //get user input

							if (choice.equals("-2")) { //user choices option -2
								validOption = true; //valid option selected
								gotoPage(6); //go back to page 6
							} else if (choice.equals("0")) { //user choices option 0
								validOption = true; //valid option selected
								gotoPage(10); //proceed to page 10
							}
						} while (!validOption); //while the option is invalid

					} else { //selected invalid amount of product
						System.out.println("Selected Quantity not Available."); //alert user to error
						gotoPage(9); //repeat page 9
					}


				} else if (info[5].equals("CD")) { //check if the type is CD
					CD currentItem = new CD(info[0], info[1], info[3], info[4], info[5]); //make new CD item


					System.out.println("Enter quantity: "); //ask for quantity
					quantity = Integer.parseInt(userInput()); //get user input for quantity

					//TODO: addItem will also check for quantity
					if (cartO.addItem(currentItem, quantity)) { //check for valid quantity

						//TODO: append item to Cart_USERNAME.txt, do it with ShoppingCart.checkQuantity ??

						validOption = false; //reset for loop
						do { //loop until valid answer is chosen
							System.out.println("Press -2 to Continue Shopping or Press 0 to CheckOut: "); //display options to user
							choice = userInput(); //get user input

							if (choice.equals("-2")) { //user choices option -2
								validOption = true; //valid option selected
								gotoPage(6); //go back to page 6
							} else if (choice.equals("0")) { //user choices option 0
								validOption = true; //valid option selected
								gotoPage(10); //proceed to page 10
							}
						} while (!validOption); //while the option is invalid

					} else { //selected invalid amount of product
						System.out.println("Selected Quantity not Available."); //alert user to error
						gotoPage(9); //repeat page 9
					}


				}

			} else { //selected invalid sNo
				System.out.println("Selected Serial Number not Available."); //alert user to error
			}
		} while (!validSNo); //while sNo is invalid (or not -1)
	}

	private void p10( ) throws IOException{
		//**Page No. 10! User's Billing Info, yes/no comformation and given an ID**//

		double envTax = 0.02; //applies to Book and CD
		double shipTax = 0.1; //applies to Book and CD

		System.out.println("Billing Information:");
		//Name | *Percentages* | Quantity | Price
		System.out.printf("%20s%14s%14s%s",
			"Name","","Quantity","Price");



		System.out.println("Are you sure you want to pay? yes or no. ");
		choice = userInput().toLowerCase();

		if (choice.equals(("yes"))) {
			System.out.println("Comfirmation ID: " + sNo + 
				"\nItems shipped to: Mr." + currentUser);
		} else {
			System.out.println("Going back to main menu");
			gotoPage(5);
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

	public int getPage(int page) {
		return page;
	}

	public void gotoPage(int page) {
		currentPage = page;
		return;
	}

	public void getReadables() throws IOException{
		//fetch all readables from the files and place them in the readables array
		BufferedReader reader = new BufferedReader(new FileReader("Book.txt"));
		
		String line;
		while ((line = reader.readLine()) != null) {
			String[] entry = line.split(",");
			booklist.add(new Book(entry[0],entry[1],entry[2],entry[3],entry[4]));
		}
		reader.close();

		reader = new BufferedReader(new FileReader("eBook.txt"));

		while ((line = reader.readLine()) != null) {
			String[] entry = line.split(",");
			ebooklist.add(new eBook(entry[0],entry[1],entry[2],entry[3],entry[4]));
		}
		reader.close();

		return;
	}

	public void getAudioProducts() throws IOException{
		//fetch all audio products from the files and place them in the audioProducts array
		
		BufferedReader reader = new BufferedReader(new FileReader("MP3.txt"));
		
		String line;
		while ((line = reader.readLine()) != null) {
			String[] entry = line.split(",");
			mp3list.add(new MP3(entry[0],entry[1],entry[2],entry[3],entry[4]));
		}
		reader.close();

		reader = new BufferedReader(new FileReader("CD.txt"));

		while ((line = reader.readLine()) != null) {
			String[] entry = line.split(",");
			cdlist.add(new CD(entry[0],entry[1],entry[2],entry[3],entry[4]));
		}
		reader.close();

		return;
	}

	public void getUsers() throws IOException{
		//fetch all users from the files and place them in the users array
		
		BufferedReader reader = new BufferedReader(new FileReader("Users.txt"));
		String line;
		while ((line = reader.readLine()) != null) {
			users.add(line);
		}
		reader.close();
		return;
	}

	public void showReadables() {

	    for(Book obj: booklist) {
			System.out.printf("\n%-12i%-20s%-14s%-18i%-25i%-8s%",
								obj.sNo, obj.title, obj.authorName, obj.price, obj.quant, "Book");
	    }
	    for(eBook obj: ebooklist) {
			System.out.printf("\n%-12i%-20s%-14s%-18i%-25i%-8s%",
								obj.sNo, obj.title, obj.authorName, obj.price, obj.quant, "eBook");
	    }
	    return;
	}

	public void showAudioProducts() throws IOException {
		
	    for(MP3 obj: mp3list) {
			System.out.printf("\n%-12i%-20s%-14s%-18i%-25i%-8s%",
								obj.sNo, obj.title, obj.artistName, obj.price, obj.quant, "Book");
	    }
	    for(CD obj: cdlist) {
			System.out.printf("\n%-12i%-20s%-14s%-18i%-25i%-8s%",
								obj.sNo, obj.title, obj.artistName, obj.price, obj.quant, "eBook");
	    }
	    return;
	}

	public String userInput() throws IOException{
		return stung.readLine(); //return what the user types into the prompt
	}

	public boolean checkSNo(String str) {
		//TODO: check if the sNo exists, wait until audioProducts and readables ArrayLists are for Items
		return sNos.contains(str);
	}

	public void addUser(String username) throws IOException{
		//add currentUser to ArrayList and Users.txt
		File target = new File("user.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(target,true));
		users.add(username);
		writer.write(username);
		writer.close();
		return;
	}

	public String findCurrentItem(int serial) {
		
		for (Book obj : booklist) { 
			if (obj.sNo == serial) {
				return obj.getInfo() + ",Book"; 
			}
		}

		for (eBook obj : ebooklist) { 
			if (obj.sNo == serial) {
				return obj.getInfo() + ",eBook"; 
			}
		}

		for (MP3 obj : mp3list) { 
			if (obj.sNo == serial) {
				return obj.getInfo() + ",MP3"; 
			}
		}

		for (CD obj : cdlist) { 
			if (obj.sNo == serial) {
				return obj.getInfo() + ",CD"; 
			}
		}

		return "";
	}
}