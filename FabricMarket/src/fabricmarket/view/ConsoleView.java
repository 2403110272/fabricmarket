package fabricmarket.view;

import java.util.Scanner;

import fabricmarket.model.Cart;
import fabricmarket.model.Customer;
import fabricmarket.model.FabricStorage;

public class ConsoleView {
	
	private Scanner sc;

    public ConsoleView() {
        sc = new Scanner(System.in);
    }

    public void displayWelcome() {
        String welcome = "───────────────────────────────────────────\n"
                       + "│      Welcome to Online Fabric Market    │\n"
                       + "───────────────────────────────────────────";
        System.out.println(welcome);    
    }

    // 메뉴 번호 입력 받기
    public int selectMenu(String[] menuList) {
        
        displayMenu(menuList);
        
        int menu;
        
        do {
            menu = readNumber("▷ MENU : ");    
            if (menu < 0 || menu >= menuList.length) {
                System.out.println("▷ Please enter a number from 0 to " + (menuList.length-1));
            }
        } while (menu < 0 || menu >= menuList.length);
        return menu;
    }

    // 메뉴 출력
    private void displayMenu(String[] menuList) {
        System.out.println("────────────────────────────────────────");
        for (int i = 1; i < menuList.length; i++) {
            System.out.println(menuList[i]);
        }
        System.out.println(menuList[0]);
        System.out.println("────────────────────────────────────────");
    }

    public void displayFabricInfo(FabricStorage fabricStorage) {
        for (int i = 0; i < fabricStorage.getNumFabrics(); i++) {
            String fabricInfo = fabricStorage.getFabricInfo(i);
            System.out.println(fabricInfo);
        }
    }

    public void displayCart(Cart cart) {
        if(cart.isEmpty()) {
            System.out.println("▷ Cart is empty");
            return;
        }

        for (int i = 0; i < cart.getNumItems(); i++) {
            System.out.println(cart.getItemInfo(i));
        }

        System.out.println("TOTAL " + cart.getTotalPrice() + "Won");
    }

    public int selectFabricCode(FabricStorage fabricStorage) {

        int fabricCode;
        boolean result;

        do {
            fabricCode = readNumber("▷ Please enter the fabric code you want to add : ");
            result = fabricStorage.isValidFabric(fabricCode);

            if(!result) {
                System.out.println("INVALID CODE");
            } 

        } while(!result);

        return fabricCode;
    }
    
    public int selectFabricId(Cart cart) {
    	
    	int fabricId;
    	boolean result;
    	do {
    		fabricId = readNumber("▷ Input Fabric Code : ");
    		result = cart.isValidItem(fabricId);
    		if(!result)
    			System.out.println("Wrong Fabric Code");
    	} while (!result);
    	
    	return fabricId;
    }

    public int inputQuantity(int min, int max) {

        int num;

        do {
            num = readNumber("▷ Enter Quantity : ");

            if(num < min || num > max) {
                System.out.println("INVALID QUANTITY");    
            }

        } while (num < min || num > max);

        return num;

    }


    public void inputCustomerInfo(Customer customer) {

        System.out.println("▷ Please enter your name(business name) and phone number to access the online fabric market. ");

        System.out.println("▷ name(business name) : ");
        customer.setName(sc.next());

        System.out.println("▷ Tel :");
        customer.setTel(sc.next());
    }

    public void inputDeliveryInfo(Customer customer) {

        if (customer.getEmail() == null) {        
            System.out.println("▷ Please enter your e-mail and delivery address.");

            System.out.println("▷ e-mail :");
            customer.setEmail(sc.next());

            System.out.println("▷ delivery address : ");
            customer.setAddress(sc.next());
        }

    }

    public void displayOrder(Cart cart, Customer customer) {

        System.out.println("────────────────────────────────────────");
        System.out.println("▷ ORDER");
        displayCart(cart);

        System.out.println("▷ SHIPPING INFO");
        System.out.println("────────────────────────────────────────");
        System.out.println("▷ name(business name) : " + customer.getName());
        System.out.println("▷ Tel : " + customer.getTel());
        System.out.println("▷ e-mail : " + customer.getEmail());
        System.out.println("▷ delivery address : " + customer.getAddress());
        System.out.println("────────────────────────────────────────");
    }

    public boolean askConfirm(String message, String yes) {

        System.out.println(message);

        if (sc.nextLine().equals(yes)) {
            return true;
        }

        return false;
    }

    public int readNumber(String message) {

        if (message != null && !message.equals("")) {
            System.out.print(message);
        }

        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("▷ Please enter a valid number:");
            }
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String inputString(String msg) {

        System.out.println(msg);
        return sc.nextLine();
    }
}
