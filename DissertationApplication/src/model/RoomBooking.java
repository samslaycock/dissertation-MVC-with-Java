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
public class RoomBooking extends DatabaseConnection {
    
    /** Creates a RoomBooking object and connects to the database
     *
     * @param dbName
     */
    public RoomBooking(String dbName) {
        this.connectDatabase(dbName);
    }

    /** Model function which creates a new room booking record
     *
     * @param bookingNo booking number
     * @param roomNo room number
     * @param guestNo number of guests
     * @param catered catered? yes/no
     */
    public void addRoomBooking(int bookingNo, String roomNo, int guestNo, boolean catered) {
         //set up insert statement to create a new room booking record
        final String insertStmt = "INSERT INTO APP.ROOMBOOKING (BOOKINGNUMBER, ROOMNUMBER, GUESTNUMBER, CATERED, CONFIRMED) VALUES (?,?,?,?,?)";
        try {
            
            //prepare insert statement
            PreparedStatement pstmt = getConnection().prepareStatement(insertStmt);
            
            //insert fields for room booking record into the statement
            pstmt.setInt(1, bookingNo);
            pstmt.setString(2, roomNo);
            pstmt.setInt(3, guestNo);
            pstmt.setBoolean(4, catered);
            pstmt.setBoolean(5, false);

           
            //execute statement to add the new room booking record
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when adding Room Booking record:" + sqle.toString());
        }
    }
    
    /** Model function which deletes a room booking record given booking number and room number
     *
     * @param bookingNo booking number
     * @param roomNo room number
     */
    public void deleteRoomBooking(int bookingNo, String roomNo){
           //set up the delete statement
        final String updateStmt = "DELETE FROM APP.ROOMBOOKING WHERE BOOKINGNUMBER="+bookingNo+" AND ROOMNUMBER='"+roomNo+"'";
        try {
        //prepare the delete statement for exeution
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        //execute the delete statement, removing the room booking record from the table
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when deleting room booking record:" + sqle.toString());
        }
    }
    
    /** Model function which generates a list of room booking records
     *
     * @param bookingNo booking number
     * @return 2d array which contains a list of room booking records
     */
    public Object[][] loadRoomBookings(int bookingNo){
           int recordCount;
        try {
            //Determine number of list in the table
            final String countQuery = "SELECT COUNT(ROOMNUMBER) AS roomBookingCount FROM APP.ROOMBOOKING WHERE BOOKINGNUMBER="+bookingNo;
            this.setQuery(countQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            recordCount = output.getInt("roomBookingCount");
        } catch (SQLException sqle) {
            recordCount = 0;
            System.out.println("Exception when getting room booking count:" + sqle.toString());
        }
        
        Object[][] roomBookingList = new Object[recordCount][3];
        int arrayCount = 0;
        try {
            //select all records in the room booking table
            final String allQuery = "SELECT * FROM APP.ROOMBOOKING WHERE BOOKINGNUMBER="+bookingNo;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            
            //load all records into the array
            while ((output.next()) && (arrayCount < recordCount)) {     
                //put all of the record fields into the array
                roomBookingList[arrayCount][0] = output.getString("ROOMNUMBER");
                roomBookingList[arrayCount][1] = output.getInt("GUESTNUMBER");
                roomBookingList[arrayCount][2] = output.getBoolean("CATERED");
                arrayCount++;
            }
        } catch (SQLException sqle) {
            System.out.println("Exception when populating room booking array:" + sqle.toString());
        }
        return roomBookingList;
        
        
    }

    /** Model function which confirms room bookings record when booking record is created/finalised
     *
     * @param bookingNo booking number
     */
    public void confirmRoomBookings(int bookingNo){
         //Set up update statement, inserting the fields passed to the method
        final String updateStmt = "UPDATE APP.ROOMBOOKING SET CONFIRMED="+true+" WHERE BOOKINGNUMBER="+bookingNo;
        try {
        //prepare the update statement for execution
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        //execute the update statement, amending the record in the table
        pstmt.executeUpdate();
        
        } catch (SQLException sqle) {
            System.out.println("Exception when confirming room record:" + sqle.toString());
        }              
    }
    
    /** Model function which deletes unconfirmed records which belong to unconfirmed bookings
     *
     */
    public void deleteUnconfirmedRoomBookings(){
                   //set up the delete statement
        final String updateStmt = "DELETE FROM APP.ROOMBOOKING WHERE CONFIRMED="+false;
        try {
        //prepare the delete statement for exeution
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        //execute the delete statement, removing the room booking record from the table
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when deleting unconfirmed room booking record:" + sqle.toString());
        }
    }
    
    /** Model function which deletes room booking records when booking record is deleted
     *
     * @param bookingNo booking number
     */
    public void deleteBooking(int bookingNo){
            //set up the delete statement
        final String updateStmt = "DELETE FROM APP.ROOMBOOKING WHERE BOOKINGNUMBER="+bookingNo;
        try {
        //prepare the delete statement for exeution
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        //execute the delete statement, removing the room booking record from the table
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when deleting room booking when deleting booking record:" + sqle.toString());
        }
    }

  
    
}
