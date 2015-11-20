import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;


public class ShoppingCart extends User {

	protected List<String> content = new ArrayList<String>();
    protected File cart = null;
    protected FileWriter writer = null;
    protected BufferedWriter buffwriter = null;
	protected BufferedReader buffreader = null;

	protected String getDate() {
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyyy");
		Date date = new Date();
		return simpleDate.format(date);
	}

    //TODO: safely preserve file to be written incase of accidental termination

    public ShoppingCart(String username) throws IOException{
    	super(username);
    	String cartname = "cart_" + username;
    	cart = new File(cartname);
    	
    	if (cart.exists() && !cart.isDirectory()) 
    		readContentFile(cart);
    	
    	writer = new FileWriter(cart);
    	buffwriter = new BufferedWriter(writer);	
    }

	public String getContent() {
		String items = ""; 
		for (String item: content) {
			items += item + "\n";
		}
		return items;
	}
	
	public boolean addItem(Book item, int quantity) {
		if (quantity <= item.getQuant()) {
			content.add(item.getSerial() + "," + item.getTitle() + "," + getDate() + "," + item.getQuant());
			return true;
		}
		else {
			return false;
		}
	}

	public boolean addItem(eBook item, int quantity) {
		if (quantity <= item.getQuant()) {
			content.add(item.getSerial() + "," + item.getTitle() + "," + getDate() + "," + item.getQuant());
			return true;
		}
		else {
			return false;
		}
	}

	public boolean addItem(MP3 item, int quantity) {
		if (quantity <= item.getQuant()) {
			content.add(item.getSerial() + "," + item.getTitle() + "," + getDate() + "," + item.getQuant());
			return true;
		}
		else {
			return false;
		}
	}

	public boolean addItem(CD item, int quantity) {
		if (quantity <= item.getQuant()) {
			content.add(item.getSerial() + "," + item.getTitle() + "," + getDate() + "," + item.getQuant());
			return true;
		}
		else {
			return false;
		}
	}

	public void readContentFile(File cart) throws IOException{
		buffreader = new BufferedReader(new FileReader(cart));
		String line;
		while ((line = buffreader.readLine()) != null) {
        	content.add(line);
    	}
	}

	//Clear the shopping cart
	public void clearCart() {
		this.content = new ArrayList<String>();
	}
	
	

}