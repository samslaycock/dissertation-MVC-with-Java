/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.RoomBooking;

/**
 *
 * @author Sam
 */
public class RoomBookingController {

    /** Controller function to add a room booking record using RoomBooking model
     *
     * @param bookingNo booking number
     * @param roomNo room number
     * @param guestNo number of guests
     * @param catered catered? yes/no
     */
    public void addRoomBooking(int bookingNo, String roomNo, int guestNo, boolean catered){
        RoomBooking rb = new RoomBooking("dissdb");
        rb.addRoomBooking(bookingNo, roomNo, guestNo, catered);
        rb.closeConnection();
    }
    
    /** Controller function to delete a room booking record using RoomBooking model
     *
     * @param bookingNo booking number
     * @param roomNo room number
     */
    public void deleteRoomBooking(int bookingNo, String roomNo){
        RoomBooking rb = new RoomBooking("dissdb");
        rb.deleteRoomBooking(bookingNo, roomNo);
        rb.closeConnection();     
    }
    
    /** Controller function to delete room booking records linked to a deleted booking record using RoomBooking model
     *
     * @param bookingNo booking number
     */
    public void deleteBooking(int bookingNo){
        RoomBooking rb = new RoomBooking("dissdb");
        rb.deleteBooking(bookingNo);
        rb.closeConnection();     
    }
    
    /** Controller function to load all room bookings belonging to a single booking using RoomBooking model
     *
     * @param bookingNo booking number
     * @return 2d array which contains a list of all the room booking records relating to booking
     */
    public Object[][] loadRoomBookings(int bookingNo){
        
        RoomBooking rb = new RoomBooking("dissdb");
        Object[][] roomBookingList = rb.loadRoomBookings(bookingNo);
        rb.closeConnection();    
        
        return roomBookingList;
    }
    
    /** Controller function to confirm created room booking records when booking is created using RoomBooking model
     *
     * @param bookingNo booking number
     */
    public void confirmRoomBookings(int bookingNo){
         RoomBooking rb = new RoomBooking("dissdb");
         rb.confirmRoomBookings(bookingNo);
         rb.closeConnection();
         
    }
    
    /** Controller function to delete unconfirmed room bookings from non-created bookings using RoomBooking model
     *
     */
    public void deleteUnconfirmedRoomBookings(){
        RoomBooking rb = new RoomBooking("dissdb");
        rb.deleteUnconfirmedRoomBookings();
        rb.closeConnection();
    }
  
    
}
