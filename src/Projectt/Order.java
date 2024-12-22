package Projectt;

public class Order {
	public enum ordertype{
		BUY,SELL
		
	}
	private ordertype type;
	private Instrument instrument;
	private int quality ;
	private double price ;
	
	
	
	public Order(ordertype type ,Instrument instrument,int quality ,double price) {
		this.type=type;
		this.instrument=instrument;
		this.quality=quality;
		this.price=price;
	}
	public ordertype gettype() {
		return type;
	}
	public Instrument getInstrument() {
		return instrument;
	}
	public int getQuality() {
		return quality;
	}
	public double getPrice() {
		return price;
	}
	
	
	
	
	
	
	
}
