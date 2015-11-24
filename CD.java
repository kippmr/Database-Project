public class CD extends Audio {																	// CD is a subclass of Audio
			

	public CD(String sNo, String title, String artistName, String price, String quant){			// Instantiation with given serial number, title, artist name, price, and quantity	
		super(sNo,title,artistName,price,quant);												// Call the Audio instantiation method to set the member variables for the CD
	}
	
	@Override																					// Overrides getPrice method in Audio
	public int getPrice() {																		// Returns the price of the CD as an integer, including the tax
		return (int)Math.round((double)super.getPrice() * 1.02); 								// Return the price of the CD including tax
	}

	public int getTax() {																		// Returns the tax on the book as an integer
		return (int) ((double)super.getPrice() * 0.02);											// Return the tax on the book
	}

	@Override 																					// Overrides getInfo method in Audio
	public String getInfo() {																	// Returns info on the CD as a comma-separated string
		return super.getInfo() + "," + quant;													// Calls readable getInfo method, concantenates quantity of the CD to the end
	}

	public void setQuant(int quant) { 															// Set the quantity of the CD to a given integer
		this.quant = quant;																		// Set the quantity of the CD 
	}

	public int getQuant() {																		// Returns the quantity of  the CD as an integer
		return quant;																			// Return the quantity of the CD
	}


}
