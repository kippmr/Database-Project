public class CD extends Audio {
	
	protected int quant;

	public CD(String sNo, String title, String artistName, String price, String quant){
		super(sNo,title,artistName,price);
		this.quant = Integer.parseInt(quant);
	}
	
	@Override
	public int getPrice() {
		return (int)Math.round((double)super.getPrice() * 1.02); //still need envirotax, multiply by 1.02? and 1.13?
	}

	public int getPriceR() {
		return super.getPrice();
	}

	@Override 
	public String getInfo() {
		return super.getInfo() + "," + quant;
	}

	@Override
	public String[] getInfoArray() { //[sNo, title, authorName, type]
		return new String[] {Integer.toString(sNo), title, artistName, Integer.toString(price), Integer.toString(quant)};
	}

	public void setQuant(int quant) { //however many are bought
		this.quant = quant;
	}


}
