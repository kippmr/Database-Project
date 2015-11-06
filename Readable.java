//Comma-Separated-File: sNo, title, authorName, price, quantity
public class Readable extends Item {

	protected String title, authorName;
	
	public Readable(int sNo, String title, String authorName){
		this.sNo = sNo;
		this.title = title;
		this.authorName = authorName;
	}

	@Override
	public int getPrice() {
		return super.price;
	}

    public String getInfo() {
        return sNo + ", " + title + ", " + authorName;
    }

    public String[] getInfoArray() { //supposed to be a string not string array? is this OK
        return new String[]{sNo, title, authorName};
    }
}