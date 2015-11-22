//Comma-Separated-File: sNo, title, authorName, price, quantity
public class Readable extends Item {

	protected String title, authorName;
	protected int price, quant;
	
	public Readable(String sNo, String title, String authorName, String price, String quant){
		this.sNo = Integer.parseInt(sNo);
		this.title = title;
		this.authorName = authorName;
		this.price = Integer.parseInt(price);
		this.quant = Integer.parseInt(quant);
	}

	@Override
	public int getPrice() {
		return this.price;
	}

    public String getInfo() {
        return sNo + "," + title + "," + authorName + "," + price;
    }

    public String getTitle() {
		return this.title;
	}

	public int getSerial() {
		return this.sNo;
	}

	public String getAuthor() {
		return this.authorName;
	}

	public int getQuant() {
		return quant;
	}
	public int setQuant(int quant) { //however many are bought
		return (this.quant = quant);
	}
}