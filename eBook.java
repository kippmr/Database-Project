public class eBook extends Readable {

	protected int price, quant;

	public eBook(String sNo, String title, String authorName, String price, String quant){
		super(sNo, title, authorName);
		this.price = Integer.parseInt(price);
		this.quant = Integer.parseInt(quant);
	}

	@Override
	public int getPrice() {
		return super.getPrice();
	}

	@Override
	public String getInfo() {
		return super.getInfo + ", " + 
	}

	@Override
	public String[] getInfoArray() { //[sNo, title, authorName, type]
		return new String[] {Integer.toString(sNo), title, authorName, type, Integer.toString(price), Integer.toString(quant)};
	}

	public int getQuant() {
		return quant;
	}
	
	public void setQuant(int quant) { //however many are bought
		this.quant = quant;
	}

	}
