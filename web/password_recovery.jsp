<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <title>Recupero Password</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="well">
            <div class="container">
                <p>Inserisci username:</p>
                <form name="input" action="sendMail" method="get">
                    <input type="text" name="user">
                    <input type="submit" value="Submit" >
                </form>
            </div>
        </div>
    </body>
</html>
