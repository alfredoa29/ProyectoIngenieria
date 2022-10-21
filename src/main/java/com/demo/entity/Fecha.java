package com.demo.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fecha {


    LocalDate calcularFecha(String fecha, int days){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        //String date = "16/08/2022";
        String date = fecha;
        //convert String to LocalDate
        LocalDate givenDate = LocalDate.parse(date, formatter);

        DayOfWeek dayOfWeek = DayOfWeek.SUNDAY;
        if (givenDate.getDayOfWeek().equals(dayOfWeek)){
            throw new RuntimeException("Debe de escoger un dia laborable");
        } else {
            LocalDate result = givenDate;
            int addedDays = 0;
            while (addedDays < days) {
                result = result.plusDays(1);
                if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                    ++addedDays;
                }

            }
            return result;
        }
    }
}
