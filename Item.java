/* 
*Names: Yu You, Matthew Kipp, Sean McKay
*MacIDs: youy2, mckaysm, kippmr
*Student Number: 1419572 (Yu), 1423885 (Sean), 1303604 (Matt)
*Description: Parent Class of All objects that be added
to shoppingcart
*/

public abstract class Item {					// Abstract class implemented by readable and audio
	
	protected int price;						// Price of the item
	protected int sNo;							// Serial number of the item
	
	public abstract int getPrice();				// Return the price of the item as an int, implemented in subclasses
	public abstract String getInfo();			// Return info on the item as a string, implemented in subclasses
	
}