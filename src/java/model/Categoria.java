/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author andrea
 */
public class Categoria {
    private int id_categoria;
    private String name;

    public Categoria(int categoria_id, String name) {
        this.id_categoria = categoria_id;
        this.name = name;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public String getName() {
        return name;
    }
    
}
