package db;

import email.SendEmail;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import model.Categoria;
import model.Prodotto;
import model.StoricoAsta;
import model.Utente;
import model.Vendita;

/**
 *
 * @author Damiano
 */
public class DBManager implements Serializable{
    
    private transient Connection con;
       
    /**
     *
     * @param dburl
     */
    public DBManager(String dburl) throws SQLException{
      try {
        Class.forName("com.mysql.jdbc.Driver", true,getClass().getClassLoader());
        
      } catch(Exception e) {
        throw new RuntimeException(e.toString(), e);
      }
       
      this.con = DriverManager.getConnection(dburl,"root","root");
        
    }
    
    public static void shutdown() {
      try {
        DriverManager.getConnection("jdbc:mysql:;shutdown=true");
      } catch (SQLException e) {
        Logger.getLogger(DBManager.class.getName()).info(e.getMessage());
      }
    }
    
    public Utente authenticate(String username, String password) throws SQLException {
      PreparedStatement stm = con.prepareStatement(
              "SELECT * FROM utente WHERE username = ? and password = ?");
      
      try {
        stm.setString(1, username);
        stm.setString(2, password);
      
        ResultSet rs = stm.executeQuery();
        
        try {
          if (rs.next()){
            Utente utente = new Utente();
            utente.setUsername(username);
            utente.setPassword(password);
            int id_utente = rs.getInt("id");
            utente.setId(id_utente);
            utente.setRuolo(rs.getString("ruolo"));
            utente.setNome(rs.getString("nome"));
            utente.setCity(rs.getString("città"));
            utente.setEmail(rs.getString("email"));
            utente.setIndirizzo(rs.getString("indirizzo"));
            utente.setData_registrazione(rs.getDate("data_registrazione"));
            return utente;
          } else {
                return null;
          }
          
        } finally {
          rs.close();
        }
      } finally {
        stm.close();
      }
    }
    
