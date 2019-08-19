
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uncc.nbad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.owasp.esapi.ESAPI;

/**
 *
 * @author teluk
 */
public class FeedbackDB {
    
    //adds record in the itemfeedback table
    public static void addItemFeedback(String itemCode, String userId, String rating) {
        try {
            Connection        conn = DBUtil.getConnection();
            PreparedStatement stmt =
                conn.prepareStatement("INSERT INTO itemfeedback(userID,itemCode,rating) VALUES(?,?,?)");

            stmt.setString(1, userId);
            stmt.setString(2, itemCode);
            stmt.setString(3, rating);
            stmt.executeUpdate();

            Connection        conn1 = DBUtil.getConnection();
            PreparedStatement stmt1 = conn1.prepareStatement("select rating from itemfeedback where itemCode=?");

            stmt1.setString(1, itemCode);

            ResultSet rs1   = stmt1.executeQuery();
            int       count = 0;
            int       sum   = 0;

            while (rs1.next()) {
                sum = sum + Integer.parseInt(ESAPI.encoder().encodeForHTML(rs1.getString("rating")));
                count++;
            }

            double average   = sum / count;
            String avgRating = average + "/5";
            Connection conn2     = DBUtil.getConnection();
            PreparedStatement stmt2     = conn2.prepareStatement("UPDATE items SET Rating=? WHERE ItemCode=?");

            stmt2.setString(1, avgRating);
            stmt2.setString(2, itemCode);
            stmt2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void addOfferFeedback(String offerID, String userID1, String userID2, String rating) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO userfeedback VALUES(?,?,?,?)");

            stmt.setString(1, offerID);
            stmt.setString(2, userID1);
            stmt.setString(3, userID2);
            stmt.setString(4, rating);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //add record in the userfeedbac table
    public static void addUserFeedback(String itemCode, String fromUser, String toUser, String rating) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt =
                conn.prepareStatement("select ID, RequestedTo from swaps where RequestedFrom =? AND itemCode=?");

            stmt.setString(1, fromUser);
            stmt.setString(2, itemCode);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String   offerid = ESAPI.encoder().encodeForHTML(rs.getString("ID"));
                Connection conn1   = DBUtil.getConnection();
                PreparedStatement stmt1   = conn1.prepareStatement("INSERT INTO userfeedback VALUES(?,?,?,?)");

                stmt1.setString(1, offerid);
                stmt1.setString(2, fromUser);
                stmt1.setString(3, toUser);
                stmt1.setString(4, rating);
                stmt1.executeUpdate();
            } else {
                Connection        conn2 = DBUtil.getConnection();
                PreparedStatement stmt2 =
                    conn2.prepareStatement("select ID, RequestedFrom from swaps where RequestedTo =? AND itemCode=?");

                stmt2.setString(1, fromUser);
                stmt2.setString(2, itemCode);

                ResultSet rs2 = stmt2.executeQuery();

                if (rs2.next()) {
                    String offerid = ESAPI.encoder().encodeForHTML(rs.getString("ID"));
                    Connection conn1   = DBUtil.getConnection();
                    PreparedStatement stmt1   = conn1.prepareStatement("INSERT INTO userfeedback VALUES(?,?,?,?)");

                    stmt1.setString(1, offerid);
                    stmt1.setString(2, fromUser);
                    stmt1.setString(3, toUser);
                    stmt1.setString(4, rating);
                    stmt1.executeUpdate();
                } else {
                    FeedbackDB.addUserFeedback2(itemCode, fromUser, toUser, rating);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addUserFeedback2(String itemCode, String fromUser, String toUser, String rating) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt =
                conn.prepareStatement(
                    "select ID, RequestedTo from swaps where RequestedFrom =? AND RequestedItemcode=?");

            stmt.setString(1, fromUser);
            stmt.setString(2, itemCode);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String offerid = ESAPI.encoder().encodeForHTML(rs.getString("ID"));
                Connection conn1   = DBUtil.getConnection();
                PreparedStatement stmt1   = conn1.prepareStatement("INSERT INTO userfeedback VALUES(?,?,?,?)");

                stmt1.setString(1, offerid);
                stmt1.setString(2, fromUser);
                stmt1.setString(3, toUser);
                stmt1.setString(4, rating);
                stmt1.executeUpdate();
            } else {
                Connection conn2 = DBUtil.getConnection();
                PreparedStatement stmt2 =
                    conn2.prepareStatement("select ID from swaps where RequestedTo =? AND RequestedItemcode=?");

                stmt2.setString(1, fromUser);
                stmt2.setString(2, itemCode);

                ResultSet rs2 = stmt2.executeQuery();

                if (rs2.next()) {
                    String offerid = ESAPI.encoder().encodeForHTML(rs2.getString("ID"));
                    Connection conn1   = DBUtil.getConnection();
                    PreparedStatement stmt1   = conn1.prepareStatement("INSERT INTO userfeedback VALUES(?,?,?,?)");

                    stmt1.setString(1, offerid);
                    stmt1.setString(2, fromUser);
                    stmt1.setString(3, toUser);
                    stmt1.setString(4, rating);
                    stmt1.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean itemFeedbackPresent(String itemCode, String userId) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from itemfeedback where userID =? AND itemCode=?");

            stmt.setString(1, userId);
            stmt.setString(2, itemCode);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static boolean userFeedBackPresent(String user, String userForFeedback) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from userfeedback where fromUser =? AND toUser=?");

            stmt.setString(1, user);
            stmt.setString(2, userForFeedback);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    //This Db class returns the rating for a user
    public static String getUserForFeedBack(String itemCode, String user) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt =
                conn.prepareStatement("select ID, RequestedTo from swaps where RequestedFrom =? AND itemCode=?");

            stmt.setString(1, user);
            stmt.setString(2, itemCode);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return ESAPI.encoder().encodeForHTML(rs.getString("RequestedTo"));
            } else {
                Connection conn1 = DBUtil.getConnection();
                PreparedStatement stmt1 =
                    conn1.prepareStatement("select ID, RequestedFrom from swaps where RequestedTo =? AND itemCode=?");

                stmt1.setString(1, user);
                stmt1.setString(2, itemCode);

                ResultSet rs1 = stmt.executeQuery();

                if (rs1.next()) {
                    return ESAPI.encoder().encodeForHTML(rs.getString("RequestedFrom"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static String getUserForFeedBack1(String itemCode, String user) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt =
                conn.prepareStatement(
                    "select ID, RequestedTo from swaps where RequestedFrom =? AND RequestedItemcode=?");

            stmt.setString(1, user);
            stmt.setString(2, itemCode);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return ESAPI.encoder().encodeForHTML(rs.getString("RequestedTo"));
            } else {
                Connection conn1 = DBUtil.getConnection();
                PreparedStatement stmt1 =
                    conn1.prepareStatement(
                        "select ID, RequestedFrom from swaps where RequestedTo =? AND RequestedItemcode=?");

                stmt1.setString(1, user);
                stmt1.setString(2, itemCode);

                ResultSet rs1 = stmt1.executeQuery();

                if (rs1.next()) {
                    return ESAPI.encoder().encodeForHTML(rs1.getString("RequestedFrom"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    // This Db method returns the rating for user
    public static String getUserRating(String user) {
        try {
            Connection  conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select rating from userfeedback where toUser=?");

            stmt.setString(1, user);

            ResultSet rs   = stmt.executeQuery();
            int count = 0;
            int sum   = 0;

            while (rs.next()) {
                sum = sum + Integer.parseInt(ESAPI.encoder().encodeForHTML(rs.getString("rating")));
                count++;
            }

            double average   = sum / count;
            String avgRating = average + "/5";

            return avgRating;
        } catch (Exception e) {}

        return null;
    }
}


