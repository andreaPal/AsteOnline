package servlet;

import db.DBManager;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Utente;

/**
 *
 * @author Damiano
 */
public class Registration extends HttpServlet {

    private DBManager manager;
    
    @Override
    public void init() throws ServletException {
      this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {     
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nome = request.getParameter("nome");
        String città = request.getParameter("city");
        String indirizzo = request.getParameter("indirizzo");
        String email = request.getParameter("email");
        Date data_registrazione = new Date(System.currentTimeMillis());
        Utente utente = null;
      
        try {
            if (manager.checkUsername(username) ==0) {
            manager.aggiungiUtente(username, password, nome, città, indirizzo, email, data_registrazione);
            utente = manager.authenticate(username, password);
            }
            else {
              response.sendRedirect(request.getContextPath() + "/login.jsp");
              return;

            }
            

            if (utente == null) {
              request.setAttribute("errorMessage", "Username/password non esistente !");
              response.sendRedirect(request.getContextPath() + "/login.jsp");
              return;
            } else if ("user".equals(utente.getRuolo())) {
              HttpSession session = request.getSession(true);
              session.setAttribute("user", utente);
              response.sendRedirect(request.getContextPath() + "/User_Page");
            }
            else if ("admin".equals(utente.getRuolo())) {                 
              HttpSession session = request.getSession(true);
              session.setAttribute("user", utente);
              response.sendRedirect(request.getContextPath() + "/Admin_Page");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        //request.getRequestDispatcher("user_page.jsp").forward(request, response);

        
        
    }

   
}
