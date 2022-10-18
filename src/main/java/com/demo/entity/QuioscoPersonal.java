package com.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)

public abstract class QuioscoPersonal implements Serializable {

/*    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    //@Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @Column
    protected String nombreSolicitud; //vacacion, prestamo, etc

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getNombreSolicitud() {
        return nombreSolicitud;
    }

    public void setNombreSolicitud(String nombreSolicitud) {
        this.nombreSolicitud = nombreSolicitud;
    }*/
}
