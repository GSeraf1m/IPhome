package com.app.saintjimmy.iphome.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ingrediente extends Comida implements Serializable {
    private String categoria;

    public Ingrediente() {
    }

    public Ingrediente(long idIngrediente, String nomeIngrediente, String categoria) {
        super();
        this.categoria = categoria;
    }

    public String getCategoria() {return categoria; }

    public void setCategoria(String categoria) {this.categoria = categoria; }
}
