<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <title>Login</title>
        <script>
            function validateLoginForm()
            {
                var username=document.forms["loginForm"]["username"].value;
                var password=document.forms["loginForm"]["password"].value;
                
                if (username==null || username=="" || password==null || password=="")
                {
                    alert($('#flash_error') == []);
                    if ($('#flash_error') == [])
                      $('#loginForm').prepend('<div id="flash_error" class="alert alert-error">I campi username/password non devono essere vuoti!</div>');
                    return false;
                }
            }
            
            function validateRegistrationForm(){
                var username=document.forms["registrationForm"]["username"].value;
                var password=document.forms["registrationForm"]["password"].value;
                var nome=document.forms["registrationForm"]["nome"].value;
                var city=document.forms["registrationForm"]["city"].value;
                var indirizzo=document.forms["registrationForm"]["indirizzo"].value;
                var email=document.forms["registrationForm"]["email"].value;
                var email2=document.forms["registrationForm"]["email2"].value;
                
                if(username==null || username=="" || password==null || password==""
                    || nome==null || nome=="" || city==null || city=="" 
                    || indirizzo==null | indirizzo=="" || email==null || email=="" 
                    || email2==null || email2==null)
                {
                    alert("I campi non devono essere vuoti!");
                    return false;
                }
                
                var atpos=email.indexOf("@");
                var dotpos=email.lastIndexOf(".");
                if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length)
                {
                    alert("Inserire un indirizzo email valido!");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <br/>      
        <div id="loginForm" class="container well">
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
