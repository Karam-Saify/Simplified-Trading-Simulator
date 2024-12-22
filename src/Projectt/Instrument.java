package Projectt;

public class Instrument {

	
	private String symbol;
	private double price ;
	
	public Instrument(String symbol, double price ) {
		this.symbol= symbol;
		this.price=price ;
	}
	public String getSymbol() {
		return symbol;
	}
	public synchronized double getprice() {
		return price;
	}
	
	public synchronized void updateprice(double nprice ) {
		this.price=nprice;
	}
	
}
