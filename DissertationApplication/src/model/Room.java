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
public class Room extends DatabaseConnection {
    
    /**
     * Creates a Room object and connects to the database
     *
     * @param dbName Service name of database
     */
    public Room(final String dbName) {
        this.connectDatabase(dbName);
    }
    
    /** Model function which creates a new room record
     *
     * @param roomNo room number
     * @param bedNo number of beds
     * @param roomPrice price of room
     * @param floorNo floor number
     * @param roomDesc room description
     * @param ensuite ensuite yes/no
     */
    public void addRoom(String roomNo, int bedNo, double roomPrice, String floorNo, String roomDesc, boolean ensuite){
        //set up insert statement to create a new room record
        final String insertStmt = "INSERT INTO APP.ROOM (ROOMNUMBER, FLOORNUMBER, BEDNUMBER, ROOMDESCRIPTION,ROOMPRICE, ENSUITE) VALUES (?,?,?,?,?,?)";
        try {
            
            //prepare insert statement
            PreparedStatement pstmt = getConnection().prepareStatement(insertStmt);
            
            //insert fields for room record into the statement
            pstmt.setString(1, roomNo);
            pstmt.setString(2, floorNo);
            pstmt.setInt(3, bedNo);
            pstmt.setString(4, roomDesc);
            pstmt.setDouble(5, roomPrice);
            pstmt.setBoolean(6, ensuite);
           
            //execute statement to add the new room record
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when adding Room record:" + sqle.toString());
        }
    }
     
    /**Model function which updates a room record
     *
     * @param roomNo room number
     * @param bedNo number of beds
     * @param roomPrice price of room
     * @param floorNo floor number
     * @param roomDesc room description
     * @param ensuite ensuite yes/no
     */
    public void updateRoom(String roomNo, int bedNo, double roomPrice, String floorNo, String roomDesc, boolean ensuite){
      //Set up update statement, inserting the fields passed to the method
        final String updateStmt = "UPDATE APP.ROOM SET BEDNUMBER="+bedNo+", ROOMPRICE="+roomPrice+", FLOORNUMBER='"+floorNo+"', ROOMDESCRIPTION='"+roomDesc+"', ENSUITE="+ensuite+" WHERE ROOMNUMBER='"+roomNo+"'";
        try {
        //prepare the update statement for execution
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        //execute the update statement, amending the record in the table
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when updating room record:" + sqle.toString());
        }
     }

    /**Model function which deletes a room record given the room number
     *
     * @param roomNo room number
     */
    public void deleteRoom(String roomNo) {
          //set up the delete statement, inserting the RoomNumber of the Room
        final String updateStmt = "DELETE FROM APP.ROOM WHERE ROOMNUMBER='"+roomNo+"'";
        try {
        //prepare the delete statement for exeution
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        //execute the delete statement, removing the room record from the table
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when deleting room record:" + sqle.toString());
        }
    }
    
    /**Model function which generates a list of all room records and their fields
     *
     * @return 2d array containing list of room records and fields
     */
    public Object[][] listRooms(){
        int recordCount;
        try {
            //Determine number of list in the table
            final String countQuery = "SELECT COUNT(ROOMNUMBER) AS roomCount FROM APP.ROOM";
            this.setQuery(countQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            recordCount = output.getInt("roomCount");
        } catch (SQLException sqle) {
            recordCount = 0;
            System.out.println("Exception when getting room count:" + sqle.toString());
        }
        
        Object[][] roomList = new Object[recordCount][6];
        int arrayCount = 0;
        try {
            //select all rooms in the rooms table
            final String allQuery = "SELECT * FROM APP.ROOM";
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            
            //load all room records into the array
            while ((output.next()) && (arrayCount < recordCount)) { 
                
                //put all of the player record fields into the array
                roomList[arrayCount][0] = output.getString("ROOMNUMBER");
                roomList[arrayCount][1] = output.getString("FLOORNUMBER");
                roomList[arrayCount][2] = output.getInt("BEDNUMBER");
                roomList[arrayCount][3] = output.getString("ROOMDESCRIPTION");
                roomList[arrayCount][4] = output.getDouble("ROOMPRICE");
                roomList[arrayCount][5] = output.getBoolean("ENSUITE");
                arrayCount++;
            }
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting populating room array:" + sqle.toString());
        }
        return roomList;
    }

    /**Model function which generates an array containing the room record given a room number
     *
     * @param roomNo room number
     * @return array containing room record
     */
    public Object[] loadRoom(String roomNo) {
        Object[] room = new Object[6];
        
        try {
            //select a room record, given the primary key related to that record
            final String allQuery = "SELECT * FROM APP.ROOM WHERE ROOMNUMBER='"+roomNo+"'";
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            
            //put all of the record's field contents into an array
            room[0] = output.getString("ROOMNUMBER");
            room[1] = output.getString("FLOORNUMBER");
            room[2] = Integer.parseInt(output.getString("BEDNUMBER"));
            room[3] = output.getString("ROOMDESCRIPTION");
            room[4] = output.getString("ROOMPRICE");
            room[5] = output.getBoolean("ENSUITE");
           
            
         } catch (SQLException sqle) {
            System.out.println("Exception when loading room details:" + sqle.toString());
        }   
         return room;
    }
    
}
