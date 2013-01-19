<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,db.*,model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
        <script type="text/javascript" src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js"></script>
        <script src="js/bootstrap-datetimepicker.min.js"></script>
        <script src="js/ajaxfileupload.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap-datetimepicker.min.css">
        <title>Nuovo Prodotto</title>
        <script type="text/javascript">
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
            
            function ajax_upload_image() {
              //starting setting some animation when the ajax starts and completes
                
                $("#loading")
                .ajaxStart(function(){
                    $(this).show();
                })
                .ajaxComplete(function(){
                    $(this).hide();
                });
                
                $.ajaxFileUpload({
                        url:'UploadFile', 
                        secureuri:false,
                        fileElementId:'fileToUpload',
                        dataType: 'text/html',
                        success: function (data, status)
                        {
                            $("#hide_image_filename").text(data);
                        },
                        error: function (data, status, e)
                        {}
                    }
                )
                return false;
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
                        <td>Scadenza: </td><td>
                            <div id="datetimepicker2" class="input-append">
                                <input data-format="dd/MM/yyyy hh:mm:ss" name="scadenza" type="text"></input>
                                <span class="add-on">
                                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                                  </i>
                                </span>
                            </div>
                            <script type="text/javascript">
                              $(function() {
                                $('#datetimepicker2').datetimepicker({
                                  language: 'en',
                                  pick24HourFormat: true
                                });
                              });
                            </script>
                        </td> 
                    </tr>

                    <input name="nome_immagine" id="hide_image_filename" type="hidden"></input>
                </table>
                <br/>
                <input class="btn btn-primary" type="submit" value="Aggiungi" />
                <input class="btn" type="reset" value="Reset"/>
            </form>
            
            <form name="form" action="" method="POST" enctype="multipart/form-data">	
                <input id="fileToUpload" type="file" size="45" name="fileToUpload" class="input">
                <button class="button" id="buttonUpload" onclick="return ajax_upload_image();">Upload</button>
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
