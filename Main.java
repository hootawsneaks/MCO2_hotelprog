
public class Main {
	public static void main(String[] args) {
		HotelView hotelView = new HotelView();
		HotelModel hotelModel = new HotelModel();
		
		HotelController hotelController = new HotelController(hotelView, hotelModel);
	}
}
