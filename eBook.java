public class eBook extends Readable {

	public eBook(int sNo, String title, String authorName, int quantity, int price){
		super(int sNo, String title, String authorName, int quantity, int price); 
	}
	
	@Override
	public int getPrice() {
		return this.price; //No envirotax
	}
}
