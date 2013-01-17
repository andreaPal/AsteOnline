package db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
            String categoria, float prezzo_iniziale, float prezzo_minimo, float incremento_minimo,
            float prezzo_spedizione, Date scadenza, String nome_immagine) throws SQLException{
        PreparedStatement stm = con.prepareStatement("INSERT INTO prodotto(id_venditore,nome,descrizione,"
                + "quantity,categoria,prezzo_iniziale,prezzo_minimo,incremento_minimo,"
                + "prezzo_spedizione,scadenza,nome_immagine) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
        
        //String nome_immagine = "default.gif";
        
        try{
            stm.setInt(1, id_venditore);
            stm.setString(2, nome);
            stm.setString(3, descrizione);
            stm.setInt(4, quantity);            
            stm.setString(5, categoria);
            stm.setFloat(6, prezzo_iniziale);
            stm.setFloat(7, prezzo_minimo);
            stm.setFloat(8, incremento_minimo);
            stm.setFloat(9, prezzo_spedizione);
            stm.setDate(10, scadenza);
            stm.setString(11, nome_immagine);
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
        PreparedStatement stm = con.prepareStatement("SELECT * FROM prodotto");
        
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
                    product.setCategoria(rs.getString("categoria"));
                    product.setPrezzo_iniziale(rs.getFloat("prezzo_iniziale"));
                    product.setPrezzo_minimo(rs.getFloat("prezzo_minimo"));
                    product.setPrezzo_spedizione(rs.getFloat("prezzo_spedizione"));
                    product.setIncremento_minimo(rs.getFloat("incremento_minimo"));
                    product.setScadenza(rs.getDate("scadenza"));
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
        PreparedStatement stm = con.prepareCall("SELECT * FROM (storico_asta INNER JOIN prodotto ON storico_asta.id_prodotto = prodotto.id_prodotto) WHERE id_utente = ?");
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
        PreparedStatement stm = con.prepareCall("SELECT * FROM (vendita INNER JOIN prodotto ON vendita.id_prodotto = prodotto.id_prodotto) WHERE id_compratore = ?");
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

}
