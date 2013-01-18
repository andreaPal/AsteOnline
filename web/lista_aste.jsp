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
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <script>
            $(document).ready(function() {
                var product_deleted_id = "";

                $(".table_delete_button").click(function(e){
                    product_deleted_id = $(this).parent().parent().find("td:first-child").text();
                });
                
                $("#delete_button").click(function(){
                    ajax_deleted(product_deleted_id);
                });
            });
            
            function ajax_deleted(product_deleted_id) {
                $.ajax({
                      type: "POST",
                      url: "/AsteOnline/ListaAsteDeleted",
                      data: "id_prodotto=" + product_deleted_id,
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
                            tbody_products += "<td>"+ json[i]['act_price'] +"</td>";
                            tbody_products += "<td>"+ json[i]['inc_min'] +"</td>";
                            tbody_products += "<td>"+ json[i]['delivery_price'] +"</td>";
                            tbody_products += "<td>"+ json[i]['deadline'] +"</td>";
                            tbody_products += "<td>"+ json[i]['image'] +"</td>";
                            tbody_products += "<td><a href=\"#myModal\" role=\"button\" class=\"btn btn-danger table_delete_button\" data-toggle=\"modal\">Cancella</a></td></tr>"
                        }
                        $("#products_table").html(tbody_products);
                        $("#myModal").modal("hide");
                      },
                      error: function()
                      {
                        $("#myModal").modal("hide");
                        alert("Chiamata fallita, si prega di riprovare...");
                      }
                    });
            }
        </script>
        
        <title>Lista Prodotti Page</title>
        
        <!-- Modal -->
        <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="myModalLabel">Sei sicuro?</h3>
          </div>
          <div class="modal-body">
            <p>Vuoi veramente cancellare l'asta selezionata?</p>
          </div>
          <div class="modal-footer">
            <button class="btn" data-dismiss="modal" aria-hidden="true">No</button>
            <button id="delete_button" class="btn btn-primary">Si</button>
          </div>
        </div>
    </head>
    <body>
         <%@ include file="header_admin.jsp" %>
                <div class="container well">
        <h3>Lista Prodotti in vendita</h3>
        <table style="margin-left:-13px;" class="table table-bordered">
            <thead>
                <tr>
                    <th>ID Prodotto</th>
                    <th>Nome</th>
                    <th>Descrizione</th>
                    <th>Quantità</th>
                    <th>Categoria</th>
                    <th>Prezzo iniziale</th>
                    <th>Prezzo attuale</th>
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
                        <td><c:out value="${p.getPrezzo_attuale()}"/></td>
                        <td><c:out value="${p.getIncremento_minimo()}"/></td>
                        <td><c:out value="${p.getPrezzo_spedizione()}"/></td>
                        <td><c:out value="${p.getScadenza()}"/></td>
                        <td><c:out value="${p.getNome_immagine()}"/></td>
                        <td>
                            <a href="#myModal" role="button" class="btn btn-danger table_delete_button" data-toggle="modal">Cancella</a>
                        </td>
                    </tr>
              </c:forEach>
            </tbody>
            
        </table>
    </body>
</html>
