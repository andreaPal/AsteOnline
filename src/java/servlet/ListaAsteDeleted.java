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
public class ListaAsteDeleted extends HttpServlet {

    private DBManager manager;
    private String id_prodotto;
   
    
    @Override
    public void init() throws ServletException {
      this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");
    }


    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            id_prodotto = request.getParameter("id_prodotto");
            manager.deleteProduct(Integer.parseInt(id_prodotto));
            List<Prodotto> products = manager.getProducts();
            JSONArray json_products = new JSONArray();
            for(Prodotto p : products){
                
                    JSONObject json_product = new JSONObject();
                    json_product.put("id_product", p.getId_prodotto());
                    json_product.put("name", p.getNome());
                    json_product.put("description", p.getDescrizione());
                    json_product.put("quantity", Integer.toString(p.getQuantity()));
                    json_product.put("category", p.getCategoria());
                    json_product.put("init_price", p.getPrezzo_iniziale());
                    json_product.put("act_price", p.getPrezzo_attuale());
                    json_product.put("inc_min", p.getIncremento_minimo());
                    json_product.put("delivery_price", p.getPrezzo_spedizione());
                    json_product.put("deadline", p.getRightScadenza());
                    json_product.put("image", p.getNome_immagine());               
                    json_products.add(json_product);
                 
            }
            
            response.setContentType("application/json");
            System.out.println(json_products.toString());
            response.getWriter().print(json_products.toString());
            
        } catch (SQLException ex) {
            Logger.getLogger(ListaAsteDeleted.class.getName()).log(Level.SEVERE, null, ex);
        }
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
