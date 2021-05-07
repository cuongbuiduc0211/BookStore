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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import student.StudentDAO;

/**
 *
 * @author USER
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
    private final String errorUpdatePage = "updateErr.html";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String admin = request.getParameter("chkAdmin");
            //lấy giá trị chkbox được check hay ko được check
            boolean role = false;
            if (admin != null) {
            //nếu được check thì trị = value
            //ko được check thì trị = null
                role = true;
            }
            String searchValue = request.getParameter("lastSearchValue");
            StudentDAO dao = new StudentDAO();
            boolean result = dao.updateAccount(username, password, role);
            String url = errorUpdatePage;
            if (result) { // update rồi gọi search lại lần nữa (user sẽ hiểu là tự động cập nhật lại trang search)
                url = "ProcessServlet?btAction=Search&txtSearchValue=" + searchValue; ;
            }
            //ko dùng requestdispatcher vì request obj còn lưu trữ 
            //=> tên param trùng tên nhau, lấy thằng đầu tiên
            //=> phải dùng sendredirect
            //kĩ thuật urlrewriting vẫn giúp che giấu đường truyền
            response.sendRedirect(url);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }finally{
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
