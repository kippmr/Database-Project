//Comma-Separated-File: sNo, title, authorName, price, quantity
public class Readable extends Item {

	protected String title, authorName;
	
	public Readable(String sNo, String title, String authorName){
		this.sNo = Integer.parseInt(sNo);
		this.title = title;
		this.authorName = authorName;
	}

	@Override
	public int getPrice() {
		return this.price;
	}

    public String getInfo() {
        return sNo + ", " + title + ", " + authorName;
    }

    public String[] getInfoArray() { //supposed to be a string not string array? is this OK
        return new String[]{sNo, title, authorName, type};
    }
}