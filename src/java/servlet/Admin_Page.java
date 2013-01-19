/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import db.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Utente;

/**
 *
 * @author andrea
 */
public class Admin_Page extends HttpServlet {
    private DBManager manager;
    private int id_compratore=1;
    @Override
    public void init() throws ServletException {
      this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Utente> sellers = manager.getTopSellers();
            request.setAttribute("sellers", sellers);
            List<Utente> buyers = manager.getTopBuyers();
            request.setAttribute("buyers", buyers);
            request.getRequestDispatcher("admin_page.jsp").forward(request, response);


        } catch (SQLException ex) {
            Logger.getLogger(Admin_Page.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
