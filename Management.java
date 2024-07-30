import java.util.ArrayList;

public class Management {
	//print reservation details
	private static ArrayList<Hotel> hotelsList = new ArrayList<Hotel>();
	
	/**
	 * Adds a hotel in an ArrayList, where all existing hotels are stored.
	 */
	public static void addHotel(Hotel hotel) {
		hotelsList.add(hotel);
	}
	
	/**
	 * Removes a hotel in an ArrayList, where all existing hotels are stored.
	 */
	public static void removeHotel(Hotel hotel) {
		hotelsList.add(hotel);
	}
	
	/**
	 * Display the details of a reservation, given the object reservation
	 * @param reservation is an object that stores all the details of a reservation.
	 */
	public static void displayReservation(Reservation reservation) {
		System.out.println("Guest name: " + reservation.getGuestName() + "\n");
		System.out.println("Room: " + reservation.getRoomInfo().getRoomName());
		System.out.println("Dates Booked:\n" +  "Check-in: " + reservation.getCheckIn() + "  Check-out: " + reservation.getCheckOut());
		System.out.println("Overnight dates: " + reservation.getDaysStay() + "\n");
		System.out.println("Cost per night: " + reservation.getRoomInfo().getPrice());
		System.out.println("Total cost of booking: " + reservation.getTotalPay());
	}
	
	/**
	 * Display the details of a room, given the object room
	 * @param room is an object that stores all the details of a room.
	 */
	public static void displayRoom(Room room) {
		System.out.println("Room name: " + room.getRoomName() + "\n");
		System.out.println("Cost per night: " + room.getPrice() + "\n");
		System.out.println("Overnight Dates Booked:");
		System.out.println(room.getDatesBooked());
	}
	
	/**
	 * Get the index of a hotel within an ArrayList
	 * @param name is the name of the hotel.
	 * @param hotels is the list where all hotels are located/ where you want the hotel name found.
	 * @return the index of the hotel if it exists, -1 if otherwise.
	 */
	 public static int getHotelIndex(String name, ArrayList<Hotel> hotels) {
	    	int index = -1;
	    	for(int i = 0; i < hotels.size(); i++) {
	    		if(hotels.get(i).getHotelName().equals(name)) {
	    			index = i;
	    		}
	    	}
	    	return index;
	    }
	 
	 /**
	 * Clears the screen, purely for aesthetic and cleanliness purposes.
	 */
	 public static void out() {
		
			    System.out.print("\033[H\033[2J");  
			    System.out.flush();  
			
	 }
	///print hotel info
	
	
}
