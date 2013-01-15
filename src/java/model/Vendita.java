package model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Damiano
 */
public class Vendita implements Serializable{
    private Integer id_vendita;
    private Integer id_compratore;
    private Integer id_prodotto;
    private Date data_vendita;
    private Float prezzo_finale;
    private Float prezzo_spedizione;
    private Float tasse_vendita;

    /**
     * @return the id_vendita
     */
    public Integer getId_vendita() {
        return id_vendita;
    }

    /**
     * @param id_vendita the id_vendita to set
     */
    public void setId_vendita(Integer id_vendita) {
        this.id_vendita = id_vendita;
    }

    /**
     * @return the id_prodotto
     */
    public Integer getId_prodotto() {
        return id_prodotto;
    }

    /**
     * @param id_prodotto the id_prodotto to set
     */
    public void setId_prodotto(Integer id_prodotto) {
        this.id_prodotto = id_prodotto;
    }

    /**
     * @return the data_vendita
     */
    public Date getData_vendita() {
        return data_vendita;
    }

    /**
     * @param data_vendita the data_vendita to set
     */
    public void setData_vendita(Date data_vendita) {
        this.data_vendita = data_vendita;
    }

    /**
     * @return the prezzo_finale
     */
    public Float getPrezzo_finale() {
        return prezzo_finale;
    }

    /**
     * @param prezzo_finale the prezzo_finale to set
     */
    public void setPrezzo_finale(Float prezzo_finale) {
        this.prezzo_finale = prezzo_finale;
    }

    /**
     * @return the prezzo_spedizione
     */
    public Float getPrezzo_spedizione() {
        return prezzo_spedizione;
    }

    /**
     * @param prezzo_spedizione the prezzo_spedizione to set
     */
    public void setPrezzo_spedizione(Float prezzo_spedizione) {
        this.prezzo_spedizione = prezzo_spedizione;
    }

    /**
     * @return the tasse_vendita
     */
    public Float getTasse_vendita() {
        return tasse_vendita;
    }

    /**
     * @param tasse_vendita the tasse_vendita to set
     */
    public void setTasse_vendita(Float tasse_vendita) {
        this.tasse_vendita = tasse_vendita;
    }

    /**
     * @return the id_utente
     */
    public Integer getId_compratore() {
        return id_compratore;
    }

    /**
     * @param id_utente the id_utente to set
     */
    public void setId_compratore(Integer id_utente) {
        this.id_compratore = id_utente;
    }
}
