import java.io.*; // Working with input and output
import java.lang.*; // More methods
import java.util.*; // More methods

public class UserInterface {
	BufferedReader stung = new BufferedReader(new InputStreamReader(System.in)); 	//object for user's input

	private int currentPage, quantity; 											 	//page number, quantity
		
	private int cID 					= 0;										//confirmation ID for checkout							
	private int sNo 					= 0;										//serial number of an item
	private int total					= 0; 										//total cost of an item
	private String currentUser  		= ""; 										//current user
	private String choice 				= "";										//stores user input								
	
	public ArrayList<Readable> readables	= new ArrayList<Readable>(); 			//ArrayList for readables
	private ArrayList<Audio> audioProducts	= new ArrayList<Audio>(); 				//ArrayList for audio
				
	private ArrayList<Readable> readablesbuffer	= new ArrayList<Readable>(); 		//for adding items to readables and writing them to file			
	private ArrayList<Audio> audioProductsbuffer	= new ArrayList<Audio>(); 		//for adding item to audio and writing them to file
	
	private ArrayList<String> users 	= new ArrayList<String>(); 					//ArrayList for users

	private User userO 		   					= null; 							//current user		
	private ShoppingCart cartO 					= null; 							//shooping cart for current user

	boolean validOption   				= false; 									//check if the entered option is valid
	boolean	validSNo 	  				= false; 									//check if the entered sNo is valid
	boolean validQuantity 				= false; 									//check if the quantity is valid
	boolean adminLogged 				= false;
	
	public UserInterface()  {														//UI object for easy user navigation
		
		currentPage = 1;															//go to page 1

		getReadables();																//add the readable items from file to the array
		getAudioProducts();															//add the audio items from file to the array
		getUsers();																	//get the names of the users from user.txt
		getConfirm();																//get the information on past purchases 													
	}

	public void mainLoop() { //function to loop and use a switch statment to change the page to whichever page the user is on**/
		while(true) { //loop forever, forever
			getCurrentPage(); //get the current page
		}
	}

	private void p1() { 												
	//**Page No. 1! Where a user either (1) signs in or (2) signs up**//
		System.out.println("1.Sign in" 									// Signing in 
						 + "\n2.Sign up" 								// Signing on 
						 + "\n\nChoose your Option: "); 				//display options to user
		choice = userInput(); 											//get the selected option
		
		if (choice.equals("1")) { 										//the user choices 1
			System.out.println("Enter your username: "); 				//ask for a username
			currentUser = userInput(); 									//get the username 
			
			if (users.contains(currentUser.toLowerCase())) { 			//Check if the username exists

				try {         											//Try it out
					userO = new User(currentUser);						//Create a user with specified username, set him as the current user
					cartO = new ShoppingCart(currentUser);				//Create a cart for the user
				} catch (IOException e) {								//Exception handling
					System.out.println("Error with User and Cart @ p1()");	//Inform user of error
					changeCurrentPage(1);								//Reset page 1
					return;												//Exit
				}
				getCart(cartO);											//Add items stored in currentuser_cart.txt to the current user's cart

				changeCurrentPage(3); 									//Proceed to page 3
			}
			else if (currentUser.equals("ADMIN")) {						//Admin login
				userO = new User(currentUser, true);					//Create an ADMIN user
				System.out.println("Enter password(case-sensitive): ");	//Password for admin logon
				String password = userInput();							//Get the password from user input
				if (password.equals(userO.getPassword())) {				//Check if the password is correct
					try {												//Try it out
						cartO = new ShoppingCart(currentUser); 			//Make a cart for the admin
					} catch (IOException | NumberFormatException e) {	//Exception handling
						System.out.println("Error with ADMIN Cart @ p1()"); //Inform user of error
						changeCurrentPage(1);							//Reset page 1	
						return;											//Exit
					}
					getCart(cartO);										//Add items stored in ADMIN_cart.txt to the admin's cart
					adminLogged = true;									//User is an admin, more options for him
					changeCurrentPage(3);								//Proceed to page 3
				}
				else {													//If the password was incorrect
					System.out.println("Incorrect Password");			//Inform the user 
					userO = null;										//Set the current user back to nothing
					changeCurrentPage(1);								//Reset page 1
				}

			} 
			else														//If the username does not exist
				changeCurrentPage(4);									//Proceed to page 4
		}
		
		else if (choice.equals("2")) 									//The user chooses 2
			changeCurrentPage(2); 										//Proceed to page 2

		else 															//The user does not choice a valid option
			changeCurrentPage(1); 										//Reset page 1
	}

	private void p2() {													
	//**Page No. 2! User types in a Username and check for validity**//
		System.out.println("Choose your username: ");					//Prompt user to enter a username


		currentUser = userInput().toLowerCase();						//Username is not case-sensitive

		if (!users.contains(currentUser) && !currentUser.equals("admin")) { //Check for already existing username

			addUser(currentUser);										//Add the username to the users

			System.out.println("Username successfully added."); 		//Tell user input was successful
			changeCurrentPage(1); 										//Go back to page 1
		} 
		else { 															//Username already exists
			System.out.println("Username not added"); 					//Tell user input was unsuccessful
			changeCurrentPage(1); 										//Go back to page 1
		}
	}

	private void p3() {													
	//**Page No. 3! A friendly greeting**//
		System.out.println("Hello Mr. " + userO.getUsername().toLowerCase() + "\n");  //Greet the user
		changeCurrentPage(5); 											//Proceed to page 5
	}

	private void p4() {													
	//**Page No. 4! Seems like you didn't make the cut**//
		System.out.println("No Access\n"); 								//Tell user no access
		changeCurrentPage(1); 											//Go back to page 1
	}

