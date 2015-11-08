//Comma-Separated-File: sNo, title, artistName, price, quantity
public class Audio extends Item {

	protected String title, artistName;

	public Audio(int sNo, String title, String artistName, int quantity, int price) {
		
		this.sNo = sNo;
		this.title = title;
		this.artistName = artistName;
		this.quantity = quantity;
		this.price = price;
	}

	public String getInfo() { //supposed to be a string not string array? is this OK
		return sNo + ", " + title + ", " + artistName;
	}

	@Override
	public int getPrice() {
		return this.price;
	}
	
	public String getTitle() { //Need this for shoppingcart
		return this.title;
	}
	
	public String getArtistName() { //Need this for shoppingcart
		return this.artistName;
	}
}
