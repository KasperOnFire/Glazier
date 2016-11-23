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

/**
 *
 * @author Kasper
 */
@WebServlet(name = "PriceResult", urlPatterns = {"/PriceResult"})
public class PriceResult extends HttpServlet {

    private double price;

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PriceResult</title>");
            out.println("<link href=\"Glazier.css\" rel=\"stylesheet\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"maindiv\">");
            out.println("<h1>Price of window:</h1>");
            try {
                DBConnector con = new DBConnector();
                DataAccessObject dao = new DataAccessObject(con);
                //returnPrice needs : height, width, frametype, glasstype, and metric for calculations.
                price = dao.returnPrice(request.getParameter("height"), request.getParameter("width"),
                        request.getParameter("frametype"), request.getParameter("glass"), request.getParameter("metric"));

                if (price <= 0) {
                    response.sendRedirect("error.html");
                } else {
                    String cc = currencyConvert(price, request.getParameter("currency"));
                    dao.writeOrder(request.getParameter("orderid"), price, request.getParameter("currency"));
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

//made with exchangerates from xe.com on 23/11/16
    private String currencyConvert(double Price, String currency) {
        switch (currency) {
            case "DKK":
                return Price + " DKK";
            case "USD":
                price = (Price * 0.14187);
                return price + " USD";
            case "GBP":
                price = (Price * 0.11403);
                return price + " GBP";
            case "EUR":
                price = (Price * 0.13442);
                return price + " EUR";
            default:
                return "something is horribly wrong with the chosen currency!";
        }
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
        return "Short description";
    }// </editor-fold>

}
