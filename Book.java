public class Book extends Readable { 																// Book is a subclass of Readable
	
	public Book(String sNo, String title, String authorName, String price, String quant){			// Instantition with given serial number, author name, price, and quantity
		super(sNo,title,authorName,price,quant);													// Call the readable instantiation method to set the instance variables for the book
	}

	@Override																						// Overrides getPrice method in Readable
	public int getPrice() {																			// Returns the price of the book as an integer, including the tax
		return (int)Math.round((double)super.getPrice() * 1.02);									// Return the price of the book including tax
	}

	public int getTax() {																			// Returns the tax on the book as an integer
		return (int) ((double)super.getPrice() * 0.02);												// Return the tax on the book
	}

	@Override																						// Overrides getInfo method in Readable
	public String getInfo() {																		// Returns info on the book as a comma-separated string
		return super.getInfo() + "," + quant; 														// Calls readable getInfo method, concantenates quantity of the book to the end
	}

	public int getQuant() {																			// Returns the quantity of the book as a string
		return quant;																				// Return the quantity of the book
	}
	public int setQuant(int quant) { 																// Set the quantity of the book to a given integer															
		return (this.quant = quant);																// Set the quantity of the book 
	}


}
