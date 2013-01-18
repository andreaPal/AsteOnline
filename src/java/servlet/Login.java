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
              Utente utente = (Utente) session.getAttribute("user");
              
        if (utente != null){
            if (utente.getRuolo().equalsIgnoreCase("user"))            
                response.sendRedirect(request.getContextPath() + "/User_Page");
            else if (utente.getRuolo().equalsIgnoreCase("admin")) 
                response.sendRedirect(request.getContextPath() + "/Admin_Page");

        }
        else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        
        
        String password = request.getParameter("password");
        
        Utente utente;
      
        try {
            utente = manager.authenticate(username, password);
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
        } catch (SQLException e) {
            throw new ServletException(e);
        } 
    }

   
}
