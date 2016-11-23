package PriceCalcServlet;

/**
 *
 * @author Kasper
 */
public class PriceCalculator {

    /**
     *
     * This method calculates the total price when the dimensions for the window
     * is given in centimeters.
     *
     * @param hei height of the window.
     * @param wid width of the windows.
     * @param framePrice is the price of the frame pr meter
     * @param glassPrice is the price of the glass pr squaremeter
     * @return is the total price for the window in dkk.
     */
    public double calculatePriceCM(double hei, double wid, double framePrice, double glassPrice) {
        double gp;
        double fp;
        double price = 0;
        if (framePrice > 0 && glassPrice > 0 && hei > 0 && wid > 0) {
            gp = (hei * wid) / 10000 * glassPrice;
            fp = (hei + wid) / 100 * framePrice;
            price = gp + fp;
            return price;
        } else {
            return price;
        }

    }

    /**
     *
     * This method calculates the total price when the dimensions for the window
     * is given in meters.
     * 
     * @param height is the height of the window.
     * @param width is the width of the window.
     * @param framePrice is the price of the frame pr meter
     * @param glassPriceis the price of the glass pr squaremeter
     * @return is the total price for the window in dkk.
     */
    public double calculatePriceM(double height, double width, double framePrice, double glassPrice) {
        double gp;
        double fp;
        double price = 0;
        if (framePrice > 0 && glassPrice > 0 && height > 0 && width > 0) {
            gp = (height * width) * glassPrice;
            fp = (height + width) * framePrice;
            price = gp + fp;
            return price;
        } else {
            return price;
        }
    }
}