	private void p5() {													
	//**Page No. 5! User can (1) view items (2) view cart or (3) sign out**//
			System.out.println("\n1.View Items By Category"				// Press 1 to view our selection!
							 + "\n2.View Shopping Cart"					// Press 2 to see what you're about to spend moolah on
							 + "\n3.Sign Out"							// Press 3 to get out while you still can!
							 + "\n4.View Previous Orders");				// Press 4 to.. You again? 

			if(adminLogged) {											// If the user is an admin
				System.out.println("5.Change Password");				// Press 5 to change your password
				System.out.println("6.Admin Functions");				// Press 6 to open up the super secret admin menu
			}

			System.out.println("\n\nChoose your option: ");				//Prompt the user to enter an option


			choice = userInput();										//Get the user's selection

			if (choice.equals("1"))  									//User selects 1
				changeCurrentPage(6);									//Proceed to page 6
			else if (choice.equals("2"))  								//User selects 2
				changeCurrentPage(7);									//Proceed to page 7
			else if (choice.equals("3")) { 								//User selects 3
				
				writetoCart();											//Write all the items in the users cart to file
				
				userO = null;											//Reset the user
				cartO = null;											//Reset the user's cart
				
				currentUser = "";										//Reset the current username
			    adminLogged = false;									//Reset admin privileges
				
				changeCurrentPage(1); 									//Go back to page 1
			} 
			else if (choice.equals("4")) { 								//User selects 4
				File file = new File("ItemsBought.txt");				//Previous orders file
				if (file.exists() && !file.isDirectory()) {				//If previous order file already exists
					try {												//Try it out
						BufferedReader reader = new BufferedReader(new FileReader("ItemsBought.txt")); // To read from the previous order file
						String line;	//Information on past orders
						while ((line = reader.readLine()) != null)	//Read to the end of the file
							System.out.println(line);	//Display information to the user
					} catch (IOException e) {	//Exception Handling
						System.out.println("Error Accessing ItemsBought.txt");	//Inform user of error
						changeCurrentPage(5);	//Reset page 5
						return;	//Exit 
					}
				}
				else	//If the user has not ordered anything before
					System.out.println("No Previous Items Bought\n");	//Inform the user
				changeCurrentPage(5);	//Reset page 5											
			}
			else if (choice.equals("5") && adminLogged) {	//Admin user selects 5
				
				System.out.println("\nEnter New Password: ");	//Prompt Admin to enter new password 
				
				try {	//Try it out
					String pw = userInput();	//Get the password from user input
					userO.writePassword(pw);	//Change the admin's password
				} catch (IOException e) {		//Exception handling
					System.out.println("New Password Failed to be Stored");	//Inform the admin the password did not change
					changeCurrentPage(5);	//Reset page 5
					return; //Exit
				}

				System.out.println("\nNew Password has been stored\n"); //Inform the user the password has been changed
				changeCurrentPage(5);	//Reset page 5
			}

			else if (choice.equals("6") && adminLogged) {	//Admin user selects 6
				changeCurrentPage(11);	//Proceed to page 11
			} 
			
			else	//User does not choice a valid option
				changeCurrentPage(5); 											//repeat page 5
			
	}

	private void p6() {													
	//**Page No. 6! User can (1) view readables (2) view audio or (-1) return**//
		System.out.println("1.Readables"								//Press 1 to view items you use your eyes to process
						 + "\n2.Audio"									//Press 2 to view items you use your ears to process
						 + "\nPress -1 to return to previous menu"		//Press -1 to return to previous menu
				         + "\n\nChoose your option: "); 				//Display options to user
		choice = userInput(); 											//Get the selected option

		if (choice.equals("1")) {									//user choices 1
			sortBySerialR(readables);								//Sort readables by their serial number, lowest to highest
			changeCurrentPage(8); 									//Proceed to page 8
		}															
		else if (choice.equals("2")) { 									//User chooses 2
			sortBySerialA(audioProducts);								//Sort audio by their serial number, lowest to highest 
			changeCurrentPage(9); 										//Proceed to page 9
		}
		else if (choice.equals("-1")) 									//user chooses -1
			changeCurrentPage(5); 												//go back to page 5
		else  															//user does not choice a valid option
			changeCurrentPage(6); 												//repeat page 6
		
	}

	private void p7() {													//**Page No. 7! User is viewing their Cart_USERNAME.txt file!**//

		System.out.println(cartO.getContent());							//print info on items in cart
		if(cartO.getContent().equals(""))								//If the cart is empty
			System.out.println("\nCart is Empty\n");					//Inform the user their cart is empty

		changeCurrentPage(5);											//Go back to page 5
	}

	private void p8() {
		//**Page No. 8! User is viewing readables. Numbers equal sNo's,**//
		//**selecting one will request a quantity. (-1) goes back a menu**//

		if(adminLogged) {												//If the user is admin
			System.out.println("1.Sorted By Serial"						//Press 1 to view the items sorted nicely by their serial number
						 + "\n2.Sorted By Alphabet"						//Press 2 to view the items sorted nicely and alphabetically, a-z
						 + "\n3.Sorted By Price"						//Press 3 to view the items sorted nicely by price, lowest to highest
				         + "\n\nChoose your option: "); 				//display options to user
			choice = userInput(); 										//Get user choice

			if(choice.equals("1"))										//User chooses 1 
				sortBySerialR(readables);								//Sort the readables by their serial number 
			else if(choice.equals("2"))									//User chooses 2
				sortByAlphabetR(readables);								//Sort the readables by their alphanumeric position, a-z 
			else if(choice.equals("3"))									//User chooses 3
				sortByPriceR(readables);								//Sort the readables by their price, lowest to highest 
		}

		System.out.printf("\nReadables\n\n%-5s%-25s%-10s%-10s%-20s%-5s",				//formatting for displaying readables
				"S.No","Name of Book","Author","Price($)","Quantity in Store","Type"); 	//display readables
		showReadables(); 																//call show readables function
		System.out.println();
		validSNo = false; 																//reset for loop
		do { 																			//loop until sNo is valid, or -1
			System.out.println("Press -1 to return to previous menu."
					         + "\nChoose your option: "); 								//display options to user
			choice = userInput(); 														//get user input
																						//check for valid sNo
			if (choice.equals("-1")) { 													//if the user choices -1
				changeCurrentPage(6); 														//go back to page 6
				validSNo = true; 														//valid option selected


			} 
			else if (checkSNo(Integer.parseInt(choice))) { 								//check if the number enetered is a sNo

				sNo = Integer.parseInt(choice); 										//assign sNo to variable
				validSNo = true; 														//valid sNo selected

				Object itemObj = findItem(sNo); 										//find the string that contains the serial number
				if (itemObj instanceof Book) { 											//check if the type is Book
					Book currentItem = (Book) itemObj; 									//make a new Book item

					System.out.println("Enter quantity: "); 							//ask for quantity
					quantity = Integer.parseInt(userInput()); 							//get user input for quantity

					if (cartO.addItem(currentItem, quantity)) { 						//check for valid quantity
						subtractQuant(sNo,quantity);
		

						validOption = false; 											//reset for loop
						do { 				 											//loop until valid answer is chosen
							System.out.println("Press -2 to Continue Shopping" +
												"or Press 0 to CheckOut: "); 			//display options to user
							choice = userInput(); 										//get user input

							if (choice.equals("-2")) { 									//user choices option -2
								validOption = true; 									//valid option selected
								changeCurrentPage(6); 											//go back to page 6
							} else if (choice.equals("0")) { 							//user choices option 0
								validOption = true; 									//valid option selected
								changeCurrentPage(10); 											//proceed to page 10
							}
						} while (!validOption); 										//while the option is invalid

					} else { 															//selected invalid amount of product
						System.out.println("Selected Quantity not Available."); 		//alert user to error
						changeCurrentPage(8);													// previous code: currentPage = 8; //repeat page 6 or page 8????
					}

				} 
				else if (itemObj instanceof eBook) {									//check if the type is eBook
					eBook currentItem = (eBook) itemObj; 								//make new eBook item


					System.out.println("Enter quantity: "); 							//ask for quantity
					quantity = Integer.parseInt(userInput()); 							//get user input for quantity

					if (cartO.addItem(currentItem, quantity)) {							//check for valid quantity
						subtractQuant(sNo,quantity);
						

						validOption = false; 											//reset for loop
						do { 															//loop until valid answer is chosen
							System.out.println("Press -2 to Continue Shopping" + 
												"or Press 0 to CheckOut: "); 			//display options to user
							choice = userInput(); 										//get user input

							if (choice.equals("-2")) { 									//user choices option -2
								validOption = true; 									//valid option selected
								changeCurrentPage(6); 											//go back to page 6
							} else if (choice.equals("0")) { 							//user choices option 0
								validOption = true; 									//valid option selected
								changeCurrentPage(10); 											//proceed to page 10
							}
						} while (!validOption); 										//while the option is invalid

					} else { 															//selected invalid amount of product
						System.out.println("Selected Quantity not Available."); 		//alert user to error
						changeCurrentPage(8); 													//repeat page 6
					}
				}

			} else { 																	//selected invalid sNo
				System.out.println("Selected Serial Number not Available."); 			//alert user to error
			}
		} while (!validSNo); 															//while sNo is invalid (or not -1)
	}

