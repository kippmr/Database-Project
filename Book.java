public class Book extends Readable {
	
	public Book(String book){
		//how do we want to do this?
	}

	public void setQuant(int quant) { //however many are bought
		this.quantity -= quant;
	}

	@Override
	public int getPrice() {
		return Math.round((double)super.getPrice() * 1.02);
	}

	@Override
	public String[] getInfo() { //[sNo, title, authorName, type]
		return new String[] {String.parseString(this.sNo), title, authorName, type};
	}
}
