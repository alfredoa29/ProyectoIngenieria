package com.demo.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fecha {


    public String calcularFecha(String fecha, int days){

        String cadena = fecha;
        String[] fragmentos = cadena.split("-");
        String anniodeUltimo = fragmentos[2]+"/"+ fragmentos[1] +"/"+ fragmentos[0];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = anniodeUltimo;
        //convert String to LocalDate
        LocalDate givenDate = LocalDate.parse(date, formatter);

        DayOfWeek dayOfWeek = DayOfWeek.SUNDAY;

            LocalDate result = givenDate;
            int addedDays = 0;
            while (addedDays < days) {
                result = result.plusDays(1);
                if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                    ++addedDays;
                }

            }
            return result.toString();

    }

   public boolean validarFechas(String fecha){
        //esta parte divide la fecha y le da formato d/MM/yyyy y no yyyy/MM/d
        String cadena = fecha;
        String[] fragmentos = cadena.split("-");
        String anniodeUltimo = fragmentos[2]+"/"+ fragmentos[1] +"/"+ fragmentos[0];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = anniodeUltimo;
        //convert String to LocalDate
        LocalDate givenDate = LocalDate.parse(date, formatter);

        DayOfWeek dayOfWeek = DayOfWeek.SUNDAY;

        boolean fechaBoolean = true;
        if (givenDate.getDayOfWeek().equals(dayOfWeek)){
            fechaBoolean = false;
            throw new RuntimeException(" ERROR! Debe de escoger un dia  laborable");



        }

        return fechaBoolean;
    }

   public boolean validarDias(int dias){

        boolean diasBoolean = true;

        if (dias>15){

            diasBoolean = false;
            throw new RuntimeException(" ERROR! Numero de dia no valido");
        }


        return diasBoolean;
    }


}
