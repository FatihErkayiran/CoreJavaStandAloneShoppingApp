package com.ecommerce.model;

public class Item {
	
	String name;
	String itemCode;
	String price;
	
	public Item(String name, String itemCode, String price) {
		super();
		this.name = name;
		this.itemCode = itemCode;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", itemCode=" + itemCode + ", price=" + price + "]";
	}
	
	
	
	

}
