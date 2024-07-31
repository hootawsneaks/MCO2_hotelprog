import java.util.ArrayList;
/*
 * Is the model of the Hotel reservation System
 * holds the information and is what gives info to the controller
 */
public class HotelModel {
	private ArrayList<Hotel> hotelList;
	
	/**
	 * Constructs the hotelModel, a list of hotels
	 */
	public HotelModel() {
		this.hotelList = new ArrayList<Hotel>();
	}
	
	/**
	 * Checks if given inputs are valid, creates the hotel if so
	 * @param hotelName the name of the hotel
	 * @param numStandard number of standrd rooms of the hote;
	 * @param numDeluxe number of deluxe rooms of a hotel
	 * @param numExec number of executive rooms of a hotel
	 * @param price the base price of a hotel
	 * @return returns a boolean value to signify if hotel creation was sucessful
	 */
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
	
	/**
	 * Checks if inputs are valid if so returns a true value to then allow rooms available on certain dates to be displayed
	 * @param date date that will be checked
	 * @param hotelIndex the hotel where in a date will be checked
	 * @return a boolean value representing whether inputs were valid or not
	 */
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
	
	/**
	 * Checks if inputs are valid, if so retrieve the inputted room information
	 * @param roomNum the room number/name that will be checked
	 * @param hotelIndex the index of hotel where in room will be checked
	 * @return a room holding the info of the desired room, if inputs were invalid returns a dummy room
	 */
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
	
	/**
	 * Checks if inputs are valid, if so retrieve the inputed reservation information
	 * @param reservationIndex roomNum the reservation index that will be checked
	 * @param hotelIndex the index of hotel where in reservation will be checked
	 * @return reservation holding the info of the desired roeservation, if inputs were invalid returns a dummy reservation
	 */
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
	
	/**
	 * Checks if inputs are valid for the number of standard rooms
	 * @param numAddStandard number of standard rooms to be added
	 * @param hotelIndex index of hotel where rooms will be added
	 * @return a boolean value representing if inputs were valid
	 */
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
	
	/**
	 * Checks if inputs are valid for the number of deluxe rooms to be added
	 * @param numAddDeluxe number of deluxe rooms to be added
	 * @param hotelIndex index of hotel where rooms will be added
	 * @return a boolean value representing if inputs were valid
	 */
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
	
	/**
	 * Checks if inputs are valid for the number of executive rooms
	 * @param numAddSExecutive number of executive rooms to be added
	 * @param hotelIndex index of hotel where rooms will be added
	 * @return a boolean value representing if inputs were valid
	 */
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
	
	/**
	 * Checks if inputs are valid for rooms to be removed
	 * @param roomToRemove room number/name of room to be removed
	 * @param hotelIndex hotelIndex where in the room will be removed
	 * @return boolean value representing if the values were valid or not
	 */
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
	
	/**
	 * Checks if inputs are valid for updating hotel's price
	 * @param newPrice the price old price will be updated to
	 * @param hotelIndex hotel wherein the price will be updated
	 * @return boolean value represetning whether or not inputs were valid
	 */
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
	
	/**
	 * Checks if inputs are valid for reservation to be removed
	 * @param reservationIndex reservation index of reservation to be removed
	 * @param hotelIndex hotelIndex where in the reservation will be removed
	 * @return boolean value representing if the values were valid or not
	 */
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
	
	/**
	 * Checks if inputs are valid for modifying the rate on a certain date
	 * @param dateToModify the date that will be modified
	 * @param newRate rate that old rate will be updated to
	 * @param hotelIndex index of hotel where in rate will be modified
	 * @return boolean value representing whether or not values are valid
	 */
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
	
	/**
	 * Books a reservation if given inputs are valid
	 * @param guestName name of guest
	 * @param checkIn check in date 
	 * @param checkOut check out date
	 * @param roomChosen room guest will check into
	 * @param discountCode discountcode applied
	 * @param hotelIndex index of hotel where  guest will book into
	 * @return boolean value representing whether or not booking was successful
	 */
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
	
	/**
	 * Getter for list of hotels
	 * @return returns the list of hotels
	 */
	public ArrayList<Hotel> getHotelList(){
		return this.hotelList;
	}
	
	/**
	 * Return the index of a hotel in hotelList given it's name
	 * @param hotelName name of hotel to find the index of
	 * @return int index of hotel in hotelList
	 */
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
