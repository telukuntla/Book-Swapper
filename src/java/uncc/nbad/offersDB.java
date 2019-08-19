
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package uncc.nbad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import org.owasp.esapi.ESAPI;

/**
 *
 * @author teluk
 */
public class offersDB {
    
    // This Db method removes offer from offers table and adds the offer to 
    //swaps table
    public static void acceptSwapOffer(String itemcode, String userid) {
        try {
            Statement statement = DBUtil.preprareStatement();
            Connection conn      = DBUtil.getConnection();
            PreparedStatement stmt      =
                conn.prepareStatement("SELECT * FROM offers WHERE ItemCode=? AND RequestedTo=?");

            stmt.setString(1, itemcode);
            stmt.setString(2, userid);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String itemCode          = rs.getString("ItemCode");
                String itemName          = rs.getString("ItemName");
                String requestedItemCode = rs.getString("RequestedItemcode");
                String requestedItemName = rs.getString("RequestedItemName");
                String requestedFrom     = rs.getString("requestedFrom");
                String requestedTo       = rs.getString("RequestedTo");
                Connection conn1             = DBUtil.getConnection();
                PreparedStatement stmt1             =
                    conn1.prepareStatement(
                        "INSERT INTO swaps (ItemCode,ItemName, RequestedItemcode, RequestedItemName, RequestedFrom, RequestedTo) VALUES(?,?,?,?,?,?)");

                stmt1.setString(1, ESAPI.encoder().encodeForHTML(itemCode));
                stmt1.setString(2, ESAPI.encoder().encodeForHTML(itemName));
                stmt1.setString(3, ESAPI.encoder().encodeForHTML(requestedItemCode));
                stmt1.setString(4, ESAPI.encoder().encodeForHTML(requestedItemName));
                stmt1.setString(5, ESAPI.encoder().encodeForHTML(requestedFrom));
                stmt1.setString(6, ESAPI.encoder().encodeForHTML(requestedTo));
                stmt1.executeUpdate();
            }

            Connection conn2 = DBUtil.getConnection();
            PreparedStatement stmt2 = conn2.prepareStatement("DELETE FROM offers WHERE ItemCode=? AND RequestedTo=?");

            stmt2.setString(1, itemcode);
            stmt2.setString(2, userid);
            stmt2.executeUpdate();
        } catch (SQLException e) {

        }
    }

    //This DB method adds offer to the offers table
    public void addOffer(String RequestedFrom, String RequestedTo, String ItemCode, String RequestedItemCode) {
        try {

            // get a connection
            Connection        conn = DBUtil.getConnection();
            PreparedStatement stmt =
                conn.prepareStatement(
                    "INSERT INTO offers (ItemCode,ItemName ,RequestedItemcode,RequestedItemName,RequestedFrom, RequestedTo) VALUES (?,?,?,?,?,?)");

            stmt.setString(1, ItemCode);
            stmt.setString(2, itemDB.getItemName(ItemCode));
            stmt.setString(3, RequestedItemCode);
            stmt.setString(4, itemDB.getItemName(RequestedItemCode));
            stmt.setString(5, RequestedFrom);
            stmt.setString(6, RequestedTo);
            stmt.executeUpdate();
        } catch (Exception e) {

        }
    }

    //This DB method removes the offer from the offers table
    public static void rejectSwapOffer(String itemcode, String userid) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM offers WHERE ItemCode=? AND RequestedTo=?");

            stmt.setString(1, itemcode);
            stmt.setString(2, userid);
            stmt.executeUpdate();

           // statement.executeUpdate(sub_query1 + itemcode + sub_query + sub_query2 + userid + sub_query);
        } catch (SQLException e) {
        }
    }
   
    //This DB method removes the persisted offer from the user profile
    public static void withdrawSwapOffer(String itemcode, String userid) {
        try {
            Connection  conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM offers WHERE RequestedFrom=?");

            stmt.setString(1, userid);
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    //This DB method returns the active offers in the user profile
    public static List<Offer> getOffers(String userid) {
        List<Offer> list = new ArrayList<Offer>();

        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from offers where RequestedFrom =?");

            stmt.setString(1, userid);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Offer offer = new Offer();

                // ESAPI.encoder().encodeForHTML(itemcode)
                String itemcode = rs.getString("ItemCode");

                offer.setItemcode(ESAPI.encoder().encodeForHTML(itemcode));

                String itemname = rs.getString("ItemName");
                offer.setItemName(ESAPI.encoder().encodeForHTML(itemname));

                String requestedItemCode = rs.getString("RequestedItemcode");
                offer.setRequestedItemcode(ESAPI.encoder().encodeForHTML(requestedItemCode));

                String requestedItemName = rs.getString("RequestedItemName");
                offer.setRequestedItemName(ESAPI.encoder().encodeForHTML(requestedItemName));

                String requestedFrom = rs.getString("RequestedFrom");
                offer.setRequestedFrom(ESAPI.encoder().encodeForHTML(requestedFrom));

                String requestedTo = rs.getString("RequestedTo");
                offer.setRequestedTo(ESAPI.encoder().encodeForHTML(requestedTo));
                list.add(offer);
            }

            stmt = conn.prepareStatement("select * from offers where RequestedTo=?");
            stmt.setString(1, userid);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                Offer  offer    = new Offer();
                String itemcode = rs1.getString("ItemCode");

                offer.setItemcode(ESAPI.encoder().encodeForHTML(itemcode));

                String requestedItemCode = rs1.getString("RequestedItemcode");
                offer.setRequestedItemcode(ESAPI.encoder().encodeForHTML(requestedItemCode));

                String itemname = rs1.getString("ItemName");
                offer.setItemName(ESAPI.encoder().encodeForHTML(itemname));

                String requestedFrom = rs1.getString("RequestedFrom");
                offer.setRequestedFrom(ESAPI.encoder().encodeForHTML(requestedFrom));

                String requestedItemName = rs1.getString("RequestedItemName");
                offer.setRequestedItemName(ESAPI.encoder().encodeForHTML(requestedItemName));

                String requestedTo = rs1.getString("RequestedTo");
                offer.setRequestedTo(ESAPI.encoder().encodeForHTML(requestedTo));
                list.add(offer);
            }
        } catch (Exception e) {

        }

        return list;
    }

    // This DB method only returns the offers the current user has requested for
    public static List<Offer> getOffersReqFromUser(String userid) {
        List<Offer> list = new ArrayList<Offer>();

        try {
            Connection        conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from offers where RequestedFrom =?");

            stmt.setString(1, userid);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Offer  offer    = new Offer();
                String itemcode = rs.getString("ItemCode");
                offer.setItemcode(ESAPI.encoder().encodeForHTML(itemcode));

                String itemname = rs.getString("ItemName");
                offer.setItemName(ESAPI.encoder().encodeForHTML(itemname));

                String requestedItemCode = rs.getString("RequestedItemcode");
                offer.setRequestedItemcode(ESAPI.encoder().encodeForHTML(requestedItemCode));

                String requestedItemName = rs.getString("RequestedItemName");
                offer.setRequestedItemName(ESAPI.encoder().encodeForHTML(requestedItemName));

                String requestedFrom = rs.getString("RequestedFrom");
                offer.setRequestedFrom(ESAPI.encoder().encodeForHTML(requestedFrom));

                String requestedTo = rs.getString("RequestedTo");
                offer.setRequestedTo(ESAPI.encoder().encodeForHTML(requestedTo));
                list.add(offer);
            }
        } catch (Exception e) {
        }

        return list;
    }

    // This DB method only returns the offers requested to current user
    public static List<Offer> getOffersReqToUser(String userid) {
        List<Offer> list = new ArrayList<Offer>();

        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from offers where RequestedTo=?");

            stmt.setString(1, userid);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                Offer  offer    = new Offer();
                String itemcode = rs1.getString("ItemCode");
          
                offer.setItemcode(ESAPI.encoder().encodeForHTML(itemcode));
                
                String requestedItemCode = rs1.getString("RequestedItemcode");
                offer.setRequestedItemcode(ESAPI.encoder().encodeForHTML(requestedItemCode));

                String itemname = rs1.getString("ItemName");
                offer.setItemName(ESAPI.encoder().encodeForHTML(itemname));

                String requestedFrom = rs1.getString("RequestedFrom");
                offer.setRequestedFrom(ESAPI.encoder().encodeForHTML(requestedFrom));

                String requestedItemName = rs1.getString("RequestedItemName");
                offer.setRequestedItemName(ESAPI.encoder().encodeForHTML(requestedItemName));

                String requestedTo = rs1.getString("RequestedTo");
                offer.setRequestedTo(ESAPI.encoder().encodeForHTML(requestedTo));
             
                list.add(offer);
            }
        } catch (Exception e) {

        }

        return list;
    }
}

