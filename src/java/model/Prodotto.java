package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Prodotto implements Serializable{
    private Integer id_prodotto;
    private Integer id_venditore;
    private String nome;
    private String descrizione;
    private int quantity;
    private String categoria;
    private float prezzo_iniziale;
    private float prezzo_minimo;
    private float prezzo_attuale;
    private float incremento_minimo;
    private String nome_immagine;
    private float prezzo_spedizione;
    private Date scadenza;

    
    public float getPrezzo_attuale() {
        return prezzo_attuale;
    }

    public void setPrezzo_attuale(float prezzo_attuale) {
        this.prezzo_attuale = prezzo_attuale;
    }
    /**
     * @return the id
     */
    public Integer getId_prodotto() {
        return id_prodotto;
    }

    /**
     * @param id the id to set
     */
    public void setId_prodotto(Integer id_prodotto) {
        this.id_prodotto = id_prodotto;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the prezzo_finale
     */
    public float getPrezzo_iniziale() {
        return prezzo_iniziale;
    }

    /**
     * @param prezzo_finale the prezzo_finale to set
     */
    public void setPrezzo_iniziale(float prezzo_iniziale) {
        this.prezzo_iniziale = prezzo_iniziale;
    }

    /**
     * @return the prezzo_minimo
     */
    public float getPrezzo_minimo() {
        return prezzo_minimo;
    }

    /**
     * @param prezzo_minimo the prezzo_minimo to set
     */
    public void setPrezzo_minimo(float prezzo_minimo) {
        this.prezzo_minimo = prezzo_minimo;
    }

    /**
     * @return the incremento_minimo
     */
    public float getIncremento_minimo() {
        return incremento_minimo;
    }

    /**
     * @param incremento_minimo the incremento_minimo to set
     */
    public void setIncremento_minimo(float incremento_minimo) {
        this.incremento_minimo = incremento_minimo;
    }

    /**
     * @return the nome_immagine
     */
    public String getNome_immagine() {
        return nome_immagine;
    }

    /**
     * @param nome_immagine the nome_immagine to set
     */
    public void setNome_immagine(String nome_immagine) {
        this.nome_immagine = nome_immagine;
    }

    /**
     * @return the prezzo_spedizione
     */
    public float getPrezzo_spedizione() {
        return prezzo_spedizione;
    }

    /**
     * @param prezzo_spedizione the prezzo_spedizione to set
     */
    public void setPrezzo_spedizione(float prezzo_spedizione) {
        this.prezzo_spedizione = prezzo_spedizione;
    }

    /**
     * @return the scadenza
     */
    public Date getScadenza() {
        return scadenza;
    }

    /**
     * @return the scadenza
     */
    public String getRightScadenza() {
        Format df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return df.format(scadenza);
    }

    /**
     * @param scadenza the scadenza to set
     */
    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the id_utente
     */
    public Integer getId_venditore() {
        return id_venditore;
    }

    /**
     * @param id_utente the id_utente to set
     */
    public void setId_venditore(Integer id_venditore) {
        this.id_venditore = id_venditore;
    }

    /**
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @param descrizione the descrizione to set
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
