import java.util.*;	// Additional methods
import java.io.*;	// Working with input and output																															
import java.text.SimpleDateFormat; // Formattting date for the shopping cart


public class ShoppingCart extends User { // Shopping cart for the user 

	protected List<Item> content = new ArrayList<Item>(); // Arraylist containing items in the users cart

	protected String getDate() { // Returns a string containing the date in the form dd/MM/yyyy for outputting to the cart file
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy"); // Set format
		Date date = new Date(); // Get date
		return simpleDate.format(date); // Return formatted date 

    //TODO: safely preserve file to be written incase of accidental termination

    public ShoppingCart(String username) throws IOException{ // Instantiation with given username as a string
    	super(username); // Call users instantiation methhod to set member variables for the shopping cart
    }

    public String getCartname() { // Returns the name for the users shopping cart file
    	return "cart_" + super.getUsername().toLowerCase() + ".txt"; // Return the file name in the form user_cart.txt
    }

	public String getContent() { // Returns info for all the items in the cart as a string
		String items = "";	// String to hold info
		if(content.size() > 0) { // Check if the cart is nonempty
			for (Item obj: content) { // Iterate through all items in the cart
				if (obj instanceof Readable) { // Check if the object is a readable item
					Readable item = (Readable) obj; // Refer to the object as item
					items += item.getSerial() + "," + item.getTitle() + "," + getDate()  + "," +  item.getQuant() + "\n"; // Concatenate the info for the readable item to the string
				}
				else if (obj instanceof Audio) { // Check if the object is an audio item
					Audio item = (Audio) obj; // Refer to the object as item
					items += item.getSerial() + "," + item.getTitle() + "," + getDate()  + "," +  item.getQuant() + "\n"; // Concatenate the info for the audio item to the string
				}
			}
			return items; // Return the info for all the items
		}
		return ""; // If the cart is empty, return the empty string
	}

	public List<Item> allContent() { // Returns all the items in shopping cart as a list of items
		return content; // Return the items in shopping cart
	}
	
	public boolean addItem(Book item, int quantity) { // Adds a given quantity of the given book to the cart, returns true if the book was added, false otherwise
		if (itemPresent(item.getSerial())) { // Checks if some of the given book is already in the cart
			if (quantity <= item.getQuant() && quantity >= 1) { // Checks if the requested quantity is valid
				Book cItem = (Book) searchFor(item.getSerial()); // Find the book already in the cart
				content.remove(cItem); // Remove the existing book from the cart
				cItem.setQuant(cItem.getQuant() + quantity); // Set the quantity to the total quantity 
				content.add(cItem); // Add the total quantity (requested + existing) of the given book to the cart
				return true; // Book was succesfully added to the cart
			}
		}
		else { // If the book is not already in the cart
			if (quantity <= item.getQuant() && quantity >= 1) { // Checks if the requested quantity is valid
				String serial = Integer.toString(item.getSerial()); // Get the serial number of the book
				String iPrice = Integer.toString(item.price); // Get the price of the book
				String iQuant = Integer.toString(quantity); // Get the quantity of the book
				content.add(new Book(serial,item.getTitle(),item.getAuthor(),iPrice,iQuant)); // Add the given quantity of the given book to the cart
				return true; // Book was succesfully added to the cart
			}
			else { // If the quantity is invalid
				return false; // Book was not succesfully added to the cart
			}
		}
		return false; // Book was not succesfully added to the cart, default behaviour

	}

	public boolean addItem(eBook item, int quantity) { // Adds a given quantity of the given ebook to the cart, returns true if the ebook was added, false otherwise
		if (itemPresent(item.getSerial())) { // Checks if some of the given ebook is already in the cart
			if (quantity <= item.getQuant() && quantity >= 1) { // Checks if the requested quantity is valid
				eBook cItem = (eBook) searchFor(item.getSerial()); // Find the ebook already in the cart
				content.remove(cItem); // Remove the existing ebook from the cart
				cItem.setQuant(cItem.getQuant() + quantity); // Set the quantity to the total quantity 
				content.add(cItem); // Add the total quantity (requested + existing) of the given ebook to the cart
				return true; // eBook was succesfully added to the cart
			}
		}
		else { // If the ebook is not already in the cart
			if (quantity <= item.getQuant() && quantity >= 1) { // Checks if the requested quantity is valid
				String serial = Integer.toString(item.getSerial()); // Get the serial number of the ebook
				String iPrice = Integer.toString(item.price); // Get the price of the ebook
				String iQuant = Integer.toString(quantity); // Get the quantity of the ebook
				content.add(new eBook(serial,item.getTitle(),item.getAuthor(),iPrice,iQuant)); // Add the given quantity of the given ebook to the cart
				return true; // eBook was succesfully added to the cart
			}
			else { // If the quantity is invalid
				return false; // eBook was not succesfully added to the cart
			}
		}
		return false; // eBook was not succesfully added to the cart, default behaviour
	}

	public boolean addItem(MP3 item, int quantity) { // Adds a given quantity of the given MP3 to the cart, returns true if the MP3 was added, false otherwise
		if (itemPresent(item.getSerial())) { // Checks if some of the given MP3 is already in the cart
			if (quantity <= item.getQuant() && quantity >= 1) { // Checks if the requested quantity is valid
				MP3 cItem = (MP3) searchFor(item.getSerial()); // Find the MP3 already in the cart
				content.remove(cItem); // Remove the existing MP3 from the cart
				cItem.setQuant(cItem.getQuant() + quantity); // Set the quantity to the total quantity 
				content.add(cItem); // Add the total quantity (requested + existing) of the given MP3 to the cart
				return true; // MP3 was succesfully added to the cart
			}
		}
		else { // If the MP3 is not already in the cart
			if (quantity <= item.getQuant() && quantity >= 1) { // Checks if the requested quantity is valid
				String serial = Integer.toString(item.getSerial()); // Get the serial number of the MP3
				String iPrice = Integer.toString(item.price); // Get the price of the MP3
				String iQuant = Integer.toString(quantity); // Get the quantity of the MP3
				content.add(new MP3(serial,item.getTitle(),item.getArtist(),iPrice,iQuant)); // Add the given quantity of the given MP3 to the cart
				return true; // MP3 was succesfully added to the cart
			}
			else { // If the quantity is invalid
				return false; // MP3 was not succesfully added to the cart
			}
		}
		return false; // MP3 was not succesfully added to the cart, default behaviour
	}

	public boolean addItem(CD item, int quantity) { // Adds a given quantity of the given CD to the cart, returns true if the CD was added, false otherwise
		if (itemPresent(item.getSerial())) { // Checks if some of the given CD is already in the cart
			if (quantity <= item.getQuant() && quantity >= 1) { // Checks if the requested quantity is valid
				CD cItem = (CD) searchFor(item.getSerial()); // Find the CD already in the cart
				content.remove(cItem); // Remove the existing CD from the cart
				cItem.setQuant(cItem.getQuant() + quantity); // Set the quantity to the total quantity 
				content.add(cItem); // Add the total quantity (requested + existing) of the given CD to the cart
				return true; // CD was succesfully added to the cart
			}
		}
		else { // If the CD is not already in the cart
			if (quantity <= item.getQuant() && quantity >= 1) { // Checks if the requested quantity is valid
				String serial = Integer.toString(item.getSerial()); // Get the serial number of the CD
				String iPrice = Integer.toString(item.price); // Get the price of the CD
				String iQuant = Integer.toString(quantity); // Get the quantity of the CD
				content.add(new CD(serial,item.getTitle(),item.getArtist(),iPrice,iQuant)); // Add the given quantity of the given CD to the cart
				return true; // CD was succesfully added to the cart
			}
			else { // If the quantity is invalid
				return false; // CD was not succesfully added to the cart
			}
		}
		return false; // CD was not succesfully added to the cart, default behaviour
	}

	public Item searchFor(int serial) { // Returns the item with the given serial number from the users cart, if no item is found, returns null
		
		for (Item item: content) // Iterate through items in cart
			if (item.sNo == serial)  // Check if the item's serial number is the one we're looking for
				return item; // If so, return the item with that serial number
		return null; // if no item is found, return nothing
	}

	public boolean itemPresent(int serial) { // Returns true if an item with the given serial number exists in the cart, false otherwise
		for (Item item : content)  // Iterate though items in cart
			if (item.sNo == serial)  // Checks if the item's serial number is the one we're looking for
				return true; // If so, return true
		return false; // If no item with that serial number exists, return false
	}

	
	public void clearCart() { // Clear the shopping cart
		this.content = new ArrayList<Item>(); // Reset the cart to an empty arraylist
	}
	
	

}