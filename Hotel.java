import java.util.ArrayList;
/**
 * Hotel represents a hotel, it has rooms, where in the rooms can be booked into 
 * A Hotel has a name, amounts for each room type, a base price, a list of rooms and reservations, and a list of the rates on each date
 */
public class Hotel{
    private String hotelName;
    private int numStandard;
    private int numDeluxe;
    private int numExec;
    private float price = 1299f;
    private ArrayList<Reservation> reservationsList = new ArrayList<Reservation>();
    private ArrayList<Room> roomsList = new ArrayList<Room>();
    private ArrayList<Float> ratesList = new ArrayList<Float>();
    
	/**
	 * Class constructor for a hotel; hotel name, number of each room type, and price need to be indicated.
	 * @param hotelName the hotel's unique name
	 * @param numStandard the number of standard rooms
	 * @param numDeluxe the number of deluxe rooms
	 * @param numExec the number of executive rooms
	 * @param price the hotel's base price
	 */
    public Hotel(String hotelName, int numStandard, int numDeluxe, int numExec, float price){
        this.hotelName = hotelName;
        this.numStandard = numStandard;
        this.numDeluxe = numDeluxe;
        this.numExec = numExec;
        this.price = price;
        
        this.createRates();
        this.createRooms(numStandard, numDeluxe, numExec);
    }
    
    
    /**
     * sets the hotel's list of rates to the default
     */
    private void createRates(){
		for(int i = 0; i < 30; i++){
			this.ratesList.add(1f);
		}
	}
    
    /**
     * To modify the rate on a certain date
     * @param dateToModify the date that will be modified
     * @param newRate the new rate that will be applied
     */
    public void modifyRatesList(int dateToModify, float newRate) {
    	for(int i = 0; i < this.ratesList.size(); i++) {
    		if(i+1 == dateToModify) {
    			this.ratesList.set(i, newRate);
    		}
    	}
    }
    
    /**
     * creates the hotel's rooms based on the given parameters
     * @param numStandard number of standard rooms to create
     * @param numDeluxe number of deluxe rooms to create
     * @param numExec number of executive rooms to create
     * @return a boolean value for if the creation was successful
     */
    private boolean createRooms(int numStandard, int numDeluxe, int numExec){
    	if((numStandard + numDeluxe + numExec) < 1 || (numStandard + numDeluxe + numExec) > 50)
    	{
    		return false;
    	}
    	
    	int n = 1;
        for(int i = 0; i < numStandard; i++){
        	this.roomsList.add(new Room(n, this.price, "Standard"));
            n++;
        }
       
        for(int i = 0; i < numDeluxe; i++){
        	this.roomsList.add(new Room(n, this.price, "Deluxe"));
            n++;
        }
        
        for(int i = 0; i < numExec; i++){
        	this.roomsList.add(new Room(n, this.price, "Executive"));
            n++;
        }
        
        return true;
    }
    
	/**
	 *  Books a reservation via a given name, check in and out date, and a room number.
	 *  The check in date must always be earlier than the check out date.
	 *  @param guestName a string that signifies the one reserving the room.
	 *  @param checkIn an integer from 1-30 that signifies the day the guest has checked in.
	 *  @param checkOut an integer from 2-31 that signifies the day the guest will check out.
	 *  @param roomNum an integer that signifies the chosen room that will be reserved.
	 *  @return true if booked successfully, false if otherwise.
	 */
    public boolean bookReservation(String guestName, int checkIn, int checkOut, int roomNum, String discountCode) {
    	//bawal checkin last day, bawal checkOut first
    	if(!(checkIn >= 1 && checkIn < 31)) {
    		return false;
    	}
    	if(!(checkOut > 1 && checkOut <= 31) || checkOut <= checkIn){
    		return false;
    	}
    	if(checkOut == checkIn) {
    		return false;
    	}
    	
    	ArrayList<Integer> bookDates = new ArrayList<Integer>();
    	int n = checkIn;
    	do {
    		bookDates.add(n);
    		n++;
    	}while(n < checkOut);// room is available again on day of checkout so not included
    	
    	int roomIndex = this.getRoomIndex(roomNum);
    	
    	if(roomIndex == -1) {
    		System.out.println("Error booking room, room does not exist");
    		return false;//room does not exist
    	}
    	
    	//checking availability of rooms
 		for(int d: roomsList.get(roomIndex).getDatesBooked()) {
 			   for(int b: bookDates) {
 				   if(d == b) {
 					  System.out.println("Tried booking booked room");
 					   return false;
 				   }
 			   }
 	   }
 	   
 	   roomsList.get(roomIndex).setDatesBooked(checkIn, checkOut);
 	   reservationsList.add(new Reservation(guestName, checkIn, checkOut, roomsList.get(roomIndex), discountCode, this.ratesList));
 	   return true;
    }
    
