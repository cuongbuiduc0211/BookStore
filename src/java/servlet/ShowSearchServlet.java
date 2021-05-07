/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import student.StudentDTO;

/**
 *
 * @author USER
 */
@WebServlet(name = "ShowSearchServlet", urlPatterns = {"/ShowSearchServlet"})
public class ShowSearchServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Search Result</title>");
            out.println("</head>");
            out.println("<body>");
            
            //Khi có cookie rồi thì mới hiển thị welcome, tên user
            Cookie[] cookies = request.getCookies();
            if (cookies != null) { //kiểm tra cookie cuối cùng đăng nhập
                String username = cookies[cookies.length - 1].getName();
                out.println("<font color='red'>Welcome, "
                        + username
                        + "</font>");
            }
            
            out.println("<h1>Your Search Result</h1>");
            
            //Tại màn hình Show, mình có thể biết User đã nhập gì
            //Vì dùng RequestDispatcher -> forward, request object còn tồn tại
            //giá trị searchValue trong txtSearch là parameter trong request object
            String searchValue = request.getParameter("txtSearchValue"); 
            
            out.println("Your search value is: " + searchValue);
            //kết quả Search kiểu dữ liệu List
            //Vì getAttribute trả về Object thuần, nên phải ép kiểu 1 cách tường minh
            List<StudentDTO> result
                    = (List<StudentDTO>)request.getAttribute("SEARCHRESULT"); 
                    //tên att đặt ở SearchLastnameServlet -> copy qua

            //Servlet output HTML into Response Object -> khuyết điểm: run code mới biết 
            if (result != null) {
                //Dùng table bên trang html làm 
                //table -> xóa thành phần tbody -> copy table qua đây
                //copy out.println từng dòng
                //ctrl + shift + di chuyển lên
                out.println("<table border='1'>"); 
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>No.</th>");
                out.println("<th>Username</th>");
                out.println("<th>Password</th>");
                out.println("<th>Last name</th>");
                out.println("<th>Role</th>");
                out.println("<th>Delete</th>");
                out.println("<th>Update</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");
                
                int count = 0;
                //mỗi phần tử của result có kiểu dữ liệu DTO
                for (StudentDTO dto : result) {
                    out.println("<form action='ProcessServlet'>");
                    out.println("<tr>");
                    out.println("<td>"
                            + ++count
                            + ".</td>");
                    out.println("<td>"
                            + dto.getUsername()
                            + "<input type='hidden' name='txtUsername' value='"
                            + dto.getUsername()
                            + "' />" 
                            + "</td>");
                    out.println("<td>"
                            + "<input type='text' name='txtPassword' value='"
                            + dto.getPassword()
                            + "' />"
                            + "</td>");
                    out.println("<td>"
                            + dto.getLastname()
                            + "</td>");
                    if (dto.isRole()) {
                        out.println("<td>"
                            + "<input type='checkbox' name='chkAdmin' value='ADMIN' checked='checked' />"
                            + "</td>");
                    }else{
                        out.println("<td>"
                            + "<input type='checkbox' name='chkAdmin' value='ADMIN' />"
                            + "</td>");
                    }
                    String urlRewriting = "ProcessServlet?btAction=Delete&pk="
                            + dto.getUsername() + "&lastSearchValue="
                            + request.getParameter("txtSearchValue");
                    out.println("<td>"
                            + "<a href='"
                            + urlRewriting
                            + "'>Delete</a>"
                            + "</td>");
                    out.println("<td>"
                            + "<input type='submit' value='Update' name='btAction' />"
                            + "<input type='hidden' name='lastSearchValue' value='"
                            + request.getParameter("txtSearchValue")
                            + "' />"
                            + "</td>");
                    out.println("</tr>");
                    out.println("</form>");
                }//end for
                out.println("</tbody>");
                out.println("</table>");
            } 
            else { //nếu tìm không có thì thông báo :
                out.println("<h2>No record is matched!</h2>");
            }
            
            out.println("</body>");
            out.println("</html>");
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
