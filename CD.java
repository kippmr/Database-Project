public class CD extends Audio {
	

	public CD(String sNo, String title, String artistName, String price, String quant){
		super(sNo,title,artistName,price,quant);
	}
	
	@Override
	public int getPrice() {
		return (int)Math.round((double)super.getPrice() * 1.02); //still need envirotax, multiply by 1.02? and 1.13?
	}

	@Override 
	public String getInfo() {
		return super.getInfo() + "," + quant;
	}

	public void setQuant(int quant) { //however many are bought
		this.quant = quant;
	}

	public int getQuant() {
		return quant;
	}


}
