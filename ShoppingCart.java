import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

public class ShoppingCart extends User {

	protected List<Object> content = new ArrayList<Object>();
    protected File cart = null;
    protected PrintWriter pw = null;
	
	protected String getDate() {
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyyy");
		Date date = new Date();
		return d.format(date);
	}

    
    //Creates the shoppingcart for the user
    public ShoppingCart(String uName) {
    	super(uName);
    	cart = new File("cart_" + uName);
    	if (cart.exists() && !cart.isDirectory()) {
    		pw = new PrintWriter(new FileOutputStream(cart,true));
    	}
    	else {
    		pw = new PrintWriter(cart);
    	}
    }

	public String getContent() {
		String items = ""; 
		for (Object obj: content) {
			if(obj instanceof Book) {
				Book item = (Book) obj;
				items += item.getSerial() + "," + item.getTitle() + "," + getDate() + "," + item.getQuant() + "\n";
			}
			else if (obj instanceof eBook) {
				eBook item = (eBook) obj;
				items += item.getSerial() + "," + item.getTitle() + "," + getDate() + "," + item.getQuant() + "\n";
			}
			else if (obj instanceof MP3) {
				MP3 item = (MP3) obj;
				items += item.getSerial() + "," + item.getTitle() + "," + getDate() + "," + item.getQuant() + "\n";
			}
			else if (obj instanceof CD) {
				CD item = (CD) obj;
				items += item.getSerial() + "," + item.getTitle() + "," + getDate() + "," + item.getQuant() + "\n";
			}
			else {
				System.out.println("incompatible object added");
				return "";
			}
		}
		return items;
	}
	
	public void addItem(Book item, int quantity) {
		if (quantity <= item.getQuant()) {
			content.add(item);
			pw.println(item.getSerial() + "," + item.getTitle() + "," + getDate() + "," + item.getQuant());
			pw.flush();
		}
		else {
			System.out.println("Not enough in stock");
		}
	}

	public void addItem(eBook item, int quantity) {
		if (quantity <= item.getQuant()) {
			content.add(item);
			pw.println(item.getSerial() + "," + item.getTitle() + "," + getDate() + "," + item.getQuant());
			pw.flush();
		}
		else {
			System.out.println("Not enough in stock");
		}
	}

	public void addItem(MP3 item, int quantity) {
		if (quantity <= item.getQuant()) {
			content.add(item);
			pw.println(item.getSerial() + "," + item.getTitle() + "," + getDate() + "," + item.getQuant());
			pw.flush();
		}
		else {
			System.out.println("Not enough in stock");
		}
	}

	public void addItem(CD item, int quantity) {
		if (quantity <= item.getQuant()) {
			content.add(item);
			pw.println(item.getSerial() + "," + item.getTitle() + "," + getDate() + "," + item.getQuant());
			pw.flush();
		}
		else {
			System.out.println("Not enough in stock");
		}
	}

	//Clear the shopping cart
	public void clearCart() {
		this.content = new List<Object>();
	}
	
	

}