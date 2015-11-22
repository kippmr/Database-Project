public class CD extends Audio {
	

	public CD(String sNo, String title, String artistName, String price, String quant){
		super(sNo,title,artistName,price,quant);
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

	public void setQuant(int quant) { 
		this.quant = quant;
	}

	public int getQuant() {
		return quant;
	}


}
