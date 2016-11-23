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

    /**
     * The class constructor gives access to the getters in the class, which
     * give access to the database. The constructor also opens a connection to
     * the database.
     *
     * @param inputcon is the connection to the database that will be used.
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
     * This method gets the data from the database, and passes it along to the
     * method that does the actual calculation.
     *
     * @param hei height of the window.
     * @param wid width of the windows.
     * @param frame type of frame. Gets the price from DB
     * @param Glass type of glass. Gets the price from DB
     * @param metric centimeter or meter. For correct calulations.
     * @return the calculated prize for the specified window. Will later be
     * currency converted in PriceResult.java/currencyConvert();
     */
    public double getPrice(String hei, String wid, String frame,String Glass, String metric) {
        double height = Double.parseDouble(hei);
        double width = Double.parseDouble(wid);
        double frameprice = getFramePriceFromSQL(frame);
        double glassprice = getGlassPriceFromSQL(Glass);
        double finalPrice = 0;

        if (frameprice == 0) {
            return finalPrice;
        } else if (metric.toLowerCase().equals("cm")) {
            finalPrice = pc.calculatePriceCM(height, width, frameprice, glassprice);
            return finalPrice;
        } else if (metric.toLowerCase().equals("m")) {
            finalPrice = pc.calculatePriceM(height, width, frameprice, glassprice);
            return finalPrice;
        }
        return finalPrice;
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

    /**
     *
     * This method writes the users order to the database, in the "orders"
     * table.
     *
     * @param orderid the users chosen order id
     * @param price the price after it has been currencyconverted.
     * @param currency the currency the price is in, fx USD or EUR.
     */
    public void writeOrderToDB(String orderid, double price, String currency) {
        String sql = "insert into orders values('" + orderid + "', CURDATE(), " + price + ", '" + currency + "');";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException sqlex) {

        }
    }
}
