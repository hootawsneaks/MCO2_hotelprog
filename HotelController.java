import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * controller for hotel reservaton system
 * coordinates between model and view
 */
public class HotelController {
	private HotelView hotelView;
	private HotelModel hotelModel;
	
	/**
	 * Constructor for hotelController, takes in hotelView and hotelModel
	 * has the logic for all the input checks and displays
	 * @param hotelView user input and feedback
	 * @param hotelModel hotel information and data
	 */
	public HotelController(HotelView hotelView, HotelModel hotelModel){
		this.hotelModel = hotelModel;
		this.hotelView = hotelView;
		
		this.hotelView.setAddHotelActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.togglePanelVisibility(1);
				displayHotelList(0);
			}
		});
		
		this.hotelView.setFinalHotelActnListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String hotelName = hotelView.getTextFieldList().get(0).getText();
					String numStandard = hotelView.getTextFieldList().get(1).getText();
					String numDeluxe = hotelView.getTextFieldList().get(2).getText();
					String numExec = hotelView.getTextFieldList().get(3).getText();
					String basePrice = hotelView.getTextFieldList().get(4).getText();
				
					
					boolean result = hotelModel.addHotel(hotelName, numStandard, numDeluxe, numExec, basePrice);
					
					if(result) {
						hotelView.setDisplayText("Hotel Successfully Created", 1);
						hotelView.clearTextFields();
						displayHotelList(0);
						
					}
					else {
						hotelView.setDisplayText("Invalid Input/s:\n Hotel Name must be unique and valid\n Total number of rooms cannot exceed 50\n Base Price At least 100\n", 1);
					}
				}
			});
		
		this.hotelView.setViewHotelActnListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.togglePanelVisibility(2);
				displayHotelList(2);
				
				if(hotelModel.getHotelList().size() != 0) {
					hotelView.getTextFieldList().get(5).setEditable(true);
					hotelView.getButtonList().get(5).setEnabled(true);
				}
				else {
					hotelView.getTextFieldList().get(5).setEditable(false);
					hotelView.getButtonList().get(5).setEnabled(false);
					hotelView.getTextFieldList().get(5).setText("No Hotels Currently");
				}
			}
		});
		
		this.hotelView.setChooseHotelActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String hotelChoice = hotelView.getTextFieldList().get(5).getText();
				//hotelView.togglePanelVisibility(5);
				int hotelIndex = hotelModel.getHotelIndex(hotelChoice);
				if(hotelIndex != -1) {
					hotelView.getButtonList().get(6).setVisible(true);
					hotelView.getButtonList().get(7).setVisible(true);
					hotelView.getButtonList().get(8).setVisible(true);
					hotelView.getTextFieldList().get(6).setVisible(true);
					hotelView.getTextFieldList().get(7).setVisible(true);
					//hotelView.getTextFieldList().get(8).setVisible(true);
					hotelView.getTextAreaList().get(3).setVisible(true);
					hotelView.getTextAreaList().get(4).setVisible(false);
					hotelView.getTextAreaList().get(5).setVisible(true);
					hotelView.getTextAreaList().get(6).setVisible(false);
					hotelView.getTextAreaList().get(7).setVisible(true);
					hotelView.getTextAreaList().get(8).setVisible(false);
					hotelView.getTextAreaList().get(9).setVisible(true);
					hotelView.getComboBoxList().get(1).removeAllItems();
					hotelView.getComboBoxList().get(1).setVisible(true);
					
					String hotelInfo = "Hotel Info:                \n";
					hotelInfo = hotelInfo + "Hotel name: " + hotelModel.getHotelList().get(hotelIndex).getHotelName() + "\n";
					hotelInfo = hotelInfo + "Standard Rooms: " + hotelModel.getHotelList().get(hotelIndex).getNumStandard() + "\n";
					hotelInfo = hotelInfo + "Deluxe Rooms: " + hotelModel.getHotelList().get(hotelIndex).getNumDeluxe() + "\n";
					hotelInfo = hotelInfo + "Executive Rooms: " + hotelModel.getHotelList().get(hotelIndex).getNumExec() + "\n";
					hotelInfo = hotelInfo + "Base Price Per Night: " + hotelModel.getHotelList().get(hotelIndex).getPrice() + "\n";
					hotelInfo = hotelInfo + "Total Earnings: " + hotelModel.getHotelList().get(hotelIndex).getTotalEarnings();
					hotelView.getTextAreaList().get(9).setText(hotelInfo);
					
					
					hotelView.getTextAreaList().get(3).setText("Check Available Rooms for Certain Date: (1-30)");
					
					int newLine = 0;
					String roomsList = "Choose a Room to View:                        ";
					for(int i = 0; i < hotelModel.getHotelList().get(hotelIndex).getRoomsList().size(); i++) {
						if(newLine % 10 == 0) {
							roomsList = roomsList + "\n";
						}
						roomsList = roomsList + hotelModel.getHotelList().get(hotelIndex).getRoomsList().get(i).getRoomName() + "     ";
						newLine++;
					}
					
					hotelView.getTextAreaList().get(5).setText(roomsList);
			
					/*
					String reservationList = "Choose a Reservation to View:                         \n";	
					if(hotelModel.getHotelList().get(hotelIndex).getReservationsList().size() != 0) {
						hotelView.getButtonList().get(8).setEnabled(true);
						hotelView.getTextFieldList().get(8).setEditable(true);
						hotelView.getTextFieldList().get(8).setText("");
						
						for(int i = 0; i < hotelModel.getHotelList().get(hotelIndex).getReservationsList().size(); i++) {
							reservationList = reservationList + (i+1)+  " - "+ hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(i).getGuestName() + 
									"     ";
						}
						hotelView.getTextFieldList().get(8).setText("Input Number of Reservation to View");
					}
					else {
						reservationList = reservationList + "-No Reservations Booked Currently-";
						hotelView.getButtonList().get(8).setEnabled(false);
						hotelView.getTextFieldList().get(8).setEditable(false);
						hotelView.getTextFieldList().get(8).setText("No Reservations Currently");
					}
					
					hotelView.getTextAreaList().get(7).setText(reservationList);
					*/
					
					
				}
				else {
					hotelView.getTextAreaList().get(9).setText("Hotel Does Not Exist");
					hotelView.getTextAreaList().get(9).setVisible(true);
					hotelView.getButtonList().get(6).setVisible(false);
					hotelView.getButtonList().get(7).setVisible(false);
					hotelView.getButtonList().get(8).setVisible(false);
					hotelView.getTextFieldList().get(6).setVisible(false);
					hotelView.getTextFieldList().get(7).setVisible(false);
					hotelView.getTextFieldList().get(8).setVisible(false);
					hotelView.getTextAreaList().get(3).setVisible(false);
					hotelView.getTextAreaList().get(4).setVisible(false);
					hotelView.getTextAreaList().get(5).setVisible(false);
					hotelView.getTextAreaList().get(6).setVisible(false);
					hotelView.getTextAreaList().get(7).setVisible(false);
					hotelView.getTextAreaList().get(8).setVisible(false);
					hotelView.getComboBoxList().get(1).setVisible(false);
				}
			}
		});
				
		this.hotelView.setCheckAvailDateActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.getTextAreaList().get(4).setVisible(true);
				hotelView.getTextAreaList().get(6).setVisible(false);
				hotelView.getTextAreaList().get(8).setVisible(false);
				
				String hotelChoice = hotelView.getTextFieldList().get(5).getText();
				String hotelIndex = "" + hotelModel.getHotelIndex(hotelChoice);
				String date = hotelView.getTextFieldList().get(6).getText();
				int newLine = 0;
				
				if(hotelModel.viewDateAvailability(date, hotelIndex).get(0) != -1 ) {
					String roomsAvailable = "Available Rooms: " + hotelModel.viewDateAvailability(date, hotelIndex).size() + "\n";
					roomsAvailable = roomsAvailable + "Unavailable Rooms: " + (hotelModel.getHotelList().get(Integer.parseInt(hotelIndex)).getRoomsList().size()-hotelModel.viewDateAvailability(date, hotelIndex).size()) + "\nAvailable Rooms are: ";
					for(int i = 0; i < hotelModel.viewDateAvailability(date, hotelIndex).size(); i++) {
						if(newLine % 10 == 0) {
							roomsAvailable = roomsAvailable + "\n";
						}
						roomsAvailable = roomsAvailable + hotelModel.viewDateAvailability(date, hotelIndex).get(i) + "     ";
						newLine++;
					}
					
					
					hotelView.getTextAreaList().get(4).setText(roomsAvailable);
				}
				else {
					hotelView.getTextAreaList().get(4).setText("Invalid Date");
				}
			}
			
		});
		
		this.hotelView.setViewRoomInfoActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.getTextAreaList().get(6).setVisible(true);
				hotelView.getTextAreaList().get(4).setVisible(false);
				hotelView.getTextAreaList().get(8).setVisible(false);
				
				String roomChosen = hotelView.getTextFieldList().get(7).getText();
				String hotelIndex = "" + hotelModel.getHotelIndex(hotelView.getTextFieldList().get(5).getText());
				
				String roomInfo = "Room Info:                \n";
				
				int newLine = 0;
				
				 if(hotelModel.viewRoomInformation(roomChosen, hotelIndex).getRoomName() != -1) {
					 roomInfo = roomInfo + "Room Name: " + hotelModel.viewRoomInformation(roomChosen, hotelIndex).getRoomName() + "\n";
					 roomInfo = roomInfo + "Room Type: " + hotelModel.viewRoomInformation(roomChosen, hotelIndex).getRoomType() + "\n";
					 roomInfo = roomInfo + "Price per night: " + hotelModel.viewRoomInformation(roomChosen, hotelIndex).getPrice() + "\n";
					 roomInfo = roomInfo + "Booked Dates:                ";
					 
					 if(hotelModel.viewRoomInformation(roomChosen, hotelIndex).getDatesBooked().size() == 0) {
						roomInfo = roomInfo + "-No Dates Booked Currently-";
					 }
					 else {
						 for(int i = 0; i < hotelModel.viewRoomInformation(roomChosen, hotelIndex).getDatesBooked().size(); i++) {
							 if(newLine % 10 == 0) {
									roomInfo = roomInfo + "\n";
								}
							 roomInfo = roomInfo + hotelModel.viewRoomInformation(roomChosen, hotelIndex).getDatesBooked().get(i)+ "     ";
							 newLine++;
						 }
					 }
					 
					 hotelView.getTextAreaList().get(6).setText(roomInfo);
				 }
				 else {
					 hotelView.getTextAreaList().get(6).setText("Choose a valid room");
				 }
			}
			
		});
		/*
		this.hotelView.setViewReservationActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.getTextAreaList().get(8).setVisible(true);
				hotelView.getTextAreaList().get(6).setVisible(false);
				hotelView.getTextAreaList().get(4).setVisible(false);
				
				
				String reservationNum = hotelView.getTextFieldList().get(8).getText();
				String hotelIndex = "" + hotelModel.getHotelIndex(hotelView.getTextFieldList().get(5).getText());
				
				String reserveInfo = "Reservation Info:                \n";
				
				 if(hotelModel.viewReservationInformation(reservationNum, hotelIndex).getCheckIn() != -1) {
					 reserveInfo = reserveInfo + "Guest Name: " + hotelModel.viewReservationInformation(reservationNum, hotelIndex).getGuestName() + "\n";
					 reserveInfo = reserveInfo + "Check-in: " + hotelModel.viewReservationInformation(reservationNum, hotelIndex).getCheckIn() + "\n";
					 reserveInfo = reserveInfo + "Check-out: " + hotelModel.viewReservationInformation(reservationNum, hotelIndex).getCheckOut() + "\n";
					 //reserveInfo = reserveInfo + "Cost: " + hotelModel.viewReservationInformation(reservationNum, hotelIndex).getCheckIn() + "\n";
					 
					 hotelView.getTextAreaList().get(8).setText(reserveInfo);
				 }
				 else {
					 hotelView.getTextAreaList().get(8).setText("Input a valid reservation Number");
				 }
			}
			
		});
		*/
		
		this.hotelView.setManageHotelActnListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.togglePanelVisibility(3);
				displayHotelList(10);
				hotelView.getTextAreaList().get(11).setVisible(false);
				
				if(hotelModel.getHotelList().size() != 0) {
					hotelView.getButtonList().get(9).setEnabled(true);
					hotelView.getTextFieldList().get(9).setEditable(true);
				}
				else {
					hotelView.getButtonList().get(9).setEnabled(false);
					hotelView.getTextFieldList().get(9).setEditable(false);
					hotelView.getTextFieldList().get(9).setText("No Hotels Exist Currently");
				}
			}
			
		});
		
		this.hotelView.setChooseManageHotelActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String hotelChoice = hotelView.getTextFieldList().get(9).getText();
				int hotelIndex = hotelModel.getHotelIndex(hotelChoice);
				
				if(hotelIndex != -1) {
					for(int i = 11; i < 16; i++) {
						hotelView.getTextAreaList().get(i).setVisible(true);
					}
					
					for(int i = 10; i < 17; i++) {
						hotelView.getTextFieldList().get(i).setVisible(true);
					}
					
					for(int i = 10; i < 18; i++) {
						hotelView.getButtonList().get(i).setVisible(true);
					}
					
					hotelView.getTextAreaList().get(24).setVisible(true);
					hotelView.getTextFieldList().get(23).setVisible(true);
					hotelView.getButtonList().get(20).setVisible(true);
					hotelView.getComboBoxList().get(0).setVisible(true);
					
					hotelView.getTextAreaList().get(11).setText("Hotel Name: " + hotelModel.getHotelList().get(hotelIndex).getHotelName() + "                                                                                                          ");
					
					displayNumRooms(hotelIndex);
					
					displayRoomsList(13, hotelIndex);
					
					hotelView.getTextAreaList().get(14).setText("Hotel Base Price: " + hotelModel.getHotelList().get(hotelIndex).getPrice());
					
					String reservationList = "Reservations:              \n";
					if(hotelModel.getHotelList().get(hotelIndex).getReservationsList().size() != 0) {
						hotelView.getTextFieldList().get(14).setEditable(true);
						hotelView.getButtonList().get(14).setEnabled(true);
						hotelView.getTextFieldList().get(14).setText("");
						for(int i = 0; i < hotelModel.getHotelList().get(hotelIndex).getReservationsList().size(); i++) {
							reservationList = reservationList + (i+1)+  " - "+ hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(i).getGuestName() + "\n";
						}
						reservationList = reservationList + "                                                                                                                                                                                                                                     ";
					}
					else {
						reservationList = reservationList + "-No Reservations Booked Currently-                                          ";
						hotelView.getTextFieldList().get(14).setEditable(false);
						hotelView.getTextFieldList().get(14).setText("No reservations currently");
						hotelView.getButtonList().get(14).setEnabled(false);
					}
					
					
					hotelView.getTextAreaList().get(15).setText(reservationList);
					
					String dateModifierPrompt = "Rates on Days of The Month: \n";
					dateModifierPrompt = dateModifierPrompt + "Day 1: " + hotelModel.getHotelList().get(hotelIndex).getRatesList().get(0) + " times base price"; 
					
					hotelView.getTextAreaList().get(24).setText(dateModifierPrompt);
					
					}
				else {
					hotelView.getTextAreaList().get(11).setText("Invalid Hotel Input");
					hotelView.getTextAreaList().get(11).setVisible(true);
					for(int i = 12; i < 16; i++) {
						hotelView.getTextAreaList().get(i).setVisible(false);
					}
					
					for(int i = 10; i < 17; i++) {
						hotelView.getTextFieldList().get(i).setVisible(false);
					}
					
					for(int i = 10; i < 18; i++) {
						hotelView.getButtonList().get(i).setVisible(false);
					}
					
					hotelView.getTextAreaList().get(24).setVisible(false);
					hotelView.getTextFieldList().get(23).setVisible(false);
					hotelView.getButtonList().get(20).setVisible(false);
					hotelView.getComboBoxList().get(0).setVisible(false);
				}
			}
			
		});
		
		this.hotelView.setChangeNameActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int i = 0; i < hotelView.getConfirmationButtonList().size(); i++) {
					hotelView.getConfirmationButtonList().get(i).setVisible(false);
				}
				
				String hotelName = hotelView.getTextFieldList().get(10).getText();
				boolean valid = true;
				for(int i = 0; i < hotelModel.getHotelList().size(); i++) {
					if(hotelName.equals(hotelModel.getHotelList().get(i).getHotelName())) {
						valid = false;
					}
				}
				if(hotelName.equals("")) {
					valid = false;
				}
				
				if(valid) {
					hotelView.getConfirmationButtonList().get(0).setVisible(true);
					hotelView.getConfirmationButtonList().get(1).setVisible(true);
					lockManagementInput();
				}
				else {
					hotelView.getTextFieldList().get(10).setText("Hotel Name Already Exists");
				}	
			}
		});
		
		this.hotelView.setConfirmChangeNameYesActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int hotelIndex = hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				String newHotelName = hotelView.getTextFieldList().get(10).getText();
				
				hotelModel.getHotelList().get(hotelIndex).changeHotelName(newHotelName);
				
				hotelView.getConfirmationButtonList().get(0).setVisible(false);
				hotelView.getConfirmationButtonList().get(1).setVisible(false);
				hotelView.getTextFieldList().get(10).setText("");
				hotelView.getTextAreaList().get(11).setText("Hotel Name: " + hotelModel.getHotelList().get(hotelIndex).getHotelName() + "                                                                                                          ");
				displayHotelList(10);
				hotelView.getTextFieldList().get(9).setText(newHotelName);
				unlockManagementInput();
			}
		});
		
		this.hotelView.setAddStandardActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < hotelView.getConfirmationButtonList().size(); i++) {
					hotelView.getConfirmationButtonList().get(i).setVisible(false);
				}
				
				String hotelIndex ="" + hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				String addNumStandard = hotelView.getTextFieldList().get(11).getText();
				
				boolean valid = hotelModel.addStandardRoom(addNumStandard, hotelIndex);
				
				if(valid) {
					hotelView.getConfirmationButtonList().get(2).setVisible(true);
					hotelView.getConfirmationButtonList().get(3).setVisible(true);
					lockManagementInput();
					
				}
				else {
					hotelView.getTextFieldList().get(11).setText("Invalid Number");
				}
			}
		});
		
		this.hotelView.setConfirmAddStandardYesActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int hotelIndex = hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				String addNumStandard = hotelView.getTextFieldList().get(11).getText();
				
				hotelView.getConfirmationButtonList().get(2).setVisible(false);
				hotelView.getConfirmationButtonList().get(3).setVisible(false);
				
				hotelModel.getHotelList().get(hotelIndex).addRoom(Integer.parseInt(addNumStandard), "Standard");
				hotelModel.getHotelList().get(hotelIndex).setNumStandard(Integer.parseInt(addNumStandard));
				hotelView.getTextFieldList().get(11).setText("");
				
				displayNumRooms(hotelIndex);
				displayRoomsList(13, hotelIndex);
				unlockManagementInput();
			}
		});
		
		this.hotelView.setAddDeluxeActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < hotelView.getConfirmationButtonList().size(); i++) {
					hotelView.getConfirmationButtonList().get(i).setVisible(false);
				}
				
				String hotelIndex ="" + hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				String addNumDeluxe = hotelView.getTextFieldList().get(15).getText();
				
				boolean valid = hotelModel.addDeluxeRoom(addNumDeluxe, hotelIndex);
				
				if(valid) {
					hotelView.getConfirmationButtonList().get(12).setVisible(true);
					hotelView.getConfirmationButtonList().get(13).setVisible(true);
					lockManagementInput();
					
				}
				else {
					hotelView.getTextFieldList().get(15).setText("Invalid Number");
				}
			}
		});
		
		this.hotelView.setConfirmAddDeluxeYesActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int hotelIndex = hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				String addNumDeluxe = hotelView.getTextFieldList().get(15).getText();
				
				hotelView.getConfirmationButtonList().get(12).setVisible(false);
				hotelView.getConfirmationButtonList().get(13).setVisible(false);
				
				hotelModel.getHotelList().get(hotelIndex).addRoom(Integer.parseInt(addNumDeluxe), "Deluxe");
				hotelModel.getHotelList().get(hotelIndex).setNumDeluxe(Integer.parseInt(addNumDeluxe));
				hotelView.getTextFieldList().get(15).setText("");
				
				displayNumRooms(hotelIndex);
				displayRoomsList(13, hotelIndex);
				unlockManagementInput();
			}
		});
		
		this.hotelView.setAddExecutiveActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < hotelView.getConfirmationButtonList().size(); i++) {
					hotelView.getConfirmationButtonList().get(i).setVisible(false);
				}
				
				String hotelIndex ="" + hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				String addNumExecutive = hotelView.getTextFieldList().get(16).getText();
				
				boolean valid = hotelModel.addExecutiveRoom(addNumExecutive, hotelIndex);
				
				if(valid) {
					hotelView.getConfirmationButtonList().get(14).setVisible(true);
					hotelView.getConfirmationButtonList().get(15).setVisible(true);
					lockManagementInput();
					
				}
				else {
					hotelView.getTextFieldList().get(16).setText("Invalid Number");
				}
			}
		});
		
		this.hotelView.setConfirmAddExecutiveYesActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int hotelIndex = hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				String addNumExecutive = hotelView.getTextFieldList().get(16).getText();
				
				hotelView.getConfirmationButtonList().get(14).setVisible(false);
				hotelView.getConfirmationButtonList().get(15).setVisible(false);
				
				hotelModel.getHotelList().get(hotelIndex).addRoom(Integer.parseInt(addNumExecutive), "Executive");
				hotelModel.getHotelList().get(hotelIndex).setNumExecutive(Integer.parseInt(addNumExecutive));
				hotelView.getTextFieldList().get(16).setText("");
				
				displayNumRooms(hotelIndex);
				displayRoomsList(13, hotelIndex);
				unlockManagementInput();
			}
		});
		
		this.hotelView.setRemoveRoomActnListenerr(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < hotelView.getConfirmationButtonList().size(); i++) {
					hotelView.getConfirmationButtonList().get(i).setVisible(false);
				}
				
				String hotelIndex = "" + hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				String roomToRemove = hotelView.getTextFieldList().get(12).getText();
				
				boolean valid = hotelModel.removeRoom(roomToRemove, hotelIndex);
				
				if(valid) {
					hotelView.getConfirmationButtonList().get(4).setVisible(true);
					hotelView.getConfirmationButtonList().get(5).setVisible(true);
					lockManagementInput();
				}
				else {
					hotelView.getTextFieldList().get(12).setText("Input Room From List");
				}
				
			}
		});
		
		this.hotelView.setConfirmRemoveRoomYesActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int hotelIndex = hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				String roomToRemove = hotelView.getTextFieldList().get(12).getText();
				
				hotelView.getConfirmationButtonList().get(4).setVisible(false);
				hotelView.getConfirmationButtonList().get(5).setVisible(false);
				
				//get roomType
				int roomIndex = hotelModel.getHotelList().get(hotelIndex).getRoomIndex(Integer.parseInt(roomToRemove));
				if(hotelModel.getHotelList().get(hotelIndex).getRoomsList().get(roomIndex).getRoomType().equals("Standard")) {
					hotelModel.getHotelList().get(hotelIndex).setNumStandard(-1);
				}
				else if(hotelModel.getHotelList().get(hotelIndex).getRoomsList().get(roomIndex).getRoomType().equals("Deluxe")) {
					hotelModel.getHotelList().get(hotelIndex).setNumDeluxe(-1);
				}
				else if(hotelModel.getHotelList().get(hotelIndex).getRoomsList().get(roomIndex).getRoomType().equals("Executive")) {
					hotelModel.getHotelList().get(hotelIndex).setNumExecutive(-1);
				}
				
				hotelModel.getHotelList().get(hotelIndex).removeRoom(Integer.parseInt(roomToRemove));
				hotelView.getTextFieldList().get(12).setText("");
				
				displayNumRooms(hotelIndex);
				displayRoomsList(13, hotelIndex);
				unlockManagementInput();
			}
		});
		
		this.hotelView.setUpdatePriceActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < hotelView.getConfirmationButtonList().size(); i++) {
					hotelView.getConfirmationButtonList().get(i).setVisible(false);
				}
				
				String newPrice = hotelView.getTextFieldList().get(13).getText();
				String hotelIndex = "" + hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				
				boolean valid = hotelModel.updatePrice(newPrice, hotelIndex);
				
				if(valid) {
					hotelView.getConfirmationButtonList().get(6).setVisible(true);
					hotelView.getConfirmationButtonList().get(7).setVisible(true);
					lockManagementInput();
				}
				else if(hotelModel.getHotelList().get(Integer.parseInt(hotelIndex)).getReservationsList().size() != 0 && valid == false){
					hotelView.getTextFieldList().get(13).setText("Hotel Booked");
				}
				else {
					hotelView.getTextFieldList().get(13).setText("Invalid price");
				}
				
			}
		});
		
		this.hotelView.setConfirmUpdatePriceYesActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int hotelIndex = hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				String newPrice= hotelView.getTextFieldList().get(13).getText();
				
				hotelView.getConfirmationButtonList().get(6).setVisible(false);
				hotelView.getConfirmationButtonList().get(7).setVisible(false);
				
				hotelModel.getHotelList().get(hotelIndex).changeBasePrice(Float.parseFloat(newPrice));
				hotelView.getTextFieldList().get(13).setText("");
				
				hotelView.getTextAreaList().get(14).setText("Hotel Base Price: " + hotelModel.getHotelList().get(hotelIndex).getPrice());
				unlockManagementInput();
			}
		});
		
		this.hotelView.setRemoveReservationActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < hotelView.getConfirmationButtonList().size(); i++) {
					hotelView.getConfirmationButtonList().get(i).setVisible(false);
				}
				
				String hotelIndex = "" + hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				String reservationIndex = hotelView.getTextFieldList().get(14).getText();
				
				boolean valid = hotelModel.removeReservation(reservationIndex, hotelIndex);
				
				if(valid) {
					hotelView.getConfirmationButtonList().get(8).setVisible(true);
					hotelView.getConfirmationButtonList().get(9).setVisible(true);
					lockManagementInput();
				}
				else {
					hotelView.getTextFieldList().get(14).setText("Input Number from List");
				}
				
			}
		});
		
		this.hotelView.setConfirmRemoveReserveationYesActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int hotelIndex = hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				String reservationIndex = hotelView.getTextFieldList().get(14).getText();
				
				hotelView.getConfirmationButtonList().get(8).setVisible(false);
				hotelView.getConfirmationButtonList().get(9).setVisible(false);
				unlockManagementInput();
				
				hotelModel.getHotelList().get(hotelIndex).removeReservation(Integer.parseInt(reservationIndex)-1);
				
				hotelView.getTextFieldList().get(14).setText("");
				
				String reservationList = "Reservations:              \n";
				if(hotelModel.getHotelList().get(hotelIndex).getReservationsList().size() != 0) {
					hotelView.getTextFieldList().get(14).setEditable(true);
					hotelView.getButtonList().get(14).setEnabled(true);
					hotelView.getTextFieldList().get(14).setText("");
					for(int i = 0; i < hotelModel.getHotelList().get(hotelIndex).getReservationsList().size(); i++) {
						reservationList = reservationList + (i+1)+  " - "+ hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(i).getGuestName() + "\n";
					}
					reservationList = reservationList + "                                                                                                                                                                                                                                     ";
				}
				else {
					reservationList = reservationList + "-No Reservations Booked Currently-                                          ";
					hotelView.getTextFieldList().get(14).setEditable(false);
					hotelView.getTextFieldList().get(14).setText("No reservations currently");
					hotelView.getButtonList().get(14).setEnabled(false);
				}
				
				
				hotelView.getTextAreaList().get(15).setText(reservationList);
			}
		});
		
		this.hotelView.setDateModifierActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dateSelected = "" + hotelView.getComboBoxList().get(0).getSelectedItem();
				String hotelIndex = "" + hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				String newRate = hotelView.getTextFieldList().get(23).getText();
				
				boolean valid = hotelModel.modifyRateOnDate(dateSelected, newRate, hotelIndex);
				
				if(valid) {
					hotelView.getConfirmationButtonList().get(16).setVisible(true);
					hotelView.getConfirmationButtonList().get(17).setVisible(true);
					lockManagementInput();
				}
				else {
					hotelView.getTextFieldList().get(23).setText("Invalid Price");
				}
			}
		});
		
		this.hotelView.setConfirmModifyRateOnDateYesActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dateSelected = "" + hotelView.getComboBoxList().get(0).getSelectedItem();
				int hotelIndex = hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				String newRate = hotelView.getTextFieldList().get(23).getText();
				
				hotelView.getConfirmationButtonList().get(16).setVisible(false);
				hotelView.getConfirmationButtonList().get(17).setVisible(false);
				unlockManagementInput();
				
				hotelModel.getHotelList().get(hotelIndex).modifyRatesList(Integer.parseInt(dateSelected), Float.parseFloat(newRate));
				
				String rateForDay = "Rates on Days of The Month: \n";
				rateForDay = rateForDay +  "Day " + dateSelected + ": ";
				
				rateForDay = rateForDay + hotelModel.getHotelList().get(hotelIndex).getRatesList().get(Integer.parseInt(dateSelected)-1) + " times base price";
				
				hotelView.getTextAreaList().get(24).setText(rateForDay);
				hotelView.getTextFieldList().get(23).setText("");
			}
		});
		
		this.hotelView.setDatesRatesListCmBxActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dateSelected = "" + hotelView.getComboBoxList().get(0).getSelectedItem();
				int hotelIndex = hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				
				String rateForDay = "Rates on Days of The Month: \n";
				rateForDay = rateForDay +  "Day " + dateSelected + ": ";
				
				rateForDay = rateForDay + hotelModel.getHotelList().get(hotelIndex).getRatesList().get(Integer.parseInt(dateSelected)-1) + " times base price";
				
				hotelView.getTextAreaList().get(24).setText(rateForDay);
			}
		});
		
		this.hotelView.setRemoveHotelActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < hotelView.getConfirmationButtonList().size(); i++) {
					hotelView.getConfirmationButtonList().get(i).setVisible(false);
				}
				
				hotelView.getConfirmationButtonList().get(10).setVisible(true);
				hotelView.getConfirmationButtonList().get(11).setVisible(true);
				lockManagementInput();
			}
		});
		
		this.hotelView.setConfirmRemoveHotelYesActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int hotelIndex = hotelModel.getHotelIndex(hotelView.getTextFieldList().get(9).getText());
				
				hotelModel.getHotelList().remove(hotelIndex);
				
				unlockManagementInput();
				
				for(int i = 11; i < 16; i++) {
					hotelView.getTextAreaList().get(i).setVisible(false);
				}
				
				for(int i = 10; i < 17; i++) {
					hotelView.getTextFieldList().get(i).setVisible(false);
				}
				
				for(int i = 10; i < 18; i++) {
					hotelView.getButtonList().get(i).setVisible(false);
				}
				
				for(int i = 0; i < hotelView.getConfirmationButtonList().size(); i++) {
					hotelView.getConfirmationButtonList().get(i).setVisible(false);
				}
				
				displayHotelList(10);
				
				if(hotelModel.getHotelList().size() != 0) {
					hotelView.getButtonList().get(9).setEnabled(true);
					hotelView.getTextFieldList().get(9).setEditable(true);
				}
				else {
					hotelView.getButtonList().get(9).setEnabled(false);
					hotelView.getTextFieldList().get(9).setEditable(false);
					hotelView.getTextFieldList().get(9).setText("No Hotels Exist Currently");
				}
				
				hotelView.getConfirmationButtonList().get(10).setVisible(false);
				hotelView.getConfirmationButtonList().get(11).setVisible(false);
			}
		});
		
		this.hotelView.setConfirmChangeNameNoActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.getConfirmationButtonList().get(0).setVisible(false);
				hotelView.getConfirmationButtonList().get(1).setVisible(false);
				hotelView.getTextFieldList().get(10).setText("");
				unlockManagementInput();
			}
			
		});
		
		this.hotelView.setConfirmAddStandardNoActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.getConfirmationButtonList().get(2).setVisible(false);
				hotelView.getConfirmationButtonList().get(3).setVisible(false);
				hotelView.getTextFieldList().get(11).setText("");
				unlockManagementInput();
			}
			
		});
		
		this.hotelView.setConfirmAddDeluxeNoActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.getConfirmationButtonList().get(12).setVisible(false);
				hotelView.getConfirmationButtonList().get(13).setVisible(false);
				hotelView.getTextFieldList().get(15).setText("");
				unlockManagementInput();
			}
			
		});
		
		this.hotelView.setConfirmAddExecutiveNoActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.getConfirmationButtonList().get(14).setVisible(false);
				hotelView.getConfirmationButtonList().get(15).setVisible(false);
				hotelView.getTextFieldList().get(16).setText("");
				unlockManagementInput();
			}
			
		});
		
		this.hotelView.setConfirmRemoveRoomNoActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.getConfirmationButtonList().get(4).setVisible(false);
				hotelView.getConfirmationButtonList().get(5).setVisible(false);
				hotelView.getTextFieldList().get(12).setText("");
				unlockManagementInput();
			}
			
		});
		
		this.hotelView.setConfirmUpdatePriceNoActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.getConfirmationButtonList().get(6).setVisible(false);
				hotelView.getConfirmationButtonList().get(7).setVisible(false);
				hotelView.getTextFieldList().get(13).setText("");
				unlockManagementInput();
			}
			
		});
		
		this.hotelView.setConfirmRemoveReservationNoActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.getConfirmationButtonList().get(8).setVisible(false);
				hotelView.getConfirmationButtonList().get(9).setVisible(false);
				hotelView.getTextFieldList().get(14).setText("");
				unlockManagementInput();
			}
			
		});
		
		this.hotelView.setConfirmRemoveHotelNoActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.getConfirmationButtonList().get(10).setVisible(false);
				hotelView.getConfirmationButtonList().get(11).setVisible(false);
				unlockManagementInput();
			}
		});
		
		this.hotelView.setConfirmModifyRateOnDateNoActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.getConfirmationButtonList().get(16).setVisible(false);
				hotelView.getConfirmationButtonList().get(17).setVisible(false);
				unlockManagementInput();
			}
		});
		
		this.hotelView.setBookReservationActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.togglePanelVisibility(4);
				
				displayHotelList(16);
				if(hotelModel.getHotelList().size() != 0) {
					hotelView.getButtonList().get(18).setEnabled(true);
					hotelView.getTextFieldList().get(17).setEditable(true);
				}
				else {
					hotelView.getButtonList().get(18).setEnabled(false);
					hotelView.getTextFieldList().get(17).setEditable(false);
					hotelView.getTextFieldList().get(17).setText("No Hotels Exist Currently");
				}
				
				for(int i = 18; i < 23; i++) {
					hotelView.getTextFieldList().get(i).setVisible(false);
				}
				
				for(int i = 18; i < 24; i++) {
					hotelView.getTextAreaList().get(i).setVisible(false);
				}
				
				hotelView.getButtonList().get(19).setVisible(false);
				
			}
			
		});
		
		this.hotelView.setChooseHotelBookReservationActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String hotel = hotelView.getTextFieldList().get(17).getText();
				
				if(hotelModel.getHotelIndex(hotel) != -1) {
					for(int i = 18; i < 23; i++) {
						hotelView.getTextFieldList().get(i).setVisible(true);
					}
					
					for(int i = 18; i < 24; i++) {
						hotelView.getTextAreaList().get(i).setVisible(true);
					}
					
					hotelView.getButtonList().get(19).setVisible(true);
					
					hotelView.getTextAreaList().get(21).setVisible(false);
					
					hotelView.getTextAreaList().get(18).setText("Input Guest Name");
					
					displayRoomsList(22, hotelModel.getHotelIndex(hotel));
					
					
				}
				else {
					hotelView.getTextAreaList().get(18).setVisible(true);
					hotelView.getTextAreaList().get(18).setText("Hotel Does Not Exist");
				}
			}
			
		});
		
		this.hotelView.setFinalizeBookingActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String guestName = hotelView.getTextFieldList().get(18).getText();
				String checkIn = hotelView.getTextFieldList().get(19).getText();
				String checkOut = hotelView.getTextFieldList().get(20).getText();
				String roomChosen = hotelView.getTextFieldList().get(21).getText();
				String hotelIndex = "" + hotelModel.getHotelIndex(hotelView.getTextFieldList().get(17).getText());
				String discountCode = hotelView.getTextFieldList().get(22).getText();
				
				boolean valid = hotelModel.finalizeReservation(guestName, checkIn, checkOut, roomChosen, discountCode, hotelIndex);
				
				if(valid) {
					hotelView.getTextAreaList().get(21).setVisible(true);
					hotelView.getTextAreaList().get(21).setText("Booking Successful");
					for(int i = 18; i < 23; i++) {
						hotelView.getTextFieldList().get(i).setText("");
					}
				}
				else {
					hotelView.getTextAreaList().get(21).setVisible(true);
					hotelView.getTextAreaList().get(21).setText("Invalid Inputs\n Check-In Check-Out Must be Valid\n Cannot Book a Booked Room\n ");
				}
			}
		});
		

		
		this.hotelView.setReservationInfoActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.togglePanelVisibility(5);
				displayHotelList(25);
				
				if(hotelModel.getHotelList().size() != 0) {
					hotelView.getTextAreaList().get(7).setVisible(false);
					hotelView.getComboBoxList().get(1).setVisible(false);
					hotelView.getButtonList().get(22).setEnabled(true);
				}
				else {
					hotelView.getTextFieldList().get(24).setText("No Hotels Currently");
					hotelView.getButtonList().get(22).setEnabled(false);
					hotelView.getTextAreaList().get(7).setVisible(false);
					hotelView.getComboBoxList().get(1).setVisible(false);
				}
				

			}
		});
		
		this.hotelView.setChooseHotelReservationInfoActnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int hotelIndex = hotelModel.getHotelIndex(hotelView.getTextFieldList().get(24).getText());
				if(hotelIndex != -1) {
				hotelView.getTextAreaList().get(7).setVisible(true);
				hotelView.getComboBoxList().get(1).setVisible(true);
				String reservationList = "";	
				if(hotelModel.getHotelList().get(hotelIndex).getReservationsList().size() != 0) {
					reservationList = reservationList + "Guest: " + hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(0).getGuestName() + "\n";
					reservationList = reservationList + "Booked into Room: " + hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(0).getRoomInfo().getRoomName() + ", " + hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(0).getRoomInfo().getRoomType() +"\n";
					reservationList = reservationList + "Check-In: " + hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(0).getCheckIn() + "     Check-Out: " + hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(0).getCheckOut() +"\n";
					reservationList = reservationList + "Discount Code: " + hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(0).getDiscountCode() + "\n";
					reservationList = reservationList + "Total Cost: " + hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(0).getTotalPay() + "\n";
					reservationList = reservationList + "Cost Per Night: \n";
					for(int i = 0; i < hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(0).getCostPerNightList().size(); i++) {
						reservationList = reservationList + "Night " + (i+1) + ": " + hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(0).getCostPerNightList().get(i) + "\n";
						}
						
					hotelView.getComboBoxList().get(1).removeAllItems();
					for(int i = 0; i < hotelModel.getHotelList().get(hotelIndex).getReservationsList().size(); i++) {
						if((hotelView.getComboBoxList().get(1).getItemCount() < hotelModel.getHotelList().get(hotelIndex).getReservationsList().size())) {
							hotelView.getComboBoxList().get(1).addItem("" + (i+1));
						}
					}
				}
		
				else {
					reservationList = reservationList + "-No Reservations Currently-";
					hotelView.getComboBoxList().get(1).removeAllItems();
				}
				
				hotelView.getTextAreaList().get(7).setText(reservationList);
			}
			else {
				hotelView.getTextFieldList().get(24).setText("Invalid Input");
				hotelView.getTextAreaList().get(7).setVisible(false);
				hotelView.getComboBoxList().get(1).setVisible(false);
			}
			}		
		});
		
		this.hotelView.setViewReservationComboBox(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String chosenReservation = "" + hotelView.getComboBoxList().get(1).getSelectedItem();
				int hotelIndex = hotelModel.getHotelIndex(hotelView.getTextFieldList().get(24).getText());
				
				String reservationList = "";	
				if(hotelModel.getHotelList().get(hotelIndex).getReservationsList().size() != 0) {
					reservationList = reservationList + "Guest: " + hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(Integer.parseInt(chosenReservation)-1).getGuestName() + "\n";
					reservationList = reservationList + "Booked into Room: " + hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(Integer.parseInt(chosenReservation)-1).getRoomInfo().getRoomName() + ", " + hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(Integer.parseInt(chosenReservation)-1).getRoomInfo().getRoomType() +"\n";
					reservationList = reservationList + "Check-In: " + hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(Integer.parseInt(chosenReservation)-1).getCheckIn() + "     Check-Out: " + hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(0).getCheckOut() +"\n";
					reservationList = reservationList + "Discount Code: " + hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(Integer.parseInt(chosenReservation)-1).getDiscountCode() + "\n";
					reservationList = reservationList + "Total Cost: " + hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(Integer.parseInt(chosenReservation)-1).getTotalPay() + "\n";
					reservationList = reservationList + "Cost Per Night: \n";
					for(int i = 0; i < hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(Integer.parseInt(chosenReservation)-1).getCostPerNightList().size(); i++) {
						reservationList = reservationList + "Night " + (i + 1) + ": " + hotelModel.getHotelList().get(hotelIndex).getReservationsList().get(Integer.parseInt(chosenReservation)-1).getCostPerNightList().get(i) + "\n";
					}
					
				}
				else {
					reservationList = reservationList + "No Reservations Currently";
					hotelView.getComboBoxList().get(1).removeAllItems();
				}
				
				hotelView.getTextAreaList().get(7).setText(reservationList);
			}
			
		});
		
		this.hotelView.setReturnActnListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hotelView.togglePanelVisibility(0);
				hotelView.clearTextArea(1);
				hotelView.clearTextFields();
				hotelView.getButtonList().get(6).setVisible(false);
				hotelView.getButtonList().get(7).setVisible(false);
				hotelView.getButtonList().get(8).setVisible(false);
				hotelView.getTextFieldList().get(6).setVisible(false);
				hotelView.getTextFieldList().get(7).setVisible(false);
				hotelView.getTextFieldList().get(8).setVisible(false);
				hotelView.getTextAreaList().get(3).setVisible(false);
				hotelView.getTextAreaList().get(4).setVisible(false);
				hotelView.getTextAreaList().get(5).setVisible(false);
				hotelView.getTextAreaList().get(6).setVisible(false);
				hotelView.getTextAreaList().get(7).setVisible(false);
				hotelView.getTextAreaList().get(8).setVisible(false);
				hotelView.getTextAreaList().get(9).setText("Hotel Info:                ");
				hotelView.getTextAreaList().get(9).setVisible(false);
				
				for(int i = 11; i < 16; i++) {
					hotelView.getTextAreaList().get(i).setVisible(false);
				}
				
				for(int i = 10; i < 17; i++) {
					hotelView.getTextFieldList().get(i).setVisible(false);
				}
				
				for(int i = 10; i < 18; i++) {
					hotelView.getButtonList().get(i).setVisible(false);
				}
				
				for(int i = 0; i < hotelView.getConfirmationButtonList().size(); i++) {
					hotelView.getConfirmationButtonList().get(i).setVisible(false);
				}
				
				hotelView.getTextAreaList().get(24).setVisible(false);
				hotelView.getTextFieldList().get(23).setVisible(false);
				hotelView.getButtonList().get(20).setVisible(false);
				hotelView.getComboBoxList().get(0).setVisible(false);
				hotelView.getComboBoxList().get(1).setVisible(false);
				
				
				unlockManagementInput();
			}
		});
	}
	/**
	 * will display hotel list in chosen text area
	 * @param textAreaIndex index of chosen text area
	 */
	private void displayHotelList(int textAreaIndex) {
		String listHotels = "List of Hotels:          \n";
		if(hotelModel.getHotelList().size() != 0) {
			for(int i = 0; i < hotelModel.getHotelList().size(); i++) {
				listHotels = listHotels + "- " + hotelModel.getHotelList().get(i).getHotelName() + "\n";
			}
			listHotels = listHotels + "                                                                                                                                                                        ";
		}
		else {
			listHotels = listHotels + "-No Hotels Exist Currently-                                          ";
		}
		
	
		
		hotelView.setDisplayText(listHotels, textAreaIndex);
	}
	
	/**
	 * displays rooms of a chosen hotel to a fixed text area
	 * @param hotelIndex inddex of chosen hotel
	 */
	private void displayNumRooms(int hotelIndex) {
		String numRooms = "";
		numRooms = numRooms + hotelModel.getHotelList().get(hotelIndex).getNumStandard() + " Standard Rooms\n";
		numRooms = numRooms + hotelModel.getHotelList().get(hotelIndex).getNumDeluxe() + " Deluxe Rooms\n";
		numRooms = numRooms + hotelModel.getHotelList().get(hotelIndex).getNumExec() + " Exectuive Rooms";
		
		numRooms += "                                                             ";
		
		hotelView.getTextAreaList().get(12).setText(numRooms);
	}
	/**
	 * displays rooms list in a certain text area
	 * @param textAreaIndex index of chosen text area
	 * @param hotelIndex hotel index
	 */
	private void displayRoomsList(int textAreaIndex, int hotelIndex) {
		String roomsList = "Rooms: ";
		int newLine = 0;
		
		for( int i = 0; i < hotelModel.getHotelList().get(hotelIndex).getRoomsList().size(); i++ ) {
				if(newLine % 10 == 0) {
					roomsList = roomsList + "\n";
				}
				roomsList = roomsList + hotelModel.getHotelList().get(hotelIndex).getRoomsList().get(i).getRoomName() +  "     ";
				newLine++;
		}
		
		roomsList = roomsList + "                                                                                                                                                                                                                                                    ";
		hotelView.getTextAreaList().get(textAreaIndex).setText(roomsList);
	}
	/**
	 * locks all the inputs in hotel management
	 */
	private void lockManagementInput() {
		for(int i = 9; i < 17; i++) {
			hotelView.getTextFieldList().get(i).setEditable(false);
		}
		
		for(int i = 9; i < 18; i++) {
			hotelView.getButtonList().get(i).setEnabled(false);
		}
		
		
		hotelView.getButtonList().get(20).setEnabled(false);
		hotelView.getTextFieldList().get(23).setEditable(false);
		hotelView.getComboBoxList().get(0).setEnabled(false);
	}
	
	/**
	 * unLocks all the inputs in hotel management
	 */
	private void unlockManagementInput() {
		for(int i = 9; i < 17; i++) {
			hotelView.getTextFieldList().get(i).setEditable(true);
		}
		
		for(int i = 9; i < 18; i++) {
			hotelView.getButtonList().get(i).setEnabled(true);
		}
		
		hotelView.getComboBoxList().get(0).setEnabled(true);
		hotelView.getButtonList().get(20).setEnabled(true);
		hotelView.getTextFieldList().get(23).setEditable(true);
		hotelView.getComboBoxList().get(0).setEnabled(true);
		
	}

}