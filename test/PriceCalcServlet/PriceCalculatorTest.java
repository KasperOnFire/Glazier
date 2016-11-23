package PriceCalcServlet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PriceCalculatorTest {

    public PriceCalculatorTest() {
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
     * Test of calculatePrice method, of class PriceCalculator, with numbers
     * from assignment papers.
     */
    @Test
    public void testCalculatePriceCM() {
        System.out.println("calculatePrice");
        double hei = 100;
        double wid = 160.0;
        double framePrice = 200.0;
        double glassPrice = 300.0;
        PriceCalculator instance = new PriceCalculator();
        double expResult = 1000.0;
        double result = instance.calculatePriceCM(hei, wid, framePrice, glassPrice);
        assertEquals(expResult, result, 0.0);
        System.out.println("calculatePrice Passed");
    }

    @Test
    public void testCalculatePriceNegative() {
        //Negative test
        System.out.println("calculatePrice");
        double hei = -100;
        double wid = -160;
        double framePrice = 200.0;
        double glassPrice = 300.0;
        PriceCalculator instance = new PriceCalculator();
        double expResult = 0;
        double result = instance.calculatePriceCM(hei, wid, framePrice, glassPrice);
        assertEquals(expResult, result, 0.0);
        System.out.println("calculatePrice with negative numbers Passed");
    }

    @Test
    public void testCalculatePriceWithFrameZero() {
        System.out.println("calculatePrice");
        double hei = 0;
        double wid = 160.0;
        double framePrice = 20.0;
        double glassPrice = 300.0;
        PriceCalculator instance = new PriceCalculator();
        double expResult = 0;
        double result = instance.calculatePriceCM(hei, wid, framePrice, glassPrice);
        assertEquals(expResult, result, 0.0);
        System.out.println("calculatePrice with frame Zero Passed");
    }

    @Test
    public void testCalculatePriceLargeFrame() {
        System.out.println("calculatePrice");
        double hei = 26000;
        double wid = 16000;
        double framePrice = 200.0;
        double glassPrice = 300.0;
        PriceCalculator instance = new PriceCalculator();
        double expResult = 12564000.0;
        double result = instance.calculatePriceCM(hei, wid, framePrice, glassPrice);
        assertEquals(expResult, result, 0.0);
        System.out.println("calculatePrice with Large frame Passed");
    }

    @Test
    public void testCalculateCMAndMIsSame() {
        fail();
    }

}