	private void p9() {
		//**Page No. 9! User is viewing Audio. Numbers equal sNo's,**//
		//**selecting one will request a quantity. (-1) goes back a menu**//

		if(adminLogged) {
			System.out.println("1.Sorted By Serial"						//Press 1 to view the items sorted nicely by their serial number
						 + "\n2.Sorted By Alphabet"						//Press 2 to view the items sorted nicely and alphabetically, a-z
						 + "\n3.Sorted By Price"						//Press 3 to view the items sorted nicely by price, lowest to highest
				         + "\n\nChoose your option: "); 				//display options to user
			choice = userInput(); 										//Get the Admin user's choice

			if(choice.equals("1"))										//User chooses 1
				sortBySerialA(audioProducts);							//Sort the audio products by their serial number 
			else if(choice.equals("2"))									//User chooses 2
				sortByAlphabetA(audioProducts);							//Sort the audio products by their alphanumeric position, a-z 
			else if(choice.equals("3"))									//User chooses 3
				sortByPriceA(audioProducts);							//Sort the audio products by their price, lowest to highest 
		}

		System.out.printf("Audio:\n\n%-5s%-25s%-10s%-10s%-20s%-5s",						//Formatting for displaying audio
				          "S.No","Name","Artist","Price($)","Quantity in Store","Type"); //display audio products
		showAudioProducts(); 															//call show audioproducts function
		System.out.println();
		validSNo = false; 																//reset for loop
		do { 																			//loop until sNo is valid, or -1
			System.out.println("Press -1 to return to previous menu."
					+ "\nChoose your option: "); 										//display options to user
			choice = userInput(); 														//get user input

																						//check for valid sNo
			if (choice.equals("-1")) { 													//if the user choices -1
				changeCurrentPage(6); 															//go back to page 6
				validSNo = true; 														//valid option selected

			} 
			else if (checkSNo(Integer.parseInt(choice))) { 								//check if the number enetered is a sNo

				sNo = Integer.parseInt(choice); 										//assign sNo to variable
				validSNo = true; 														//valid sNo selected

				Object itemObj = findItem(sNo); 										//find the string that contains the serial number
				if (itemObj instanceof MP3) { 											//check if the type is MP3
					MP3 currentItem = (MP3) itemObj; 									//make a new MP3 item

					System.out.println("Enter quantity: "); 							//ask for quantity
					quantity = Integer.parseInt(userInput()); 							//get user input for quantity

					if (cartO.addItem(currentItem, quantity)) { 						//check for valid quantity
						subtractQuant(sNo,quantity);

						validOption = false; 											//reset for loop
						do { 															//loop until valid answer is chosen
							System.out.println("Press -2 to Continue Shopping" + 
												"or Press 0 to CheckOut: "); 			//display options to user
							choice = userInput(); 										//get user input

							if (choice.equals("-2")) { 									//user choices option -2
								validOption = true; 									//valid option selected
								changeCurrentPage(6); 											//go back to page 6
							} 
							else if (choice.equals("0")) { 								//user choices option 0
								validOption = true; 									//valid option selected
								changeCurrentPage(10); 											//proceed to page 10
							}
						} while (!validOption); 										//while the option is invalid

					} 
					else { 																//selected invalid amount of product
						System.out.println("Selected Quantity not Available."); 		//alert user to error
						changeCurrentPage(9); 													//repeat page 9
					}



				} 
				else if (itemObj instanceof CD) { 										//check if the type is CD
					CD currentItem = (CD) itemObj; 										//make new CD item


					System.out.println("Enter quantity: "); 							//ask for quantity
					quantity = Integer.parseInt(userInput()); 							//get user input for quantity

					if (cartO.addItem(currentItem, quantity)) { 						//check for valid quantity
						subtractQuant(sNo,quantity);

						validOption = false; 											//reset for loop
						do { 															//loop until valid answer is chosen
							System.out.println("Press -2 to Continue Shopping" + 
												"or Press 0 to CheckOut: "); 			//display options to user
							choice = userInput(); 										//get user input

							if (choice.equals("-2")) { 									//user choices option -2
								validOption = true; 									//valid option selected
								changeCurrentPage(6); 											//go back to page 6
							} 
							else if (choice.equals("0")) { 								//user choices option 0
								validOption = true; 									//valid option selected
								changeCurrentPage(10);											//proceed to page 10
							}
						} while (!validOption); 										//while the option is invalid

					} 
					else { 																//selected invalid amount of product
						System.out.println("Selected Quantity not Available."); 		//alert user to error
						changeCurrentPage(9); 													//repeat page 9
					}


				}

			} 
			else { 																		//selected invalid sNo
				System.out.println("Selected Serial Number not Available."); 			//alert user to error
			}
		} while (!validSNo); 															//while sNo is invalid (or not -1)
	}

