public abstract class Item {
	
	protected int price;
	protected int sNo;
	protected int quantity; 
	
	public abstract int getPrice();
	public abstract String getInfo();
	public void setQuant(quant) {
		quantity = quant
	}
	public int getQuant() { // No difference between quantity of audio vs readables so might as well implement in Item class
		return quantity;
	}
	public int getSNo() { //Need this for shoppingcart
		return sNo;
	}
	
}