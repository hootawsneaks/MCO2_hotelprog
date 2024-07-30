import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HotelView {
	private JFrame frame;
	private ArrayList<JPanel> panelList = new ArrayList<JPanel>();
	/*Legend
	 * 0 - mainPnl
	 * 1 - addHotelPnl
	 * 2 - viewHotelPnl
	 * 3 - manageHotelPnl
	 * 4 - bookReservationPnl
	 * 5 - manageHotelPnl
	 * 6 - viewReservationPnl
	 * */
	private ArrayList<JButton> buttonList = new ArrayList<JButton>();
	/*Legend
	 * 0 - addHotelBtn
	 * 1 - viewHotelBtn
	 * 2 - manageHotelBtn
	 * 3 - bookReservationBtn
	 * 4 - submitHotelCreateBtn
	 * 5 - chooseHotelBtn
	 * 6 - numAvailableRoomsBtn
	 * 7 - roomInfoBtn
	 * 8 - reserveInfoBtn
	 * 9 - manage Hotel Chosen Hotel
	 * 10 -changeHotelName
	 * 11 -addStandardRoom
	 * 12 -removeRoom
	 * 13 -updatePirce
	 * 14 -removeReservation
	 * 15 -removeHotel
	 * 16 - addDeluxeRoom
	 * 17 - addExecutiveRoom
	 * 18 -chooseHotelBookReservation
	 * 19 -finalizeReservation
	 * */
	private ArrayList<JButton> confirmationButtonList = new ArrayList<JButton>();
	/*Legend
	 * 0 - changeHotelNameYes
	 * 1 - changeHotelNameNo
	 * 2 - addStandardRoomYes
	 * 3 - addStandardRoomNo
	 * 4 - removeRoomsYes
	 * 5 - removeRoomsNo
	 * 6 - updatePriceYes
	 * 7 - updatePriceNo
	 * 8 - removeReservationYes
	 * 9 -removeReservatinoNo
	 * 10 -removeHotelYes
	 * 11 -removeHotelNo
	 * 12 - addDeluxeRoomsYes
	 * 13 - addDeluxeRoomsNo
	 * 14 -addExecutiveRoomsYes
	 * 15 -addExecutiveRoomsNo
	 */
	private ArrayList<JButton> returnButtonList = new ArrayList<JButton>();
	/*Legend
	 * 0 - addHotelReturn
	 * 1 - viewHotelReturn
	 * 2 - manageHotelReturn
	 * 3 - bookReservationReturn
	 * */
	private  ArrayList<JTextArea> textAreaList = new ArrayList<JTextArea>();
	/*Legend
	 * 0 - listOfHotelNamesHotelCreation
	 * 1 - hotelAddSuccessPrompt
	 * 2 - listOfHotelNamesViewHotel
	 * 3 - numAvailableRoomDatePrompt
	 * 4 - numAvailabelRoomDateInfo
	 * 5 - roomList
	 * 6 - roomListInfo
	 * 7 - reservationList
	 * 8 - reservationListInfo
	 * 9 - viewHoteltextHeader
	 * 10 - hotelListManageHotel
	 * 11 - changeHoteName
	 * 12 - addRooms
	 * 13 - removeRooms
	 * 14 - updatePrice
	 * 15 - removeReservation
	 * 16 - hotelListBookReservation
	 * 17 -chooseHotelBookReservation
	 * 18 -guestName
	 * 19 - checkIn
	 * 20 - checkOut
	 * 21 - finalizeBookingPrompt
	 * 22 - roomListBookReservation
	 * 23 -discountCode
	 * 24 - discountCodePrompt
	 */
	private ArrayList<JTextField> textFieldList = new ArrayList<JTextField>();
	/*Legend
	 * 0 - inputHotelname
	 * 1 - numStandard
	 * 2 - numDeluxe
	 * 3 - numExec
	 * 4 - basePrice
	 * 5 - hotelToView
	 * 6 - numAvailableRoomsforDate
	 * 7 - roomInfo
	 * 8 - reserveInfo
	 * 9 - chooseHotelManageHotel
	 * 10 - changeHotelName
	 * 11 -addStandardRooms
	 * 12 -removeRooms
	 * 13 - update price
	 * 14 -remove reservation
	 * 15 - addDeluxeROoms
	 * 16 -AddExecutiveRooms
	 * 17 -chooseHotelBookReservation
	 * 18 -guestName
	 * 19 -checkIn
	 * 20 -checkOut
	 * 21 -chooseRoomBookReservation
	 * 22 - inputDiscountCode
	 */
	
	public HotelView() {
		this.frame = new JFrame();
		
		this.frame.setSize(650, 650); // sets x and y dimensions
		this.frame.setLayout(new FlowLayout()); // thing for border
		this.frame.setTitle("Main Menu"); // name of 
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // default is HIDE_ON_CLOSE, there is also NOTHING_ON_CLOSE
		
		//this.createButton("Return to Main Menu");
		this.createButton("Create a Hotel");
		this.createButton("View a Hotel's Details");
		this.createButton("Manage a Hotel");
		this.createButton("Book a Reservation");
		
		//MAIN PANEL STUFF
		this.createPanel("Main Menu:");
		this.panelList.get(0).setVisible(true);
		
		for(int i = 0; i < buttonList.size(); i++) {
			this.panelList.get(0).add(buttonList.get(i));
		}
		
		
		// making main panels
		this.createPanel("Create a Hotel:");
		this.createPanel("View Hotel Information:");
		this.createPanel("Manage Hotel:                                             ");
		this.createPanel("Book a Reservation:");
		this.createPanel("Reservation Info");
		
		
		//making commponenents of main panels
		this.hotelCreationMenu();
		this.viewHotelMenu();
		this.manageHotelMenu();
		this.bookReservation();
		
		//adding panels to frame
		for(int i = 0; i < this.panelList.size(); i++) {
			this.frame.add(this.panelList.get(i));
		}
		
		
		this.frame.setVisible(true); // lets it be visible
	}
	
	public void setAddHotelActnListener(ActionListener actionListener) {
		this.buttonList.get(0).addActionListener(actionListener);
	}
	
	public void setViewHotelActnListener(ActionListener actionListener) {
		this.buttonList.get(1).addActionListener(actionListener);
	}
	
	public void setManageHotelActnListener(ActionListener actionListener) {
		this.buttonList.get(2).addActionListener(actionListener);
	}
	
	public void setBookReservationActnListener(ActionListener actionListener) {
		this.buttonList.get(3).addActionListener(actionListener);
	}
	
	public void setFinalHotelActnListener(ActionListener actionListener) {
		this.buttonList.get(4).addActionListener(actionListener);
	}
	
	public void setChooseHotelActnListener(ActionListener actionListener) {
		this.buttonList.get(5).addActionListener(actionListener);
	}
	
	public void setCheckAvailDateActnListener(ActionListener actionListener) {
		this.buttonList.get(6).addActionListener(actionListener);
	}
	
	public void setViewRoomInfoActnListener(ActionListener actionListener) {
		this.buttonList.get(7).addActionListener(actionListener);
	}
	
	public void setViewReservationActnListener(ActionListener actionListener) {
		this.buttonList.get(8).addActionListener(actionListener);
	}
	
	public void setChooseManageHotelActnListener(ActionListener actionListener) {
		this.buttonList.get(9).addActionListener(actionListener);
	}
	
	public void setChangeNameActnListener(ActionListener actionListener) {
		this.buttonList.get(10).addActionListener(actionListener);
	}
	
	public void setAddStandardActnListener(ActionListener actionListener) {
		this.buttonList.get(11).addActionListener(actionListener);
	}
	
	public void setRemoveRoomActnListenerr(ActionListener actionListener) {
		this.buttonList.get(12).addActionListener(actionListener);
	}
	
	public void setUpdatePriceActnListener(ActionListener actionListener) {
		this.buttonList.get(13).addActionListener(actionListener);
	}
	
	public void setRemoveReservationActnListener(ActionListener actionListener) {
		this.buttonList.get(14).addActionListener(actionListener);
	}
	
	public void setRemoveHotelActnListener(ActionListener actionListener) {
		this.buttonList.get(15).addActionListener(actionListener);
	}
	
	public void setAddDeluxeActnListener(ActionListener actionListener) {
		this.buttonList.get(16).addActionListener(actionListener);
	}
	
	public void setAddExecutiveActnListener(ActionListener actionListener) {
		this.buttonList.get(17).addActionListener(actionListener);
	}
	
	public void setConfirmChangeNameYesActnListener(ActionListener actionListener) {
		this.confirmationButtonList.get(0).addActionListener(actionListener);
	}
	
	public void setConfirmChangeNameNoActnListener(ActionListener actionListener) {
		this.confirmationButtonList.get(1).addActionListener(actionListener);
	}
	
	public void setConfirmAddStandardYesActnListener(ActionListener actionListener) {
		this.confirmationButtonList.get(2).addActionListener(actionListener);
	}
	
	public void setConfirmAddStandardNoActnListener(ActionListener actionListener) {
		this.confirmationButtonList.get(3).addActionListener(actionListener);
	}
	
	public void setConfirmRemoveRoomYesActnListener(ActionListener actionListener) {
		this.confirmationButtonList.get(4).addActionListener(actionListener);
	}
	
	public void setConfirmRemoveRoomNoActnListener(ActionListener actionListener) {
		this.confirmationButtonList.get(5).addActionListener(actionListener);
	}
	
	public void setConfirmUpdatePriceYesActnListener(ActionListener actionListener) {
		this.confirmationButtonList.get(6).addActionListener(actionListener);
	}
	
	public void setConfirmUpdatePriceNoActnListener(ActionListener actionListener) {
		this.confirmationButtonList.get(7).addActionListener(actionListener);
	}
	
	public void setConfirmRemoveReserveationYesActnListener(ActionListener actionListener) {
		this.confirmationButtonList.get(8).addActionListener(actionListener);
	}
	
	public void setConfirmRemoveReservationNoActnListener(ActionListener actionListener) {
		this.confirmationButtonList.get(9).addActionListener(actionListener);
	}
	
	public void setConfirmRemoveHotelYesActnListener(ActionListener actionListener) {
		this.confirmationButtonList.get(10).addActionListener(actionListener);
	}
	
	public void setConfirmRemoveHotelNoActnListener(ActionListener actionListener) {
		this.confirmationButtonList.get(11).addActionListener(actionListener);
	}
	
	public void setConfirmAddDeluxeYesActnListener(ActionListener actionListener) {
		this.confirmationButtonList.get(12).addActionListener(actionListener);
	}
	
	public void setConfirmAddDeluxeNoActnListener(ActionListener actionListener) {
		this.confirmationButtonList.get(13).addActionListener(actionListener);
	}
	
	public void setConfirmAddExecutiveYesActnListener(ActionListener actionListener) {
		this.confirmationButtonList.get(14).addActionListener(actionListener);
	}
	
	public void setConfirmAddExecutiveNoActnListener(ActionListener actionListener) {
		this.confirmationButtonList.get(15).addActionListener(actionListener);
	}
	
	public void setChooseHotelBookReservationActnListener(ActionListener actionListener) {
		this.buttonList.get(18).addActionListener(actionListener);
	}
	
	public void setFinalizeBookingActnListener(ActionListener actionListener) {
		this.buttonList.get(19).addActionListener(actionListener);
	}
	
	public void setReturnActnListener(ActionListener actionListener) {
		for(int i = 0; i < this.returnButtonList.size(); i++) {
			this.returnButtonList.get(i).addActionListener(actionListener);
		}
	}
	
	
	
	public void setDisplayText(String list, int areaIndex) {
		this.textAreaList.get(areaIndex).setText(list);
	}
	
	public void togglePanelVisibility(int panelIndex) {
		for(int i = 0; i < this.panelList.size(); i++) {
			if(i != panelIndex) {
				this.panelList.get(i).setVisible(false);
			}
			else {
				this.panelList.get(panelIndex).setVisible(true);
			}
		}
	}
	
	private void createPanel(String header) {
		int n = this.panelList.size();
		
		this.panelList.add(new JPanel());
		this.panelList.get(n).setLayout(new FlowLayout());
		this.panelList.get(n).setVisible(false);
		this.panelList.get(n).setLayout(new FlowLayout(FlowLayout.LEFT, 100, 5));
		this.panelList.get(n).setBackground(Color.LIGHT_GRAY);
		this.panelList.get(n).setPreferredSize(new Dimension(400, 400));
		this.panelList.get(n).setOpaque(true);
		
		this.panelList.get(n).add(new JLabel(header));
		if(panelList.size() != 1) {
			this.returnButtonList.add(new JButton("Return to Main Menu"));
			this.panelList.get(n).add(this.returnButtonList.get(this.returnButtonList.size()-1));
		}
	}
	
	private void createTextArea(String initialText) {
		this.textAreaList.add(new JTextArea(initialText));
		
		for(int i = 0; i < this.textAreaList.size(); i++) {
			this.textAreaList.get(i).setEditable(false);
			this.textAreaList.get(i).setOpaque(false);
			this.textAreaList.get(i).setFont(new Font("Dialog", Font.BOLD, 12));
		}
	}
	
	private void createTextField() {
		this.textFieldList.add(new JTextField());
		this.textFieldList.get(this.textFieldList.size()-1).setColumns(20);
	}
	
	private void createButton(String buttonText) {
		this.buttonList.add(new JButton(buttonText));
		this.buttonList.get(this.buttonList.size()-1).setPreferredSize(new Dimension(200, 25));
		buttonList.get(this.buttonList.size()-1).setFocusable(false);
	}
	
	private void createConfirmationButton(String buttonText) {
		this.confirmationButtonList.add(new JButton(buttonText));
		this.confirmationButtonList.get(this.confirmationButtonList.size()-1).setPreferredSize(new Dimension(110, 25));
		this.confirmationButtonList.get(this.confirmationButtonList.size()-1).setFocusable(false);
		this.confirmationButtonList.get(this.confirmationButtonList.size()-1).setFont(new Font("Dialog", Font.BOLD, 8));
	}
	public void clearTextFields() {
		for(int i = 0; i < this.textFieldList.size(); i++) {
			this.textFieldList.get(i).setText("");
		}
	}
	
	public void clearTextArea() {
		for(int i = 0; i < this.textAreaList.size(); i++) {
			this.textAreaList.get(i).setText("");
		}
	}
	
	public void clearTextArea(int textAreaIndex) {
			this.textAreaList.get(textAreaIndex).setText("");
	}
	
	private void hotelCreationMenu() {
		this.panelList.get(1).setLayout(new FlowLayout(FlowLayout.LEFT, 125, 5));
		this.panelList.get(1).setPreferredSize(new Dimension(400, 450));
		
		this.createTextArea("");
		
		JLabel hotelNameInputLbl = new JLabel("Hotel Name:");
		JLabel numStanInputLbl = new JLabel("Number of Standard Rooms:");
		JLabel numDelInputLbl = new JLabel("Number of Deluxe Rooms:");
		JLabel numExecInputLbl = new JLabel("Number of Executive Rooms:");
		JLabel basePriceInputLbl = new JLabel("Input Hotel's Base Price:");
		
		this.createTextField();
		this.createTextField();
		this.createTextField();
		this.createTextField();
		this.createTextField();
		
		this.panelList.get(1).add(this.textAreaList.get(0));
		this.panelList.get(1).add(hotelNameInputLbl);
		this.panelList.get(1).add(this.textFieldList.get(0));
		this.panelList.get(1).add(numStanInputLbl);
		this.panelList.get(1).add(this.textFieldList.get(1));
		this.panelList.get(1).add(numDelInputLbl);
		this.panelList.get(1).add(this.textFieldList.get(2));
		this.panelList.get(1).add(numExecInputLbl);
		this.panelList.get(1).add(this.textFieldList.get(3));
		this.panelList.get(1).add(basePriceInputLbl);
		this.panelList.get(1).add(this.textFieldList.get(4));
		
		this.createButton("Finalize Hotel");
		
		this.panelList.get(1).add(this.buttonList.get(4));
		this.createTextArea("");
		this.panelList.get(1).add(this.textAreaList.get(1));
	}
	
	private void viewHotelMenu() {
		this.panelList.get(2).setPreferredSize(new Dimension(450, 800));
		 this.createTextArea("");
		 
		JLabel chooseHotelLabel = new JLabel("Input Hotel to be Viewed                    ");
		this.createTextField();
		this.textFieldList.get(5).setEditable(false);
		this.textFieldList.get(5).setText("No Hotels Currently Exist");
		this.createButton("View Chosen Hotel");
		this.buttonList.get(5).setEnabled(false);
		
		this.panelList.get(2).add(this.textAreaList.get(2));
		this.panelList.get(2).add(chooseHotelLabel);
		this.panelList.get(2).add(this.textFieldList.get(5));
		this.panelList.get(2).add(this.buttonList.get(5));
		//this.panelList.get(2).add(viewHotelHeaderLbl);
		
		
		this.createButton("Check Date");
		this.createButton("View Room Info");
		this.createButton("View Reservation Info");
		this.buttonList.get(6).setVisible(false);
		this.buttonList.get(7).setVisible(false);
		this.buttonList.get(8).setVisible(false);
		
		this.createTextField();
		this.createTextField();
		this.createTextField();
		this.textFieldList.get(6).setVisible(false);
		this.textFieldList.get(7).setVisible(false);
		this.textFieldList.get(8).setVisible(false);
		
		
		this.createTextArea("");//for showing dates to choose from?
		this.createTextArea("");//text area for showing available rooms for certain date
		this.createTextArea("");//showing list of rooms
		this.createTextArea("");//showing room info
		this.createTextArea("");//show reservation list
		this.createTextArea("");// reservation info
		this.createTextArea("Hotel Does not Exist");//viewHotel header
		
		this.textAreaList.get(3).setVisible(false);
		this.textAreaList.get(4).setVisible(false);
		this.textAreaList.get(5).setVisible(false);
		this.textAreaList.get(6).setVisible(false);
		this.textAreaList.get(7).setVisible(false);
		this.textAreaList.get(8).setVisible(false);
		this.textAreaList.get(9).setVisible(false);
		
		this.textFieldList.get(6).setColumns(5);
		this.buttonList.get(6).setPreferredSize(new Dimension(135, 25));
		
		this.panelList.get(2).add(this.textAreaList.get(9));
		
		this.panelList.get(2).add(this.textAreaList.get(3));
		this.panelList.get(2).add(this.textFieldList.get(6));
		this.panelList.get(2).add(this.getButtonList().get(6));
		this.panelList.get(2).add(this.textAreaList.get(4));
		
		this.panelList.get(2).add(this.textAreaList.get(5));
		this.panelList.get(2).add(this.textFieldList.get(7));
		this.panelList.get(2).add(this.getButtonList().get(7));
		this.panelList.get(2).add(this.textAreaList.get(6));
		
		this.panelList.get(2).add(this.textAreaList.get(7));
		this.panelList.get(2).add(this.textFieldList.get(8));
		this.panelList.get(2).add(this.getButtonList().get(8));
		this.panelList.get(2).add(this.textAreaList.get(8));
		
	}
	
	private void manageHotelMenu() {
		this.panelList.get(3).setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		this.panelList.get(3).setPreferredSize(new Dimension(400, 800));
		this.createTextArea("");//List of hotels
		this.createTextField();
		this.createButton("Manage Chosen Hotel");
		
		this.panelList.get(3).add(this.textAreaList.get(10));
		this.panelList.get(3).add(this.textFieldList.get(9));
		this.panelList.get(3).add(this.buttonList.get(9));
		
		this.createTextArea("");
		this.createTextField();
		this.createButton("Change Hote's Name");
		
		this.createTextArea("");
		this.createTextField();
		this.createButton("Add this Amount of Standard");
		
		this.createTextArea("");
		this.createTextField();
		this.createButton("Remove Chosen Room");
		
		this.createTextArea("");
		this.createTextField();
		this.createButton("Update Base Price");
		
		this.createTextArea("");
		this.createTextField();
		this.createButton("Remove Reservation");
		
		this.createButton("Remove Hotel");
		
		//this.createTextArea("");
		this.createTextField();
		this.createButton("Add this Amount of Deluxe");
		
		//this.createTextArea("");
		this.createTextField();
		this.createButton("Add this Amount of Executive");
		
		/*
		this.buttonList.get(10).setPreferredSize(new Dimension(100, 25));
		this.buttonList.get(11).setPreferredSize(new Dimension(100, 25));
		this.buttonList.get(12).setPreferredSize(new Dimension(100, 25));
		this.buttonList.get(13).setPreferredSize(new Dimension(100, 25));
		this.buttonList.get(14).setPreferredSize(new Dimension(100, 25));
		this.buttonList.get(15).setPreferredSize(new Dimension(80, 25));
		this.buttonList.get(16).setPreferredSize(new Dimension(80, 25));
		*/
		
		this.buttonList.get(13).setPreferredSize(new Dimension(145, 25));
		this.getButtonList().get(15).setPreferredSize(new Dimension(350, 25));
		
		this.textFieldList.get(9).setColumns(15);
		this.textFieldList.get(10).setColumns(15);
		this.textFieldList.get(11).setColumns(15);
		this.textFieldList.get(12).setColumns(15);
		this.textFieldList.get(13).setColumns(8);
		this.textFieldList.get(14).setColumns(15);
		this.textFieldList.get(15).setColumns(15);
		this.textFieldList.get(16).setColumns(15);
		
		
		this.createConfirmationButton("Confirm Changes");
		this.createConfirmationButton("Discard Changes");
		
		this.createConfirmationButton("Confirm Changes");
		this.createConfirmationButton("Discard Changes");
		
		this.createConfirmationButton("Confirm Changes");
		this.createConfirmationButton("Discard Changes");
		
		this.createConfirmationButton("Confirm Changes");
		this.createConfirmationButton("Discard Changes");
		
		this.createConfirmationButton("Confirm Changes");
		this.createConfirmationButton("Discard Changes");
		
		this.createConfirmationButton("CONFIRM DELETION");
		this.createConfirmationButton("Wag nalang");
		
		this.createConfirmationButton("Confirm Changes");
		this.createConfirmationButton("Discard Changes");
		
		this.createConfirmationButton("Confirm Changes");
		this.createConfirmationButton("Discard Changes");
		
		
		
		//setting things to initially not visible
		for(int i = 11; i < 16; i++) {
			this.textAreaList.get(i).setVisible(false);
		}
		
		for(int i = 10; i < 17; i++) {
			this.textFieldList.get(i).setVisible(false);
		}
		
		for(int i = 10; i < 18; i++) {
			this.buttonList.get(i).setVisible(false);
		}
		
		for(int i = 0; i < this.confirmationButtonList.size(); i++) {
			this.confirmationButtonList.get(i).setVisible(false);
		}
		
		//add to panel
		this.panelList.get(3).add(this.textAreaList.get(11));
		this.panelList.get(3).add(this.textFieldList.get(10));
		this.panelList.get(3).add(this.buttonList.get(10));
		this.panelList.get(3).add(this.confirmationButtonList.get(0));
		this.panelList.get(3).add(this.confirmationButtonList.get(1));
		
		this.panelList.get(3).add(this.textAreaList.get(12));
		this.panelList.get(3).add(this.textFieldList.get(11));
		this.panelList.get(3).add(this.buttonList.get(11));
		this.panelList.get(3).add(this.textFieldList.get(15));
		this.panelList.get(3).add(this.buttonList.get(16));
		this.panelList.get(3).add(this.textFieldList.get(16));
		this.panelList.get(3).add(this.buttonList.get(17));
		this.panelList.get(3).add(this.confirmationButtonList.get(2));
		this.panelList.get(3).add(this.confirmationButtonList.get(3));
		this.panelList.get(3).add(this.confirmationButtonList.get(12));
		this.panelList.get(3).add(this.confirmationButtonList.get(13));
		this.panelList.get(3).add(this.confirmationButtonList.get(14));
		this.panelList.get(3).add(this.confirmationButtonList.get(15));
		
		this.panelList.get(3).add(this.textAreaList.get(13));
		this.panelList.get(3).add(this.textFieldList.get(12));
		this.panelList.get(3).add(this.buttonList.get(12));
		this.panelList.get(3).add(this.confirmationButtonList.get(4));
		this.panelList.get(3).add(this.confirmationButtonList.get(5));
		
		this.panelList.get(3).add(this.textAreaList.get(14));
		this.panelList.get(3).add(this.textFieldList.get(13));
		this.panelList.get(3).add(this.buttonList.get(13));
		this.panelList.get(3).add(this.confirmationButtonList.get(6));
		this.panelList.get(3).add(this.confirmationButtonList.get(7));
		
		this.panelList.get(3).add(this.textAreaList.get(15));
		this.panelList.get(3).add(this.textFieldList.get(14));
		this.panelList.get(3).add(this.buttonList.get(14));
		this.panelList.get(3).add(this.confirmationButtonList.get(8));
		this.panelList.get(3).add(this.confirmationButtonList.get(9));
		
		this.panelList.get(3).add(this.buttonList.get(15));
		this.panelList.get(3).add(this.confirmationButtonList.get(10));
		this.panelList.get(3).add(this.confirmationButtonList.get(11));
		
	}
	
	private void bookReservation() {
		this.panelList.get(4).setPreferredSize(new Dimension(400, 800));
		this.createTextArea("");
		this.createTextArea("Input Hotel To Book Into:");
		this.createTextField();
		this.createButton("Choose This Hotel");
		
		this.createTextArea("Input Guest Name:");
		this.createTextField();
		
		this.createTextArea("Input Check-In");
		this.createTextField();
		this.createTextArea("Input Check-Out");
		this.createTextField();
		this.createTextArea("");
		
		this.createTextArea("");
		this.createTextField();
		this.createTextArea("");
		
		this.createTextArea("Input Discount Code");
		this.createTextField();
		this.createTextArea("");
		
		this.createButton("Finalize Reservation");
		
		this.panelList.get(4).add(this.textAreaList.get(16));
		this.panelList.get(4).add(this.textAreaList.get(17));
		this.panelList.get(4).add(this.textFieldList.get(17));
		this.panelList.get(4).add(this.buttonList.get(18));
		
		this.panelList.get(4).add(this.textAreaList.get(18));
		this.panelList.get(4).add(this.textFieldList.get(18));
		
		this.panelList.get(4).add(this.textAreaList.get(19));
		this.panelList.get(4).add(this.textFieldList.get(19));
		this.panelList.get(4).add(this.textAreaList.get(20));
		this.panelList.get(4).add(this.textFieldList.get(20));
		
		this.panelList.get(4).add(this.textAreaList.get(22));
		this.panelList.get(4).add(this.textFieldList.get(21));

		this.panelList.get(4).add(this.buttonList.get(19));
		this.panelList.get(4).add(this.textAreaList.get(21));
	}
	
	public ArrayList<JTextArea> getTextAreaList(){
		return this.textAreaList;
	}
	
	public ArrayList<JTextField> getTextFieldList(){
		return this.textFieldList;
	}
	
	public ArrayList<JButton> getButtonList(){
		return this.buttonList;
	}
	
	public ArrayList<JPanel> getPanelList(){
		return this.panelList;
	}
	
	public ArrayList<JButton> getConfirmationButtonList(){
		return this.confirmationButtonList;
	}
}