	private void p10 () {
		//**Page No. 10! User's Billing Info, yes/no comformation and given an ID**//
		int env = getEnviro();	//Environment tax
		int totalbefore = getAllPrices();	//Total price before envirotax, shipping and hst
		int ship = (int) ((double)totalbefore * 0.1);	//Shipping cost
		int hst =  (int) ((double)totalbefore * 0.13);	//HST tax
		int everything = env + ship + hst + totalbefore;	//Total cost
		total = everything; // Total cost

		System.out.println("Billing Information:");	//Purchase info
		System.out.println();	//newline
		//Name | *Percentages* | Quantity | Price
		System.out.printf("%-20s%6s%-15s%-5s\n", //Format for purchase info
			"Name","","Quantity","Price");	//Purchase info 

		for (Item item : cartO.allContent()) { //Iterate through all items in user's cart
			if (item instanceof Readable) {	//If the item is a readable 
				Readable readable = (Readable) item;	//refer to item as readable
				System.out.printf("%-20s%6s%-15s%-5d\n",	//format for readable info
				readable.getTitle(),"",readable.getQuant(),findPrice(readable.getSerial())); //Display Info for purchased readable
			}
			else if (item instanceof Audio) {	//If the item is an audio product
				Audio audio = (Audio) item;	//Refer to the item as audio
				System.out.printf("%-20s%6s%-15s%-5d\n",	//Format for the audio info
				audio.getTitle(),"",audio.getQuant(),findPrice(audio.getSerial()));	//Display info for purchased audio
			}
		}
		System.out.println();	//Newline
		System.out.printf("%-20s%-6s%15s%-5d\n",	//Formatting for envirotax
			"Enviroment Tax","2%","",env);	//Display environment tax on purchase

		System.out.printf("%-20s%-6s%15s%-5d\n\n",	//Formatting for HST
			"HST","13%","", hst);	//Display HST on purchase

		System.out.printf("%-20s%-6s%15s%-5d\n\n", //Formatting for shipping 
			"Shipping","10%","", ship); //Display shipping cost for purchase

		System.out.printf("%-20s%6s%15s%s\n", //Formatting
			"","","", "------------"); //Spacer

		System.out.printf("%-20s%6s%15s%-5d\n", //Formatting for total
			"Total:","","", everything); //Disaply total cost of purchase


		System.out.println("Are you sure you want to pay? yes or no. "); // Prompt user to confirm purchase
		choice = userInput().toLowerCase(); //Get user input 

		if (choice.equals(("yes"))) { //User chooses yes
			System.out.println("Comfirmation ID: " + "U" + cID +  //Print confirmation ID for purchase
				"\nItems shipped to: Mr." + currentUser);	//Ooowweeee
			updateQuant();	//Update quantity of items in stock 
			updateFiles();	//Update this info in files
			writeConfirm(); //Write the purchase to previous orders
			cartO.clearCart(); //Clear the user's cart
			cID++;	//Increment the confirmation ID
			changeCurrentPage(5); //Go back to page 5
		} 
		else if (choice.equals("no")) {	//User chooses no
			System.out.println("Going back to main menu"); //Let user know
			changeCurrentPage(5); // Go back to page 5
		}
	}

	private void p11() {
		//**Page No. 11! Admin functions**//
		System.out.println("\nADMIN FUNCTIONS" //Admin's only, Users get out! REEEE
						 + "\n1.Add Book"	//Press 1 to add a shiny new book to the collection
						 + "\n2.Add eBook"	//Press 2 to add a cute new ebook to the collection
						 + "\n3.Add CD"	//Press 3 to add a slippery new CD to the collection
						 + "\n4.Add MP3"	//Press 4 to add a greasy new MP3 to the collection
						 + "\nPress -1 to return to previous menu." // Go back, this is not for me!
						 + "\n\nChoose your Option: "); //Prompt user to pick 
		choice = userInput(); //Get user choice
		
		String serial, title, author, price, quantity; //serial number, title, author or artist name, price, and quantity of item to add
		if (choice.equals("1")) {	//Admin chooses 1
			System.out.println("\nEnter serial number: ");	//Prompt user to enter a serial number
			serial = userInput();	//Get serial number from user input 
			System.out.println("Enter product name: ");	//Prompt user to enter title of product they want to add
			title = userInput();	//Get product title from user input 
			System.out.println("Enter product author: ");	//Prompt user to enter author of product
			author = userInput();	//Get product author from user input
			System.out.println("Enter product price: ");	//Prompt user to enter the price of the product they want to add
			price = userInput();	//Get product price from user input
			System.out.println("Enter product quantity: ");	//Prompt user to enter the number of product they want to add
			quantity = userInput(); // Get product quantity from user input 
			if (!checkSNo(Integer.parseInt(serial))) { // If the serial number does not already belong to another product
				readables.add(new Book(serial,title,author,price,quantity));	//Add the product to readables
				readablesbuffer.add(new Book(serial,title,author,price,quantity));  //Add the product to readables buffer
				updateFiles(); //Update info in files
				System.out.println("\nProduct successfully added"); //Inform the user they succesfully added a product!
			}
			else	//If the serial number belongs to another product
				System.out.println("\nFailed to add Product"); //Inform the user the product was not able to be added
			changeCurrentPage(5); // Go back to page 5
		}
		else if (choice.equals("2")) { //Admin chooses 2
			System.out.println("\nEnter serial number: ");	//Prompt user to enter a serial number
			serial = userInput();	//Get serial number from user input 
			System.out.println("Enter product name: ");	//Prompt user to enter title of product they want to add
			title = userInput();	//Get product author from user input
			System.out.println("Enter product author: ");	//Prompt user to enter author of product
			author = userInput();	//Get product author from user input
			System.out.println("Enter product price: ");	//Prompt user to enter the price of the product they want to add
			price = userInput();	//Get product price from user input
			System.out.println("Enter product quantity: ");	//Prompt user to enter the number of product they want to add
			quantity = userInput();	// Get product quantity from user input 
			if (!checkSNo(Integer.parseInt(serial))) { // If the serial number does not already belong to another product
				readables.add(new eBook(serial,title,author,price,quantity)); //Add the product to readables
				readablesbuffer.add(new eBook(serial,title,author,price,quantity)); //Add the product to readables buffer
				updateFiles(); //Update info in files
				System.out.println("\nProduct successfully added"); //Inform the user they succesfully added a product!
			}
			else	//If the serial number belongs to another product
				System.out.println("\nFailed to add Product"); //Inform the user the product was not able to be added
			changeCurrentPage(5); // Go back to page 5
		}
		else if (choice.equals("3")) { //Admin chooses 3
			System.out.println("\nEnter serial number: "); //Prompt user to enter a serial number
			serial = userInput();	//Get serial number from user input 
			System.out.println("Enter product name: ");	//Prompt user to enter title of product they want to add
			title = userInput();	//Get product author from user input
			System.out.println("Enter product artist: ");	//Prompt user to enter author of product
			author = userInput();	//Get product author from user input
			System.out.println("Enter product price: ");	//Prompt user to enter the price of the product they want to add
			price = userInput();	//Get product price from user input
			System.out.println("Enter product quantity: ");	//Prompt user to enter the number of product they want to add
			quantity = userInput();	// Get product quantity from user input 
			if (!checkSNo(Integer.parseInt(serial))) {	// If the serial number does not already belong to another product
				audioProducts.add(new CD(serial,title,author,price,quantity));	//Add the product to audio products
				audioProductsbuffer.add(new CD(serial,title,author,price,quantity));	//Add the product to audio products buffer
				updateFiles();	//Update info in files
				System.out.println("\nProduct successfully added");	//Inform the user they succesfully added a product!
			}
			else	//If the serial number belongs to another product
				System.out.println("\nFailed to add Product");	//Inform the user the product was not able to be added
			changeCurrentPage(5);	// Go back to page 5
		}
		else if (choice.equals("4")) { //Admin chooses 4
			System.out.println("\nEnter serial number: ");	//Prompt user to enter a serial number
			serial = userInput();	//Get serial number from user input 
			System.out.println("Enter product name: ");	//Prompt user to enter title of product they want to add
			title = userInput();	//Get product author from user input
			System.out.println("Enter product artist: ");	//Prompt user to enter author of product
			author = userInput();	//Get product author from user input
			System.out.println("Enter product price: ");	//Prompt user to enter the price of the product they want to add
			price = userInput();	//Get product price from user input
			System.out.println("Enter product quantity: ");	//Prompt user to enter the number of product they want to add
			quantity = userInput();	// Get product quantity from user input 
			if (!checkSNo(Integer.parseInt(serial))) {	// If the serial number does not already belong to another product
				audioProducts.add(new MP3(serial,title,author,price,quantity));	//Add the product to audio products
				audioProductsbuffer.add(new MP3(serial,title,author,price,quantity));	//Add the product to audio products buffer
				updateFiles();	//Update info in files
				System.out.println("\nProduct successfully added");	//Inform the user they succesfully added a product!
			}
			else	//If the serial number belongs to another product
				System.out.println("\nFailed to add Product");	//Inform the user the product was not able to be added
			changeCurrentPage(5);	// Go back to page 5
		}
		else if (choice.equals("-1")) { //User chooses -1
			changeCurrentPage(5);	// Go back to page 5
		}

	}





