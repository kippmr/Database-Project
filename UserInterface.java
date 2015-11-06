import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class UserInterface{
	private int choose, currentPage, quantity; //Option chosen for switching pages, page number, quantity
	private String file, user; //current file name, current user

	private Scanner text;
	private Scanner num;

	public UserInterface()
	{
		textIn = new Scanner(System.in);
		numIn = new Scanner(System.in);
		file = "Users.txt";
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
			} else { 
				p1();
			}
		}
	}

	private void p1() {
		//**Page No. 1! Where a user either (1) signs in or (2) signs up**//
		do {
			System.out.println("1.Sign in\n2.Sign up\n\nChoose your Option: ");
			choose = numIn.nextInt();
			if (choose == 1) {
				System.out.println("\nEnter your username: ");
				user = text.next();
				
				if (checkUser(user)) {
					currentPage = 3; //
				} else {
					currentPage = 4; //
				}
			}
			else if (choose == 2) {
				currentPage = 2; //Sign-up page
			}
		} while (choose != 1 || choose != 2); //User needs to choose option 1 or 2!
	}

	private void p2() {
		//**Page No. 2! User types in a Username and check for validity**//
		System.out.println("\nChoose your username: ");
		user = text.next();

		//TODO: check for already existing username

		if (addUser(user)) {
			System.out.println("\nUsername successfully added.");
			currentPage = 1;
		} else {
			System.out.println("\nUsername not added");
		}
	}

	private void p3() {
		//**Page No. 3! A friendly greeting**//
		currentPage = 5;
		System.out.println("\nHello Mr. " + user);
	}

	private void p4() {
		//**Page No. 4! Seems like you didn't make the cut**//
		currentPage = 1;
		System.out.println("\nNo Access\n");
	}

	private void p5() {
		//**Page No. 5! User can (1) view items (2) view cart or (3) sign out**//
		do {
			System.out.println("\n1.View Items By Catagory\n2.View Shopping Cart\n3.Sign Out\n\nChoose your option: ");
			choose = num.nextInt();

			if (choose == 1) {
				currentPage = 6;
			} else if (choose == 2) {
				currentPage = 7;
			} else if (choose == 3) {
				currentPage = 1;
			}
		} while (choose !=1 || choose !=2 || choose !=3);
	}

	private void p6() {
		//**Page No. 6! User can (1) view readables (2) view audio or (-1) return**//
		do {
			System.out.println("1.Readables\n2.Audio\n\nChoose your option: ");
			choose = num.nextInt();
			System.out.println("\n\nPress -1 to return to previous menu");

			if (choose==1) {
				currentPage = 8;
			} else if (choose == 2) {
				currentPage = 9;
			} else if (choose == -1) {
				currentPage = 5;
			}
		} while(choose != (-1) || choose != 1 || choose != 2);
	}

	private void p7() {
		
	}

	private void p8() {
		
	}

	private void p9() {
		
	}

	private void p10() {
		
	}





	//### HELPER FUINCTIONS ###\\
	public int getCurrentPage() {
		return currentPage;
	}

	public int changeCurrentPage(int page) {
		currentPage = page;
		return page;
	}

	//public void getReadables();
	//public void showReadables();
	//public void showAudioProducts();

	private boolean addUser(String userName) {
		try {
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.newLine();
			bw.write(user);
			bw.close();

			return true;
		} catch(IOException e) {
			System.out.println("Error to " + fileName);
			return false;
		}
		return false;
	}
}