<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,db.*,model.*"%>
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
            <tbody>

            </tbody>
        </table>
                    
    </body>
</html>
