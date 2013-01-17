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
        <title>Storico Vendite Page</title>
    </head>
    <body>
        <%@ include file="header_admin.jsp" %>
                <div class="container well">
        <h3>Storico Vendite</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID Prodotto</th>
                    <th>Prezzo Finale</th>
                    <th>Data</th>

                </tr>
            </thead>
              <c:forEach var="s" items="${sells}">
                    <tr>
                        <td><c:out value="${s.getId_prodotto()}"/></td>
                        <td><c:out value="${s.getPrezzo_finale()}"/></td>
                        <td><c:out value="${s.getData_vendita()}"/></td>
                    </tr>
              </c:forEach>
            <tbody>
                
            </tbody>
            
        </table>
    </body>
</html>