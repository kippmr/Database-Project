//Comma-Separated-File: sNo, title, artistName, price, quantity
public class Audio extends Item {

	protected String title, artistName, quantity, type;

	public Audio(String sNo, String title, String artistName, String price, String quantity, String type) {
		
		this.sNo = Integer.parseInt(sNo);
		this.title = title;
		this.artistName = artistName;
		this.price = Integer.parseInt(price);
		this.quantity = Integer.parseInt(quantity);
		this.type = type;
	}

	public String[] getInfo() { //supposed to be a string not string array? is this OK
		return new String[]{sNo, title, artistName, type};
	}

	@Override
	public int getPrice() {
		return this.price;
	}
}
