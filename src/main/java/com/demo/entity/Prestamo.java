package com.demo.entity;

import javax.persistence.*;
import java.io.Serializable;


public class Prestamo implements Serializable {


    @Column
    private String importe;

    @Column
    private String capital;

    @Column
    private  String plazo;
    @Id
    @GeneratedValue
    private Long id;

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
