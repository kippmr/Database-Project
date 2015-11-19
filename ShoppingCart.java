import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

public class ShoppingCart extends User {

	protected List<Book> books = new ArrayList<Book>();
	protected List<eBook> ebooks = new ArrayList<eBook>();
	protected List<MP3> mp3s = new ArrayList<MP3>();
	protected List<CD> cds = new ArrayList<CD>();

	protected List<Object> content = new ArrayList<Object>();
    protected File cart;
    PrintWriter pw;
	
	protected String getDate() {
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyyy");
		Date date = new Date();
		return d.format(date);
	}

    
    
    //Creates the shoppingcart for the user
    public ShoppingCart(String uName) {
    	super(uName);
    	cart = new File("cart_" + uName);
    	PrintWriter pw = new PrintWriter(cart);
    }
    
    
    /** Returns a string consisting of the info of each item in contents
     * separated by newline characters */
	/*public String getContent() {
		String items = ""; 
		for (Book item: books) {
			items += item.getInfoArray()[0] + " " + item.getInfoArray()[1] + " " + getDate() + " " + Integer.toString(item.getQuant());
		}
		for (eBook item: ebooks) {
			items += item.getInfoArray()[0] + " " + item.getInfoArray()[1] + " " + getDate() + " " + Integer.toString(item.getQuant());
		}
		for (MP3 item: mp3s) {
			items += item.getInfoArray()[0] + " " + item.getInfoArray()[1] + " " + getDate() + " " + Integer.toString(item.getQuant());
		}
		for (CD item: cds) {
			items += item.getInfoArray()[0] + " " + item.getInfoArray()[1] + " " + getDate() + " " + Integer.toString(item.getQuant());
		}
		return items;
	}*/

	public String getContent() {
		String items = ""; 
		for (Object item: content) {
			items += item.getInfoArray()[0] + " " + item.getInfoArray()[1] + " " + getDate() + " " + Integer.toString(item.getQuant());
		}
		return items;
	}
	/** Returns an arraylist of items **/
	//public List<Item> getItems() {
	//	return contents;
	//}
	
	/** Takes an object of a subclass of type Item and integer quantity as input, 
	 *  checks if the quantity is less than or equal
	 *  to the amount available, and if so, creates an new object of the same type, with the
	 *  specified quantity, and adds it to contents. Updates the quantity of the original object.
	 *  If the quantity is too large, prints "not enough in stock" 
	 */

	
	public void addItem(Book item, int quantity) {
		if (quantity <= item.getQuant()) {
			String[] info = item.getInfoArray();
			Book book = new Book(info[0], info[1], info[2], Integer.toString(quantity), info[3]);
			books.add(book);
			pw.println(info[0] + " " + info[1] + " " + getDate() + " " +  Integer.toString(quantity));
		}
		else {
			System.out.println("Not enough in stock");
		}
	}

	public void addItem(eBook item, int quantity) {
		if (quantity <= item.getQuant()) {
			String[] info = item.getInfoArray();
			eBook ebook = new eBook(info[0], info[1], info[2], Integer.toString(quantity), info[3]);
			ebooks.add(ebook);
			pw.println(info[0] + " " + info[1] + " " + getDate() + " " +  Integer.toString(quantity));
		}
		else {
			System.out.println("Not enough in stock");
		}
	}

	public void addItem(MP3 item, int quantity) {
		if (quantity <= item.getQuant()) {
			String[] info = item.getInfoArray();
			MP3 mp3 = new MP3(info[0], info[1], info[2], Integer.toString(quantity), info[3]);
			mp3s.add(mp3);
			pw.println(info[0] + " " + info[1] + " " + getDate() + " " +  Integer.toString(quantity));
		}
		else {
			System.out.println("Not enough in stock");
		}
	}

	public void addItem(CD item, int quantity) {
		if (quantity <= item.getQuant()) {
			String[] info = item.getInfoArray();
			CD cd = new CD(info[0], info[1], info[2], Integer.toString(quantity), info[3]);
			cds.add(cd);
			pw.println(info[0] + " " + info[1] + " " + getDate() + " " +  Integer.toString(quantity));
		}
		else {
			System.out.println("Not enough in stock");
		}
	}



	//Clear the shopping cart
	public void clearCart() {
		//this.contents = []; // need different method to reset contents, contents is not an array, rather an ArrayList
	}
	
	

}