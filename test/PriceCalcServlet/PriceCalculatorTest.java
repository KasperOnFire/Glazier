package PriceCalcServlet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kasper
 */
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

    /**
     * Test if the calculation returns 0 when inputting one or more negative
     * numbers (used for servlet error handling since price can never be 0).
     */
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

    /**
     * Tests if the calculation returns 0 when using one or more zeroes in the
     * calculation. (Zero is used for errorhandling as test above.)
     */
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

    /**
     *
     * Tests if the calculation can handle large numbers - here tested with a
     * 26km by 16km window. This is enormous, as is the price.
     */
    @Test
    public void testCalculatePriceLargeFrame() {
        System.out.println("calculatePrice");
        double hei = 26000;
        double wid = 16000;
        double framePrice = 200.0;
        double glassPrice = 300.0;
        PriceCalculator instance = new PriceCalculator();
        double expResult = 1.248084E11;
        double result = instance.calculatePriceM(hei, wid, framePrice, glassPrice);
        assertEquals(expResult, result, 0.0);
        System.out.println("calculatePrice with Large frame Passed");
    }

    /**
     *
     * Tests if the calculation with the same size (converted in cm and m) gives
     * the same price.
     */
    @Test
    public void testCalculateCMAndMIsSame() {
        System.out.println("calculatePrice");
        double heiCM = 260;
        double widCM = 160;
        double heiM = 2.60;
        double widM = 1.60;
        double framePrice = 200.0;
        double glassPrice = 300.0;
        PriceCalculator instance = new PriceCalculator();
        double expResultM = 2088;
        double expResultCM = 2088;
        double resultM = instance.calculatePriceM(heiM, widM, framePrice, glassPrice);
        double resultCM = instance.calculatePriceCM(heiCM, widCM, framePrice, glassPrice);
        assertEquals(expResultCM, resultCM, 0.0);
        assertEquals(expResultM, resultM, 0.0);
        assertEquals(resultCM, resultM, 0.0);
        System.out.println("calculatePrice with Large frame Passed");
    }

}
