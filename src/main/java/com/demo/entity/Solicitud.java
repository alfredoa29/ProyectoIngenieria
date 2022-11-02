package com.demo.entity;





import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@MappedSuperclass
public class Solicitud implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    protected Long id;



   // @JoinColumn(name = "usuario_id", nullable = false)
   @ManyToOne(fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
   protected User usuario;

    @Column
    protected String fecha;

    @Column
    protected String estado ;

    public Long getId() {
        return id;
    }

    public Solicitud() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(date);
        formatter = new SimpleDateFormat("dd MMMM yyyy");
        strDate = formatter.format(date);
        this.fecha = strDate;

    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
