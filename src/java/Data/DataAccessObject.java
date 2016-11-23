package data;

import PriceCalc.PriceCalculator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAccessObject {

    private DBConnector db = null;
    private Connection conn = null;
    private PriceCalculator pc = new PriceCalculator();

    //The class constructor gives access to the getters in the class, 
    //which give access to the database. The constructor also opens a 
    //connection to the database.
    public DataAccessObject(DBConnector inputcon) {
        try {
            db = inputcon;
            conn = db.getConnection();

        } catch (Exception ex) {
            Logger.getLogger(DataAccessObjectImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double returnPrice(String hei, String wid, String frame) {
        double height = Double.parseDouble(hei);
        double width = Double.parseDouble(wid);
        double frameprice = getFramePrice(frame);
        double finalPrice = 0;

        finalPrice = pc.calculatePrice(height, width, frameprice);
        return finalPrice;
    }

    private double getFramePrice(String frame) {
        double frameprice = 0;
        String sql = "select price from pricelist where product='?'";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, frame.toLowerCase());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                frameprice = rs.getInt("price");
                return framePrice;
            }
        } catch (SQLException sqlex) {
            return 0;
        }
        return 0;
    }
}
