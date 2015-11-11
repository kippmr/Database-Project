//Comma-Separated-File: sNo, title, authorName, price, quantity
public class Readable extends Item {

	protected String title, authorName;
	protected int price;
	
	public Readable(String sNo, String title, String authorName, String price){
		this.sNo = Integer.parseInt(sNo);
		this.title = title;
		this.authorName = authorName;
		this.price = Integer.parseInt(price);
	}

	@Override
	public int getPrice() {
		return this.price;
	}

    public String getInfo() {
        return sNo + "," + title + "," + authorName + "," price;
    }

    public String[] getInfoArray() { //supposed to be a string not string array? is this OK
        return new String[]{Integer.toString(sNo), title, authorName,Integer.toString(price)};
    }
}