<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,db.*,model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <title>Nuovo Prodotto</title>
        <script>
            function validateAddProductForm(){
                var nome=document.forms["addProduct"]["nome"].value;
                var quantity=document.forms["addProduct"]["quantity"].value;
                var descrizione=document.forms["addProduct"]["descrizione"].value;
                var categoria=document.forms["addProduct"]["categoria"].value;
                var prezzo_iniziale=document.forms["addProduct"]["prezzo_iniziale"].value;
                var prezzo_min=document.forms["addProduct"]["prezzo_min"].value;
                var incremento_minimo=document.forms["addProduct"]["incremento_minimo"].value;
                var prezzo_spedizione=document.forms["addProduct"]["prezzo_spedizione"].value;
                var scadenza=document.forms["addProduct"]["scadenza"].value;
                
                if(nome==null || nome=="" || quantity==null || quantity=="" || descrizione==null 
                    || descrizione=="" || categoria==null || categoria=="" || prezzo_iniziale==null
                    || prezzo_iniziale=="" || prezzo_min==null || prezzo_min=="" || incremento_minimo==null 
                    || incremento_minimo=="" || prezzo_spedizione==null || prezzo_spedizione=="" 
                    || scadenza==null || scadenza=="")
                {
                    if($('#flash_error').length == 0){
                    $('#addProductForm').prepend('<div id="flash_error" class="alert alert-error">I campi username/password non devono essere vuoti!</div>');
                    //$('#flash_error').delay(2000).fadeOut('<div id="flash_error" class="alert alert-error">I campi username/password non devono essere vuoti!</div>');
                    }
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <%@ include file="headerlogin.jsp" %>
        <div id="addProductForm" class="container well">
            <form class="form-signin" name="addProduct" action="AddProduct" method="POST" onsubmit="return validateAddProductForm()">
                <h2 class="form-signin-heading">Nuovo Prodotto</h2>
                <table>
                    <tr>
                        <td>Nome: </td><td> <input name="nome" type="text" /> </td> 
                    </tr>
                    <tr>
                        <td>Quantit&agrave: </td><td> <input name="quantity" type="text" /> </td> 
                    </tr>
                    <tr>
                        <td>Descrizione: </td><td><textarea name="descrizione"></textarea></td>
                    </tr>
                    <tr>
                        <td>Categoria: </td><td> <select name="category">
                                        <c:forEach var="c" items="${categories}">                   
                                            <option value="<c:out value="${c.getId_categoria()}"/>"><c:out value="${c.getName()}"/></option>
                                        </c:forEach>
                                    </select> </td> 
                    </tr>
                    <tr>
                        <td>Prezzo iniziale: </td><td> <input name="prezzo_iniziale" type="text" /> </td> 
                    </tr>
                    <tr>
                        <td>Prezzo minimo: </td><td> <input name="prezzo_min" type="text" /> </td> 
                    </tr>
                    <tr>
                        <td>Incremento minimo: </td><td> <input name="incremento_minimo" type="text" /> </td> 
                    </tr>
                    <tr>
                        <td>Prezzo spedizione: </td><td> <input name="prezzo_spedizione" type="text" /> </td> 
                    </tr>
                    <tr>
                        <td>Scadenza: </td><td> <input name="scadenza" placeholder="dd/MM/yyyy HH:mm:ss" type="text" /> </td> 
                    </tr>

                </table>
                <br/>
                <input class="btn btn-primary" type="submit" value="Aggiungi" />
                <input class="btn" type="reset" value="Reset"/>
            </form>
            <br/><br/>
            <ul class="pager">
                <li class="previous">
                    <a href="user_page.jsp">&larr; Torna indietro</a>
                </li>
            </ul>
        </div>
    </body>
</html>
