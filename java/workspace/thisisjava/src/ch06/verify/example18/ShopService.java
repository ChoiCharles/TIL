package ch06.verify.example18;

public class ShopService {
	public static ShopService singleton = new ShopService();
	
	private ShopService() {
		
	}
	
	public static ShopService getInstance() {
		return singleton;
	}
}
