package fabricmarket;

import java.io.IOException;

import fabricmarket.controller.FabController;
import fabricmarket.model.Cart;
import fabricmarket.model.FabricStorage;
import fabricmarket.view.ConsoleView;

public class FabricMarket {

	public static void main(String[] args) throws IOException{
		
		FabricStorage fabricStorage = new FabricStorage();
		Cart cart = new Cart();
		
		ConsoleView view = new ConsoleView();
		
		FabController controller = new FabController(fabricStorage, cart, view);
		controller.start();

	}

}
