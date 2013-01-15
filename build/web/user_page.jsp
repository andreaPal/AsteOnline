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
        <h3>Ultimi acquisti</h3>
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
            <tbody>
                <c:forEach var="v" items="${vendita}">
                    <tr>
                        <td><c:out value="${v.id_prodotto}"/></td>
                        <td><c:out value="${v.data}"/></td>
                        <td><c:out value="${v.prezzo_finale}"/></td>
                        <td><c:out value="${v.prezzo_spedizione}"/></td>
                        <td><c:out value="${v.tasse_vendita}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <h3>Ultime offerte</h3>
        <table class="table table-striped">
            <tr>
                <th>Prodotto</th>
                <th>Data</th>
                <th>Offerta</th>
            </tr>
        </table>
        
        <h3>Aste perse</h3>
        <table class="table table-striped">
            <tr>
                <th>Prodotto</th>
                <th>Data</th>
                <th>Offerta</th>
            </tr>
        </table>
        
        
        <a class="btn btn-primary" href="addProduct.jsp">Nuovo Prodotto</a>
        <a class="btn btn-primary" href="#">Cerca Prodotto</a>
        </div>
        
    </body>
</html>