    public void aggiungiUtente(String username, String password, String nome, String città, String indirizzo, String email, Date data_registrazione) throws SQLException{
        
        PreparedStatement stm = con.prepareStatement("INSERT INTO utente(username,password,nome,città,indirizzo,email,data_registrazione,ruolo) VALUES(?,?,?,?,?,?,?,?)");
        
        String ruolo = "user";
        
        try{
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, nome);
            stm.setString(4, città);
            stm.setString(5, indirizzo);
            stm.setString(6, email);
            stm.setDate(7, data_registrazione);
            stm.setString(8, ruolo);
            stm.executeUpdate();
        } finally{
            stm.close();
        }
        
    }
    
    public String getMail(String username) throws SQLException {
        String mail="";
        PreparedStatement stm = con.prepareStatement("SELECT email FROM utente WHERE username = ? ");
    
        try {
        stm.setString(1, username);      
        ResultSet rs = stm.executeQuery();
        try{
                while(rs.next()){
                   mail = rs.getString("email");
                }
            } finally{
                rs.close();
            }
        } finally {
            stm.close();
        }
        return mail;
    
    }
    
        public String getMail(int id_utente) throws SQLException {
        String mail="";
        PreparedStatement stm = con.prepareStatement("SELECT email FROM utente WHERE id = ? ");
    
        try {
        stm.setInt(1, id_utente);      
        ResultSet rs = stm.executeQuery();
        try{
                while(rs.next()){
                   mail = rs.getString("email");
                }
            } finally{
                rs.close();
            }
        } finally {
            stm.close();
        }
        return mail;
    
    }
    
    public String getPassword(String username) throws SQLException {
        String password="";
        PreparedStatement stm = con.prepareStatement("SELECT password FROM utente WHERE username = ? ");
    
        try {
        stm.setString(1, username);      
        ResultSet rs = stm.executeQuery();
        try{
                while(rs.next()){
                   password = rs.getString("password");
                }
            } finally{
                rs.close();
            }
        } finally {
            stm.close();
        }
        return password;
    
    }
    public void aggiungiProdotto(int id_venditore, String nome, int quantity, String descrizione, 
            int categoria, float prezzo_iniziale, float prezzo_minimo, float incremento_minimo,
            float prezzo_spedizione, java.sql.Timestamp scadenza, String nome_immagine) throws SQLException{
        PreparedStatement stm = con.prepareStatement("INSERT INTO prodotto(id_venditore,nome,descrizione,"
                + "quantity,categoria_id,prezzo_iniziale,prezzo_minimo,incremento_minimo,"
                + "prezzo_spedizione,scadenza,nome_immagine,prezzo_attuale) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
        
        
        try{
            stm.setInt(1, id_venditore);
            stm.setString(2, nome);
            stm.setString(3, descrizione);
            stm.setInt(4, quantity);            
            stm.setInt(5, categoria);
            stm.setFloat(6, prezzo_iniziale);
            stm.setFloat(7, prezzo_minimo);
            stm.setFloat(8, incremento_minimo);
            stm.setFloat(9, prezzo_spedizione);
            stm.setTimestamp(10, scadenza);
            stm.setString(11, nome_immagine);
            stm.setFloat(12, prezzo_iniziale);
            stm.executeUpdate();
        } finally{
            stm.close();
        }
    }
    
    public List<Vendita> getSells() throws SQLException{
        List<Vendita> sells = new ArrayList<Vendita>();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM vendita");
        
        try{
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    Vendita vendita = new Vendita();
                    vendita.setId_vendita(rs.getInt("id_vendita"));
                    vendita.setId_compratore(rs.getInt("id_compratore"));
                    vendita.setId_prodotto(rs.getInt("id_prodotto"));
                    vendita.setData_vendita(rs.getDate("data"));
                    vendita.setPrezzo_finale(rs.getFloat("prezzo_finale"));
                    vendita.setPrezzo_spedizione(rs.getFloat("prezzo_spedizione"));
                    vendita.setTasse_vendita(rs.getFloat("tasse_vendita"));
                    sells.add(vendita);
                }
            } finally{
                rs.close();
            }
        } finally {
            stm.close();
        }
        return sells;
    }
    
    public List<Vendita> getSellsFromBuyer(int id_compratore) throws SQLException{
        List<Vendita> sells = new ArrayList<Vendita>();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM (asteonline.vendita INNER JOIN asteonline.prodotto ON vendita.id_prodotto = prodotto.id_prodotto) WHERE id_compratore = ?");
        stm.setInt(1, id_compratore);
        
        try{
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    Vendita vendita = new Vendita();
                    vendita.setId_vendita(rs.getInt("id_vendita"));
                    vendita.setId_compratore(id_compratore);
                    vendita.setId_prodotto(rs.getInt("id_prodotto"));
                    vendita.setData_vendita(rs.getDate("data"));
                    vendita.setPrezzo_finale(rs.getFloat("prezzo_finale"));
                    vendita.setPrezzo_spedizione(rs.getFloat("prezzo_spedizione"));
                    vendita.setTasse_vendita(rs.getFloat("tasse_vendita"));
                    vendita.setNome_Prodotto(rs.getString("nome"));
                    sells.add(vendita);
                }
            } finally{
                rs.close();
            }
        } finally {
            stm.close();
        }
        return sells;
    }
    
    public List<Prodotto> getProducts() throws SQLException{
        List<Prodotto> products = new ArrayList<Prodotto>();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM "
                + "prodotto LEFT JOIN categoria "
                + "ON prodotto.categoria_id = categoria.id_categoria "
                + "WHERE prodotto.deleted = 0");
        
        try{
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    Prodotto product = new Prodotto();
                    product.setId_prodotto(rs.getInt("id_prodotto"));
                    product.setId_venditore(rs.getInt("id_venditore"));
                    product.setNome(rs.getString("nome"));
                    product.setDescrizione(rs.getString("descrizione"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setCategoria(rs.getString("categoria.nome"));
                    product.setPrezzo_iniziale(rs.getFloat("prezzo_iniziale"));
                    product.setPrezzo_minimo(rs.getFloat("prezzo_minimo"));
                    product.setPrezzo_attuale(rs.getFloat("prezzo_attuale"));
                    product.setPrezzo_spedizione(rs.getFloat("prezzo_spedizione"));
                    product.setIncremento_minimo(rs.getFloat("incremento_minimo"));
                    product.setScadenza(rs.getTimestamp("scadenza"));
                    product.setNome_immagine(rs.getString("nome_immagine"));
                    products.add(product);                    
                }
            }finally{
                rs.close();
            }
        }finally{
            stm.close();
        }
        return products;
    }
    public List<Prodotto> getProductsByCategory(String category) throws SQLException{
        List<Prodotto> products = new ArrayList<Prodotto>();
        PreparedStatement stm = con.prepareStatement(
                "SELECT * FROM prodotto "
                + "LEFT JOIN categoria ON prodotto.categoria_id = categoria.id_categoria "
                + "WHERE categoria.id_categoria = ? AND prodotto.deleted = 0");
        stm.setInt(1, Integer.parseInt(category));
        
        try{
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    Prodotto product = new Prodotto();
                    product.setId_prodotto(rs.getInt("id_prodotto"));
                    product.setId_venditore(rs.getInt("id_venditore"));
                    product.setNome(rs.getString("nome"));
                    product.setDescrizione(rs.getString("descrizione"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setCategoria(rs.getString("categoria.nome"));
                    product.setPrezzo_iniziale(rs.getFloat("prezzo_iniziale"));
                    product.setPrezzo_minimo(rs.getFloat("prezzo_minimo"));
                    product.setPrezzo_spedizione(rs.getFloat("prezzo_spedizione"));
                    product.setIncremento_minimo(rs.getFloat("incremento_minimo"));
                    product.setScadenza(rs.getTimestamp("scadenza"));
                    product.setNome_immagine(rs.getString("nome_immagine"));
                    products.add(product);                    
                }
            }finally{
                rs.close();
            }
        }finally{
            stm.close();
        }
        return products;
    }


    public List<StoricoAsta> getHistoricalFromProduct(int id_prodotto) throws SQLException{
        List<StoricoAsta> historicals = new ArrayList<StoricoAsta>();
        PreparedStatement stm = con.prepareCall("SELECT * FROM storico_asta WHERE id_prodotto = ?");
        stm.setInt(1,id_prodotto);
        
        try{
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    StoricoAsta historical = new StoricoAsta();
                    historical.setId_storico(rs.getInt("id_storico"));
                    historical.setId_prodotto(id_prodotto);
                    historical.setId_utente(rs.getInt("id_utente"));
                    historical.setData_offerta(rs.getDate("data_offerta"));
                    historical.setOfferta(rs.getFloat("offerta"));
                    historicals.add(historical);
                }
            }finally{
                rs.close();
            }
        }finally{
            stm.close();
        }
        return historicals;
    }
  public List<StoricoAsta> getHistoricalFromUser(int id_user) throws SQLException{
        List<StoricoAsta> historicals = new ArrayList<StoricoAsta>();
        PreparedStatement stm = con.prepareCall("SELECT * FROM (storico_asta INNER JOIN prodotto ON storico_asta.id_prodotto = prodotto.id_prodotto) WHERE id_utente = ? AND prodotto.deleted = 0");
        stm.setInt(1,id_user);
        
        try{
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    StoricoAsta historical = new StoricoAsta();
                    historical.setId_storico(rs.getInt("id_storico"));
                    historical.setId_prodotto(rs.getInt("id_prodotto"));
                    historical.setData_offerta(rs.getDate("data_offerta"));
                    historical.setOfferta(rs.getFloat("offerta"));
                    historical.setNome_Prodotto(rs.getString("nome"));
                    historicals.add(historical);
                }
            }finally{
                rs.close();
            }
        }finally{
            stm.close();
        }
        return historicals;
    }
  
        public List<Vendita> getWinFromUser(int id_user) throws SQLException{
        List<Vendita> wins = new ArrayList<Vendita>();
        PreparedStatement stm = con.prepareCall("SELECT * FROM "
                + "(vendita INNER JOIN prodotto ON vendita.id_prodotto = prodotto.id_prodotto) "
                + "WHERE id_compratore = ?");
        stm.setInt(1,id_user);
        
        try{
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    Vendita vendita = new Vendita();
                    vendita.setId_vendita(rs.getInt("id_vendita"));
                    vendita.setId_prodotto(rs.getInt("id_prodotto"));
                    vendita.setData_vendita(rs.getDate("data"));
                    vendita.setPrezzo_finale(rs.getFloat("prezzo_finale"));
                    vendita.setPrezzo_spedizione(rs.getFloat("prezzo_spedizione"));
                    vendita.setTasse_vendita(rs.getFloat("tasse_vendita"));
                    vendita.setNome_Prodotto(rs.getString("nome"));
                    wins.add(vendita);
                }
            } finally{
                rs.close();
            }
        } finally {
            stm.close();
        }
        return wins;
    }

    public List<Categoria> getCategories() throws SQLException {
        List<Categoria> categories = new ArrayList<Categoria>();
        PreparedStatement stm = con.prepareStatement(
                "SELECT * FROM categoria");
        
        try{
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    Categoria category = new Categoria(rs.getInt("id_categoria"), rs.getString("nome"));
                    categories.add(category);
                }
            }finally{
                rs.close();
            }
        }finally{
            stm.close();
        }
        return categories;
    }
    
    public void deleteProduct(int id_prodotto) throws SQLException {
         PreparedStatement stm2 = con.prepareStatement("SELECT DISTINCT email FROM utente INNER JOIN storico_asta ON id_utente = id "
                                                      +"WHERE id_prodotto = ?");
         PreparedStatement stm = con.prepareStatement("DELETE FROM prodotto WHERE id_prodotto = ?");
         stm.setInt(1, id_prodotto);
         stm2.setInt(1, id_prodotto);
         
         
         try{
            ResultSet rs = stm2.executeQuery();
            try{
                while(rs.next()){
                  String email = rs.getString("email");
                  try {
                    SendEmail se = new SendEmail(email, "L'asta al quale hai partecipato e' stata rimossa", "Rimozione asta");
                  } catch (AddressException ex) {
                    Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (MessagingException ex) {
                    Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
                  }
                }
            }finally{
                rs.close();
            }
        }finally{
            stm2.close();
        }
        stm.executeUpdate();      
    }
    
    public List<StoricoAsta> getHistoricalLostFromUser(int id_user) throws SQLException{
        List<StoricoAsta> historicals = new ArrayList<StoricoAsta>();
        PreparedStatement stm = con.prepareCall("SELECT * FROM (asteonline.storico_asta INNER JOIN asteonline.vendita ON storico_asta.id_prodotto = vendita.id_prodotto) "
                                                +"WHERE id_utente <> ? AND id_compratore = ? "
                                                +"GROUP BY id_utente");
        stm.setInt(1,id_user);
        stm.setInt(2,id_user);
        
        try{
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    StoricoAsta historical = new StoricoAsta();
                    historical.setId_storico(rs.getInt("id_storico"));
                    historical.setId_prodotto(rs.getInt("id_prodotto"));
                    historical.setData_offerta(rs.getDate("data_offerta"));
                    historical.setOfferta(rs.getFloat("offerta"));
                    historicals.add(historical);
                }
            }finally{
                rs.close();
            }
        }finally{
            stm.close();
        }
        return historicals;
    }

    public boolean checkIncremento(String offerta, String id_product) throws SQLException {
        PreparedStatement stm = con.prepareStatement(
                "SELECT * FROM prodotto WHERE id_prodotto = ?");
        
            stm.setInt(1, Integer.parseInt(id_product));
            float incremento=0, prezzo_iniziale =0, prezzo_minimo = 0, offerta_utente =0, prezzo_attuale=0;
            offerta_utente = Float.parseFloat(offerta);
            try{
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    incremento = rs.getFloat("incremento_minimo");
                    prezzo_iniziale = rs.getFloat("prezzo_iniziale");
                    prezzo_minimo = rs.getFloat("prezzo_minimo");
                    prezzo_attuale = rs.getFloat("prezzo_attuale");
                }
            }finally{
                rs.close();
            }
        }finally{
            stm.close();
        }
        System.out.println("offerta: "+offerta_utente);
        System.out.println("prezzo minimo: "+prezzo_minimo);

            if ((offerta_utente>prezzo_minimo)&&(offerta_utente-prezzo_attuale>incremento)) {
                return true;
        }
            else 
                return false;
        }

    public boolean checkMiglioreOfferente(String offerta, String id_product) throws SQLException {
            PreparedStatement stm = con.prepareStatement(
                "SELECT * FROM prodotto WHERE id_prodotto = ?");
        
            stm.setInt(1, Integer.parseInt(id_product));
            float incremento=0, prezzo_iniziale =0, prezzo_minimo = 0, offerta_utente =0, prezzo_attuale=0;
            offerta_utente = Float.parseFloat(offerta);
            try{
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    incremento = rs.getFloat("incremento_minimo");
                    prezzo_iniziale = rs.getFloat("prezzo_iniziale");
                    prezzo_minimo = rs.getFloat("prezzo_minimo");
                    prezzo_attuale = rs.getFloat("prezzo_attuale");
                }
            }finally{
                rs.close();
            }
        }finally{
            stm.close();
        }
        System.out.println("offerta: "+offerta_utente);
        System.out.println("prezzo minimo: "+prezzo_minimo);

            if ((offerta_utente>prezzo_attuale)) {
                return true;
        }
            else 
                return false;
    }

    
    public void aggiungiOfferta(int id_venditore, int id_prodotto, Date data_offerta, float offerta) throws SQLException{
        PreparedStatement stm = con.prepareStatement("INSERT INTO storico_asta(id_prodotto, id_utente, data_offerta, offerta) VALUES(?,?,?,?)");
        
        //String nome_immagine = "default.gif";
        
        try{
            stm.setInt(1, id_prodotto);
            stm.setInt(2, id_venditore);
            stm.setDate(3, data_offerta);
            stm.setFloat(4, offerta);            
           
            stm.executeUpdate();
        } finally{
            stm.close();
        }
    }

    public void updatePrezzoAttuale(int id_prodotto, float offerta) throws SQLException{
        
        PreparedStatement stm = con.prepareStatement("UPDATE prodotto SET prezzo_attuale = ? WHERE id_prodotto = ?");
        
        
        try{
            
            stm.setFloat(1, offerta);   
            stm.setInt(2, id_prodotto);           
            stm.executeUpdate();
        } finally{
            stm.close();
        }
    }
    
    public void checkScadenza() throws SQLException {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM prodotto WHERE scadenza < Now() AND deleted = 0");

            try {
                ResultSet rs = stm.executeQuery();
                try{
                    while(rs.next()){
                        Prodotto product = new Prodotto();
                        product.setId_prodotto(rs.getInt("id_prodotto"));
                        product.setId_venditore(rs.getInt("id_venditore"));
                        product.setPrezzo_spedizione(rs.getFloat("prezzo_spedizione"));
                        product.setScadenza(rs.getTimestamp("scadenza"));
                        product.setPrezzo_attuale(rs.getFloat("prezzo_attuale"));
                        this.updateDeleted(product.getId_prodotto());
                        this.setVendita(product);
                    }
                }finally{
                    rs.close();
                }
            }finally{
                stm.close();
            }
    }

    private void setVendita(Prodotto product) throws SQLException {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM "
                                                        + "storico_asta INNER JOIN prodotto ON storico_asta.id_prodotto = prodotto.id_prodotto "
                                                        + "WHERE prodotto.prezzo_attuale = storico_asta.offerta AND prodotto.id_prodotto = ?");
            
            stm.setInt(1, product.getId_prodotto());
            try {
                ResultSet rs = stm.executeQuery();
                try{
                    if (rs.last()) {
                        this.insertVendita(product, rs.getInt("id_utente"));
                    } 
                }finally{
                    rs.close();
                }
            }finally{
                stm.close();
            }
    }

    private void updateDeleted(Integer id_prodotto) throws SQLException {
        PreparedStatement stm = con.prepareStatement("UPDATE prodotto SET deleted = 1 WHERE id_prodotto = ?");
        stm.setInt(1, id_prodotto);
        try {
            stm.executeUpdate();
        }finally{
            stm.close();
        }
    }

    private void insertVendita(Prodotto product, int id_compratore) throws SQLException {
            PreparedStatement stm = con.prepareStatement("INSERT INTO vendita(id_compratore,id_prodotto,data,prezzo_finale,prezzo_spedizione,tasse_vendita) VALUES(?,?,?,?,?,?)");

            try{
                stm.setInt(1, id_compratore);
                stm.setInt(2, product.getId_prodotto());
                stm.setTimestamp(3, product.getScadenza());
                stm.setFloat(4, product.getPrezzo_finale());
                stm.setFloat(5, product.getPrezzo_spedizione());
                stm.setFloat(6, product.getTasse());
                stm.executeUpdate();
                String email = this.getMail(id_compratore);
            try {
                SendEmail se = new SendEmail(email,"hai vinto l'asta per ID Prodotto:" + product.getId_prodotto() + "; al prezzo di " + product.getPrezzo_finale(), "Vittoria asta");
            } catch (AddressException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            }finally{
                stm.close();
            }    
    }

    public List<Utente> getHistorySeller() throws SQLException {
        
        List<Utente> historicals = new ArrayList<Utente>();
        PreparedStatement stm = con.prepareCall("SELECT utente.nome as nome, utente.email as email, sum(tasse_vendita) as tasse FROM (prodotto INNER JOIN vendita ON prodotto.id_prodotto = vendita.id_prodotto) "
                                                + "INNER JOIN utente ON prodotto.id_venditore = utente.id "
                                                + "GROUP BY id_venditore");
        
        try{
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    Utente historical = new Utente();
                    historical.setNome(rs.getString("nome"));
                    historical.setEmail(rs.getString("email"));
                    historical.setTasse(rs.getInt("tasse"));
                    historicals.add(historical);
                }
            }finally{
                rs.close();
            }
        }finally{
            stm.close();
        }
        return historicals;
    }
    
    public List<Utente> getTopSellers() throws SQLException {
        
        List<Utente> historicals = new ArrayList<Utente>();
        PreparedStatement stm = con.prepareCall("SELECT utente.nome as nome, sum(prezzo_finale) as prezzo_finale FROM (prodotto INNER JOIN vendita ON prodotto.id_prodotto = vendita.id_prodotto) "
                                                + "INNER JOIN utente ON prodotto.id_venditore = utente.id "
                                                + "GROUP BY id_venditore DESC");
        
        try{
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    Utente historical = new Utente();
                    historical.setNome(rs.getString("nome"));
                    historical.setPrezzo_finale(rs.getInt("prezzo_finale"));
                    historicals.add(historical);
                }
            }finally{
                rs.close();
            }
        }finally{
            stm.close();
        }
        return historicals;
    }

    public List<Utente> getTopBuyers() throws SQLException {
        
        List<Utente> historicals = new ArrayList<Utente>();
        PreparedStatement stm = con.prepareCall("SELECT utente.nome as nome, sum(prezzo_finale) as prezzo_finale FROM (utente INNER JOIN vendita ON utente.id = vendita.id_compratore) "
                                                + "GROUP BY id_compratore ASC");
        
        try{
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    Utente historical = new Utente();
                    historical.setNome(rs.getString("nome"));
                    historical.setPrezzo_finale(rs.getInt("prezzo_finale"));
                    historicals.add(historical);
                }
            }finally{
                rs.close();
            }
        }finally{
            stm.close();
        }
        return historicals;
    }

    public int checkUsername(String username) throws SQLException {
        int count=0;        
        PreparedStatement stm = con.prepareCall("SELECT count(username) as count FROM utente WHERE username = ?");
        stm.setString(1, username);
        try{
            ResultSet rs = stm.executeQuery();
            try{
                if(rs.last())
                   count = rs.getInt("count");
            }finally{
                rs.close();
            }
        }finally{
            stm.close();
        }
        return count;
    }

}


