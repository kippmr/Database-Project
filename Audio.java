//Comma-Separated-File: sNo, title, artistName, price, quantity
public class Audio extends Item {

	protected String title, artistName;
	protected int price,quant;
	
	public Audio(String sNo, String title, String artistName, String price, String quant) {
		this.sNo = Integer.parseInt(sNo);
		this.title = title;
		this.artistName = artistName;
		this.price = Integer.parseInt(price);
		this.quant = Integer.parseInt(quant);
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

	public int getQuant() {
		return quant;
	}
	public int setQuant(int quant) { //however many are bought
		return (this.quant = quant);
	}
}