    // removes reservation, adjusts dates booked of room
	/**
	 *  Removes a reservation given by a certain guest's name
	 * 	@param guestName is a string that is within a reservation; used for searching the list.
	 *  @return true if successfully removed, false if otherwise.
	 */
    public boolean removeReservation(int guestIndex) {
    	//int guestIndex;
    	//String roomName;
    	int reservationDay = 0;
    	
    	int k = 0;
    	
    	
    			//roomName = this.reservationsList.get(i).getRoomInfo().getRoomName();
    			for(int j = 0; j < this.roomsList.size(); j++) {
    				if(this.reservationsList.get(guestIndex).getRoomInfo().getRoomName() == this.roomsList.get(j).getRoomName()) {
    					while( k < this.roomsList.get(j).getDatesBooked().size()) {
    						
    						if(this.roomsList.get(j).getDatesBooked().get(k) == this.reservationsList.get(guestIndex).getDaysStay().get(reservationDay) ) {
    							this.roomsList.get(j).getDatesBooked().remove(k);
    							if(reservationDay < this.reservationsList.get(guestIndex).getDaysStay().size() - 1) {
    								reservationDay++;
    							}
    						}
    						else {
    							k++;
    						}
    						
    					
    			}
    			
    			this.reservationsList.remove(guestIndex);
    			return true;
    		}
    
    	}
    	return false;	
    }
    
    
	/**
	 * Adds rooms to a hotel depending on the amount wanted if the max rooms have not been reached.
	 * @param numRooms is an integer, the number of rooms that the user wants to be added.
	 * @return true if successfully added, false if otherwise.
	 */
    public boolean addRoom(int numRooms, String roomType) {
    	if(this.roomsList.size() + numRooms > 50 || numRooms < 1) {
    		return false;
    	}
    	
    	int roomName = this.roomsList.getLast().getRoomName() + 1;
    	for(int i = 0; i < numRooms; i++) {
    		this.roomsList.add(new Room(roomName, this.getPrice(), roomType));
    		roomName++;
    	}
    	return true;
    }
    
	/**
	 * Removes rooms that don't have a reservation, given an integer.
	 * @param roomNum is an integer; the amount of rooms that the user wants to be removed.
	 * @return true if succesfully removed, false if otherwise.
	 */
    public boolean removeRoom(int roomNum) {
    	
    	int roomIndex = this.getRoomIndex(roomNum);
    	if(roomIndex == -1) {
    		System.out.println("Error cannot remove room that does not exist");
    		return false;
    	}
    	
    	if(this.roomsList.get(roomIndex).getDatesBooked().size() != 0) {
    		return false;
    	}
    	else
    	{
    		this.roomsList.remove(roomIndex);
    		//rearrange naming scheme
    		/*
    		int n = 1;
    		for(int i = 0; i < this.roomsList.size(); i++) {
    			this.roomsList.get(i).changeRoomName("" + n);
    			n++;
    		}
    		*/
    		return true;
    	}
    }
   
	/**
	 * Changes the name of the hotel name.
	 * @param newName is a string that signifies the new name of the hotel.
	 * @return true
	 */
    public boolean changeHotelName(String newName) {
    	this.hotelName = newName;
    	return true;
    }
    
    /*
    public void showRoomList() {
    	int n = 1;
    	for(int i = 0; i < this.roomsList.size(); i++) {
    		System.out.format("%8s" , this.roomsList.get(i).getRoomName());
    		n++;
    		
    		if(n % 6 == 0) {
    			System.out.print("  ");
    			System.out.println();
    			n++;
    		}
    		
    	}

    	System.out.println();
    }
    */
    
	/**
	 * returns the index of a room, given a room number.
	 * @param roomNum is an integer; the number of the room that the user wants to find the index of.
	 * @return the index of the room if successful, -1 if otherwise
	 */
    public int getRoomIndex(int roomNum) {
    	int roomIndex = -1;
    	for(int i = 0; i < this.roomsList.size(); i++) {
    		if(this.roomsList.get(i).getRoomName() == roomNum) {
    			roomIndex = i;
    		}
    	}
    	
    	return roomIndex;
    }
    
	/**
	 * returns the index of a reservation, given the guest's name.
	 * @param guest is a string, which is the guest's name in the reservation.
	 * @return the index of the reservation if successful, -1 if otherwise.
	 */
    public int getReservIndex(String guest) {
    	int index = -1;
    	for(int i = 0; i < this.reservationsList.size(); i++) {
    		if(this.reservationsList.get(i).getGuestName().equals(guest)) {
    			index = i;
    		}
    	}
    	
    	return index;
    }
    
    //returns list of rooms goods on a single date
	/**
	 * Returns the list of rooms that aren't booked, given a single date.
	 * @param date is an integer, the day in where you want to find all free rooms.
	 * @return a list of integers, they signify the rooms that are not booked in that day.
	 */
    public ArrayList<Integer> getRoomAvailability(int date){
    	ArrayList<Integer> rooms = new ArrayList<Integer>();//rooms available on this date
    	boolean present;
    	for(int i = 0; i < this.roomsList.size(); i++) {
    		present = false;
    		for(int j = 0; j < this.roomsList.get(i).getDatesBooked().size(); j++) {
    			if(this.roomsList.get(i).getDatesBooked().get(j) == date) {
    				present = true;
    			}
    		}
    		if(present == false) {
    			rooms.add(this.roomsList.get(i).getRoomName());
    		}
    		
    	}
    	
    	return rooms;
    }
     
