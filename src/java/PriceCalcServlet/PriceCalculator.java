package PriceCalcServlet;

public class PriceCalculator {

    public double calculatePrice(double hei, double wid, double framePrice, double glassPrice) {
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
}
