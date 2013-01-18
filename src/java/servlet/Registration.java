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
        System.out.println(username);
        String nome = request.getParameter("nome");
        String città = request.getParameter("city");
        String indirizzo = request.getParameter("indirizzo");
        String email = request.getParameter("email");
        Date data_registrazione = new Date(System.currentTimeMillis());
        
        try {
            manager.aggiungiUtente(username, password, nome, città, indirizzo, email, data_registrazione);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }     
        request.getRequestDispatcher("user_page.jsp").forward(request, response);

        
        
    }

   
}
