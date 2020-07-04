/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sam
 */
public class Booking extends DatabaseConnection {
    
    /**Constructor which creates Booking and connects to database
     *
     * @param dbName database name
     */
    public Booking(String dbName) {
        this.connectDatabase(dbName);
    }

    /** Model function which creates a new Booking record in database
     *
     * @param bookingNo booking number
     * @param mobNo mobile number
     * @param arrivalDate arrival date of customer
     * @param departureDate departure date of customer
     */
    public void createBooking(int bookingNo, String mobNo, String arrivalDate, String departureDate) {
          //set up insert statement to create a new room booking record
        final String insertStmt = "INSERT INTO APP.BOOKING (BOOKINGNUMBER, MOBILENUMBER, ARRIVALDATE, DEPARTUREDATE) VALUES (?,?,?,?)";
        try {
            
            //prepare insert statement
            PreparedStatement pstmt = getConnection().prepareStatement(insertStmt);
            
            //insert fields for room record into the statement
            pstmt.setInt(1, bookingNo);
            pstmt.setString(2, mobNo);
            pstmt.setString(3, arrivalDate);
            pstmt.setString(4, departureDate);

           
            //execute statement to add the new booking record
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when adding  Booking record:" + sqle.toString());
        } 
    }
    
    /** Model function which updates a booking record in database
     *
     * @param bookingNo booking number
     * @param mobNo mobile number
     * @param arrivalDate arrival date of customer
     * @param departureDate departure date of customer
     */
    public void updateBooking(int bookingNo, String mobNo, String arrivalDate, String departureDate){
        //Set up update statement, inserting the fields passed to the method
        final String updateStmt = "UPDATE APP.BOOKING SET MOBILENUMBER='"+mobNo+"', ARRIVALDATE='"+arrivalDate+"', DEPARTUREDATE='"+departureDate+"' WHERE BOOKINGNUMBER="+bookingNo;
        try {
        //prepare the update statement for execution
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        //execute the update statement, amending the record in the table
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when updating booking record:" + sqle.toString());
        }
    }
    
    /** Model function which deletes a booking record in database
     *
     * @param bookingNumber booking number
     */
    public void deleteBooking(int bookingNumber) {
          //set up the delete statement, inserting the bookingnumber of the booking
        final String updateStmt = "DELETE FROM APP.BOOKING WHERE BOOKINGNUMBER="+bookingNumber;
        try {
        //prepare the delete statement for exeution
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        //execute the delete statement, removing the room record from the table
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when deleting booking record:" + sqle.toString());
        }
    }

    /** Model function which generates a booking number for a new booking record
     *
     * @return new booking number
     */
    public int newBookingNumber() {
    int bookingNumber = 0;
    
    try {
        //determine current max bookingnumber in the table booking
        final String countQuery = "SELECT MAX(BOOKINGNUMBER) AS maxCount FROM APP.BOOKING";
        this.setQuery(countQuery);
        this.runQuery();
        ResultSet output = this.getResultSet();
        output.next();
        bookingNumber = output.getInt("maxCount");
        int test = 3;        
        }catch(SQLException sqle) {   
        System.out.println("Exception when determining booking number:" + sqle.toString());
        }
    bookingNumber++;
    return bookingNumber;
    }
    
    /** Model function which generates a list of booking records
     *
     * @return 2d array which contains a list of booking records
     */
    public Object[][] listBookings(){
        int recordCount;
        try {
            //Determine number of list in the table
            final String countQuery = "SELECT COUNT(BOOKINGNUMBER) AS bookingCount FROM APP.BOOKING";
            this.setQuery(countQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            recordCount = output.getInt("bookingCount");
        } catch (SQLException sqle) {
            recordCount = 0;
            System.out.println("Exception when getting booking count:" + sqle.toString());
        }
        
        Object[][] bookingList = new Object[recordCount][4];
        int arrayCount = 0;
        try {
            //select all records in the booking table
            final String allQuery = "SELECT * FROM APP.BOOKING";
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            
            //load all player records into the array
            while ((output.next()) && (arrayCount < recordCount)) { 
                
                //put all of the player record fields into the array
                bookingList[arrayCount][0] = output.getInt("BOOKINGNUMBER");
                bookingList[arrayCount][1] = output.getString("MOBILENUMBER");
                bookingList[arrayCount][2] = output.getString("ARRIVALDATE");
                bookingList[arrayCount][3] = output.getString("DEPARTUREDATE");
                arrayCount++;
            }
        } catch (SQLException sqle) {
            System.out.println("Exception when populating booking array:" + sqle.toString());
        }
        return bookingList;
    }
    
    /**Model function to load a specific booking record given booking number
     *
     * @param bookingNumber booking number
     * @return array containing booking record
     */
    public Object[] loadBooking(int bookingNumber){
         String[] booking = new String[4];
        
        try {
            //select a booking record, given the primary key related to that record
            final String allQuery = "SELECT * FROM APP.BOOKING WHERE BOOKINGNUMBER="+bookingNumber;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            
            //put all of the record's field contents into an array
            booking[0] = output.getString("BOOKINGNUMBER");
            booking[1] = output.getString("MOBILENUMBER");
            booking[2] = output.getString("ARRIVALDATE");
            booking[3] = output.getString("DEPARTUREDATE");
           
            
         } catch (SQLException sqle) {
            System.out.println("Exception when loading booking details:" + sqle.toString());
        }   
         return booking;
    
    }

    /** Model function to get mobile number of a booking record given booking number
     *
     * @param bookingNumber booking number
     * @return mobile number
     */
    public String getMobileNumber(int bookingNumber) {
        String mobileNumber = null;
        try {
            //select a booking record, given the primary key related to that record
            final String allQuery = "SELECT * FROM APP.BOOKING WHERE BOOKINGNUMBER="+bookingNumber;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            
            //put all of the record's field contents into an array
            mobileNumber = output.getString("MOBILENUMBER");
  
           
            
         } catch (SQLException sqle) {
            System.out.println("Exception when loading mobile number from booking:" + sqle.toString());
        }   
         return mobileNumber;
    }
    }
    
    

