/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import db.DBManager;
import email.SendEmail;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author andrea
 */
public class RecoveryPassword extends HttpServlet {

    String username,mail,password;
    private DBManager manager;

    @Override
    public void init() throws ServletException {
      this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        username = request.getParameter("user");
        try {
            mail = manager.getMail(username);
            password = manager.getPassword(username);
                    try {
                        SendEmail PasswordMail = new SendEmail(mail,"questa\ne' la passoword: \n"+password+"\n", "Invio Password");
                    } catch (AddressException ex) {
                        Logger.getLogger(RecoveryPassword.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MessagingException ex) {
                        Logger.getLogger(RecoveryPassword.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } catch (SQLException ex) {
            Logger.getLogger(RecoveryPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect(request.getContextPath());
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