	/**
	 * Returns the list of rooms that aren't booked, within a span of time greater than a 1 day.
	 * @param checkIn is an integer; the start of the timespan
	 * @param checkOut is an integer; the end of the timespan
	 * @return a list of integers, they signify the rooms that are not booked in that day.
	 */
    public ArrayList<Integer> getRoomAvailability(int checkIn, int checkOut){
    	ArrayList<Integer> rooms = new ArrayList<Integer>();//rooms available on this date
    	int count = 0;
    	int dateIndex;
    	ArrayList<Integer> bookDates = new ArrayList<Integer>();
    	int n = checkIn;
    	do {
    		bookDates.add(n);
    		n++;
    	}while(n < checkOut);// room is available again on day of checkout so not included
    	
    	for(int i = 0; i < this.roomsList.size(); i++) {
    		count = 0;
    		dateIndex = 0;
    		for(int j = 0; j < this.roomsList.get(i).getDatesBooked().size(); j++) {
    			if(this.roomsList.get(i).getDatesBooked().get(j) == bookDates.get(dateIndex)) {
    				count++;
    				dateIndex++;
    			}
    		}
    		if(count == bookDates.size()) {
    			rooms.add(this.roomsList.get(i).getRoomName());
    		}
    	}
    	
    	return rooms;
    }
    
	/**
	 * Changes the price of staying in a room in the hotel, depending on the new given price.
	 * @param price is the new price that the user wants it changed to.
	 * @return true if successful, false if unsuccessful. The minimum price is 100.
	 */
    public boolean changeBasePrice(float price) {
    	if(this.reservationsList.size() != 0 ) {
    		return false;
    	}
    	if(price < 100) {
    		return false;
    	}
    	this.price = price;
    	
    	for(int i = 0; i < roomsList.size(); i++) {
    		roomsList.get(i).changePrice(price);
    	}
    	
    	return true;
    }

    /**
	 * Returns the total expected earnings of a hotel.
	 * @return a float, the sum of every reservation in a hotel.
	 */
    public float getTotalEarnings() {
    	float sum = 0;
    	for(int  i = 0; i < this.reservationsList.size(); i++) {
    		sum = sum + this.reservationsList.get(i).getTotalPay();
    	}
    	
    	return sum;
    }
    
	/**
	 * Returns the ArrayList of rooms, where all the rooms are stored within a hotel.
	 * @return the ArrayList of rooms
	 */
    public ArrayList<Room> getRoomsList() {
    	return roomsList;
    }
    
	/**
	 * Returns the ArrayList of reservations, where all reservations are stored within a hotel.
	 * @return the ArrayList of reservations.
	 */
    public ArrayList<Reservation> getReservationsList() {
    	return reservationsList;
    }
    
	/**
	 * Returns the name of the hotel.
	 * @return a string, the name of the hotel.
	 */
    public String getHotelName() {
    	return this.hotelName;
    }
    /**Getter for the number of standard rooms
     * @return int representing number of standard rooms for this hotel
     */
    public int getNumStandard() {
    	return this.numStandard;
    }
    /**
     * Getter for number of deluxe rooms
     * @return int representing the number of deluxe rooms
     */
    public int getNumDeluxe() {
    	return this.numDeluxe;
    }
    /**
     * getter for the number of executive rooms
     * @return int representing the number of executive rooms
     */
    public int getNumExec() {
    	return this.numExec;
    }
    /**
     * Setter for incrementing or decrementing the number of standard rooms
     * @param numAddDeluxe int that num standard rooms will be adjusted by
     */
    public void setNumStandard(int numAddStandard) {
    	this.numStandard += numAddStandard;
    }
    /**
     * Setter for incrementing or decrementing the number of deluxe rooms
     * @param numAddDeluxe int that numDeluxe will be adjusted by
     */
    public void setNumDeluxe(int numAddDeluxe) {
    	this.numDeluxe += numAddDeluxe;
    }
    /**
     * Setter for incrementing or decrementing the number of executive rooms
     * @param numAddExecutive int that numExective will be adjusted by
     */
    public void setNumExecutive(int numAddExecutive) {
    	this.numExec += numAddExecutive;
    }
    
	/**
	 * Returns the price of the rooms in the hotel.
	 * @return a float value; the price of the rooms in the hotel.
	 */
    public float getPrice() {
    	return this.price;
    }
    /**
     * Getter for the rates list of this hotel
     * @return the rates list for this hotel
     */
    public ArrayList<Float> getRatesList(){
		return this.ratesList;
	}
}