public class eBook extends Readable {

	public eBook(String sNo, String title, String authorName, String price, String quant){
		super(sNo, title, authorName, price, quant);
	}

	@Override
	public int getPrice() {
		return super.getPrice();
	}

	@Override
	public String getInfo() {
		return super.getInfo() + "," + quant; 
	}

	public int getQuant() {
		return quant;
	}
	
	public void setQuant(int quant) { 
		this.quant = quant;
	}

	}
