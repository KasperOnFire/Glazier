package data;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Kasper
 */
public class DBConnector {

    private Connection connection = null;

    //Constants
    private static final String IP = "localhost";
    private static final String PORT = "3306";

    /**
     * also constants
     */
    public static final String DATABASE = "glazierprices";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    /**
     * makes the connection to mysql db
     * @throws Exception
     */
    public DBConnector() throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE;
        this.connection = (Connection) DriverManager.getConnection(url, USERNAME, PASSWORD);
    }

    /**
     *returns connection.
     * @return
     */
    public Connection getConnection() {
        return this.connection;
    }

}
