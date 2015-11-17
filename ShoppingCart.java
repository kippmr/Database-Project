public class ShoppingCart extends User {

    protected List<Item> contents = new ArrayList<Item>();
    
    
    /** Returns a string consisting of the info of each item in contents
     * separated by newline characters */
	public String getContent() {
		String items = "" 
		for (Item o: contents) {
			items += o.getInfo() + "\n"
		}
		return items
	}
	
	/** Takes an object of a subclass of type Item and integer quantity as input, 
	 * checks if the quantity is less than or equal
	 * to the amount available, and if so, creates an new object of the same type, with the
	 * specified quantity, and adds it to contents. Updates the quantity of the original object.
	 * If the quantity is too large, prints "not enough in stock" */
	public void addItem(Item o, int quantity ) { 
		if (quantity <= o.getQuant()) {
			if (o instanceof Readable) {
				if (o instanceof Book) {
					Book p = new Book(o.getSNo, o.getTitle, o.getAuthorName, o.getQuantity, o.getPrice);
				} else if (o instanceof eBook) {
					eBook p = new eBook(o.getSNo, o.getTitle, o.getAuthorName, o.getQuantity, o.getPrice);
				}
			} else if (o instanceof Audio) {
				if (o instanceof CD) {
					CD p = new CD(o.getSNo, o.getTitle, o.getArtistName, o.getQuantity, o.getPrice);
				} else if (o instanceof MP3) {
					MP3 p = new MP3(o.getSNo, o.getTitle, o.getArtistName, o.getQuantity, o.getPrice);
				}
			}
			contents.add(p);
			o.setQuant(o.getQuant - quantity);
			
		} else {
			System.out.println("Not enough in stock");
		}
	}
}