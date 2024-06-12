package fabricmarket.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FabricStorage {
	ArrayList<Fabric> fabricList = new ArrayList<>();
	final int MAX_QUANTITY = 60;
	private String fabricFilename = "fabriclist.txt";
	private int lastId;
	private boolean isSaved;
	
	public FabricStorage() throws IOException {
		loadFabricListFromFile();
		generateLastId();
		isSaved = true;
	}



	private void generateLastId() {
		lastId = 0;
		for(Fabric fabric : fabricList) {
			int id = fabric.getfabricId();
			if(id > lastId) lastId = id;
		}
		
	}
	
	private void loadFabricListFromFile() throws IOException {
		FileReader fr;
		try {
			fr = new FileReader(fabricFilename);
			BufferedReader br = new BufferedReader(fr);
			String idStr;
			while ((idStr = br.readLine()) != null && !idStr.equals("")) {
				int id = Integer.parseInt(idStr);
				int yarn = Integer.parseInt(br.readLine());
				int col = Integer.parseInt(br.readLine());
				String fab = br.readLine();
				int price = Integer.parseInt(br.readLine());
				fabricList.add(new Fabric(id, yarn, col, fab, price));
			}
			fr.close();
			br.close();
		} catch(FileNotFoundException | NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public int getNumFabrics() {
		return fabricList.size();
	}
	
	public String getFabricInfo(int i) {
		return fabricList.get(i).toString();
	}
	
	public boolean isValidFabric(int fabricId) {
		for(Fabric fabric : fabricList) {
			if (fabric.getfabricId() == fabricId) return true;
		}
		return false;
	}
	
	public Fabric getFabricId (int fabricId) {
		for (Fabric fabric : fabricList) {
			if(fabric.getfabricId() == fabricId)
				return fabric;
		}
		return null;
	}
	
	public int getMaxQuantity() {
		return MAX_QUANTITY;
	}
	
	public boolean isEmpty() {
		return fabricList.size() == 0;
	}
	
	public void deleteItem (int fabricId) {
		fabricList.remove(getFabricId(fabricId));
		isSaved = false;
	}
	
	public void addFabric(int yarnCount, int colorId, String fab, int price) {
		
		Fabric fabric = new Fabric(++lastId, yarnCount, colorId, fab, price);
		fabricList.add(fabric);
		isSaved = false;
	}
	
	public boolean isSaved() {
		return isSaved;
	}
	
	public void saveFabricList2File() {
		try {
			FileWriter fw = new FileWriter(fabricFilename);
			for(Fabric fabric : fabricList) {
				fw.write(fabric.getfabricId() + "\n");
				fw.write(fabric.getfabric() + "\n");
				fw.write(fabric.getyarnCount() + "\n");
				fw.write(fabric.getcolorId() + "\n");
				fw.write(fabric.getPrice() + "\n");
			}
			fw.close();
			isSaved = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
