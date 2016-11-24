/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnitTests;

import PriceCalcServlet.PriceResult;
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
public class PriceResultTest {

    public PriceResultTest() {
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
     * Test of round method, of class PriceResult.
     * Test should round up the variable value to 2.32.
     */
    @Test
    public void testRound() {
        System.out.println("round");
        double value = 2.3167;
        int places = 2;
        double expResult = 2.32;
        double result = PriceResult.round(value, places);
        assertEquals(expResult, result, 0.0);
    }

}
