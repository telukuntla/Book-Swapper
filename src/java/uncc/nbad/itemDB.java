
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package uncc.nbad;

import java.io.FileReader;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.owasp.esapi.ESAPI;

/**
 *
 * @author saikr
 */
public class itemDB {
    
    // This DB Method updates the status of particular item 
    public static void UpdateStatus(String itemcode, String Status) {
        try {
            Connection  conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE useritems SET ItemStatus=? WHERE ItemCode=?");

            stmt.setString(1, Status);
            stmt.setString(2, itemcode);
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
   
    public void addItem(Item item) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO items VALUES (?,?,?,?,?,?,?,?)");

            stmt.setString(1, item.getName());
            stmt.setString(2, item.getItemcode());
            stmt.setString(3, item.getHeadline());
            stmt.setString(4, item.getAuthor());
            stmt.setString(5, item.getRating());
            stmt.setString(6, item.getCategory());
            stmt.setString(7, item.getImageurl());
            stmt.setString(8, item.getDescription());
            stmt.executeUpdate();
        } catch (Exception e) {}
    }

    public void addItem(String itemCode, String itemName, String Author, String headline, String Category,
                        String rating, String description, String imageUrl) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO items VALUES (?,?,?,?,?,?,?,?)");

            stmt.setString(1, itemName);
            stmt.setString(2, itemCode);
            stmt.setString(3, headline);
            stmt.setString(4, Author);
            stmt.setString(5, rating);
            stmt.setString(6, Category);
            stmt.setString(7, imageUrl);
            stmt.setString(8, description);
            stmt.executeUpdate();
        } catch (Exception e) {}
    }

    //This DB method deletes item from the user profile
    public void delete_item(String userid, String itemcode) {
        try {
            Statement  statement = DBUtil.preprareStatement();
            Connection conn      = DBUtil.getConnection();
            PreparedStatement stmt      = conn.prepareStatement("DELETE FROM offers WHERE ItemCode=?");

            stmt.setString(1, itemcode);
            stmt.executeUpdate();

            Connection conn1 = DBUtil.getConnection();
            PreparedStatement stmt1 = conn1.prepareStatement("DELETE FROM useritems WHERE ItemCode=? AND UserId=?");

            stmt1.setString(1, itemcode);
            stmt1.setString(2, userid);
            stmt1.executeUpdate();
        } catch (SQLException e) {
        }
    }

    //This method returns all items that are pesent in the system (for category page)
    public List<Item> getAllItems() {
        List<Item> list = new ArrayList<Item>();

        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM items");
            ResultSet rs   = stmt.executeQuery();

            while (rs.next()) {
                Item   item_current = new Item();
                String name         = rs.getString("Name");

                item_current.setName(ESAPI.encoder().encodeForHTML(name));
                String author = rs.getString("Author");

                item_current.setAuthor(ESAPI.encoder().encodeForHTML(author));
                String headline = rs.getString("HeadLine");

                item_current.setHeadline(ESAPI.encoder().encodeForHTML(headline));
                String descrption = rs.getString("Description");

                item_current.setDescription(ESAPI.encoder().encodeForHTML(descrption));
                String Category = rs.getString("Category");

                item_current.setCategory(ESAPI.encoder().encodeForHTML(Category));
                String imageurl = rs.getString("Imageurl");

                item_current.setImageurl(ESAPI.encoder().encodeForHTML(imageurl));
                String rating = rs.getString("Rating");

                item_current.setRating(ESAPI.encoder().encodeForHTML(rating));
                String itemcode = rs.getString("ItemCode");

                item_current.setItemcode(ESAPI.encoder().encodeForHTML(itemcode));
                list.add(item_current);
            }
        } catch (SQLException e) {

        }

        return list;
    }

    //This method returns particular item based on itemcode
    public Item getItem(String itemcode) {
        Item item = new Item();

        try {
            Connection        conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM items WHERE ItemCode=?");

            stmt.setString(1, itemcode);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                item.setItemcode(itemcode);

                String name = rs.getString("Name");
                item.setName(ESAPI.encoder().encodeForHTML(name));

                String headline = rs.getString("Headline");
                item.setHeadline(ESAPI.encoder().encodeForHTML(headline));

                String author = rs.getString("Author");
                item.setAuthor(ESAPI.encoder().encodeForHTML(author));

                String rating = rs.getString("Rating");
                item.setRating(ESAPI.encoder().encodeForHTML(rating));

                String category = rs.getString("Category");
                item.setCategory(ESAPI.encoder().encodeForHTML(category));
                
                String imageurl = rs.getString("ImageUrl");
                item.setImageurl(ESAPI.encoder().encodeForHTML(imageurl));

                String description = rs.getString("Description");
                item.setDescription(ESAPI.encoder().encodeForHTML(description));
            }
        } catch (Exception e) {
        }

        return item;
    }
 
    //This DB method returns the name of the item
    public static String getItemName(String itemCode) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT Name FROM items WHERE ItemCode=?");

            stmt.setString(1, itemCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return ESAPI.encoder().encodeForHTML(rs.getString("Name"));
            }
        } catch (Exception e) {}

        return null;
    }
   
    // This DB method returns the owner for the item
    public static String getItemOwner(String itemCode) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT UserId FROM useritems WHERE ItemCode=?");

            stmt.setString(1, itemCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return ESAPI.encoder().encodeForHTML(rs.getString("UserId"));
            }
        } catch (Exception e) {}

        return null;
    }

    //This DB method returns status of the item
    public String getItemStatus(String itemCode) {
        String status = "";

        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT ItemStatus FROM useritems WHERE ItemCode=?");

            stmt.setString(1, itemCode);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                status = ESAPI.encoder().encodeForHTML(rs.getString("ItemStatus"));
            }

            return status;
        } catch (SQLException e) {
        }

        return status;
    }

    public void getOffers(String userId) {}
}
