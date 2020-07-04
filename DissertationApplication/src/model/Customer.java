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
public class Customer extends DatabaseConnection{

    /**Constructor which creates Customer and connects to database
     *
     * @param dbName
     */
    public Customer(String dbName) {
        this.connectDatabase(dbName);
    }
    
    /**Model function to create a new customer record
     *
     * @param mobNo mobile number
     * @param firstName first name
     * @param lastName last name
     * @param email email
     */
    public void addCustomer(String mobNo, String firstName, String lastName, String email){
         //set up insert statement to create a new customer record
        final String insertStmt = "INSERT INTO APP.CUSTOMER (MOBILENUMBER, FIRSTNAME, LASTNAME, EMAIL) VALUES (?,?,?,?)";
        try {
            
            //prepare insert statement
            PreparedStatement pstmt = getConnection().prepareStatement(insertStmt);
            
            //insert fields for room record into the statement
            pstmt.setString(1, mobNo);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, email);
           
            //execute statement to add the new Customer record
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when adding Customer record:" + sqle.toString());
        }  
    }
    
    /** Model function to load a customer record given the mobile number
     *
     * @param mobNo mobile number
     * @return array containing customer record
     */
    public String[] loadCustomer(String mobNo) {
        String[] customer = new String[4];
        
        try {
            //select a customer record, given the primary key related to that record
            final String allQuery = "SELECT * FROM APP.CUSTOMER WHERE MOBILENUMBER='"+mobNo+"'";
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            
            //put all of the record's field contents into an array
            customer[0] = output.getString("MOBILENUMBER");
            customer[1] = output.getString("FIRSTNAME");
            customer[2] = output.getString("LASTNAME");
            customer[3] = output.getString("EMAIL");
           
            
         } catch (SQLException sqle) {
            System.out.println("Exception when loading customer details:" + sqle.toString());
        }   
         return customer;
    }
    
}
