<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="/AsteOnline/css/bootstrap.css">
        <title>Login</title>
        <script>
            function validateLoginForm()
            {
                var username=document.forms["loginForm"]["username"].value;
                var password=document.forms["loginForm"]["password"].value;
                
                if (username==null || username=="" || password==null || password=="")
                {
                    if($('#flash_login_error').length == 0){
                      $('#loginForm').prepend('<div id="flash_login_error" class="alert alert-error">I campi username/password non devono essere vuoti!</div>');
                    } 
                    return false;
                }
                return true;
            }
            
            function validateRegistrationForm(){
                var username=document.forms["registrationForm"]["username"].value;
                var password=document.forms["registrationForm"]["password"].value;
                var nome=document.forms["registrationForm"]["nome"].value;
                var city=document.forms["registrationForm"]["city"].value;
                var indirizzo=document.forms["registrationForm"]["indirizzo"].value;
                var email=document.forms["registrationForm"]["email"].value;
                var email2=document.forms["registrationForm"]["email2"].value;
                
                if (email!=email2) {
                    if($('#flash_registration_error').length == 0){
                        $('#registrationForm').prepend('<div id="flash_registration_error" class="alert alert-error">Gli indirizzi email non corrispondono</div>');
                    }
                    return false;                    
                }
                
                if((username==null || username=="" || password==null || password==""
                    || nome==null || nome=="" || city==null || city=="" 
                    || indirizzo==null | indirizzo=="" || email==null || email=="" 
                    || email2==null || email2==""))
                {
                    if($('#flash_registration_error').length == 0){
                    $('#registrationForm').prepend('<div id="flash_registration_error" class="alert alert-error">I campi non devono essere vuoti!</div>');
                    }
                    return false;
                }
                
                /*alert($("#hidden_username_check").val());
                ajax_check(username);
                alert($("#hidden_username_check").val());
                while ($("#hidden_username_check").val() == ""){
                }
                
                alert($("#hidden_username_check").val());
                return false;//TEST
                if ($("#hidden_username_check").val() == "false")
                    return false;*/
                
                var atpos=email.indexOf("@");
                var dotpos=email.lastIndexOf(".");
                if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length)
                {
                    if($('#flash_email_error').length == 0){
                    $('#registrationForm').prepend('<div id="flash_email_error" class="alert alert-error">Inserire un indirizzo email valido!</div>');
                    }
                    return false;
                }
                
                return true;
            }
            
            function ajax_check(username) {
                $.ajax({
                      type: "POST",
                      url: "/AsteOnline/CheckUsername",
                      data: "username=" + username,
                      dataType: "json",
                      success: function(json)
                      {
                        if (json['check'] != 0){
                            var flash='<div id="flash_offer" class="alert alert-error">Username già in uso</div>';
                            $('#registrationForm').prepend(flash);
                            //return false;
                            $("#hidden_username_check").val("false");
                        } else {
                            //return true;
                            $("#hidden_username_check").val("true");
                        }
                      },
                      error: function()
                      {
                        alert("request error!");
                      }
                    });
            }
        </script>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <br/>      
        <div id="loginForm" class="container well">
            <input type="hidden" id="hidden_username_check" />
            <form class="form-signin" name="loginForm" action="Login" method="POST" onsubmit="return validateLoginForm()">
                <h2 class="form-signin-heading">Login</h2>
                <table>
                    <tr>
                        <td>Username: </td><td> <input type="text" name="username" /> </td> 
                    </tr>
                    <tr>
                        <td>Password: </td><td> <input type="password" name="password" /> </td> 
                    </tr>
                </table>
                <input class="btn btn-primary" type="submit" value="Login" />
                <input class="btn" type="reset" value="Reset"/><br/><br/>
                <a href="password_recovery.jsp">Password dimenticata?</a>
        </form>
        </div>
        
        <div id="registrationForm" class="container well">
            <form class="form-signin" name="registrationForm" action="Registration" method="POST" onsubmit="return validateRegistrationForm()">
                <h2 class="form-signin-heading">Nuovo utente?</h2>
                <table>
                    <tr>
                        <td>Username: </td><td> <input name="username" type="text" /> </td> 
                    </tr>
                    <tr>
                        <td>Password: </td><td> <input name="password" type="password" /> </td> 
                    </tr>
                    <tr>
                        <td>Nome: </td><td> <input name="nome" type="text" /> </td> 
                    </tr>
                    <tr>
                        <td>Città: </td><td> <input name="city" type="text" /> </td> 
                    </tr>
                    <tr>
                        <td>Indirizzo: </td><td> <input name="indirizzo" type="text" /> </td> 
                    </tr>
                    <tr>
                        <td>Email: </td><td> <input name="email" type="text" /> </td> 
                    </tr>
                    <tr>
                        <td>Ripeti email: </td><td> <input name="email2" type="text" /> </td> 
                    </tr>                    
                </table>
                <input class="btn btn-primary" type="submit" value="Register" />
                <input class="btn" type="reset" value="Reset"/>
        </form>
        </div>
    </body>
</html>
