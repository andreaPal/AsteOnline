package servlet;

import db.DBManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
public class Login extends HttpServlet {

    private DBManager manager;
    
    @Override
    public void init() throws ServletException {
      this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("utente") != null){
          response.sendRedirect(request.getContextPath() + "/User_Page");
          return;
        } else if (session.getAttribute("admin") != null){
          response.sendRedirect(request.getContextPath() + "/Admin_Page");
          return;
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Map<String, String> messages = new HashMap<String, String>();
        
        
        String username = request.getParameter("username");
        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("username", "Inserire lo username!");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        } else if (!username.matches("\\p{Alnum}+")) {
            request.setAttribute("username", "Inserire solo caratteri alfanumerici!");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        String password = request.getParameter("password"); 
        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("password", "Inserire la password");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        } 
        
        Utente utente = null;
      
        try {
            utente = manager.authenticate(username, password);
        } catch (SQLException e) {
            throw new ServletException(e);
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
                session.setAttribute("admin", utente);
                response.sendRedirect(request.getContextPath() + "/Admin_Page");
            }        
        
        
    }

   
}
