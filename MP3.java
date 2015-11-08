public class MP3 extends Audio {

	public MP3(int sNo, String title, String artistName, int quantity, int price){ 
		super(int sNo, String title, String artistName, int quantity, int price)
	}

	@Override
	public int getPrice() {
		return super.getprice(); // No envirotax
	}

}
