public class eBook extends Readable { 																//Book is a subclass of Readable

	public eBook(String sNo, String title, String authorName, String price, String quant){			//Instantition with given serial number, author name, price, and quantity
		super(sNo, title, authorName, price, quant);												//Call the readable instantiation method to set the instance variables for the book
	}

	@Override																						//Overrides getPrice method in Readable
	public int getPrice() {																			//Returns the price of the book as an integer
		return super.getPrice();																	//Return the price of the ebook 
	}

	@Override																						//Overrides getInfo method in Readable
	public String getInfo() {																		//Returns info on the ebook as a comma-separated string
		return super.getInfo() + "," + quant;														//Calls readable getInfo method, concantenates quantity of the ebook to the end
	}

	public int getQuant() {																			//Returns the quantity of the ebook as an integer
		return quant;																				//Return the quantity of the ebook
	}
	
	public void setQuant(int quant) {																//Set the quantity of the ebook to a given integer	
		this.quant = quant;																			//Set the quantity of the ebook 
	}

	}
