/* 
*Names: Yu You, Matthew Kipp, Sean McKay
*MacIDs: youy2, mckaysm, kippmr
*Student Number: 1419572 (Yu), 1423885 (Sean), 1303604 (Matt)
*Description: Parent class of book and ebook
*/

//Comma-Separated-File: sNo, title, authorName, price, quantity
public class Readable extends Item {																	// Readable is a subclass of item

	protected String title, authorName;																	// Title and author of the readable item													
	protected int price, quant;																			// Price and quantity of the readable item
	
	public Readable(String sNo, String title, String authorName, String price, String quant){			// Instatiation with given serial number, author name, price and quantity
		this.sNo = Integer.parseInt(sNo);																// Set the serial number of the readable item
		this.title = title;																				// Set the title of the readable item
		this.authorName = authorName;																	// Set the name of the author of the readable item
		this.price = Integer.parseInt(price);															// Set the price of the readable item
		this.quant = Integer.parseInt(quant);															// Set the quantity of the readable item
	}

	@Override																							// Override method from Item superclass
	public int getPrice() {																				// Returns the Readable item's price as an int
		return this.price;																				// Return the price of the audio item
	}

    public String getInfo() {																			// Returns info on the readable item as a comma-separated string
        return sNo + "," + title + "," + authorName + "," + price;										// Concatenate item's serial number, title, artist name and price and return it
    }

    public String getTitle() {																			// Returns the readable item's title as a a string
		return this.title;																				// Return the readable items's title
	}																				

	public int getSerial() {																			// Returns the readable item's serial number as an integer
		return this.sNo;																				// Return the readable item's serial number
	}

	public String getAuthor() {																			// Returns the readable item's author name as a a string
		return this.authorName;																			// Return the readable items's author name
	}

	public int getQuant() {																				// Returns the quantity of the readable item in stock as an int
		return quant;																					// Return the quantity of the readable item
	} 
	public void setQuant(int quant) { 															// Set the quantity of the CD to a given integer
		this.quant = quant;																		// Set the quantity of the CD 
	}

}