	//### HELPER FUINCTIONS ###\\
	public void getCurrentPage() { //Displays current page to user
		if (currentPage == 1) { // If the current page is 1
				p1(); //Display page 1
			} else if (currentPage == 2) { // If the current page is 2
				p2(); //Display page 2
			} else if (currentPage == 3) { // If the current page is 3
				p3(); //Display page 3
			} else if (currentPage == 4) { // If the current page is 4
				p4(); //Display page 4
			} else if (currentPage == 5) { // If the current page is 5
				p5(); //Display page 5
			} else if (currentPage == 6) { // If the current page is 6
				p6(); //Display page 6
			} else if (currentPage == 7) { // If the current page is 7
				p7(); //Display page 7
			} else if (currentPage == 8) { // If the current page is 8
				p8(); //Display page 8
			} else if (currentPage == 9) { // If the current page is 9
				p9(); //Display page 9
			} else if (currentPage == 10) { // If the current page is 10
				p10(); //Display page 10
			} else if (currentPage == 11) { // If the current page is 11
				p11(); //Display page 11
			} else { //If no current page
				p1(); //Display page 1
			}
	}

	public void changeCurrentPage(int page) {	
	//Change the current page to the given integer value
		currentPage = page;	// Change the current page
		return;	//Exit 
	}

	public void getReadables() {	
	//Fetch all readables from the files and place them in the readables array
		try {	//Catch errors
			BufferedReader reader = new BufferedReader(new FileReader("Books.txt")); //Read from Books.txt 
		
			String line;	//Lines in Item File
			while ((line = reader.readLine()) != null) { //Read to the end of the file
				String[] entry = line.split(",");	//Split the lines at commas, split into [sNo, title, author, price, quantity] 
				readables.add(new Book(entry[0],entry[1],entry[2],entry[3],entry[4])); //Add the item from file to the readables
			}
			reader.close(); // Close the file

			reader = new BufferedReader(new FileReader("EBooks.txt")); //Read from EBooks.txt

			while ((line = reader.readLine()) != null) { //Read to the end of the file
				String[] entry = line.split(",");	//Split the lines at commas, split into [sNo, title, author, price, quantity] 
				readables.add(new eBook(entry[0],entry[1],entry[2],entry[3],entry[4])); //Add the item from file to the readables
			}
			reader.close(); // Close the file

			readablesbuffer = new ArrayList<Readable>(readables); //Set the buffer to be the same as readables
				
			return; //Exit
		} catch (IOException e) { //Exception handling 
			System.out.println("Error Fetching Readables @ getReadables()"); //Inform user of error fetching readables
		} finally { //Do this always
			return; //Exit
		}
	}

	public void getAudioProducts(){ 	
	//Fetch all audio products from the files and place them in the audioProducts array
		try {	//Catch errors
			BufferedReader reader = new BufferedReader(new FileReader("MP3.txt")); //Read from MP3.txt
		
			String line;	//Lines in Item File
			while ((line = reader.readLine()) != null) {	//Read to the end of the file
				String[] entry = line.split(",");	//Split the lines at commas, split into [sNo, title, artist, price, quantity] 
				audioProducts.add(new MP3(entry[0],entry[1],entry[2],entry[3],entry[4]));	//Add the item from file to the audio products
			}
			reader.close();	// Close the file

			reader = new BufferedReader(new FileReader("CDs.txt"));	//Read from CDs.txt

			while ((line = reader.readLine()) != null) {	//Read to the end of the file
				String[] entry = line.split(",");	//Split the lines at commas, split into [sNo, title, artist, price, quantity] 
				audioProducts.add(new CD(entry[0],entry[1],entry[2],entry[3],entry[4]));	//Add the item from file to the audio products
			}
			reader.close();	// Close the file

			audioProductsbuffer = new ArrayList<Audio>(audioProducts);	//Set the buffer to be the same as audio products
		} catch (IOException e) { //Exception handling 
			System.out.println("Error Fetching Audio Products @ getAudioProducts()");	//Inform user of error fetching audio products
		} finally {	//After catching error
			return;	//Exit
		}		
	}

	public void getUsers() {
	//Fetch all users from the files and place them in the users array
		try { //Catch errors
			File userfile = new File("Users.txt");	//specify user file
			if(userfile.exists() && !userfile.isDirectory()) {  //If the user file exists
				BufferedReader reader = new BufferedReader(new FileReader("Users.txt"));	//Read from Users.txt
				String line;	//Lines in user file
				while ((line = reader.readLine()) != null) {	//Read to the end of the file
					users.add(line);	//Add the username on that line the users list
				}
				reader.close();	//Close the users file
			} 
			else {	//If the user file doesn't exist
				BufferedWriter writer = new BufferedWriter(new FileWriter("Users.txt"));	//Create the users file
				writer.flush();	//Flush the writer idfk
				writer.close();	//Close the file
			}
		} catch(IOException e) {	//Catch error
				System.out.println("Error Accessing Users @ getUsers()");	//Infrom user of error accessing the users
		} finally {	//After catching error
			return;	//Exit
		}
	}
	public int getEnviro() {	
		//Return environment tax on all items in the cart as an intger
		int enviro = 0;	// enviro tax
		Object obj;	//object to work with
		for (Item item : cartO.allContent()) {	//Iterate through items in users cart 
			obj = findItem(item.sNo);	//Set obj to the item with the serial number of the item in that cart 
			if (obj instanceof Book) {	//Check if the item is a book 
				Book book = (Book) obj;	//If so, set book equal to the book item
				enviro += book.getTax() * ((Book)item).getQuant(); //Add the environment tax on the book to enviro
			}
			else if (obj instanceof CD) {	//Check if the item is a CD
				CD cd = (CD) obj; //If so, set cd equal to the book item
				enviro += cd.getTax() * ((CD)item).getQuant();	// Add the environment tax on the CD to enviro
			}
		}
		return enviro;	//Return the total environment tax
	}

