//Comma-Separated-File: sNo, title, artistName, price, quantity
public class Audio extends Item {

	protected String title, artistName;
	protected int price;
	
	public Audio(String sNo, String title, String artistName, String price) {
		this.sNo = Integer.parseInt(sNo);
		this.title = title;
		this.artistName = artistName;
		this.price = Integer.parseInt(price);
	}

	public String getInfo() {
		return sNo + "," + title + "," + artistName + "," + price;
	}

	public String getTitle() {
		return this.title;
	}

	public int getSerial() {
		return this.sNo;
	}

	@Override
	public int getPrice() {
		return this.price;
	}
}
