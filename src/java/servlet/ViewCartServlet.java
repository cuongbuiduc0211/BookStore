/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CartObj;

/**
 *
 * @author USER
 */
@WebServlet(name = "ViewCartServlet", urlPatterns = {"/ViewCartServlet"})
public class ViewCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Your Cart</h1>");
            HttpSession session = request.getSession(false);
            if (session != null) {
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        out.println("<table border='1'>");
                        out.println("<thead>");
                        out.println("<tr>");
                        out.println("<th>No.</th>");
                        out.println("<th>Book Title</th>");
                        out.println("<th>Quantity</th>");
                        out.println("<th>Action</th>");
                        out.println("</tr>");
                        out.println("</thead>");
                        out.println("<tbody>");

                        int count = 0;
                        out.println("<form action='ProcessServlet'>");
                        for (Map.Entry item : items.entrySet()) {
                            out.println("<tr>");
                            out.println("<td>"
                                    + ++count
                                    + "</td>");
                            out.println("<td>"
                                    + item.getKey()
                                    + "</td>");
                            out.println("<td>"
                                    + item.getValue()
                                    + "</td>");
                            out.println("<td>"
                                    + "<input type='checkbox' name='chkItem' value='"
                                    + item.getKey()
                                    + "' />"
                                    + "</td>");
                            out.println("</tr>");
                        }
                        out.println("<tr>");
                        out.println("<td colspan='3'>"
                                + "<a href='bookStore.html'>Add More Items To Your Cart </a>"
                                + "</td>");
                        out.println("<td>"
                                + "<input type='submit' value='Remove Selected Items' name='btAction' />"
                                + "</td>");
                        out.println("</tr>");
                        out.println("</form>");
                        
                        out.println("</tbody>");
                        out.println("</table>");
                        return;
                    }
                }
            }

            out.println("<h2>Cart is not existed!</h2>");
            out.println("</body>");
            out.println("</html>");
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
