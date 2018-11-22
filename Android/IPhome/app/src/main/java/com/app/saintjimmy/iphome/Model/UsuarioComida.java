package com.app.saintjimmy.iphome.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UsuarioComida implements Serializable {
    private long idUsuarioComida;
    private long idUsuario;
    private long idComida;


    public UsuarioComida() {
    }

    public UsuarioComida(long idUsuarioComida, long idUsuario, long idComida) {
        this.idUsuarioComida = idUsuarioComida;
        this.idUsuario = idUsuario;
        this.idComida = idComida;
    }

    public long getIdUsuarioComida() { return idUsuarioComida; }

    public void setIdUsuarioComida(long idUsuarioComida) { this.idUsuarioComida = idUsuarioComida; }

    public long getIdUsuario() { return idUsuario; }

    public void setIdUsuario(long idUsuario) { this.idUsuario = idUsuario; }

    public long getIdComida() { return idComida; }

    public void setIdComida(long idComida) { this.idComida = idComida; }
}
