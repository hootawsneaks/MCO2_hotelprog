import java.util.ArrayList;

public class HotelModel {
	private ArrayList<Hotel> hotelList;
		
	public HotelModel() {
		this.hotelList = new ArrayList<Hotel>();
	}
	
	public boolean addHotel(String hotelName, String numStandard, String numDeluxe, String numExec, String price) {
		boolean result = false;
		
		try {
			if(Integer.parseInt(numStandard) + Integer.parseInt(numDeluxe) + Integer.parseInt(numExec) <= 50 && 
					Integer.parseInt(numStandard) + Integer.parseInt(numDeluxe) + Integer.parseInt(numExec) >= 1 && 
					Integer.parseInt(numStandard) >= 0 && Integer.parseInt(numDeluxe) >= 0 && Integer.parseInt(numExec) >= 0 &&
					this.getHotelIndex(hotelName) == -1 && Float.parseFloat(price) >= 100f && !(hotelName.equals(""))) {
				
				this.hotelList.add(new Hotel(hotelName, Integer.parseInt(numStandard), Integer.parseInt(numDeluxe), Integer.parseInt(numExec), Float.parseFloat(price)));
				result = true;
			}
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
		}

		return result;
	}
	
	public ArrayList<Integer> viewDateAvailability(String date, String hotelIndex) {
		ArrayList<Integer> availableDatesList = new ArrayList<Integer>();		
		availableDatesList.add(-1);
		
		try {
			if(Integer.parseInt(date) <= 30 && Integer.parseInt(date) >= 1) {
				return this.hotelList.get(Integer.parseInt(hotelIndex)).getRoomAvailability(Integer.parseInt(date));
			}
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
		}
		
		return availableDatesList;
	}
	
	public Room viewRoomInformation(String roomNum, String hotelIndex) {
		boolean valid = false;
		Room room = new Room(-1, -1, "Standard");
		int roomIndex = -1;
		
		try {
			//check if room is within roomsList of that hotel
			for(int i = 0; i < this.hotelList.get(Integer.parseInt(hotelIndex)).getRoomsList().size(); i++) {
				if(Integer.parseInt(roomNum) == this.hotelList.get(Integer.parseInt(hotelIndex)).getRoomsList().get(i).getRoomName()) {
					valid = true;
					roomIndex = i;
				}
			}
			
			if(valid) {
				return this.hotelList.get(Integer.parseInt(hotelIndex)).getRoomsList().get(roomIndex);
			}
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
		}
		
		return room;
	}
	
	public Reservation viewReservationInformation(String reservationIndex, String hotelIndex) {
		//dummy reservation to signify a returning a false input, when given index does not exist
		Reservation reservation = new Reservation("", -1, -1, new Room(-1, -1, "Standard"), "BLA", new ArrayList<Float>());
		
		try {
			return this.hotelList.get(Integer.parseInt(hotelIndex)).getReservationsList().get(Integer.parseInt(reservationIndex)-1);
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
		}
		
		return reservation;
	}
	
	public boolean addStandardRoom(String numAddStandard, String hotelIndex) {
		boolean valid = false;
		try {
			if(hotelList.get(Integer.parseInt(hotelIndex)).getRoomsList().size() + Integer.parseInt(numAddStandard) <= 50 && Integer.parseInt(numAddStandard) > 0) {
				valid = true;
			}
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
		}
		
		return valid;
	}
	
	public boolean addDeluxeRoom(String numAddDeluxe, String hotelIndex) {
		boolean valid = false;
		try {
			if(hotelList.get(Integer.parseInt(hotelIndex)).getRoomsList().size() + Integer.parseInt(numAddDeluxe) <= 50 && Integer.parseInt(numAddDeluxe) > 0) {
				valid = true;
			}
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
		}
		
		return valid;
	}
	
	public boolean addExecutiveRoom(String numAddExecutive, String hotelIndex) {
		boolean valid = false;
		try {
			if(hotelList.get(Integer.parseInt(hotelIndex)).getRoomsList().size() + Integer.parseInt(numAddExecutive) <= 50 && Integer.parseInt(numAddExecutive) > 0) {
				valid = true;
			}
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
		}
		
		return valid;
	}
	
	public boolean removeRoom(String roomToRemove, String hotelIndex) {
		boolean valid = false;
		
		try {
			for(int i = 0; i < this.hotelList.get(Integer.parseInt(hotelIndex)).getRoomsList().size(); i++) {
				if(Integer.parseInt(roomToRemove) ==  this.hotelList.get(Integer.parseInt(hotelIndex)).getRoomsList().get(i).getRoomName()) {
					valid = true;
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
		}
		
		return valid;
	}
	
	public boolean updatePrice(String newPrice, String hotelIndex) {
		boolean valid = false;
		
		try {
			if(Float.parseFloat(newPrice) >= 100f && hotelList.get(Integer.parseInt(hotelIndex)).getReservationsList().size() == 0) {
				valid = true;
			}
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
		}
		
		return valid;
	}
	
	public boolean removeReservation(String reservationIndex, String hotelIndex) {
		boolean valid = false;
		
		try {
			if( (Integer.parseInt(reservationIndex)-1) >= 0 && (Integer.parseInt(reservationIndex)-1) <= hotelList.get(Integer.parseInt(hotelIndex)).getReservationsList().size() -1) {
				valid = true;
			}
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
		}
		
		return valid;
	}
	
	public boolean modifyRateOnDate(String dateToModify, String newRate, String hotelIndex) {
		boolean valid = false;
		
		try {
			if(Integer.parseInt(dateToModify) >= 1 && Integer.parseInt(dateToModify) <= 30 && Float.parseFloat(newRate) >= 0) {
				valid = true;
			}
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
		}
		
		return valid;
	}
	
	public boolean finalizeReservation(String guestName, String checkIn, String checkOut, String roomChosen, String discountCode, String hotelIndex) {
		boolean valid = false;
		
		try {
			if(Integer.parseInt(checkIn) >= 1 && Integer.parseInt(checkIn) <= 30 && Integer.parseInt(checkOut) >=2 && Integer.parseInt(checkOut) <= 31
					&& Integer.parseInt(checkOut) > Integer.parseInt(checkIn) && hotelList.get(Integer.parseInt(hotelIndex)).getRoomIndex(Integer.parseInt(roomChosen)) != -1
					&& (discountCode.equals("I_WORK_HERE") || discountCode.equals("STAY4_GET1") || discountCode.equals("PAYDAY") || discountCode.equals("")) && !(guestName.equals(""))) {
				if(this.hotelList.get(Integer.parseInt(hotelIndex)).bookReservation(guestName, Integer.parseInt(checkIn), Integer.parseInt(checkOut), Integer.parseInt(roomChosen), discountCode))  {
					valid = true;
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
		}
		
		return valid;
	}
	
	public ArrayList<Hotel> getHotelList(){
		return this.hotelList;
	}
	
	public int getHotelIndex(String hotelName) {
		int index = -1;
		for(int i = 0; i < hotelList.size(); i++) {
			if(hotelList.get(i).getHotelName().equals(hotelName)) {
				index = i;
			}
		}
		return index;
	}
}
