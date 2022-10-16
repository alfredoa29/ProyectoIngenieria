package com.demo.entity;





import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Solicitud implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;



   // @JoinColumn(name = "usuario_id", nullable = false)
   @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @Column
    String fecha;

    @Column
    boolean estado ;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "quiosco_id")
    private QuioscoPersonal quioscoPersonal;



    public Solicitud() {
    }

    public Solicitud(Long id, User usuario, String fecha, boolean estado) {
        this.id = id;
        this.usuario = usuario;
        this.fecha = fecha;
        this.estado = estado;

    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    public QuioscoPersonal getQuioscoPersonal() {
        return quioscoPersonal;
    }

    public void setQuioscoPersonal(QuioscoPersonal quioscoPersonal) {
        this.quioscoPersonal = quioscoPersonal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "id=" + id +
                ", Usuario=" + usuario +
                ", fecha='" + fecha + '\'' +
                ", estado=" + estado +
                '}';
    }



}
