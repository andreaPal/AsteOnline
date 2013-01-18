/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import db.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Utente;
import org.json.simple.JSONObject;

/**
 *
 * @author andrea
 */
public class offerta_prodotto extends HttpServlet {
    private String offerta;
    private DBManager manager;
    private boolean incremento,miglior_offerente;
    private String id_product;
    private int id_compratore;

    @Override
    public void init() throws ServletException {
      this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("user");
        id_compratore = u.getId();
        offerta = request.getParameter("offerta");
        id_product = request.getParameter("id_product");
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        JSONObject json_message = new JSONObject();
        
        try {
            incremento = manager.checkIncremento(offerta,id_product);
            miglior_offerente = manager.checkMiglioreOfferente(offerta,id_product);
            System.out.println(incremento); //SE FALSE -> "MIGLIORE OFFERTA MA INCREMENTO NON SUFFICIENTE -- SE TRUE -> FEEDBACK MIGLIORE OFFERTA
            System.out.println(miglior_offerente); //SE FALSE -> DIALOG "NON E' LA MIGLIOR OFFERTA"
            if (incremento && miglior_offerente) {
                manager.aggiungiOfferta(id_compratore, Integer.parseInt(id_product), sqlDate, Float.parseFloat(offerta));
                manager.updatePrezzoAttuale(Integer.parseInt(id_product),Float.parseFloat(offerta));
                //SEI IL MIGLIORE OFFERENTE
                json_message.put("value", "success");
                json_message.put("message", "Sei il miglior offerente :)");
            } else if (!incremento && miglior_offerente){
                //LA TUA E' LA MIGLIORE OFFERTA MA NON HAI INCREMENTATO ABBASTANZA L'OFFERTA
                json_message.put("value", "warning");
                json_message.put("message","La tua e' la milgiore offerta ma non hai l'hai incrementata abbastanza!");
            } else if (!incremento && !miglior_offerente){
                //LA TUA OFFERTA NON E' SUFFICIENTE
                json_message.put("value", "warning");
                json_message.put("message", "La tua offerta non e' sufficiente!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(offerta_prodotto.class.getName()).log(Level.SEVERE, null, ex);
            json_message.put("value", "error");
            json_message.put("message", "Errore nella richiesta!");
        }

        response.setContentType("application/json");
        System.out.println(json_message.toString());
        response.getWriter().print(json_message.toString());
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
