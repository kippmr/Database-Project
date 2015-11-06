public class MP3 extends Audio {

	public MP3(String mp3){
		//how do we want to do this?
	}

	public void setQuant(int quant) { //however many are bought
		this.quantity -= quant;
	}

	@Override
	public int getPrice() {
		return this.price; //still need envirotax, multiply by 1.02? and 1.13?
	}

	@Override
	public String[] getInfo() { //[sNo, title, authorName, type]
		return new String[] {String.parseString(this.sNo), title, artistName, type};
	}
}
