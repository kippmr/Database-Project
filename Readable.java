//Comma-Separated-File: sNo, title, authorName, price, quantity
public class Readable extends Item {

	protected String title, authorName, quantity, type;
	
	public Readable(String sNo, String title, String authorName, String price, String quantity, String type){
		
		this.sNo = Integer.parseInt(sNo);
		this.title = title;
		this.authorName = authorName;
		this.price = Integer.parseInt(price);
		this.quantity = Integer.parseInt(quantity);
		this.type = type;
	}

	public String[] getInfo() { //supposed to be a string not string array? is this OK
		return new String[]{sNo, title, authorName, type};
	}
	
	@Override
	public int getPrice() {
		return this.price;
	}
}