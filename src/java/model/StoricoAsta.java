/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Damiano
 */
public class StoricoAsta implements Serializable{
    private Integer id_storico;
    private Integer id_prodotto;
    private Integer id_utente;
    private Date data_offerta;
    private Float offerta;
    private String nome_prodotto;

    public String getNome_Prodotto() {
        return nome_prodotto;
    }


    public void setNome_Prodotto(String nome_prodotto) {
        this.nome_prodotto = nome_prodotto;
    }
    /**
     * @return the id_storico
     */
    public Integer getId_storico() {
        return id_storico;
    }

    /**
     * @param id_storico the id_storico to set
     */
    public void setId_storico(Integer id_storico) {
        this.id_storico = id_storico;
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
     * @return the id_utente
     */
    public Integer getId_utente() {
        return id_utente;
    }

    /**
     * @param id_utente the id_utente to set
     */
    public void setId_utente(Integer id_utente) {
        this.id_utente = id_utente;
    }

    /**
     * @return the data_offerta
     */
    public Date getData_offerta() {
        return data_offerta;
    }

    /**
     * @param data_offerta the data_offerta to set
     */
    public void setData_offerta(Date data_offerta) {
        this.data_offerta = data_offerta;
    }

    /**
     * @return the offerta
     */
    public Float getOfferta() {
        return offerta;
    }

    /**
     * @param offerta the offerta to set
     */
    public void setOfferta(Float offerta) {
        this.offerta = offerta;
    }
}
