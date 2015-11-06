
public class Readable extends Item {

	protected String authorName;
	protected String name;
	
	@Override
	public String getInfo() {
		return sNo + ", " + name + ", " + authorName;
	}
	
	@Override
	public int getPrice() {
		return price;
	}
}