package fabricmarket.controller;

import fabricmarket.model.Admin;
import fabricmarket.model.Cart;
import fabricmarket.model.Customer;
import fabricmarket.model.FabricStorage;
import fabricmarket.view.ConsoleView;

public class FabController {

	fabricmarket.view.ConsoleView view;
	FabricStorage mFabricStorage;
	Cart mCart;
	Customer mCustomer;
	Admin mAdmin;

	String[] menuList = { "0.종료", "1. 직물 정보 보기", "2. 장바구니 보기", "3. 장바구니 직물 추가", "4. 장바구니 직물 삭제", "5. 장바구니 직물 수량 변경",
			"6. 장바구니 비우기", "7. 주문", "8. 관리자 모드" };

	String[] adminMenuList = { "0. 종료", "1. 직물 정보 추가", "2. 직물 정보 삭체", "3. 직물 정보 보기", "4. 직물 정보 파일 저장" };

	public FabController(FabricStorage fabricStorage, Cart cart, ConsoleView view) {
		this.view = view;
		this.mFabricStorage = fabricStorage;
		mCart = cart;
		mAdmin = new Admin();
	}

	public void start() {
		welcome();
		registerCustomerInfo();

		int menu;

		do {
			menu = view.selectMenu(menuList);

			switch (menu) {
			case 1 -> viewFabricInfo();
			case 2 -> viewCart();
			case 3 -> addFabric2Cart();
			case 4 -> deleteFabricInCart();
			case 5 -> updateFabricInCart();
			case 6 -> resetCart();
			case 7 -> order();
			case 8 -> adminMode();
			case 0 -> end();
			default -> view.showMessage("잘못된 메뉴 번호입니다");
			}
		} while (menu != 0);

	}

	private void welcome() {
		view.displayWelcome();
		
	}

	private void registerCustomerInfo() {
		mCustomer = new Customer();
		view.inputCustomerInfo(mCustomer);
		
	}

	private void viewFabricInfo() {
		view.displayFabricInfo(mFabricStorage);
	}

	private void viewCart() {
		view.displayCart(mCart);
	}

	private void addFabric2Cart() {
		view.displayFabricInfo(mFabricStorage);
		int fabricCode = view.selectFabricCode(mFabricStorage);
		mCart.addItem(mFabricStorage.getFabricId(fabricCode));
		view.showMessage("▷ Added it to your cart.");
	}

	private void deleteFabricInCart() {
		view.displayCart(mCart);
		if(!mCart.isEmpty()) {
			int fabricId = view.selectFabricId(mCart);
			if(view.askConfirm("▷ Input YES to delete this Item", "YES")) {
				mCart.deleteItem(fabricId);
				view.showMessage("▷ DELETED");
			}
		}
	}

	private void updateFabricInCart() {
		view.displayCart(mCart);
		if(!mCart.isEmpty()) {
			int fabricId = view.selectFabricId(mCart);
			int quantity = view.inputQuantity(0, mFabricStorage.getMaxQuantity());
			mCart.updateQuantity(fabricId, quantity);
		}
	}

	private void resetCart() {
		view.displayCart(mCart);
		 
		if(!mCart.isEmpty()) {
			if(view.askConfirm("▷ Input YES to clear your cart", "YES")) {
				mCart.resetCart();
				view.showMessage("▷ We've cleared your cart");
			}
		}
	}

	private void order() {
		
		getDeliveryInfo();
		
		viewOrderInfo();
		
		if(view.askConfirm("▷ Input YES for order", "YES")) {
			
			mCart.resetCart();
		}
	}

	private void viewOrderInfo() {
		view.displayOrder(mCart, mCustomer);
		
	}

	private void getDeliveryInfo() {
		view.inputDeliveryInfo(mCustomer);
		
	}

	private void adminMode() {

		if (!authenticateAdmin()) {
			view.showMessage("UNAVILABLE");
			return;
		}

		int menu;
		do {
			menu = view.selectMenu(adminMenuList);

			switch (menu) {
			case 1 -> addFabric2Storage();
			case 2 -> deleteFabricInStorage();
			case 3 -> viewFabricInfo();
			case 4 -> saveFabricList2File();
			case 0 -> adminEnd();
			default -> view.showMessage("WRONG MENU");
			}
		} while (menu != 0);
	}
	

	private boolean authenticateAdmin() {
		view.showMessage("▷ Administrator Authentication");
		String id = view.inputString("▷ ID : ");
		String password = view.inputString("▷ PASSWORD : ");
		return mAdmin.login(id, password);
	}

	private void addFabric2Storage() {
		view.showMessage("ADD NEW FABRIC");
		
		mFabricStorage.addFabric(view.readNumber(" YarnCount : "), view.readNumber(" ColorID : "), view.inputString(" Fabric : "), view.readNumber("Price : "));
	}

	private void deleteFabricInStorage() {
		if(mFabricStorage.isEmpty()) {
			view.showMessage("No Fabric in the Storage");
			return;
		}
		
		viewFabricInfo();
		
		int fabricId = view.selectFabricCode(mFabricStorage);
		if (view.askConfirm("▷ Input YES to delete the fabric", "YES")) {
			mFabricStorage.deleteItem(fabricId);
			view.showMessage("▷ DELETED");
		}
	}

	private void saveFabricList2File() {
		if (mFabricStorage.isSaved()) {
			view.showMessage("Fabric INFO is same as the file");
		} else {
			mFabricStorage.saveFabricList2File();
			view.showMessage("Fabric INFO has been saved");
		}
	}

	private void adminEnd() {
		if (!mFabricStorage.isSaved()) {
			view.showMessage("Fabric INFO hasn't been saved as a file.");
			if (view.askConfirm("Input YES to save INFO file", "YES")) {
				mFabricStorage.saveFabricList2File();
			}
		}
		view.showMessage("ADMIN MODE OFF");
	}

	private void end() {
		view.showMessage("▷ Closing The Online Fabric Market");
	}

}