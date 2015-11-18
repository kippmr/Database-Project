//Comma-Separated-File: sNo, title, artistName, price, quantity
public class Audio extends Item {

	protected String title, artistName;
	protected int price;
	public Audio(String sNo, String title, String artistName, String price) {
		this.sNo = Integer.parseInt(sNo);
		this.title = title;
		this.artistName = artistName;
		this.price;
	}

	public String getInfo() {
		return sNo + "," + title + "," + artistName + "," + price;
	}

	public String[] getInfoArray() { //supposed to be a string not string array? is this OK
		return new String[]{Integer.toString(sNo), title, artistName, Integer.toString(price)};
	}

	@Override
	public int getPrice() {
		return this.price;
	}
}
