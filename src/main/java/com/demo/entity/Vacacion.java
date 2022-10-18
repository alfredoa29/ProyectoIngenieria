package com.demo.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Vacacion implements Serializable {


    @Column
    Integer numDias;
    @Column
    String fechaInicio;
    @Column
    String fechaFinal;
    @Id
    @GeneratedValue
    private Long id;


    public Vacacion() {
    }

    public Vacacion(Integer numDias, String fechaInicio, String fechaFinal) {
        this.numDias = numDias;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }




    public Integer getNumDias() {
        return numDias;
    }

    public void setNumDias(Integer numDias) {
        this.numDias = numDias;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Override
    public String toString() {
        return "Vacacion{" +
                "numDias=" + numDias +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFinal='" + fechaFinal + '\'' +
                ", id=" + id +
                '}';
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
