package fabricmarket.model;

public class Admin extends User{
	
	private String id = "coolkidneverdie";
	private String password = "isaids0^^";
	
	public boolean login(String id, String password) {
		if(this.id.equals(id) && this.password.equals(password)) {
			return true;
		}
		return false;
	}

}
