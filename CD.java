
public class CD extends Audio {
	
	@Override
	public int getPrice() {
		return Math.round(((float)super.getPrice() * 1.02));
	}
	
}