	public int getAllPrices() { 
		//Return the total price of all items in the user's cart as integer
		int total = 0; // total price
		Object obj; //object to work with 
		for (Item item : cartO.allContent()) {	//Iterate through items in users cart 

			obj = findItem(item.sNo);	//Set obj to the item with the serial number of the item in that cart 

			if (obj instanceof Readable) {	//Check if item is a readable item
				Readable readable = (Readable) obj; //If so, set readable equal to the readable item
				total += readable.price * ((Readable)item).getQuant();	//Add the price of the readable items to the total 
			}
			else if (obj instanceof Audio) {	//Check if the item is an audio product item 
				Audio audio = (Audio) obj;	//If so, set audio equal to the audio item
				total += audio.price * ((Audio)item).getQuant();	//Add the price of the item to the the total
			}
			
		}
		return total;	//Return the total cost
	}

	public void subtractQuant(int serial, int quantity) {
		//Set the quantity of the item with given serial number, to it's current quantity minus the given quantity
		if(quantity < 1)	//If not actually subtracting
			return;	//Exit
		for (Readable readable : readables) {	//Iterate through readable items
			if(readable.getSerial() == serial) {	//If the readable item has the given serial number
				int quant = readable.getQuant() - quantity;	//Subtract the given quantity from the current quantity
				readable.setQuant(quant);	//Set the quantity of the readable item to this number
				return;	//Exit 
			}
		}
		for (Audio audio : audioProducts) {	//Iterate through audio product items
			if(audio.getSerial() == serial) {	//If the audio product item has the given serial number
				int quant = audio.getQuant() - quantity;	//Subtract the given quantity from the current quantity
				audio.setQuant(quant);	//Set the quantity of the audio product item to this number
				return;	//Exit
			}
		}
		return;	//Exit
	}

	public void updateQuant() {
		int itemQuant;	//Amount of item in stock
		int cartQuant;	//Amount of item user has in cart
		for (Item item : cartO.allContent()) { //Iterate through items in cart

			for(Readable readable : readablesbuffer) {	//Iterate through readable items in buffer
				if (readable.getSerial() == item.sNo) {	//If the readable item has the given serial number
					if (readable instanceof Book) {	//If the item in the buffer is a book
						itemQuant = readable.getQuant();	//Set the item quantity to the current quantity of the book
						cartQuant = ((Book)item).getQuant();	//Set the cart quantity to the current quantity of the book in user's cart
						readable.setQuant(itemQuant - cartQuant);	//Subtract the amount of the book in cart from the book item's quantity and update it 
					}
					else if (readable instanceof eBook) {	//If the item in the buffer is an ebook	
						itemQuant = readable.getQuant();	//Set the item quantity to the current quantity of the ebook
						cartQuant = ((eBook)item).getQuant();	//Set the cart quantity to the current quantity of the ebook in user's cart
						readable.setQuant(itemQuant - cartQuant);	//Subtract the amount of the ebook in cart from the book item's quantity and update it
					}
				}

			}

			for (Audio audio : audioProductsbuffer) {	//Iterate through audio product items in buffer
				if (audio.getSerial() == item.sNo) {	//If the audio product item has the given serial number
					if (audio instanceof CD) {	//If the item in the buffer is a CD
						itemQuant = audio.getQuant();	//Set the item quantity to the current quantity of the CD
						cartQuant = ((CD)item).getQuant();	//Set the cart quantity to the current quantity of the CD in user's cart
						audio.setQuant(itemQuant - cartQuant);	//Subtract the amount of the CD in cart from the CD items quantity and update it
					}
					else if (audio instanceof MP3) {	//If the item in the buffer is an MP3
						itemQuant = audio.getQuant();	//Set the item quantity to the current quantity of the MP3
						cartQuant = ((MP3)item).getQuant();	//Set the cart quantity to the current quantity of the MP3 in user's cart
						audio.setQuant(itemQuant - cartQuant);	//Subtract the amount of the MP3 in cart from the MP3 items quantity and update it
					}
				}
			}
			
		}
	}

	public void updateFiles(){
		//Update files Books.txt, Ebooks.txt, CD.txt, and MP3.txt, adding info from new items in the readables and audiproducts array to it
		BufferedWriter writera, writerb;	//For writing to files
		try {	//Catch errors
			writera = new BufferedWriter(new FileWriter("Books.txt"));	//Write to Books.txt
			writerb = new BufferedWriter(new FileWriter("EBooks.txt"));	//Write to eBooks.txt
			for (Readable obj : readablesbuffer) {	//Iterate through items in readables buffer array
				if (obj instanceof Book) {	//If the item is a book
					Book item = (Book) obj; //Refer to the book as item
					writera.write(item.getInfo());	//Write the info for the book to file
					writera.newLine();	//Go to next line
				}
				else if (obj instanceof eBook) {	//If the item is an ebook 
					eBook item = (eBook) obj;	//Refer to the ebook as item
					writerb.write(item.getInfo());	//Write the info for the ebook to file
					writerb.newLine();	//Go to next line
				}
			}
			writera.close();	//Close Books.txt
			writerb.close();	//Close eBooks.txt
			writera = new BufferedWriter(new FileWriter("CDs.txt"));	//Write to CDs.txt
			writerb = new BufferedWriter(new FileWriter("MP3.txt"));	//Write to MP3.txt	
			for (Audio obj : audioProductsbuffer) {	//Iterate through items in audio products buffer array
				if (obj instanceof CD) {	//If the item is a CD
					CD item = (CD) obj;	//Refer to the CD as item
					writera.write(item.getInfo());	//Write the info for the CD to file
					writera.newLine();	//Go to next line
				}
				else if (obj instanceof MP3) {	//If the item is a MP3
					MP3 item = (MP3) obj;	//Refer to the MP3 as item
					writerb.write(item.getInfo());	//Write the info for the MP3 to file
					writerb.newLine();	//Go to next line
				}
			}
			writera.close();	//Close CDs.txt
			writerb.close();	//Close MP3.txt 
		} finally {	//Do this always
			return;	//Exit
		}

	}

