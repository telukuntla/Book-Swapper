
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package uncc.nbad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author teluk
 */
public class DBUtil {
    public static Statement preprareStatement() {
        Statement statement = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            // get a connection
            String dbURL      = "jdbc:mysql://localhost:3306/book_swapper";
            String username   = "root";
            String password   = "9848705342";
            Connection connection = DriverManager.getConnection(dbURL, username, password);

            // create a statement
            statement = connection.createStatement();
        } catch (Exception e) {

            /*
             *      sqlResult = "<p>Error loading the databse driver: <br>"
             *            + e.getMessage() + "</p>";
             */
        }

        return statement;
    }

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            // get a connection
            String dbURL    = "jdbc:mysql://localhost:3306/book_swapper";
            String username = "root";
            String password = "9848705342";

            connection = DriverManager.getConnection(dbURL, username, password);

            return connection;
        } catch (Exception e) {

            /*
             *      sqlResult = "<p>Error loading the databse driver: <br>"
             *            + e.getMessage() + "</p>";
             */
        }

        return connection;
    }
}

