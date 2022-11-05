package com.demo.dto;

public class Fecha {

    private  int dias;
    private String fechaInicio, fechaFinal;

    public Fecha() {
    }

    public Fecha(int dias, String fechaInicio, String fechaFinal) {
        this.dias = dias;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }


    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
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
        return "Fecha{" +
                "dias=" + dias +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFinal='" + fechaFinal + '\'' +
                '}';
    }
}
