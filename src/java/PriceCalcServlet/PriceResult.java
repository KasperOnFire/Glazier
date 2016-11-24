package PriceCalcServlet;

import data.DBConnector;
import data.DataAccessObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PriceResult", urlPatterns = {"/PriceResult"})
public class PriceResult extends HttpServlet {

    private double price;

    /**
     *
     * @param request servlet request is where the servlet gets data from the
     * userinputs in input.html
     * @param response servlet response is where the servlet sends back
     * response.
     * @throws ServletException if i write something wrong in the html :(
     * @throws IOException dont know when. but it does. Hasnt happened yet.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Result</title>");
            out.println("<link href=\"Glazier.css\" rel=\"stylesheet\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"maindiv\">");
            out.println("<h1>Price of window:</h1>");
            try {
                DBConnector con = new DBConnector();
                DataAccessObject dao = new DataAccessObject(con);
                //returnPrice needs : height, width, frametype, glasstype, and metric for calculations.
                price = dao.getPrice(request.getParameter("height"),
                        request.getParameter("width"), request.getParameter("frametype"),
                        request.getParameter("glass"), request.getParameter("metric"));
                if (price <= 0) {
                    response.sendRedirect("error.html");
                } else {
                    String cc = currencyConvert(price, request.getParameter("currency"));
                    dao.writeOrderToDB(request.getParameter("orderid"), price,
                            request.getParameter("currency"));
                    out.println("<h3>price: " + cc + "</h3>");
                }
            } catch (Exception ex) {
                out.println("<h3>Error in connection. Maybe MySQL isnt running? Error is:" + ex + "</h3>");
            }
            out.println("<form action=\"input.html\">\n");
            out.println("<input type=\"submit\" value=\"new calculation!\">\n");
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * made with exchangerates from xe.com on 23/11/16
     *
     * This method converts the currency, if the user chooses anything but DKK.
     *
     * @param inputPrice is the price to convert.
     * @param currency is the currency the user wants the price in.
     * @return is the converted and rounded price.
     */
    private String currencyConvert(double inputPrice, String currency) {
        switch (currency) {
            case "DKK":
                return round(price, 2) + " DKK";
            case "USD":
                price = (inputPrice * 0.14187);
                return round(price, 2) + " USD";
            case "GBP":
                price = (inputPrice * 0.11403);
                return round(price, 2) + " GBP";
            case "EUR":
                price = (inputPrice * 0.13442);
                return round(price, 2) + " EUR";
            default:
                return "something is horribly wrong with the chosen currency!";
        }
    }

    //
    /**
     * borrowed with lots of love from stackoverflow. i cannot claim Credit for
     * this method, but i needed a method for making my prices in different
     * currencies shorter without being imprecise. I do understand how it works
     * though.
     *
     * @param value is the number to round.
     * @param places is the number of decimal places you want
     * @return is the rounded double.
     */
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for calculating the price of a window.";
    }// </editor-fold>

}
