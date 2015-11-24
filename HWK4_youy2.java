/* 
*Names: Yu You, Matthew Kipp, Sean McKay
*MacIDs: youy2, mckaysm, kippmr
*Student Number: 1419572 (Yu), 1423885 (Sean), 1303604 (Matt)
*Description: An e-commerce application where a user can register and sign in. 
Once signed in they are able to add to cart and purchase books, ebooks, CDs and MP3s. They can also view 
details of past transactions on their account, there also exists a admin function with ADMIN login and ADMIN pw
by default, changes to the password are permanently stored.
*/
class HWK4_youy2 { //Main class 


	public static void main(String[] args) { 
	//Main method, starts ui mainloop
		try { // Catch errors
			UserInterface ui = new UserInterface(); // Create a new userinterface object
			ui.mainLoop();	//Run the main loop of ui
		}
		catch (Exception e) {	//Catch errors
			e.printStackTrace();	//Print the error to stdout
		}
	}
}