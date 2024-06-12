package fabricmarket.model;

public class CartItem {
	Fabric fabric;
	int fabricId;
	int quantity;
	
	public CartItem(Fabric fabric) {
		this.fabric = fabric;
		this.fabricId = fabric.getfabricId();
		this.quantity = 1;
	}
	
	public Fabric getFabric(){
		return fabric;
	}
	public void setFabric(Fabric fabric) {
		this.fabric = fabric;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void addQuantity (int quantity) {
		this.quantity += quantity;
	}

	@Override
	public String toString() {
		return fabric.getfabricId() + " | " + fabric.getfabric() + " | " + quantity + "yard | " + getPrice() + "Won"; 
	}

	public int getPrice() {
		return quantity * fabric.getPrice();
	}
	
	
	

}
