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
    private PriceCalculator calc = null;

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

    public int returnPrice(String hei, String wid, String frame) {
        double height = Double.parseDouble(hei);
        double width = Double.parseDouble(wid);
        int price = 0;
        String sql = "select price from pricelist where product='?'";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, frame.toLowerCase());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //call calculation via method or constructor
                //pc()
                return price;

            }
        } catch (SQLException sqlex) {

        }
        return price;
    }

}
