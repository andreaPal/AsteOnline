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
    String filename = "";
    String value = "";
    int id_utente = 0;
    int quantità = 0;
    float prezzo_iniziale = 0;
    float prezzo_minimo = 0;
    float incremento_minimo = 0;
    float prezzo_spedizione = 0;
    java.sql.Date scadenza = null;
        
    try {
        HttpSession session = request.getSession(false);
        if (session==null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
        
        // String immagine = request.getParameter("img");
        
        /* if(nome.isEmpty() || quantity.isEmpty() || descrizione.isEmpty() ||
                categoria.isEmpty() || initial_price.isEmpty() ||
                min_price.isEmpty() || minimum_increment.isEmpty() ||
                shipping_price.isEmpty() || deadline.isEmpty()){
            session.setAttribute("message", "I campi non possono essere vuoti!");
            response.sendRedirect(request.getContextPath() + "/addProduct.jsp");
            //return;
        } */
        
        /*if(nome == null || quantity == null || descrizione == null || 
                categoria == null || initial_price == null || min_price == null ||
                minimum_increment == null || shipping_price == null ||
                deadline == null){
            session.setAttribute("message", "I campi non possono essere nulli!");
        }*/
        
        // String dirName = request.getContextPath() + "/web/img";
        
        // response.setContentType("text/plain");
              
        Utente utente = (Utente) session.getAttribute("user");
        if(utente != null){
            id_utente = utente.getId();
        }
        String nome = request.getParameter("nome");
        
        String quantity = request.getParameter("quantity");
        if(!"".equals(quantity)){
           try {
                quantità = Integer.parseInt(quantity);
            } catch (NumberFormatException e) {
                    session.setAttribute("message", "Input quantit&agrave errato");
            }           
        }

        String descrizione = request.getParameter("descrizione");
        String categoria = request.getParameter("categoria");
        System.out.println("CATEGORIA =============> " + categoria);
        String initial_price = request.getParameter("initial_price");
        System.out.println("INITIAL PRICE =============> " + initial_price);
        if(!"".equals(initial_price) || initial_price != null){
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
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date utilDate = (Date) df.parse(deadline);
            scadenza = new java.sql.Date(utilDate.getTime());
        }
        /*String dirName = getServletContext().getRealPath("/img");
        UploadFile upload = new UploadFile();
        upload.uploadFile(request, dirName);*/        
        
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