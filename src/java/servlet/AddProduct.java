/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import db.DBManager;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
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
    //private String dirName;
    
    @Override
    public void init() throws ServletException {
      this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");
    }
    
    
    /*@Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // read the uploadDir from the servlet parameters
        dirName = config.getInitParameter("img");
        if (dirName == null) {
            throw new ServletException("Please supply uploadDir parameter");
        }
    }*/   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    String name = "";
    String filename = "";
    String value = "";
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
        // String immagine = request.getParameter("img");
        
        /* if(nome.isEmpty() || quantity.isEmpty() || descrizione.isEmpty() ||
                categoria.isEmpty() || initial_price.isEmpty() ||
                min_price.isEmpty() || minimum_increment.isEmpty() ||
                shipping_price.isEmpty() || deadline.isEmpty()){
            session.setAttribute("message", "I campi non possono essere vuoti!");
            response.sendRedirect(request.getContextPath() + "/addProduct.jsp");
            //return;
        } */
        
        if(nome == null || quantity == null || descrizione == null || 
                categoria == null || initial_price == null || min_price == null ||
                minimum_increment == null || shipping_price == null ||
                deadline == null){
            session.setAttribute("message", "I campi non possono essere nulli!");
        }
        
        // String dirName = request.getContextPath() + "/web/img";
        
        response.setContentType("text/plain");
              
        Utente utente = (Utente) session.getAttribute("user");
        id_utente = utente.getId();
        int quantità = 0;
        try {
            quantità = Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
                    session.setAttribute("message", "Input quantit&agrave errato");
                    return;
        }

        float prezzo_iniziale = 0;
        try {
            prezzo_iniziale = Float.parseFloat(initial_price);
        } catch (NumberFormatException e) {
                    session.setAttribute("message", "Input prezzo iniziale errato");
                    return;
        }

        float prezzo_minimo = 0;
        try {
            prezzo_minimo = Float.parseFloat(min_price);
        } catch (NumberFormatException e) {
                    session.setAttribute("message", "Input prezzo minimo errato");
                    return;
        }

        float incremento_minimo = 0;
        try {
            incremento_minimo = Float.parseFloat(minimum_increment);
        } catch (NumberFormatException e) {
                    session.setAttribute("message", "Input incremento minimo errato");
                    return;
        }

        float prezzo_spedizione = 0;
        try {
            prezzo_spedizione = Float.parseFloat(shipping_price);
        } catch (NumberFormatException e) {
                    session.setAttribute("message", "Input prezzo spedizione errato");
                    return;
        }

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date utilDate = (Date) df.parse(deadline);
        java.sql.Date scadenza = new java.sql.Date(utilDate.getTime());
        
        try {
            // Use an advanced form of the constructor that specifies a character
            // encoding of the request (not of the file contents) and a file
            // rename policy.
            MultipartRequest multi =
                new MultipartRequest(request, getServletContext().getRealPath("/img"), 10*1024*1024,
                "ISO-8859-1", new DefaultFileRenamePolicy());

            /* Enumeration params = multi.getParameterNames();
            while (params.hasMoreElements()) {
                name = (String)params.nextElement();
                value = multi.getParameter(name);
            } */
            
            Enumeration files = multi.getFileNames();
            while (files.hasMoreElements()) {
                name = (String)files.nextElement();
                filename = multi.getFilesystemName(name);
                // String originalFilename = multi.getOriginalFileName(name);
                // String type = multi.getContentType(name);
                File f = multi.getFile(name);
                
                if (f != null) {
                    session.setAttribute("success", "file scritto correttamente");
                }
            }
        } catch (IOException IEx) {
            this.getServletContext().log("Error reading saving file");
        } 

        try {
            manager.aggiungiProdotto(id_utente,nome,quantità,descrizione,categoria,prezzo_iniziale,
                    prezzo_minimo,incremento_minimo,prezzo_spedizione,scadenza,filename);
            session.setAttribute("message", "Prodotto aggiunto correttamente");
            response.sendRedirect(request.getContextPath() + "/user_page.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (ParseException ex) {
                Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}