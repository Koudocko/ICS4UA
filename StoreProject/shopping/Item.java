package shopping;

public class Item{
	public String name, retailer;
	public int stock, salePercent;
	public double price;

	public Item(String name, String retailer, int stock, double price, int salePercent){
		this.name = name;
		this.retailer = retailer;
		this.stock = stock;
		this.price = price;
		this.salePercent = salePercent;
	}
}
