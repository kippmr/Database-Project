public class Book extends Readable {
	
	public Book(String sNo, String title, String authorName, String price, String quant){
		super(sNo,title,authorName,price,quant);
	}

	@Override
	public int getPrice() {
		return (int)Math.round((double)super.getPrice() * 1.02);
	}

	public int getTax() {
		return (int) ((double)super.getPrice() * 0.02);
	}

	@Override
	public String getInfo() {
		return super.getInfo() + "," + quant; 
	}

	public int getQuant() {
		return quant;
	}
	public int setQuant(int quant) { //however many are bought
		return (this.quant = quant);
	}


}
