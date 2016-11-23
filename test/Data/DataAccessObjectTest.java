package Data;

import data.DBConnector;
import data.DataAccessObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DataAccessObjectTest {

    public DataAccessObjectTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of returnPrice method, of class DataAccessObject, with numbers from
     * assignment papers.
     *
     * This is mainly for testing if the connection to the database can be
     * established! All calculation test will be done in
     * PriceCalculatorTest.java
     */
    @Test
    public void testReturnPriceCM() throws Exception {
        System.out.println("returnPrice");
        String hei = "100";
        String wid = "160";
        String frame = "vinyl frame";
        String glass = "glass";
        String metric = "cm";
        DBConnector conn = new DBConnector();
        DataAccessObject instance = new DataAccessObject(conn);
        double expResult = 1000.0;
        double result = instance.getPrice(hei, wid, frame, glass, metric);
        assertEquals(expResult, result, 0.0);

    }

}
