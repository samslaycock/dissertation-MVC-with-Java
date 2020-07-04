/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Customer;

/**
 *
 * @author Sam
 */
public class CustomerController {
    
    /** Controller function which creates a new customer record using Customer model
     *
     * @param mobNo mobile number
     * @param firstName first name
     * @param lastName last name
     * @param email email
     */
    public void addCustomer(String mobNo, String firstName, String lastName, String email){
        Customer c = new Customer("dissdb");
        c.addCustomer(mobNo, firstName, lastName, email);
        c.closeConnection();    
    }
    
    /** Controller function which loads a customer details given the customer's mobile number using Customer model
     *
     * @param mobNo mobile number
     * @return array which contains the fields of customer record
     */
    public String[] loadCustomer(String mobNo){
      Customer c = new Customer("dissdb");
      String[] customer = c.loadCustomer(mobNo);
      c.closeConnection();
      
      return customer;
    }
    
}
