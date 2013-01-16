/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import db.DBManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AddProduct extends HttpServlet {

    DBManager manager;
    
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
    
    int id_utente = 0;
        
    try {
        HttpSession session = request.getSession(false);
        if (session==null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/addProduct.jsp");
            return;
        }
    
        String nome = request.getParameter("nome");
        String quantity = request.getParameter("quantity");
        String descrizione = request.getParameter("descrizione");
        String categoria = request.getParameter("categoria");
        String initial_price = request.getParameter("prezzo_iniziale");
        String min_price = request.getParameter("prezzo_min");
        String minimum_increment = request.getParameter("incremento_minimo");
        String shipping_price = request.getParameter("prezzo_spedizione");
        String deadline = request.getParameter("scadenza");

        if(nome==null || nome.trim().isEmpty() || quantity==null || 
                quantity.trim().isEmpty() || descrizione==null ||
                descrizione.trim().isEmpty() || categoria==null ||
                categoria.trim().isEmpty() || initial_price==null ||
                initial_price.trim().isEmpty() || min_price==null ||
                min_price.trim().isEmpty() || minimum_increment==null ||
                minimum_increment.trim().isEmpty() || shipping_price==null ||
                shipping_price.trim().isEmpty() || deadline==null ||
                deadline.trim().isEmpty()){
            session.setAttribute("message", "I campi non possono essere vuoti!");
            response.sendRedirect(request.getContextPath() + "addProduct.jsp");
            return;
        }
        
        Utente utente = (Utente) session.getAttribute("user");
        id_utente = utente.getId();
        int quantità = 0;
        try {
            quantità = Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
                    session.setAttribute("message", "Input quantit&agrave errato");
                    //response.sendRedirect("addProduct.jsp");
        }

        float prezzo_iniziale = 0;
        try {
            prezzo_iniziale = Float.parseFloat(initial_price);
        } catch (NumberFormatException e) {
                    session.setAttribute("message", "Input prezzo iniziale errato");
                    //response.sendRedirect("addProduct.jsp");
        }

        float prezzo_minimo = 0;
        try {
            prezzo_minimo = Float.parseFloat(min_price);
        } catch (NumberFormatException e) {
                    session.setAttribute("message", "Input prezzo minimo errato");
                    //response.sendRedirect("addProduct.jsp");
        }

        float incremento_minimo = Float.parseFloat(minimum_increment);
        try {
            incremento_minimo = Float.parseFloat(minimum_increment);
        } catch (NumberFormatException e) {
                    session.setAttribute("message", "Input incremento minimo errato");
                    //response.sendRedirect("addProduct.jsp");
        }

        float prezzo_spedizione = 0;
        try {
            prezzo_spedizione = Float.parseFloat(shipping_price);
        } catch (NumberFormatException e) {
                    session.setAttribute("message", "Input prezzo spedizione errato");
                    //response.sendRedirect("addProduct.jsp");
        }

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date utilDate = (Date) df.parse(deadline);
        java.sql.Date scadenza = new java.sql.Date(utilDate.getTime());

        try {
            manager.aggiungiProdotto(id_utente,nome,quantità,descrizione,categoria,prezzo_iniziale,
                    prezzo_minimo,incremento_minimo,prezzo_spedizione,scadenza);
            session.setAttribute("message", "Prodotto aggiunto correttamente");
            response.sendRedirect("user_page.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (ParseException ex) {
                Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}