	public void getConfirm() {
		//Gets the last confirmation number from ItemsBought.txt
		File itemsBought = new File("ItemsBought.txt");	//Read from previous orders file
		if(itemsBought.exists() && !itemsBought.isDirectory() ) {	//Check if previous orders file exists
			try {	// Catch errors
				BufferedReader reader = new BufferedReader(new FileReader("ItemsBought.txt")); //Read from previous orders file
				if(reader.readLine() != null) {	//If the file isn't empty
					String line;	//Line in file
					String temp = "";	//Temprorary string to split
					while ((line = reader.readLine()) != null) {	//Read to the end of the file
						temp = line;	//Set the temp string to the current line
					}
					String[] info = temp.split("\\s+");	//Split the string at + 
					info[0] = info[0].substring(1);	//Get the confirmation ID from the line
					cID = Integer.parseInt(info[0]) + 1;	//Parse the confirmation ID as an integer and set it
				}
				else	// If the file is empty
					cID = 1000;	//Set the confirmation ID to 1000
			} catch (IOException e) {	//Catch errors
				System.out.println("Error Opening ItemsBought.txt @ getConfirm()");	//Inform user of error opening itemsbought.txt
			} finally {	//Do this always
				return;	//Exit
			}
			
		}
		else	//If no previous orders exist
			cID = 1000;	//Set cID to 1000
		return;	//Exit
	}

	public void writeConfirm () {	
		//Write confirmation info to file
		File itemsBought = new File("ItemsBought.txt"); //Write to previous orders file
		BufferedWriter writer;	//To write to file
		try {	//Catch errors
			if(itemsBought.exists() && !itemsBought.isDirectory()) {	//Check if previous orders file exists
				writer = new BufferedWriter(new FileWriter("ItemsBought.txt",true));	//Append to previous orders file
			}
			else {	//If previous orders file does not exist
				writer = new BufferedWriter(new FileWriter("ItemsBought.txt"));	//Create previous orders file and write to it
				writer.write(String.format("%-20s%-30s%-20s","Confirmation ID", "Product Name","Total"));	//Headers for sections
				writer.newLine();	//Go to next line
				writer.flush();	//Flush the writer
			}
			for (Item obj : cartO.allContent()) {	//Iterate through items in user's cart
				if (obj instanceof Readable) {	//If the item is a readable 
					Readable item = (Readable) obj;	//Refer to the readable as item
					writer.write(String.format("%-20s%-30s%-20d","U" + cID + " ", item.getTitle() + " ",total));	//Write the confirmation ID, title of the item, and total cost of the item to file
					writer.newLine();	//Go to next line
					writer.flush();	//Flush the writer
				}
				else if (obj instanceof Audio) {	//if the item is an audio product
					Audio item = (Audio) obj;	//Refer to the audiuo product as item
					writer.write(String.format("%-20s%-30s%-20d","U" + cID + " ", item.getTitle() + " ",total));	//Write the confirmation ID, title of the item, and total cost of the item to file
					writer.newLine();	//Go to next line
					writer.flush();	//Flush the writer
				}
			}
			writer.close();	//Close the file 
				
			return;	//Exit
		} catch (IOException e) { //Catch errors
			System.out.println("Error Writing To ItemsBought.txt @ writeConfirm()"); // Inform the user of an error writing to ItemsBought.txt
		} finally {	//Run after catch
			return;	//Exit
		}
	}

	public void getCart (ShoppingCart cart) {	
		//Adds items stored in cart file for given cart to the current user's cart
		String cartName = cart.getCartname();	//Name of the cart (user_cart.txt)
		File cartFile = new File(cartName);	//Cart file
		BufferedReader reader;	//To read from the file
		String[] info;	//List of info on each item
		Object obj;	//Object to refer to
		String line;	//Line in file
		if (cartFile.exists() && !cartFile.isDirectory()) {	//If the user has a cartfile 
			try {	// Catch errors
				reader = new BufferedReader(new FileReader(cartName));	//Read from cart file
				while((line = reader.readLine()) != null) {	//Read to the end of the file
					info = line.split(",");	//Get info from each line into info array (sNO, title, price, quantity )
					obj = findItem(Integer.parseInt(info[0])); // Find the item with serial number specified on line in file
					if (obj instanceof Book) {	//If the specified item is a book
						Book book = (Book) obj;	//Refer to the item as book
						cart.addItem(book, Integer.parseInt(info[3]));	//Add quantity specified on line in file of specified book to the user's cart
					}
					else if (obj instanceof eBook) {	//If the specified item is a ebook
						eBook ebook = (eBook) obj;	//Refer to the item as ebook
						cart.addItem(ebook, Integer.parseInt(info[3]));	//Add quantity specified on line in file of specified ebook to the user's cart
					}
					else if (obj instanceof CD) {	//If the specified item is a CD
						CD cd = (CD) obj;	//Refer to the item as CD
						cart.addItem(cd, Integer.parseInt(info[3]));	//Add quantity specified on line in file of specified CD to the user's cart
					}
					else if (obj instanceof MP3) {	//If the specified item is a MP3
						MP3 mp3 = (MP3) obj;	//Refer to the item as MP3
						cart.addItem(mp3, Integer.parseInt(info[3]));	//Add quantity specified on line in file of specified MP3 to the user's cart
					}
					else { //If the specified serial number does not exist
						System.out.println("File Tampered With and/or Item Doesn't exist @ getCart()"); //Inform the user of an error with the file
						changeCurrentPage(-10);	//
					}	

				}
			
			} catch (IOException e) {  //Catch errors
				System.out.println("Error When Writing Cart..."); //Inform the user of an error writing to cart
				return;	//Exit
			} finally {	//Do this always
				return;	//Exit
			}
		}
	}

	public void writetoCart() {	
		//Write items in the user's cart to their cart file (user_cart.txt)
		String cartname = "cart_" + currentUser.toLowerCase() + ".txt";	//Get the name of the user's cart
		try {	//Catch errors
			BufferedWriter writer = new BufferedWriter(new FileWriter(cartname));	//Write to the user's cart file
			if(!cartO.getContent().equals(""))	//If the user's cart is nonempty
				writer.write(cartO.getContent());	//Write the contents of the user's cart to file
			writer.close();	//Close the user's cart file
		} catch (IOException e) {	//Catch errors
			System.out.println("Error Writing To Cart Save File");	//Inform the user of an error writing to their cart file
		} finally {	//Do this always
			return;	//Exit
		}
	}

	public void showReadables() {
		//Print a formatted string listing info on all objects in readables array to stdout 
	    for(Readable obj: readables) {	//Iterate through all items in readables arrays
	    	if(obj instanceof Book)	//If the item is a book
				System.out.printf("\n%-5d%-25s%-10s%-10d%-20d%-5s",	//Formatting for item info
								obj.sNo, obj.title, obj.authorName, obj.price, obj.quant, "Book"); //Print the info for the item
			else if (obj instanceof eBook)	//If the item is an ebook
				System.out.printf("\n%-5d%-25s%-10s%-10d%-20d%-5s",	//Formatting for item info
								obj.sNo, obj.title, obj.authorName, obj.price, obj.quant, "eBook");	//Print the info for the item
	    }

	    return;
	}

