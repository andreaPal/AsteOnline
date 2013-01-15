package model;

import java.io.Serializable;
import java.sql.Date;


/**
 *
 * @author Damiano
 */
public class Utente implements Serializable{
    private Integer id;
    private String username;
    private String password;
    private String nome;
    private String indirizzo;
    private String email;
    private Date data_registrazione;
    private String ruolo;
    private String city;

    /**
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the indirizzo
     */
    public String getIndirizzo() {
        return this.indirizzo;
    }

    /**
     * @param indirizzo the indirizzo to set
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the data_registrazione
     */
    public Date getData_registrazione() {
        return this.data_registrazione;
    }

    /**
     * @param data_registrazione the data_registrazione to set
     */
    public void setData_registrazione(Date data_registrazione) {
        this.data_registrazione = data_registrazione;
    }

    /**
     * @return the ruolo
     */
    public String getRuolo() {
        return this.ruolo;
    }

    /**
     * @param ruolo the ruolo to set
     */
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the città
     */
    public String getCity() {
        return city;
    }

    /**
     * @param città the città to set
     */
    public void setCity(String city) {
        this.city = city;
    }
}
