/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import student.StudentDAO;
import student.StudentInsertError;

/**
 *
 * @author USER
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {
    private final String loginPage = "login.html";
//    private final String createNewAccountErrServlet = "CreateNewAccountErrServlet";
    private final String createNewAccountErrServlet = "createNewAccount.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullName");
        boolean bErr = false;
        StudentInsertError errors = new StudentInsertError();
        String url = createNewAccountErrServlet;
        try {

            if (username.trim().length() < 6 || username.trim().length() > 20) {
                bErr = true;
                errors.setUsernameLengthErr("Username length is from 6 to 20 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                bErr = true;
                errors.setPasswordLengthErr("Password length is from 6 to 30 characters");
            } else if (!confirm.trim().equals(password.trim())) {
                bErr = true;
                errors.setConfirmNotMatch("Confirm must be equal to password");
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                bErr = true;
                errors.setFullNameLengthErr("Fullname length is from 2 to 50 characters");
            }

            if (bErr) {
                request.setAttribute("CREATEERRS", errors);
            }else{
                StudentDAO dao = new StudentDAO();
                boolean result = dao.createNewAccount(username, password, fullname, false);
                if (result) {
                    url = loginPage;
                }
            }
        } catch (SQLException ex) {
            log("CreateNewAccountServlet_ClassNotFound" + ex.getMessage());
        } catch (NamingException ex) {
            log("CreateNewAccountServlet_SQL" + ex.getMessage());
            errors.setUsernameIsExisted(username + " is existed");
            request.setAttribute("CREATEERRS", errors);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
