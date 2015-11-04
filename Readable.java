
public class Readable extends Item {

	protected String authorName;
	
	@Override
	public String getInfo() {
		return sNo + " " + authorName;
	}
	
	@Override
	public int getPrice() {
		return price;
	}
}