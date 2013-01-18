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
        <title>Storico Aste Perse</title>
    </head>
    <body>
        <%@ include file="headerlogin.jsp" %>
                <div class="container well">
        <h3>Storico Aste Perse</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID Storico</th>
                    <th>ID Prodotto</th>
                    <th>Data</th>
                    <th>Offerta</th>

                </tr>
            </thead>
              <c:forEach var="h" items="${historicals}">
                    <tr>
                        <td><c:out value="${h.getId_storico()}"/></td>
                        <td><c:out value="${h.getId_prodotto()}"/></td>
                        <td><c:out value="${h.getData_offerta()}"/></td>
                        <td><c:out value="${h.getOfferta()}"/></td>
                    </tr>
              </c:forEach>
            <tbody>
                
            </tbody>
            
        </table>
    </body>
</html>