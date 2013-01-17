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
import javax.servlet.http.HttpSession;
import model.StoricoAsta;
import model.Utente;
import model.Vendita;


/**
 *
 * @author andrea
 */
public class User_Page extends HttpServlet {
    private DBManager manager;
    private int id_compratore=1;
    @Override
    public void init() throws ServletException {
      this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("user");
        id_compratore = u.getId();
        try {
            List<StoricoAsta> historicals = manager.getHistoricalFromUser(id_compratore);
            List<Vendita> wins = manager.getWinFromUser(id_compratore);
            request.setAttribute("historicals", historicals);
            request.setAttribute("wins", wins);
            request.getRequestDispatcher("user_page.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Acquisti.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
