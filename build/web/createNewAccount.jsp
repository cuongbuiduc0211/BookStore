<%-- 
    Document   : createNewAccount
    Created on : Mar 6, 2021, 3:04:38 PM
    Author     : USER
--%>

<%@page import="student.StudentInsertError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1>Create new account</h1>
        <form action="ProcessServlet" method="POST">
            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" /> (6-20 chars)<br/>
            <c:set var="errors" value="${requestScope.CREATEERRS}"/>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                ${errors.usernameLengthErr}
                </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /> (6-30 chars)<br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                ${errors.passwordLengthErr}
                </font><br/>
            </c:if>
            Confirm Password* <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red">
                ${errors.confirmNotMatch}
                </font><br/>
            </c:if>
            Full name* <input type="text" name="txtFullName" 
                              value="${param.txtFullName}" /> (2-50 chars)<br/>
            <c:if test="${not empty errors.fullNameLengthErr}">
                <font color="red">
                ${errors.fullNameLengthErr}
                </font><br/>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form><br/>
        <c:if test="${not empty errors.usernameIsExisted}">
            <font color="red">
            ${errors.usernameIsExisted}
            </font><br/>
        </c:if>

        <%-- <form action="ProcessServlet" method="POST">
            Username* <input type="text" name="txtUsername" 
                             value="<%= request.getParameter("txtUsername")%>" /> (6-20 chars)<br/>
            <%
                StudentInsertError errors = (StudentInsertError) request.getAttribute("CREATEERRS");
                if (errors != null) {
                    if (errors.getUsernameLengthErr() != null) {
            %>
                    <font color="red">
                        <%= errors.getUsernameLengthErr() %>
                    </font><br/>
            <%
                    }
                }
            %>
            Password* <input type="password" name="txtPassword" value="" /> (6-30 chars)<br/>
            <%
                if (errors != null) {
                    if (errors.getPasswordLengthErr()!= null) {
            %>
                    <font color="red">
                        <%= errors.getPasswordLengthErr() %>
                    </font><br/>
            <%
                    }
                }
            %>
            Confirm Password* <input type="password" name="txtConfirm" value="" /><br/>
            <%
                if (errors != null) {
                    if (errors.getConfirmNotMatch()!= null) {
            %>
                    <font color="red">
                        <%= errors.getConfirmNotMatch() %>
                    </font><br/>
            <%
                    }
                }
            %>
            Full name* <input type="text" name="txtFullName" 
                              value="<%= request.getParameter("txtFullName")%>" /> (2-50 chars)<br/>
            <%
                if (errors != null) {
                    if (errors.getFullNameLengthErr()!= null) {
            %>
                    <font color="red">
                        <%= errors.getFullNameLengthErr() %>
                    </font><br/>
            <%
                    }
                }
            %>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form><br/> 
            <%
                if (errors != null) {
                    if (errors.getUsernameIsExisted()!= null) {
            %>
                    <font color="red">
                        <%= errors.getUsernameIsExisted() %>
                    </font><br/>
            <%
                    }
                }
            %> 
        --%>
    </body>
</html>
