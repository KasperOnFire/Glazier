package data;

import PriceCalcServlet.PriceCalculator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kasper
 */
public class DataAccessObject {

    private DBConnector db = null;
    private Connection conn = null;
    private PriceCalculator pc = new PriceCalculator();

    //The class constructor gives access to the getters in the class, 
    //which give access to the database. The constructor also opens a 
    //connection to the database.
    /**
     *
     * @param inputcon
     */
    public DataAccessObject(DBConnector inputcon) {
        try {
            db = inputcon;
            conn = db.getConnection();

        } catch (Exception ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param hei
     * @param wid
     * @param frame
     * @param Glass
     * @return
     */
    public double returnPrice(String hei, String wid, String frame, String Glass) {
        double height = Double.parseDouble(hei);
        double width = Double.parseDouble(wid);
        double frameprice = getFramePriceFromSQL(frame);
        double glassprice = getGlassPriceFromSQL(Glass);
        double finalPrice = 0;

        if (frameprice == 0) {
            return finalPrice;
        } else {
            finalPrice = pc.calculatePrice(height, width, frameprice, glassprice);
            return finalPrice;
        }
    }

    private double getFramePriceFromSQL(String frame) {
        double framePrice = 0;
        String sql = "select price from pricelist where product='" + frame + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                framePrice = rs.getInt("price");
                return framePrice;
            }
        } catch (SQLException sqlex) {
            return framePrice;
        }
        return framePrice;
    }

    private double getGlassPriceFromSQL(String glass) {
        double glassPrice = 0;
        String sql = "select price from pricelist where product='" + glass + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                glassPrice = rs.getInt("price");
                return glassPrice;
            }
        } catch (SQLException sqlex) {
            return glassPrice;
        }
        return glassPrice;
    }
}
