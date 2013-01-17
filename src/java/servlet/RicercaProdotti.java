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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author andrea
 */
public class RicercaProdotti extends HttpServlet {
    
    private DBManager manager;
    private String ricerca;
    private int x;
    
    @Override
    public void init() throws ServletException {
      this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ricerca = request.getParameter("search_text");
            List<Prodotto> products = manager.getProducts();
            
            JSONArray json_products = new JSONArray();
            for(Prodotto p : products){
                 if (p.getNome().toLowerCase().contains(ricerca.toLowerCase())) {

                    JSONObject json_product = new JSONObject();
                    json_products.put("ID Prodotto", p.getId_prodotto());
                    json_products.put(json_product);
                 }
            }
            
            response.setContentType("application/json");
            response.getWriter().print(json_products.toString());
            //response.getWriter().print("{'si':'prova'}".toString());
        } catch (SQLException ex) {
            Logger.getLogger(Acquisti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
