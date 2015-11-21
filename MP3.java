public class MP3 extends Audio {
	

	public MP3(String sNo, String title, String artistName, String price, String quant){
		super(sNo,title,artistName,price,quant);
	}
	
	public void setQuant(int quant) { 
		this.quant = quant;
	}

	public int getQuant() {
		return quant;
	}

	@Override
	public int getPrice() {
		return super.getPrice(); 
	}

	@Override 
	public String getInfo() {
		return super.getInfo() + "," + quant;
	}

}