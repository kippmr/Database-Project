public class Book extends Readable {

	public Book(int sNo, String title, String authorName, int quantity, int price){
		super(int sNo, String title, String authorName, int quantity, int price);
	}

	@Override
	public int getPrice() {
		return (int)(super.getPrice() * 1.02); //Envirotax
	}
}
