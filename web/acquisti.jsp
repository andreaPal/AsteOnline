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
        <title>Acquisti Page</title>
        
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
        <script>
            $(document).ready(function() {

                $("#search_button").click(function(){
                    ajax_search();
                });
                
                $("#categoria_id").click(function(){
                    ajax_search();
                });
            });
            
            function ajax_search() {
                $.ajax({
                      type: "POST",
                      url: "/AsteOnline/RicercaProdotti",
                      dataType: "json",
                      data: "search_text=" + $("#search_text").val() + "&category=" + $('#categoria_id option:selected').val(),
                      success: function(msg)
                      {
                        var tbody_products = "";
                        for(var i=0; i<msg.length;i++){
                            tbody_products += "<tr><td>"+ msg[i]['id_product'] +"</td>";
                            tbody_products += "<td>"+ msg[i]['name'] +"</td>";
                            tbody_products += "<td>"+ msg[i]['description'] +"</td>";
                            tbody_products += "<td>"+ msg[i]['quantity'] +"</td>";
                            tbody_products += "<td>"+ msg[i]['category'] +"</td>";
                            tbody_products += "<td>"+ msg[i]['init_price'] +"</td>";
                            tbody_products += "<td>"+ msg[i]['min_price'] +"</td>";
                            tbody_products += "<td>"+ msg[i]['inc_min'] +"</td>";
                            tbody_products += "<td>"+ msg[i]['delivery_price'] +"</td>";
                            tbody_products += "<td>"+ msg[i]['deadline'] +"</td>";
                            tbody_products += "<td>"+ msg[i]['image'] +"</td></tr>";
                        }
                        $("#products_table").html(tbody_products);
                      },
                      error: function()
                      {
                        alert("Chiamata fallita, si prega di riprovare...");
                      }
                    });
            }
        </script>
    </head>
    <body>
        <%@ include file="headerlogin.jsp" %>
                <div class="container well">
        <h3>Acquisti</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID Prodotto</th>
                    <th>Nome</th>
                    <th>Descrizione</th>
                    <th>Quantità</th>
                    <th>Categoria</th>
                    <th>Prezzo iniziale</th>
                    <th>Prezzo minimo</th>
                    <th>Incremento minimo</th>
                    <th>Prezzo Spedizione</th>
                    <th>Scadenza</th>
                    <th>Immagine</th>
                    <th>Offerta</th>
                </tr>
            </thead>
            <tbody id="products_table">
              <c:forEach var="p" items="${products}">
                    <tr>
                        <td><c:out value="${p.getId_prodotto()}"/></td>
                        <td><c:out value="${p.getNome()}"/></td>
                        <td><c:out value="${p.getDescrizione()}"/></td>
                        <td><c:out value="${p.getQuantity()}"/></td>
                        <td><c:out value="${p.getCategoria()}"/></td>
                        <td><c:out value="${p.getPrezzo_iniziale()}"/></td>
                        <td><c:out value="${p.getPrezzo_minimo()}"/></td>
                        <td><c:out value="${p.getIncremento_minimo()}"/></td>
                        <td><c:out value="${p.getPrezzo_spedizione()}"/></td>
                        <td><c:out value="${p.getScadenza()}"/></td>
                        <td><c:out value="${p.getNome_immagine()}"/></td>
                        <td>
                            <form name="form_offerta" action="offerta_prodotto" method="POST">
                                Offerta: <input type="text" name="offerta">
                                <input type="submit" value="Submit">
                            </form></form>
                        </td>
                    </tr>
              </c:forEach>
            </tbody>
            
        </table>
        <form class="form-search" onsubmit="return false;">
            <input type="text" id="search_text" class="input-medium search-query" />
            <button type="submit" id="search_button" class="btn">Ricerca Prodotto</button>
        </form>    
        <br> Filtro Categoria</br>
        <form action="ricerca_prodotto" method="POST">
            <select id="categoria_id">
                <option value="any">Any</option>
                <c:forEach var="c" items="${categories}">
                    <option value="<c:out value="${c.getId_categoria()}"/>">
                        <c:out value="${c.getName()}"/>
                    </option>
                </c:forEach>
            </select>
        </form>  
    </body>
</html>
