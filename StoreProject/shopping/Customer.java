package shopping;

import java.util.ArrayList;
import java.util.Random;
import shopping.Item;

public class Customer{
	public ArrayList<Item> itemsBought;
	public String name;
	public double balance;
	public ArrayList<Integer> coupons;

	public Customer(String name){
		Random rand = new Random();

		this.name = name;
		this.balance = rand.nextInt(1000) + 1;

		int numCoupons = rand.nextInt(6);
		this.coupons = new ArrayList<Integer>();
		for (int i = 0; i < numCoupons; ++i){
			this.coupons.add(rand.nextInt(50) + 1);
		}
	}
}