	public void showAudioProducts() {
		//Print a formatted string listing info on all items in the Audio products array to stdout
	    for(Audio obj: audioProducts) { //Iterate through all items in audiProducts array
	    	if (obj instanceof MP3) //If the item is an MP3
				System.out.printf("\n%-5d%-25s%-10s%-10d%-20d%-5s",	//Formatting for item info
								obj.sNo, obj.title, obj.artistName, obj.price, obj.quant, "MP3");	//Print the info for the item
			else if (obj instanceof CD) //If the item is a CD
				System.out.printf("\n%-5d%-25s%-10s%-10d%-20d%-5s",	//Formatting for item info
								obj.sNo, obj.title, obj.artistName, obj.price, obj.quant, "CD");	//Print the info for the item
	    }
	}

	public String userInput() {	
		//Reads user input from stdin, returns a string containing the input
		String input = "";	//input string 
		try {	//Catch errors
			input = stung.readLine(); //Read from stdin and set the line read to input 
		} catch (IOException e) {	//Catch errors
			System.out.println("Error With Input, Please Try Again"); //Inform user of an error with their input
			return userInput();	//Exit
		} finally {	//Do this always
		 	return input;	//Returns the user's input as a string
		}
	}

	
	public void addUser(String username) {	
		//add currentUser to ArrayList and Users.txt
		File target = new File("Users.txt");	//Users file to write to
		try {	//Catch errors
			BufferedWriter writer = new BufferedWriter(new FileWriter(target,true)); //Append to users file
			users.add(username); //Add currentuser to users array
			writer.newLine();	//Go to next line in file
			writer.write(username);	//Write username to file
			writer.close();	//Close users file
		} catch (IOException e) { //Catch errors 
			System.out.println("Error Writing to Users.txt @ addUsers()"); //Inform user of error writing to users.txt
		}
		finally {	//Do this always
			return;	//Exit
		}
	}

	public Object findItem(int serial) {
		//Returns an item with specified integer serial number
		for (Readable obj : readables) //Iterate through readables array
			if (obj.sNo == serial) 	//If the item is the one we are looking for 
				return obj;	//Return that object 
				

		for (Audio obj : audioProducts) //Iterate through audio products array
			if (obj.sNo == serial)	//If the item is the one we are looking for 
				return obj;	//Return that object

		return null;	//Return nothing if item with given serial number not found
	}

	public int findPrice(int serial) {
		//Return price of item with given integer serial number as an integer
		for (Readable obj : readables) //Iterate through readables array
			if (obj.sNo == serial) 	//If the item is the one we are looking for
				return obj.price;	//Return that object's price
				

		for (Audio obj : audioProducts)	//Iterate through audio products array
			if (obj.sNo == serial)	//If the item is the one we are looking for  
				return obj.price;	//Return that object's price

		return 0; //Return 0 if item with given serial number not found
	}
	
	public boolean checkSNo(int serial) {
		//Returns true if an object with given integer serial number exists, false otherwise
		for (Readable obj : readables) {	//Iterate through readables array	
			if (obj.sNo == serial) {	//If the item is the one we are looking for
				return true; //Return true
			}
		}

		for (Audio obj : audioProducts) {	//Iterate through audio products array 
			if (obj.sNo == serial) {	//If the item is the one we are looking for
				return true; 	//Return true 
			}
		}

		return false; //If no item with given serial number is found, return false
	}

	public void sortBySerialR(ArrayList<Readable> R) {
		//Sorts the given arraylist of readables by serial number, from lowest to highest
		if (R.size() > 0) {	//If the readables array is non-empty
			Collections.sort(R, new Comparator<Readable>() {	//Sort the array using the compare function specified below
				public int compare(Readable r1, Readable r2) {	
					//Returns the difference between the first and second readable's serial number as an integer
					return r1.getSerial() - r2.getSerial();	//Return the difference between the two readable's serial numbers
				}
			} );
		}
	}

	public void sortBySerialA(ArrayList<Audio> A) {
		//Sorts the given arraylist of audio products by serial number, from lowest to highest
		if (A.size() > 0) { //If the audio products array is non-empty
			Collections.sort(A, new Comparator<Audio>() {	//Sort the array using the compare function specified below
				public int compare(Audio a1, Audio a2) {
					//Returns the difference between the first and second audio product's serial number as an integer
					return a1.getSerial() - a2.getSerial();	//Return the difference between the two audio product's serial numbers
				}
			} );
		}
	}

	public void sortByAlphabetR(ArrayList<Readable> R) {
		//Sorts the given arraylist of readables by their titles alphanumerically, a-z
		if (R.size() > 0) {	//If the readables array is non-empty
			Collections.sort(R, new Comparator<Readable>() {	//Sort the array using the compare function specified below
				public int compare(Readable r1, Readable r2) {
					//Returns the lexicographical difference between the first and second readable's titles as an integer
					return r1.getTitle().compareTo(r2.getTitle()); //Return the diffference between the two readable's titles
				}
			} );
		}
	}

	public void sortByAlphabetA(ArrayList<Audio> A) {
		//Sorts the given arraylist of audioproducts by their titles alphanumerically, a-z
		if (A.size() > 0) {	//If the audio products array is non-empty
			Collections.sort(A, new Comparator<Audio>() {	//Sort the array using the compare function specified below
				public int compare(Audio a1, Audio a2) {
					//Returns the lexicographical difference between the first and second audio products's titles as an integer
					return a1.getTitle().compareTo(a2.getTitle());	//Return the diffence between the two audio product's titles
				}
			} );
		}
	}

	public void sortByPriceR(ArrayList<Readable> R) {
		//Sorts the given arraylist of readables by price, from lowest to highest
		if (R.size() > 0) {	//If the readables array is non-empty
			Collections.sort(R, new Comparator<Readable>() {	//Sort the array using the compare function specified below
				public int compare(Readable r1, Readable r2) {	
					//Returns the difference between the first and second readable's prices as an integer
					return r1.price - r2.price; //Returns the difference between the two readable's prices
				}
			} );
		}
	}

	public void sortByPriceA(ArrayList<Audio> A) {
		//Sorts the given arraylist of audio products by price, from lowest to highest
		if (A.size() > 0) {	//If the audio products array is non-empty
			Collections.sort(A, new Comparator<Audio>() {	//Sort the array using the compare function specified below
				public int compare(Audio a1, Audio a2) {
					//Returns the difference between the first and second audio product's price as an integer
					return a1.price - a2.price;	//Return the difference between the two audio product's prices
				}
			} );
		}
	}


}