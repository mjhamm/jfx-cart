package application;

public class Item implements Comparable<Item>{
	
	private String product;
	private Integer priority;
	private Integer quantity;
	private String price;
	
	@Override
	public String toString() {
		return "[" + getProduct() + ", " + getPriority() + ", " + getQuantity() + ", " + getPrice() + ", " + getPurchased() + ", " + getNotPurchased() + "]"; 
	}
	
	public Item(String product, Integer priority, Integer quantity, String price, String purchased, String notPurchased) {
		this.product = new String(product);
		this.priority = new Integer(priority);
		this.quantity = new Integer(quantity);
		this.price = new String(price);
	}
	
	public String getProduct() {
		return product;
	}
	
	public Integer getPriority() {
		return priority;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getPurchased() {
		return product;
	}
	
	public String getNotPurchased() {
		return product;
	}

	@Override
	public int compareTo(Item item) {
		return (this.getPriority() < item.getPriority() ? -1 :
			(this.getPriority() == item.getPriority() ? 0 : 1));
	}
}