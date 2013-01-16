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
public class RicercaProdotti extends HttpServlet {
    
    private DBManager manager;
    
    @Override
    public void init() throws ServletException {
      this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Prodotto> products = manager.getProducts();
            //FARE ESPRESSIONE REGOLARE
            /*JSONArray json_products = new JSONArray();
            for(Prodotto p : products){
                    JSONObject json_product = new JSONObject();
                    json_products.put("course_name",c.getCourse_name()).put("url",c.getUrl()).put("actual_start_date", c.getActual_start_date()).put("place",c.getPlace()).put("plannedCoursePeriod",c.getPlannedCoursePeriod()).put("notes",c.getNotes()).put("academic_year", c.getAcademic_year());
                    json_products.put(json_product);
            }*/
            response.setContentType("application/json");
            //response.getWriter().print(json_products.toString());
            response.getWriter().print("{'si':'prova'}".toString());
        } catch (SQLException ex) {
            Logger.getLogger(Acquisti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
