package com.dh.entrega.ClinicaEntrega.service.implementation;

import com.dh.entrega.ClinicaEntrega.entity.Domicilio;
import com.dh.entrega.ClinicaEntrega.entity.Odontologo;
import com.dh.entrega.ClinicaEntrega.entity.Paciente;
import com.dh.entrega.ClinicaEntrega.entity.Turno;
import com.dh.entrega.ClinicaEntrega.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    private Paciente paciente1;
    private Paciente paciente2;
    private Set<Turno> turnoSet;
    private Domicilio domicilio;
    private LocalDate fechaLocalDate;


    @BeforeEach
    void setUp() {
        turnoSet = new HashSet<>();
        domicilio = new Domicilio();
        String fecha = "2023/10/10";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        fechaLocalDate = LocalDate.parse(fecha, formatter);
        paciente1 = new Paciente(1L, "Ignacio", "Lopez", "123456", fechaLocalDate, domicilio, turnoSet);
        paciente2 = new Paciente(2L, "Chiara", "Lopez", "123456", fechaLocalDate, domicilio, turnoSet);
    }

    @Test
    void guardarTest() {
        pacienteService.guardar(paciente1);
        assertNotNull(paciente1);
    }

    @Test
    void listarTodosTest(){
        List<Paciente> pacienteList;
        pacienteService.guardar(paciente1);
        pacienteService.guardar(paciente2);
        pacienteList = pacienteService.listarTodos();
        assertNotNull(pacienteList);
    }

    @Test
    void buscarPorIdTest(){
        pacienteService.guardar(paciente2);
        Paciente pacienteBuscado = pacienteService.buscarPorId(paciente2.getId());
        assertNotNull(pacienteBuscado);
    }

    @Test
    void buscarPorIdExceptionTest(){
        String mensajeEsperado = "no se encontró el paciente con id 4";

        Exception excepcionALanzar = assertThrows(BadRequestException.class, () -> pacienteService.buscarPorId(4L));

        assertEquals(mensajeEsperado, excepcionALanzar.getMessage());
    }

    @Test
    void eliminarTest(){
        pacienteService.guardar(paciente1);
        pacienteService.eliminar(paciente1.getId());
        assertFalse(pacienteService.listarTodos().contains(paciente1));
    }

    @Test
    void actualizarTest(){
        pacienteService.guardar(paciente1);
        Paciente pacienteModificado = new Paciente(1L, "Ignacio", "Lopez", "321456", fechaLocalDate, domicilio, turnoSet);
        pacienteService.actualizar(pacienteModificado);
        assertNotEquals(paciente1, pacienteModificado);
    }

}