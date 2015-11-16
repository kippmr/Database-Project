public class eBook extends Readable {

	protected int quant;

	public eBook(String sNo, String title, String authorName, String price, String quant){
		super(sNo, title, authorName,price);
		this.quant = Integer.parseInt(quant);
	}

	@Override
	public int getPrice() {
		return super.getPrice();
	}

	public int getPriceR() {
		return super.getPrice();
	}

	@Override
	public String getInfo() {
		return super.getInfo + "," + quant; 
	}

	@Override
	public String[] getInfoArray() { 
		return new String[] {Integer.toString(sNo), title, authorName, type, Integer.toString(price), Integer.toString(quant)};
	}

	public int getQuant() {
		return quant;
	}
	
	public void setQuant(int quant) { 
		this.quant = quant;
	}

	}
