
public class Book extends Readable {
	
	public String bookName;
	
	@Override
	public int getPrice() {
		return Math.round(((float)super.getPrice() * 1.02));
	}
	
}