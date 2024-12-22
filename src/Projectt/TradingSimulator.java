package Projectt;

import java.util.Scanner;

public class TradingSimulator {
    public static void main(String[] args) {
        
        Instrument instrument = new Instrument("Thready", 100.0);
        MarketdataGenerator marketData = new MarketdataGenerator(instrument);
        Portfolio portfolio = new Portfolio(10000.0); // Starting cash
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        double exposureLimit = 5000.0;

        Thread marketThread = new Thread(marketData);
        marketThread.start();

        System.out.println("Welcome to the Simplified Trading Simulator!");

        while (running) {
        	//** Display Menu **
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. View Market Price");
            System.out.println("2. Place Order");
            System.out.println("3. View Portfolio");
            System.out.println("4. View Profit and Loss");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.printf("Current price of %s: $%.2f%n", instrument.getSymbol(), instrument.getprice());
                    break;
                case "2":
                    System.out.print("Enter order type (BUY/SELL): ");
                    String typeInput = scanner.nextLine().toUpperCase();
                    Order.ordertype type;
                    if (typeInput.equals("BUY")) {
                        type = Order.ordertype.BUY;
                    } else if (typeInput.equals("SELL")) {
                        type = Order.ordertype.SELL;
                    } else {
                        System.out.println("Invalid order type.");
                        break;
                    }

                    System.out.print("Enter quantity: ");
                    int quantity;
                    try {
                        quantity = Integer.parseInt(scanner.nextLine());
                        if (quantity <= 0) {
                            System.out.println("Quantity must be positive.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity.");
                        break;
                    }

                    double currentPrice = instrument.getprice();
                    Order order = new Order(type, instrument, quantity, currentPrice);
                    portfolio.excuteOrder(order);

                 
                    double exposure = portfolio.getTotalExposure(instrument);
                    if (exposure > exposureLimit) {
                        System.out.println("Warning: Exposure limit exceeded!");
                    }
                    break;
                case "3":
                    portfolio.displayPortfolio(instrument);
                    break;
                case "4":
                    portfolio.calculatePnL(instrument);
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

    
        marketData.stop();
        try {
            marketThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        scanner.close();
        System.out.println("Exiting Trading Simulator. Goodbye!");
    }
}
