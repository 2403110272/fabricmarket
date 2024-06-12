package fabricmarket.model;

public class Fabric {
	private int fabricId;
	private int yarnCount;
	private int colorId;
	private String fabric;
	private int price;
	
	public Fabric (int fabricId, int yarnCount, int colorId, String fabric, int price) {
		this.fabricId = fabricId;
		this.yarnCount = yarnCount;
		this.colorId = colorId;
		this.fabric = fabric;
		this.price = price;
	}
	
	public int getfabricId() {
		return fabricId;
	}
	public int getyarnCount() {
		return yarnCount;
	}
	public int getcolorId() {
		return colorId;
	}
	public String getfabric() {
		return fabric;
	}
	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return fabricId + " │ " + fabric + " │ " + yarnCount + "수" +  " │ " + colorId + " │ " + price + " Won";
	}

}
