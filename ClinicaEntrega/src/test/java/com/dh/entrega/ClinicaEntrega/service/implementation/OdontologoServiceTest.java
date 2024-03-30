package com.dh.entrega.ClinicaEntrega.service.implementation;

import com.dh.entrega.ClinicaEntrega.entity.Odontologo;
import com.dh.entrega.ClinicaEntrega.entity.Turno;
import com.dh.entrega.ClinicaEntrega.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    private Odontologo odontologo1;
    private Odontologo odontologo2;
    private Set<Turno> turnoSet;

    @BeforeEach
    void setUp() {
        turnoSet = new HashSet<>();
        odontologo1 = new Odontologo(1L, "Vanina", "Godoy", "123", turnoSet);
        odontologo2 = new Odontologo(2L, "Gaston", "Zini", "456", turnoSet);
    }

    @Test
    void guardarTest() {
        odontologoService.guardar(odontologo1);
        assertNotNull(odontologo1);
    }

    @Test
    void listarTodosTest(){
        List<Odontologo> odontologoList;
        odontologoService.guardar(odontologo1);
        odontologoService.guardar(odontologo2);
        odontologoList = odontologoService.listarTodos();
        assertNotNull(odontologoList);
    }

    @Test
    void buscarPorIdTest(){
        odontologoService.guardar(odontologo2);
        Odontologo odontologoBuscado = odontologoService.buscarPorId(odontologo2.getId());
        assertNotNull(odontologoBuscado);
    }

    @Test
    void buscarPorIdExceptionTest(){
        String mensajeEsperado = "no se encontró el odontólogo con id 4";

        Exception excepcionALanzar = assertThrows(BadRequestException.class, () -> odontologoService.buscarPorId(4L));

        assertEquals(mensajeEsperado, excepcionALanzar.getMessage());
    }

    @Test
    void eliminarTest(){
        odontologoService.guardar(odontologo1);
        odontologoService.eliminar(odontologo1.getId());
        assertFalse(odontologoService.listarTodos().contains(odontologo1));
    }

    @Test
    void actualizarTest(){
        odontologoService.guardar(odontologo1);
        Odontologo odontologoModificado = new Odontologo(1L, "Vanina", "Godoy", "321", turnoSet);
        odontologoService.actualizar(odontologoModificado);
        assertNotEquals(odontologo1, odontologoModificado);
    }

}