//Comma-Separated-File: sNo, title, authorName, price, quantity
public class Readable extends Item {

	protected String title, authorName;
	
	public Readable(int sNo, String title, String authorName, int quantity, int price){
		
		this.sNo = sNo;
		this.title = title;
		this.authorName = authorName;
		this.quantity = quantity;
		this.price = price
	}
	
	@Override
    public String getInfo() {
        return sNo + ", " + title + ", " + authorName;
    }
    
	@Override
	public int getPrice() { 
		return this.price;
	}

	public String getTitle() { //Need this for shoppingcart
		return this.title;
	}
	
	public String getAuthorName() { //Need this for shoppingcart
		return this.authorName;
	}
}