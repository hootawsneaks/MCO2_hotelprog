import java.util.ArrayList;

public class Reservation{
    private String guestName;
    private int checkIn;
    private int checkOut;
    private Room room;
    private ArrayList<Float> ratesList = new ArrayList<Float>();
    private ArrayList<Float> costPerNightList = new ArrayList<Float>();
    private String discountCode;

    
    /**
	 * Class constructor for a reservation; the guest's name, check in date, 
     * check out date, and room number need to be indicated.
	 */
    public Reservation(String guestName, int checkIn, int checkOut, Room room, String discountCode, ArrayList<Float> ratesList) {
    	this.guestName = guestName;
    	this.checkIn = checkIn;
    	this.checkOut = checkOut;
    	this.room = room;
    	this.discountCode = discountCode;
    	this.ratesList = ratesList;
    	
    	this.setCostPerNightList();
    }
    
    //gets passed hotels updated rates list 
    public boolean changeRatesList(ArrayList<Float> ratesList){
		this.ratesList = ratesList;
		return true;
	}
    
    
    /**
	 * Returns the days in which reservation will last.
     * @return a list of days (like in a calendar) where a reservation will last.
	 */
    public ArrayList<Integer> getDaysStay(){
    	ArrayList<Integer> booked= new ArrayList<Integer>();
    	int n = this.checkIn;
    	do {
    		booked.add(n);
    		n++;
    	}while(n < this.checkOut);
    	return booked;
    }
    
    /**
	 * Get the total pay that the reservation will cost.
     * @return the total cost of the reservation.
	 */
    /**
	 * Get the total pay that the reservation will cost.
     * @return the total cost of the reservation.
	 */
    public float getTotalPay() {
    	int i = 0;
        int sum = 0;
        for(i = this.checkIn-1; i<this.checkOut-1; i++){
            sum += this.room.getPrice()*ratesList.get(i);
        }
        if(this.discountCode.equals("I_WORK_HERE")){
            return sum * 0.9f;
        }
        if(this.discountCode.equals("STAY4_GET1")){
            if(this.checkOut - this.checkIn >= 5){
                return sum - this.room.getPrice();
            }
        }
        if(this.discountCode.equals("PAYDAY")){
            for(i = 0; i< getDaysStay().size(); i++){  // change to -1 in case that checkout is included
                if(getDaysStay().get(i) == 15 || getDaysStay().get(i) == 28){
                    return sum * 0.93f;
                }
            }
        }
        return sum;
    }
    
    /**
	 * Sets a room's info, given another room's details.
	 * @param room is an object that stores all the details of a room.
	 */
    public void setRoomInfo(Room room) {
    	this.room = room;
    }
    
    /**
	 * Gets the name of the guest that did the reservation.
     * @return a string; the guest's name that reserved.
	 */
    public String getGuestName() {
    	return this.guestName;
    }
    
    /**
	 * Get the check in date of a given reservation.
     * @return an integer, the check in date of a reservation.
	 */
    public int getCheckIn() {
    	return this.checkIn;
    }
    
    /**
	 * Get the check out date of a given reservation.
     * @return an integer, the check out date of a reservation.
	 */
    public int getCheckOut() {
    	return this.checkOut;
    }
    
    /**
	 * Returns the information of a room, via the room object itself.
     * @return the room object where the reservation was made.
	 */
    public Room getRoomInfo() {
    	return this.room;
    }
    
    public void setCostPerNightList(){
        for(int i = 0; i < getDaysStay().size(); i++){
            this.costPerNightList.add(this.room.getPrice() * this.ratesList.get(i));
        }
    }
    
    public ArrayList<Float> getCostPerNightList(){
    	return this.costPerNightList;
    }
}

