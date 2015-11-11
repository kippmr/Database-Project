public class Book extends Readable {
	
	protected int quant;

	public Book(String sNo, String title, String authorName, String price, String quant){
		super(sNo,title,authorName,price);
		this.quant = Integer.parseInt(quant);
	}

	@Override
	public int getPrice() {
		return (int)Math.round((double)super.getPrice() * 1.02);
	}

	public int getPriceR() {
		return super.getPrice();
	}

	@Override
	public String getInfo() {
		return super.getInfo + "," + quant; 
	}

	@Override
	public String[] getInfoArray() { //[sNo, title, authorName, type]
		return new String[] {Integer.toString(sNo), title, authorName, type, Integer.toString(price), Integer.toString(quant)};
	}

	public int getQuant() {
		return quant;
	}
	public int setQuant(int quant) { //however many are bought
		return (this.quant = quant);
	}


}
