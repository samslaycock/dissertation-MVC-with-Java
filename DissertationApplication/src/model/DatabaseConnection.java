/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sam
 */
public class DatabaseConnection {
    private Connection dbConnection = null;
    private Statement stmt = null;
    private ResultSet dataRS = null;
    private String query = null;

    /**Loads sun driver for connection to database
     * 
     */
    private void loadDriver() {
        //TRY LOADING SUN DRIVER
        try {
            //LOAD SUN DRIVER
            Class driver = org.apache.derby.jdbc.ClientDriver.class;
            System.out.println("Class=" + driver.getSimpleName());
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        }//END try
        //DRIVER NOT FOUND, REPORT ERROR
        catch (ClassNotFoundException err) {
            System.out.println("Could not load driver ");
            System.exit(1);
        }//END catch
    }//END loadDriver()

    
    /** Method to connect to database, given the name of the database
     * 
     * @param dbName name of the database we're connecting to
     */
    public void connectDatabase(final String dbName)    {
        try {
            //CONNECT TO DATABASE
            loadDriver();
            dbConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/" + dbName, "APP", "APP");
        }//END try
        catch (SQLException error) {
            System.err.println("Error connecting to database: " + error.toString());
        }//END catch
    }//END connectDatabase()

    /**
     * Method to run the set query string
     */
    public void runQuery() {
        try {
            //CREATE A STATEMENT OBJECT FOR THE ALUMNI CONNECTION
            stmt = dbConnection.createStatement();
            //EXECUTE AN SQL STATEMENT TO RETURN A RESULT SET
            dataRS = stmt.executeQuery(query);
        } catch (SQLException error) {
            System.err.println("Error connecting to database: " + error.toString());
        }//END catch
    }//END generateResultSet()

    /**
     * Method to set the query string
     *
     * @param q The query to run
     */
    public void setQuery(final String q) {
        query = new String(q);
    }

    /**
     * Method to close the database connection. Tests to ensure that the
     * connection object is neither null nor the connection has been closed
     * first.
     */
    public void closeConnection() {
        try {
            if (null != dbConnection && !dbConnection.isClosed()) {
                dbConnection.close();
            }
        } catch (SQLException sqle) {
            System.out.println("Error closing connection: " + sqle.toString());
        }
    }

    /**
     * Returns the data retrieved from running a query
     *
     * @return The result set containing the data
     */
    public ResultSet getResultSet() {
        return dataRS;
    }

    /**
     * Returns the connection object for the current database
     *
     * @return The connection
     */
    public Connection getConnection() {
        return dbConnection;
    }

}

