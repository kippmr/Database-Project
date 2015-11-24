public abstract class Item {					// Abstract class implemented by readable and audio
	
	protected int price;						// Price of the item
	protected int sNo;							// Serial number of the item
	
	public abstract int getPrice();				// Return the price of the item as an int, implemented in subclasses
	public abstract String getInfo();			// Return info on the item as a string, implemented in subclasses
	
}