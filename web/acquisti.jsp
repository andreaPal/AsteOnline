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
        
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
        <script type="text/javascript">
        
            $(document).ready(function() {
                $(".offerta_button").live("click", function(){
                    ajax_offer($(this).attr("data-pro"), $(this).parent().find("input:first-child").val());
                });
                
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
                      data: "search_text=" + $("#search_text").val() + "&category=" + $('#categoria_id option:selected').val(),
                      dataType: "json",
                      success: function(json)
                      {
                        var tbody_products = "";
                        for(var i=0; i<json.length;i++){
                            tbody_products += "<tr><td>"+ json[i]['id_product'] +"</td>";
                            tbody_products += "<td>"+ json[i]['name'] +"</td>";
                            tbody_products += "<td>"+ json[i]['description'] +"</td>";
                            tbody_products += "<td>"+ json[i]['quantity'] +"</td>";
                            tbody_products += "<td>"+ json[i]['category'] +"</td>";
                            tbody_products += "<td>"+ json[i]['init_price'] +"</td>";
                            tbody_products += "<td>"+ json[i]['min_price'] +"</td>";
                            tbody_products += "<td>"+ json[i]['inc_min'] +"</td>";
                            tbody_products += "<td>"+ json[i]['delivery_price'] +"</td>";
                            tbody_products += "<td>"+ json[i]['deadline'] +"</td>";
                            tbody_products += "<td>"+ json[i]['image'] +"</td>";
                            tbody_products += "<td><form name=\"form_offerta\" onsubmit=\"return false;\">"
                                                    + "Offerta: <input type=\"text\" style=\"width:45px\" name=\"offerta\">"
                                                    + "<input type=\"submit\" class=\"btn offerta_button\" data-pro=\""+ json[i]['id_product'] +"\">"
                                                    + "</form></td></tr>";
                        }
                        $("#products_table").html(tbody_products);
                      },
                      error: function()
                      {
                        alert("Chiamata fallita, si prega di riprovare...");
                      }
                    });
            }
            
            function ajax_offer(id_product, offerta) {
                $.ajax({
                      type: "POST",
                      url: "/AsteOnline/offerta_prodotto",
                      data: "offerta=" + offerta + "&id_product=" + id_product,
                      dataType: "json",
                      success: function(json)
                      {
                        $("#flash_offer").remove();
                        var flash='<div id="flash_offer" class="alert alert-'+json["value"]+'">'+json["message"]+'</div>';
                        $('#container').prepend(flash);  
                      },
                      error: function()
                      {
                        $("#flash_offer").remove();
                        var flash='<div id="flash_offer" class="alert alert-error">Campo offerta vuoto!</div>';
                        $('#container').prepend(flash);
                      }
                    });
            }
        </script>
    </head>
    <body>
        <%@ include file="headerlogin.jsp" %>
                <div id="container" class="container well">
        <h3>Acquisti</h3>
        <table style="margin-left:-13px;" class="table table-bordered">
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
                            <form name="form_offerta" onsubmit="return false;">
                                Offerta: <input type="text" style="width:45px" name="offerta">
                                <input type="submit" class="btn offerta_button" data-pro="<c:out value="${p.getId_prodotto()}"/>">
                            </form>
                        </td>
                    </tr>
              </c:forEach>
            </tbody>
            
        </table>
        <form style="float:left;" class="form-search" onsubmit="return false;">
            <input type="text" id="search_text" class="input-medium search-query" />
            <button type="submit" id="search_button" class="btn">Ricerca Prodotto</button>
        </form>
        <form style="margin-left:30px;float:left;" action="ricerca_prodotto" method="POST">
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
