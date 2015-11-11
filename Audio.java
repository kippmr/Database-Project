//Comma-Separated-File: sNo, title, artistName, price, quantity
public class Audio extends Item {

	protected String title, artistName;

	public Audio(String sNo, String title, String artistName) {
		this.sNo = Integer.parseInt(sNo);
		this.title = title;
		this.artistName = artistName;
	}

	public String getInfo() {
		return sNo + "," + title + "," artistName;
	}

	public String[] getInfoArray() { //supposed to be a string not string array? is this OK
		return new String[]{Integer.toString(sNo), title, artistName};
	}

	@Override
	public int getPrice() {
		return this.price;
	}
}
