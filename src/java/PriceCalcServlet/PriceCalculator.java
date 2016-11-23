package PriceCalcServlet;

/**
 *
 * @author Kasper
 */
public class PriceCalculator {

    /**
     *
     * @param hei
     * @param wid
     * @param framePrice
     * @param glassPrice
     * @return
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
