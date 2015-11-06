public class Book extends Readable {
	
	protected int price;

	public Book(int sNo, String title, String authorName, int price){
		super(sNo,title,authorName);
		this.price = price;
		//how do we want to do this?
	}

	public void setQuant(int quant) { //however many are bought
		
	}

	@Override
	public int getPrice() {
		return (int)Math.round((double)super.getPrice() * 1.02);
	}

	public String[] getInfoArray() { //[sNo, title, authorName, type]
		return new String[] {Integer.toString(super.sNo), title, authorName};
	}
}
