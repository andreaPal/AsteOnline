<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,db.*,model.*"%>
<jsp:useBean id="user" scope="session" class="model.Utente" />
<jsp:setProperty name="user" property="*"/> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <title>Admin Page</title>
    </head>
    <body>
        <%@ include file="header_admin.jsp" %>
        <h1>Benvenuto <%= user.getNome()%></h1>
        
        <div class="container well">
        <h3>Top venditori per fatturato</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <td>Nome</td>
                    <td>Fatturato</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${sellers}">
                    <tr>
                        <td><c:out value="${s.getNome()}"/></td>
                        <td><c:out value="${s.getPrezzo_finale()}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
       <h3>Top acquirenti per fatturato</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <td>Nome</td>
                    <td>Fatturato</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="b" items="${buyers}">
                    <tr>
                        <td><c:out value="${b.getNome()}"/></td>
                        <td><c:out value="${b.getPrezzo_finale()}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
    </body>
</html>
