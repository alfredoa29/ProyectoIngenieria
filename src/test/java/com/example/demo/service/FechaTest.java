package com.example.demo.service;

import com.demo.service.Fecha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FechaTest {

    @Autowired
    Fecha fecha;

    @BeforeEach
    void setUp() {
          fecha = new Fecha();
    }

    @Test
    void calcularFecha() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = "25/10/2022";

        //convert String to LocalDate
       // LocalDate expected = LocalDate.parse(date, formatter);
        String expected = date;
        String actual = fecha.calcularFecha("21/10/2022", 2);
        // deberia devolver que la fecha esperaad es 25/10/2022
        assertEquals(expected, actual);
    }

    @Test
    void validarFechas() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = "2022/10/2023";

        //convert String to LocalDate
        //LocalDate expected = LocalDate.parse(date, formatter);


        Throwable exception = assertThrows(RuntimeException.class, () -> fecha.validarFechas(date));
        assertEquals("ERROR! Debe de escoger un dia  laborable", exception.getMessage());
    }
}