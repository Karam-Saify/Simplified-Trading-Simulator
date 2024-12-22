package Projectt;
import java.util.HashMap;
public class Portfolio {

	
	private HashMap<String,Integer> positions;
	private double cashBalance ;
	private double initialCashBalance;
	
	public Portfolio(double initialCash) {
		positions=new HashMap<String, Integer>();
		cashBalance=initialCash;
		initialCashBalance=initialCash;
		
	}
	public synchronized void excuteOrder(Order order) {
		String symbol = order.getInstrument().getSymbol();
		double price = order.getPrice();
		int quantity = order.getQuality();
		double cost =price*quantity;
		
		
		switch (order.gettype()) {
		case BUY: 
			
			if(cashBalance>=cost) {
				positions.put(symbol,positions.getOrDefault(symbol, 0)+quantity);
				cashBalance+=cost ;
				System.out.printf("Brought %d shares of %s at $%.2f each .%n" ,quantity,symbol,price);
			}
			else {
				System.out.println("Insuffient funds to excute the order");
			}
			break;
		case SELL:
			int availability =positions.getOrDefault(symbol, 0);
			if(availability>=quantity) {
				positions.put(symbol,availability-quantity);
				cashBalance+=cost ;
				System.out.printf
				("Sold %d shares of %s at $%.2f  each .%n",quantity,symbol,price);
			}
			else {
				System.out.println("Insuffient Shares to excute");
			
		}
			break;
		}
	}
	
	
		 public void displayPortfolio(Instrument instrument ) {
			 String symbol = instrument.getSymbol();
			 int  quantity = positions.getOrDefault(symbol, 0);
			 double Marketvalue =   quantity*instrument.getprice();
			 System.out.println("\n--- Portfolio Summary ---");
		        System.out.printf("Instrument: %s%n", symbol);
		        System.out.printf("Quantity: %d%n", quantity);
		        System.out.printf("Market Value: $%.2f%n", Marketvalue);
		        System.out.printf("Cash Balance: $%.2f%n", cashBalance);
		        System.out.printf("Total Portfolio Value: $%.2f%n", (cashBalance + Marketvalue));
		    }

		    public void calculatePnL(Instrument instrument) {
		        String symbol = instrument.getSymbol();
		        int quantity = positions.getOrDefault(symbol, 0);
		        double marketPrice = instrument.getprice();
		        double investedAmount = initialCashBalance - cashBalance;
		        double currentValue = cashBalance + (quantity * marketPrice);
		        double profitLoss = currentValue - initialCashBalance;
		        System.out.println("\n--- Profit and Loss ---");
		        System.out.printf("Invested Amount: $%.2f%n", investedAmount);
		        System.out.printf("Current Portfolio Value: $%.2f%n", currentValue);
		        System.out.printf("Unrealized P&L: $%.2f%n", profitLoss);
		    }

		    public double getTotalExposure(Instrument instrument) {
		        String symbol = instrument.getSymbol();
		        int quantity = positions.getOrDefault(symbol, 0);
		        return quantity * instrument.getprice();
		    }
		}