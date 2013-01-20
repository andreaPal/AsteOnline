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
import upload.UploadFile;

/**
 *
 * @author Damiano
 */
public class AddProduct extends HttpServlet {

    DBManager manager;
    // private String dirName;
    
    @Override
    public void init() throws ServletException {
      this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");
    }
    
    
    /*@Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // read the uploadDir from the servlet parameters
        dirName = config.getInitParameter("uploadDir");
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
    String filename = "default.gif";
    String value = "";
    int id_utente = 0;
    int quantità = 0;
    float prezzo_iniziale = 0;
    float prezzo_minimo = 0;
    float incremento_minimo = 0;
    float prezzo_spedizione = 0;
    java.sql.Timestamp scadenza = null;
        
    try {
        HttpSession session = request.getSession(false);
        if (session==null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
        
        Utente utente = (Utente) session.getAttribute("user");
        if(utente != null){
            id_utente = utente.getId();
        }
        String nome = request.getParameter("nome");        
        String quantity = request.getParameter("quantity");
        if(!quantity.equals("")){
           try {
                quantità = Integer.parseInt(quantity);
            } catch (NumberFormatException e) {
                    session.setAttribute("message", "Input quantit&agrave errato");
            }           
        }

        String descrizione = request.getParameter("descrizione");
        int categoria = Integer.parseInt(request.getParameter("category"));
        
        String initial_price = request.getParameter("prezzo_iniziale");
        
        if(!initial_price.equals("") || initial_price != null){
            try {
                prezzo_iniziale = Float.parseFloat(initial_price);
            } catch (NumberFormatException e) {
                    session.setAttribute("message", "Input prezzo iniziale errato");
            }
        }

        String min_price = request.getParameter("prezzo_min");
        if(!"".equals(min_price)){
            try {
                prezzo_minimo = Float.parseFloat(min_price);
            } catch (NumberFormatException e) {
                    session.setAttribute("message", "Input prezzo minimo errato");
            }
        }

        String minimum_increment = request.getParameter("incremento_minimo");
        if(!"".equals(minimum_increment)){
            try {
                incremento_minimo = Float.parseFloat(minimum_increment);
            } catch (NumberFormatException e) {
                    session.setAttribute("message", "Input incremento minimo errato");
            }
        }

        String shipping_price = request.getParameter("prezzo_spedizione");
        if(!"".equals(shipping_price)){
            try {
                prezzo_spedizione = Float.parseFloat(shipping_price);
            } catch (NumberFormatException e) {
                    session.setAttribute("message", "Input prezzo spedizione errato");
            }
        }

        String deadline = request.getParameter("scadenza");
        if(!"".equals(deadline)){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            Date utilDate = (Date) df.parse(deadline);
            scadenza = new  java.sql.Timestamp(utilDate.getTime());
        }
        
        if (!request.getParameter("nome_immagine").equals(""))
            filename = request.getParameter("nome_immagine");  
        
        
        
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
