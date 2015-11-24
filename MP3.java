public class MP3 extends Audio {																	// CD is a subclass of Audio
	

	public MP3(String sNo, String title, String artistName, String price, String quant){			// Instantiation with given serial number, title, artist name, price, and quantity
		super(sNo,title,artistName,price,quant);													// Call the Audio instantiation method to set the member variables for the CD
	}
	
	public void setQuant(int quant) { 																// Set the quantity of the MP3 to a given integer
		this.quant = quant;																			// Set the quantity of the MP3
	}

	public int getQuant() {																			// Returns the quantity of  the MP3 as an integer
		return quant;																				// Return the quantity of the MP3
	}

	@Override																						// Overrides method in Audio
	public int getPrice() {																			// Returns the price of the MP3
		return super.getPrice(); 																	// Return the price of the MP3
	}

	@Override 																						// Overrides getInfo method in Audio
	public String getInfo() {																		// Returns info on the MP3 as a comma-separated string
		return super.getInfo() + "," + quant;														// Calls Audio getInfo method, concantenates quantity of the MP3 to the end
	}

}