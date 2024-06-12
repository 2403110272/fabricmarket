package fabricmarket.model;

import java.util.ArrayList;

public class Cart {

	private ArrayList<CartItem> itemList = new ArrayList<>();


	public boolean isEmpty() {
		return itemList.isEmpty();
	}

	public ArrayList<CartItem> getItemList() {
		return itemList;
	}

	public int getNumItems() {
		return itemList.size();
	}

	public String getItemInfo(int index) {
		return itemList.get(index).toString();
	}

	public void addItem(Fabric fabric) {
		
		CartItem item = getCartItem(fabric);
		if (item == null) {
			itemList.add(new CartItem(fabric));
		}
		else {
			item.addQuantity(1);
		}
	}
	
	private CartItem getCartItem(Fabric fabric) {
		
		for (CartItem item : itemList) {
			if (item.getFabric() == fabric) return item;
		}
		
		return null;
	}
	
	private CartItem getCartItem(int fabricId) {
		for (CartItem item : itemList) {
			if (item.fabricId == fabricId) return item;
		}
		return null;
	}
	

	public void resetCart() {
		itemList.clear();
	}

	public boolean isValidItem(int fabricId) {
		for (CartItem item : itemList) {
			if (item.fabricId == fabricId) return true;
		}
		return false;
	}

	public void deleteItem(int fabricId) {
		CartItem item = getCartItem(fabricId);
		itemList.remove(item);
	}

	public void updateQuantity(int fabricId, int quantity) {
		
		if (quantity == 0)
			deleteItem(fabricId);
		else {
			CartItem item = getCartItem(fabricId);
			item.setQuantity(quantity);;
		}
		
	}

	public int getTotalPrice() {
		int totalPrice = 0;
		for (CartItem item : itemList) {
			totalPrice += item.getPrice();
		}
		return totalPrice;
	}

	
	
}


