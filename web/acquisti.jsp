<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" 
           uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,db.*,model.*"%>
<jsp:useBean id="products" scope="page" class="model.Prodotto" />
<jsp:useBean id="user" scope="session" class="model.Utente" />
<jsp:setProperty name="user" property="*"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <title>Acquisti Page</title>
    </head>
    <body>
        <%@ include file="headerlogin.jsp" %>
                <div class="container well">
        <h3>Acquisti</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Prodotto</th>
                    <th>Data</th>
                    <th>Prezzo finale</th>
                    <th>Prezzo spedizione</th>
                    <th>Tasse</th>
                </tr>
            </thead>
              <c:forEach var="p" items="${products}">
                    <tr>
                        <td><c:out value="${p.id_prodotto}"/></td>
                        <td><c:out value="${p.nome}"/></td>
                        <td><c:out value="${p.descrizione}"/></td>
                    </tr>
              </c:forEach>
            <tbody>
                
            </tbody>
            
        </table>
        <form class="form-search" action="ricerca_prodotto" method="POST">
        <input type="text" class="input-medium search-query">
        <button type="submit" class="btn">Ricerca Prodotto</button>
        </form>    
        <br> Filtro Categoria</br>
        <form action="ricerca_prodotto" method="POST">
            <select>
                <option value="categoria1">categoria1</option>
            </select>
        </form>  
    </body>
</html>
