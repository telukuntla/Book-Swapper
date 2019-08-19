package uncc.nbad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

import org.owasp.esapi.ESAPI;

public class UserDB {
    
   //This DB method adds user to the system by persisting user details in the database
    public static void addUser(User user) {
        try {
            Statement  statement = DBUtil.preprareStatement();
            Connection  conn      = DBUtil.getConnection();
            PreparedStatement stmt      = conn.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?,?,?,?,?,?)");

            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getAddressField1());
            stmt.setString(6, user.getAddressFiled2());
            stmt.setString(7, user.getCity());
            stmt.setString(8, user.getState());
            stmt.setString(9, user.getPostalCode());
            stmt.setString(10, user.getPostalCode());
            stmt.executeUpdate();

            Connection conn1 = DBUtil.getConnection();
            PreparedStatement stmt1 = conn1.prepareStatement("INSERT INTO credentials VALUES(?,?)");

            stmt1.setString(1, user.getUserId());
            stmt1.setString(2, user.getPassword());
            stmt1.executeUpdate();
        } catch (Exception e) {}
    }

    public static void addUser(String firstName, String lastName, String email, String address1, String userId,
                               String address2, String city, String state, String zipcode, String country) {
        try {
            Connection  conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?,?,?,?,?,?)");

            stmt.setString(1, userId);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
            stmt.setString(5, address1);
            stmt.setString(6, address2);
            stmt.setString(7, city);
            stmt.setString(8, state);
            stmt.setString(9, zipcode);
            stmt.setString(10, country);
            stmt.executeUpdate();
        } catch (Exception e) {}
    }
    
   //This DB method returns all users present in the system  
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<User>();

        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs   = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setUserId(ESAPI.encoder().encodeForHTML(rs.getString("UserId")));
                user.setFirstName(ESAPI.encoder().encodeForHTML(rs.getString("FirstName")));
                user.setLastName(ESAPI.encoder().encodeForHTML(rs.getString("LastName")));
                user.setPostalCode(ESAPI.encoder().encodeForHTML(rs.getString("EmailAddress")));
                user.setCountry(ESAPI.encoder().encodeForHTML(rs.getString("Country")));
                user.setCity(ESAPI.encoder().encodeForHTML(rs.getString("City")));
                user.setAddressFiled2(ESAPI.encoder().encodeForHTML(rs.getString("AddressFiled2")));
                user.setAddressField1(ESAPI.encoder().encodeForHTML(rs.getString("AddressField1")));
                user.setEmail(ESAPI.encoder().encodeForHTML(rs.getString("EmailAddress")));
                user.setState(ESAPI.encoder().encodeForHTML(rs.getString("StateCode")));
                list.add(user);
            }
        } catch (Exception e) {}

        return list;
    }

    //This DB mthod returns requeted user object
    public static User getUser(String userID) {
        User user = new User();

        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE UserId=?");

            stmt.setString(1, userID);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user.setUserId(ESAPI.encoder().encodeForHTML(rs.getString("UserId")));
                user.setFirstName(ESAPI.encoder().encodeForHTML(rs.getString("FirstName")));
                user.setLastName(ESAPI.encoder().encodeForHTML(rs.getString("LastName")));
                user.setPostalCode(ESAPI.encoder().encodeForHTML(rs.getString("EmailAddress")));
                user.setCountry(ESAPI.encoder().encodeForHTML(rs.getString("Country")));
                user.setCity(ESAPI.encoder().encodeForHTML(rs.getString("City")));
                user.setAddressFiled2(ESAPI.encoder().encodeForHTML(rs.getString("AddressFiled2")));
                user.setAddressField1(ESAPI.encoder().encodeForHTML(rs.getString("AddressField1")));
                user.setEmail(ESAPI.encoder().encodeForHTML(rs.getString("EmailAddress")));
                user.setState(ESAPI.encoder().encodeForHTML(rs.getString("StateCode")));
            }
        } catch (SQLException e) {
        }

        return user;
    }

    //This DB mthod returns requeted user profile object
    public static Userprofile getUserProfile(String id) {
        Userprofile up = new Userprofile();

        try {
            Statement         statement = DBUtil.preprareStatement();
            List<Useritem>    list      = new ArrayList<Useritem>();
            Connection        conn      = DBUtil.getConnection();
            PreparedStatement stmt      =
                conn.prepareStatement(
                    "select useritems.ItemCode, useritems.ItemStatus, useritems.UserRating,items.Name, items.Category FROM useritems INNER JOIN items ON useritems.ItemCode=items.ItemCode WHERE useritems.UserId=?");

            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Useritem useritem = new Useritem();
                String   itemcode = rs.getString("ItemCode");
                useritem.setUserItem(ESAPI.encoder().encodeForHTML(itemcode));

                String itemStatus = rs.getString("ItemStatus");
                useritem.setStatus(ESAPI.encoder().encodeForHTML(itemStatus));

                String rating = rs.getString("UserRating");
                useritem.setRating(ESAPI.encoder().encodeForHTML(rating));
                
                useritem.setItemName(ESAPI.encoder().encodeForHTML(rs.getString("Name")));
                useritem.setCategory(ESAPI.encoder().encodeForHTML(rs.getString("Category")));
                list.add(useritem);
            }

            up.setUserItems(list);
            up.setUserId(id);

            // return list;
        } catch (SQLException e) {

        }

        return up;
    }

    public static List<User> getUsers() {
        return null;
    }

    //This method validates if the user credentials are valid. This method gets invoked 
    //during signin process
    public static boolean isValidUser(String UserId, String password) {
        try {
            Connection  conn = DBUtil.getConnection();           
            PreparedStatement stmt = conn.prepareStatement("SELECT password FROM credentials WHERE userID=?");
            stmt.setString(1, UserId);
 
            ResultSet rs = stmt.executeQuery();
           
            if(rs.next()){       
                
               //UpdatableBCrypt is utility class that has pre defined methods for salting and hashing password.
               //JBCrypt-0.4.jar is the library 
                UpdatableBCrypt uBCrypt = new UpdatableBCrypt();
                
               //verifyHash is the method preset in UpdatableBCrypt Utility class, This method verifies Hash that is 
               //stored in DB with password entered by the user.
                return uBCrypt.verifyHash(password, rs.getString("password"));
             }
           
        } catch (SQLException e) {}

        return false;
    }
}

