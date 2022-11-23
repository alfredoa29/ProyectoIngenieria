package com.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CorreoElectronico {


    @Column
    private String correoElectronico;

    @Column
    private String tipoDeCorreo; // aqui se refiere a "administrador" u  otro tipo de correo"
    @Id
    private Long id;

    public CorreoElectronico(String correoElectronico, String tipoDeCorreo) {
        this.correoElectronico = correoElectronico;
        this.tipoDeCorreo = tipoDeCorreo;
    }

    public CorreoElectronico() {

    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTipoDeCorreo() {
        return tipoDeCorreo;
    }

    public void setTipoDeCorreo(String tipoDeCorreo) {
        this.tipoDeCorreo = tipoDeCorreo;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CorreoElectronico{" +
                "correoElectronico='" + correoElectronico + '\'' +
                ", tipoDeCorreo='" + tipoDeCorreo + '\'' +
                '}';
    }


}