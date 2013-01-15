<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <title>Nuovo Prodotto</title>
        <script>
            function validateForm()
            {
                var nome = document.forms["addProduct"]["nome"].value;
                var quantity = document.forms["addProduct"]["quantity"].value;
                var descrizione = document.forms["addProduct"]["descrizione"].value;
                var categoria = document.forms["addProduct"]["descrizione"].value;
                var prezzo_iniziale = document.forms["addProduct"]["prezzo_iniziale"].value;
                var prezzo_min = document.forms["addProduct"]["prezzo_min"].value;
                var incremento_min = document.forms["addProduct"]["incremento_min"].value;
                var prezzo_spedizione = document.forms["addProduct"]["prezzo_spedizione"].value;
                var scadenza = document.forms["addProduct"]["scadenza"].value;
                
                if(nome==null || nome=="" || quantity==null || quantity=="" || descrizione==null ||
                    descrizione=="" || categoria==null || categoria=="" || prezzo_iniziale==null ||
                    prezzo_iniziale=="" || prezzo_min==null || prezzo_min=="" || incremento_min==null ||
                    incremento_min=="" || prezzo_spedizione==null || prezzo_spedizione=="" ||
                    scadenza==null || scadenza=="")
                {
                    alert("i campi non devono essere vuoti!");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <%@ include file="headerlogin.jsp" %>
        <div class="container well">
            <form class="form-signin" name="addProduct" action="AddProduct" method="GET" onsubmit="return validateForm()">
                <h2 class="form-signin-heading">Nuovo Prodotto</h2>
                <table>
                    <tr>
                        <td>Nome: </td><td> <input type="text" name="nome" /> </td> 
                    </tr>
                    <tr>
                        <td>Quantit&agrave: </td><td> <input type="text" name="quantity" /> </td> 
                    </tr>
                    <tr>
                        <td>Descrizione: </td><td><textarea name="descrizione"></textarea></td>
                    </tr>
                    <tr>
                        <td>Categoria: </td><td> <input type="text" name="categoria" /> </td> 
                    </tr>
                    <tr>
                        <td>Prezzo iniziale: </td><td> <input type="text" name="prezzo_iniziale" /> </td> 
                    </tr>
                    <tr>
                        <td>Prezzo minimo: </td><td> <input type="text" name="prezzo_min" /> </td> 
                    </tr>
                    <tr>
                        <td>Incremento minimo: </td><td> <input type="text" name="incremento_min" /> </td> 
                    </tr>
                    <tr>
                        <td>Prezzo spedizione: </td><td> <input type="text" name="prezzo_spedizione" /> </td> 
                    </tr>
                    <tr>
                        <td>Scadenza: </td><td> <input placeholder="dd/MM/yyyy HH:mm:ss" type="text" name="scadenza" /> </td> 
                    </tr>                    
                </table>
                <br/><br/>
                <input class="btn btn-primary" type="submit" value="Aggiungi" />
                <input class="btn" type="reset" value="Reset"/><br/><br/>
        </form>
            
        <ul class="pager">
            <li class="previous">
                <a href="user_page.jsp">&larr; Torna indietro</a>
            </li>
        </ul>
        </div>
    </body>
</html>
