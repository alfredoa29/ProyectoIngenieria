package com.demo.entity;

import javax.persistence.*;

@Entity
public class Prestamo extends QuioscoPersonal{


    @Column
    private String importe;

    @Column
    private String capital;

    @Column
    private  String plazo;

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
}
