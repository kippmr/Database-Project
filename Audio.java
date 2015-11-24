/* 
*Names: Yu You, Matthew Kipp, Sean McKay
*MacIDs: youy2, mckaysm, kippmr
*Student Number: 1419572 (Yu), 1423885 (Sean), 1303604 (Matt)
*Description: Parent Class of MP3 and CD
*/
public class Audio extends Item { 																	//Audio is a subclass of item

	protected String title, artistName; 															// Title and artist of the audio item
	protected int price,quant; 																		// Price and quantity of the audio item
	
	public Audio(String sNo, String title, String artistName, String price, String quant) { 		// Instatiation with given serial number, artist name, price and quantity
		this.sNo = Integer.parseInt(sNo); 															// Set the serial number of the audio item
		this.title = title; 																		// Set the title of the audio item
		this.artistName = artistName;																// Set the name of the artist of the audio item
		this.price = Integer.parseInt(price);														// Set the price of the audio item
		this.quant = Integer.parseInt(quant);														// Set the quantity of the audio item
	}

	public String getInfo() {																		// Returns info on the audio item as a comma-separated string
		return sNo + "," + title + "," + artistName + "," + price;									// Concatenate item's serial number, title, artist name and price and return it
	}

	public String getTitle() {																		// Returns the audio item's title as a a string
		return this.title;																			// Return the audio items's title
	}

	public String getArtist() {																		// Returns the audio item's artist name as a a string															
		return this.artistName;																		// Return the audio items's artist name
	}

	public int getSerial() {																		// Returns the audio item's serial number as an integer
		return this.sNo;																			// Return the audio item's serial number
	}

	@Override																						// Override method from Item superclass
	public int getPrice() {																			// Returns the Audio item's price as an int
		return this.price;																			// Return the price of the audio item
	}

	public int getQuant() {																			// Returns the quantity of the audio item in stock as an int
		return quant;																				// Return the quantity of the audio item
	}
	public void setQuant(int quant) { 															// Set the quantity of the CD to a given integer
		this.quant = quant;																		// Set the quantity of the CD 
	}
}
