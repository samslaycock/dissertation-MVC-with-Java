/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Booking;

/**
 *
 * @author Sam
 */
public class BookingController {
    
    /** Controller function to create a booking using Booking model
     *
     * @param bookingNo booking number
     * @param mobNo mobile number
     * @param arrivalDate arrival date of customer
     * @param departureDate departure date of customer
     */
    public void createBooking(int bookingNo, String mobNo, String arrivalDate, String departureDate){
        Booking b = new Booking("dissdb");
        b.createBooking(bookingNo, mobNo, arrivalDate, departureDate);
        b.closeConnection();
        
    }
    
    /** Controller function to update a booking using Booking model
     *
     * @param bookingNo booking number
     * @param mobNo customer mobile number
     * @param arrivalDate arrival date of customer
     * @param departureDate departure date of customer
     */
    public void updateBooking(int bookingNo, String mobNo, String arrivalDate, String departureDate){
        Booking b = new Booking("dissdb");
        b.updateBooking(bookingNo, mobNo, arrivalDate, departureDate);
        b.closeConnection();
        
    }
    
    /** Delete function to delete a booking using Booking model
     *
     * @param bookingNo booking number
     */
    public void deleteBooking(int bookingNo){
        Booking b = new Booking("dissdb");
        b.deleteBooking(bookingNo);
        b.closeConnection();
    }
    
    /** Generate function to generate a new booking number using Booking model
     *
     * @return return a booking number for a new booking
     */
    public int newBookingNumber(){
        Booking b = new Booking("dissdb");
        int bookingNo = b.newBookingNumber();
        b.closeConnection();
        return bookingNo;
    }
    
    /** Generate function to generate an array which contains a list of all booking records using Booking model
     *
     * @return 2d array which contains all the booking records
     */
    public static Object[][] listBookings(){
        Booking b = new Booking("dissdb");
        Object[][] bookingList = b.listBookings(); 
        b.closeConnection();
        return bookingList;
    }
    
    /** Generate function to generate an array which contains the fields of a booking record using Booking model
     *
     * @param bookingNumber booking number
     * @return array containing the booking number record
     */
    public Object[] loadBooking(int bookingNumber){
        Booking b = new Booking("dissdb");
        Object[] booking = b.loadBooking(bookingNumber); 
        b.closeConnection();
        return booking;
    }

    /** Get function which gets the mobile number of a booking using Booking model
     *
     * @param bookingNumber booking number
     * @return mobile number belonging to given booking number
     */
    public String getMobileNumber(int bookingNumber) {
        Booking b = new Booking("dissdb");
        String mobileNumber = b.getMobileNumber(bookingNumber);
        b.closeConnection();
        return mobileNumber;
    }
    
}
