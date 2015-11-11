public class CD extends Audio {
	
	protected int price, quant;

	public CD(String sNo, String title, String artistName, String price, String quant){
		super(sNo,title,artistName);
		this.price = Integer.parseInt(price);
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
	public int getInfo() {
		return super.getInfo() + "," + price + "," + quant;
	}

	@Override
	public String[] getInfoArray() { //[sNo, title, authorName, type]
		return new String[] {Intger.toString(sNo), title, artistName, Integer.toString(price), Integer.toString(quant)};
	}

	public void setQuant(int quant) { //however many are bought
		this.quantity = quant;
	}


}
