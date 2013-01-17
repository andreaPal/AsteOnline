<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="admin" scope="session" class="model.Utente" />
<jsp:setProperty name="admin" property="*"/>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <title>Admin Page</title>
    </head>
    <body>
        <%@ include file="header_admin.jsp" %>
        <h1>Benvenuto <%= admin.getNome()%></h1>
        
        <div class="container well">
        <h3>Top venditori per fatturato</h3>
        <table class="table table-bordered">
            <tr>
                <th>Prodotto</th>
                <th>Quantit&agrave</th>
                <th>Categoria</th>
                <th>Prezzo iniziale</th>
                <th>Prezzo corrente</th>
                <th>Spedizione</th>
                <th>Scadenza</th>
            </tr>
        </table>
        
       <h3>Top acquirenti per fatturato</h3>
        <table class="table table-bordered">
            <tr>
                <th>Prodotto</th>
                <th>Quantit&agrave</th>
                <th>Categoria</th>
                <th>Prezzo iniziale</th>
                <th>Prezzo corrente</th>
                <th>Spedizione</th>
                <th>Scadenza</th>
            </tr>
        </table>
        </div>
    </body>
</html>
