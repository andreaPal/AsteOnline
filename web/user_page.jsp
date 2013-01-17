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
        <title>User Page</title>
    </head>
    <body>
        <%@ include file="headerlogin.jsp" %>
        <h1>Benvenuto <%= user.getNome()%></h1>
        
        <div class="container well">
        <h3>Ultime offerte</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Nome</td>
                    <td>Data</td>
                    <td>Offerta</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${historicals}">
                    <tr>
                        <td><c:out value="${s.id_prodotto}"/></td>
                        <td><c:out value="${s.getNome_Prodotto()}"/></td>
                        <td><c:out value="${s.getData_offerta()}"/></td>
                        <td><c:out value="${s.getOfferta()}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <h3>Ultimi acquisti</h3>
       <table class="table table-bordered">
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Nome</td>
                    <td>Prezzo finale</td>
                    <td>Data</td>
                </tr>
            </thead>
            <tbody>
              <c:forEach var="w" items="${wins}">
                    <tr>
                        <td><c:out value="${w.getId_prodotto()}"/></td>
                        <td><c:out value="${w.getNome_Prodotto()}"/></td>
                        <td><c:out value="${w.getPrezzo_finale()}"/></td>
                        <td><c:out value="${w.getData_vendita()}"/></td>
                    </tr>
              </c:forEach>
            </tbody>
        </table>

        </div>
        
    </body>
</html>
