package Projectt;


import java.util.Random;
public class MarketdataGenerator implements Runnable{
		private Instrument instrument ;
		private Random random ;
		private boolean running ;
		
		public MarketdataGenerator(Instrument instrument) {
			this.instrument=instrument ;
			this.random=new Random();
			this.running=true;
			
		}
		public void stop() {
			running = false ;
		}
		@Override
		public void run() {
			while(running) {
				siumulatePriceMovement();
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					Thread.currentThread().interrupt();
					
				}
			}
		}
		public void siumulatePriceMovement() {
			double price = instrument.getprice();
			double changePercent= ((random.nextDouble()-0.5)*0.1);
			double nprice = price + ( price*changePercent);
			if(nprice<1.0) {
				nprice = 1.0;
			}
			instrument.updateprice(nprice);
		}
		
	
	
	
	
	
}
