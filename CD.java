public class CD extends Audio {
	
	public CD(int sNo, String title, String artistName, int quantity, int price){ 
		super(int sNo, String title, String artistName, int quantity, int price);
	}

	@Override
	public int getPrice() {
		return (int)(super.getPrice() * 1.02); //still need envirotax, multiply by 1.02? and 1.13?
	}

}
