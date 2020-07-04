/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Room;

/**
 *
 * @author Sam
 */
public class RoomController {
    
    /** Controller function to add new room record using Room model
     *
     * @param roomNo room number
     * @param bedNo number of beds
     * @param roomPrice price of room
     * @param floorNo floor number
     * @param roomDesc room description
     * @param ensuite ensuite? yes/no
     */
    public static void addRoom(String roomNo, int bedNo, double roomPrice, String floorNo, String roomDesc, boolean ensuite){
        Room r = new Room("dissdb");
        r.addRoom(roomNo, bedNo, roomPrice, floorNo, roomDesc, ensuite); 
        r.closeConnection();
    }
    
    /** Controller function to update room record using Room model
     *
     * @param roomNo room number
     * @param bedNo number of beds
     * @param roomPrice price of room
     * @param floorNo floor number
     * @param roomDesc room description
     * @param ensuite ensuite? yes/no
     */
    public static void updateRoom(String roomNo, int bedNo, double roomPrice, String floorNo, String roomDesc, boolean ensuite){
        Room r = new Room("dissdb");
        r.updateRoom(roomNo, bedNo, roomPrice, floorNo, roomDesc, ensuite); 
        r.closeConnection();
    }
    
    /** Controller function to delete room record using Room model
     *
     * @param roomNo room number
     */
    public static void deleteRoom(String roomNo){
        Room r = new Room("dissdb");
        r.deleteRoom(roomNo); 
        r.closeConnection();
    }
    
    /** Controller function to generate a list of all room records using Room model
     *
     * @return 2d array with list of room records
     */
    public static Object[][] listRooms(){
        Room r = new Room("dissdb");
        Object[][] roomList = r.listRooms(); 
        r.closeConnection();
        return roomList;
    }
    
    /** Controller function to generate an array of a specific room record using Room model
     *
     * @param roomNo room number
     * @return array with room record fields
     */
    public static Object[] loadRoom(String roomNo){
        Room r = new Room("dissdb");
        Object[] room = r.loadRoom(roomNo); 
        r.closeConnection();
        return room;
    }
 
}