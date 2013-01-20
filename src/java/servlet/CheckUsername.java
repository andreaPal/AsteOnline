/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import db.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author andrea
 */


public class CheckUsername extends HttpServlet {
    private DBManager manager;
    
    @Override
    public void init() throws ServletException {
      this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");
    }
    


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        JSONObject check = new JSONObject();
        int user = 1;
        try {
            user= manager.checkUsername(username);
        } catch (SQLException ex) {
            Logger.getLogger(CheckUsername.class.getName()).log(Level.SEVERE, null, ex);
        }
        check.put("check", user);
        response.setContentType("application/json");
        response.getWriter().print(check.toString());
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
