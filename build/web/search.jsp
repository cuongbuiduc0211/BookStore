<%-- 
    Document   : search
    Created on : Mar 1, 2021, 11:59:44 AM
    Author     : USER
--%>

<%@page import="java.util.List"%>
<%@page import="student.StudentDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <font color="red">Welcome, ${sessionScope.USERNAME} </font>
        <h1>Search Page</h1>
        <form action="ProcessServlet">
            Search value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /><br/>
            <input type="submit" value="Search" name="btAction" />
        </form>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Lastname</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="ProcessServlet">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" 
                                           value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.lastname}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ADMIN" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="ProcessServlet">
                                        <c:param name="btAction" value="Delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${param.txtSearchValue}" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>No record matched!</h2>
        </c:if>
    </c:if>
    <%-- <%
        String searchValue = request.getParameter("txtSearchValue");
        if (searchValue != null) {
            List<StudentDTO> result = (List<StudentDTO>) request.getAttribute("SEARCHRESULT");
            if (result != null) {
    %>
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>Username</th>
                <th>Password</th>
                <th>Lastname</th>
                <th>Role</th>
                <th>Delete</th>
                <th>Update</th>
            </tr>
        </thead>
        <tbody>
            <%
                int count = 0;
                for (StudentDTO dto : result) {
                    String urlRewriting = "ProcessServlet?btAction=Delete&pk="
                            + dto.getUsername()
                            + "&lastSearchValue="
                            + request.getParameter("txtSearchValue");
            %>
        <form action="ProcessServlet">
            <tr>
                <td>
                    <%= ++count%>
                </td>
                <td>
                    <%= dto.getUsername()%>
                    <input type="hidden" name="txtUsername" 
                           value="<%= dto.getUsername()%>" />
                </td>
                <td>
                    <input type="text" name="txtPassword" 
                           value="<%= dto.getPassword()%>" />
                </td>
                <td>
                    <%= dto.getLastname()%>
                </td>
                <td>
                    <input type="checkbox" name="chkAdmin" value="ADMIN"
                           <%
                               if (dto.isRole()) {
                           %>
                           checked="checked"
                           <%
                               }
                           %>
                           />
                </td>
                <td>
                    <a href="<%= urlRewriting%>">Delete</a>
                </td>
                <td>
                    <input type="submit" value="Update" name="btAction" />
                    <input type="hidden" name="lastSearchValue" 
                           value="<%= request.getParameter("txtSearchValue")%>" />
                </td>
            </tr>
        </form>
        <%
            }
        %>
    </tbody>
</table>

    <%
                return;
            }
        }

    %>
    <h2>No record match!</h2>--%>
</body>
</html>
