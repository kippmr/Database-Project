import java.util.Date
import java.text.SimpleDateFormat
import java.io*

public class ShoppingCart extends User {
	
	protected String getDate() {
		DateFormat d = new SimpleDateFormat("dd/MM/yyyyy")
		Date date = new Date();
		return d.format(date);
	}
    protected List<Item> contents = new ArrayList<Item>();
    protected File cart 
    
    
    //Creates the shoppingcart for the user
    public ShoppingCart(String uName) {
    	super(uName);
    	this.contents = [];
    	cart = new File("cart_" + uName)
    	PrintWriter pw = new PrintWriter(cart, true);
    }
    
    
    /** Returns a string consisting of the info of each item in contents
     * separated by newline characters */
	public String getContent() {
		String items = "" 
		for (Item o: contents) {
			items += o.getInfoArray[0] + " " + o.getInfoArray[1] + " " + getDate() + " " + Integer.tostring(o.getQuant);
		}
		return items;
	}
	
	/** Returns an arraylist of items **/
	public List<Item> getItems() {
		return contents;
	}
	
	/** Takes an object of a subclass of type Item and integer quantity as input, 
	 * checks if the quantity is less than or equal
	 * to the amount available, and if so, creates an new object of the same type, with the
	 * specified quantity, and adds it to contents. Updates the quantity of the original object.
	 * If the quantity is too large, prints "not enough in stock" */
	public void addItem(Item o, int quantity ) { 
		if (quantity <= o.getQuant()) {
			info = o.getInfoArray();
			if (o instanceof Readable) {
				if (o instanceof Book) {
					Book p = new Book(info[0], info[1], info[2], quantity, info[3]);
				} else if (o instanceof eBook) {
					eBook p = new eBook(info[0], info[1], info[2], quantity, info[3]);
				}
			} else if (o instanceof Audio) {
				if (o instanceof CD) {
					CD p = new CD(info[0], info[1], info[2], quantity, info[3]);
				} else if (o instanceof MP3) {
					MP3 p = new MP3(info[0], info[1], info[2], quantity, info[3]);
				}
			}
			this.contents.add(p);
			pw.println(info[0] + " " + info[1] + " " + getDate() + " " +  Integer.tostring(quantity)
			
			
		} else {
			System.out.println("Not enough in stock");
			
		}
	}
	
	//Clear the shopping cart
	public void clearCart() {
		this.contents = [];
	}
	
	

}