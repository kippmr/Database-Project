public class MP3 extends Audio {
	
	protected int quant;

	public MP3(String sNo, String title, String artistName, String price, String quant){
		super(sNo,title,artistName,price);
		this.quant = Integer.parseInt(quant);
	}
	
	public void setQuant(int quant) { //however many are bought
		this.quantity = quant;
	}

	@Override
	public int getPrice() {
		return super.getPrice(); //still need envirotax, multiply by 1.02? and 1.13?
	}

	public int getPriceR() {
		return super.getPrice();
	}

	@Override 
	public int getInfo() {
		return super.getInfo() + "," + quant;
	}

	@Override
	public String[] getInfoArray() { //[sNo, title, authorName, type]
		return new String[] {Intger.toString(sNo), title, artistName, Integer.toString(price), Integer.toString(quant)};
	}
}