package com.demo.entity;



import javax.persistence.*;
import java.io.Serializable;

@Entity
public class SolicitudVacacion extends Solicitud  implements Serializable {

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name = "vacacion_id")
    Vacacion vacacion;

    public SolicitudVacacion() {
    }

    public Vacacion getVacacion() {
        return vacacion;
    }

    public void setVacacion(Vacacion vacacion) {
        this.vacacion = vacacion;
    }

    @Override
    public String toString() {
        return "SolicitudVacacion{" +
                "vacacion=" + vacacion +
                ", id=" + id +
                ", usuario=" + usuario +
                ", fecha='" + fecha + '\'' +
                ", estado=" + estado +
                '}';
    }
}
