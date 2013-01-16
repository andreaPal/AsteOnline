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
import model.Prodotto;

/**
 *
 * @author andrea
 */
public class Acquisti extends HttpServlet {

    private DBManager manager;
    public void init() throws ServletException {
      this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Prodotto> products = manager.getProducts();
        } catch (SQLException ex) {
            Logger.getLogger(Acquisti.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("acquisti.jsp");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
