import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;


public class ShoppingCart extends User {

	protected List<Item> content = new ArrayList<Item>();

	protected String getDate() {
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return simpleDate.format(date);
	}

    //TODO: safely preserve file to be written incase of accidental termination

    public ShoppingCart(String username) throws IOException{
    	super(username);
    }

    public String getCartname() {
    	return "cart_" + super.getUsername() + ".txt";
    }

	public String getContent() {
		String items = "";
		if(content.size() > 0) {
			for (Item obj: content) {
				if (obj instanceof Readable) {
					Readable item = (Readable) obj;
					items += item.getSerial() + "," + item.getTitle() + "," + getDate()  + "," +  item.getQuant() + "\n";
				}
				else if (obj instanceof Audio) {
					Audio item = (Audio) obj;
					items += item.getSerial() + "," + item.getTitle() + "," + getDate()  + "," +  item.getQuant() + "\n";
				}
			}
			return items;
		}
		else
			return "Cart is Empty";
	}

	public List<Item> allContent() {
		return content;
	}
	
	public boolean addItem(Book item, int quantity) {
		if (itemPresent(item.getSerial())) {
			if (quantity <= item.getQuant() && quantity >= 1) {
				Book cItem = (Book) searchFor(item.getSerial());
				content.remove(cItem);
				cItem.setQuant(cItem.getQuant() + quantity);
				content.add(cItem);
				return true;
			}
		}
		else {
			if (quantity <= item.getQuant() && quantity >= 1) {
				String serial = Integer.toString(item.getSerial());
				String iPrice = Integer.toString(item.price);
				String iQuant = Integer.toString(quantity);
				content.add(new Book(serial,item.getTitle(),item.getAuthor(),iPrice,iQuant));
				return true;
			}
			else {
				return false;
			}
		}
		return false;

	}

	public boolean addItem(eBook item, int quantity) {
		if (itemPresent(item.getSerial())) {
			if (quantity <= item.getQuant() && quantity >= 1) {
				eBook cItem = (eBook) searchFor(item.getSerial());
				content.remove(cItem);
				cItem.setQuant(cItem.getQuant() + quantity);
				content.add(cItem);
				return true;
			}
		}
		else {
			if (quantity <= item.getQuant() && quantity >= 1) {
				String serial = Integer.toString(item.getSerial());
				String iPrice = Integer.toString(item.price);
				String iQuant = Integer.toString(quantity);
				content.add(new eBook(serial,item.getTitle(),item.getAuthor(),iPrice,iQuant));
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}

	public boolean addItem(MP3 item, int quantity) {
		if (itemPresent(item.getSerial())) {
			if (quantity <= item.getQuant() && quantity >= 1) {
				MP3 cItem = (MP3) searchFor(item.getSerial());
				content.remove(cItem);
				cItem.setQuant(cItem.getQuant() + quantity);
				content.add(cItem);
				return true;
			}
		}
		else {
			if (quantity <= item.getQuant() && quantity >= 1) {
				String serial = Integer.toString(item.getSerial());
				String iPrice = Integer.toString(item.price);
				String iQuant = Integer.toString(quantity);
				content.add(new MP3(serial,item.getTitle(),item.getArtist(),iPrice,iQuant));
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}

	public boolean addItem(CD item, int quantity) {
		if (itemPresent(item.getSerial())) {
			if (quantity <= item.getQuant() && quantity >= 1) {
				CD cItem = (CD) searchFor(item.getSerial());
				content.remove(cItem);
				cItem.setQuant(cItem.getQuant() + quantity);
				content.add(cItem);
				return true;
			}
		}
		else {
			if (quantity <= item.getQuant() && quantity >= 1) {
				String serial = Integer.toString(item.getSerial());
				String iPrice = Integer.toString(item.price);
				String iQuant = Integer.toString(quantity);
				content.add(new CD(serial,item.getTitle(),item.getArtist(),iPrice,iQuant));
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}

	public Item searchFor(int serial) {
		
		for (Item item: content) 
			if (item.sNo == serial) 
				return item;
		return null;
	}

	public boolean itemPresent(int serial) {
		for (Item item : content) 
			if (item.sNo == serial) 
				return true;
		return false;
	}

	//Clear the shopping cart
	public void clearCart() {
		this.content = new ArrayList<Item>();
	}
	
	

}