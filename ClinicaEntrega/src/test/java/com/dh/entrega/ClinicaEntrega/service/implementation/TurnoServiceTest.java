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
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    private Turno turno1;
    private Turno turno2;
    private LocalDate fechaLocalDate;
    private Odontologo odontologo1;
    private Odontologo odontologo2;
    private Paciente paciente1;
    private Paciente paciente2;
    private Set<Turno> turnoSet;
    private Domicilio domicilio;

    @BeforeEach
    void setUp() {
        String fecha = "2024/03/26";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        fechaLocalDate = LocalDate.parse(fecha, formatter);
        turnoSet = new HashSet<>();
        domicilio = new Domicilio();
        odontologo1 = new Odontologo(1L, "Vanina", "Godoy", "123", turnoSet);
        odontologo2 = new Odontologo(2L, "Gaston", "Zini", "456", turnoSet);
        paciente1 = new Paciente(1L, "Ignacio", "Lopez", "123456", fechaLocalDate, domicilio, turnoSet);
        paciente2 = new Paciente(2L, "Chiara", "Lopez", "123456", fechaLocalDate, domicilio, turnoSet);
        turno1 = new Turno(1L, fechaLocalDate, odontologo1, paciente1);
        turno2 = new Turno(2L, fechaLocalDate, odontologo2, paciente2);
        pacienteService.guardar(paciente1);
        odontologoService.guardar(odontologo1);
        pacienteService.guardar(paciente2);
        odontologoService.guardar(odontologo2);
    }

    @Test
    void guardarTest() {
        turnoService.guardar(turno1);
        assertNotNull(turno1);
    }

    @Test
    void listarTodosTest(){
        List<Turno> turnoList;
        turnoService.guardar(turno1);
        turnoList = turnoService.listarTodos();
        assertNotNull(turnoList);
    }

    @Test
    void buscarPorIdTest(){
        turnoService.guardar(turno1);
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(turno1.getId());
        assertNotNull(turnoBuscado);
    }

    @Test
    void buscarPorIdExceptionTest(){
        String mensajeEsperado = "No se encontró el turno con id 4";

        Exception excepcionALanzar = assertThrows(BadRequestException.class, () -> turnoService.buscarPorId(4L));

        assertEquals(mensajeEsperado, excepcionALanzar.getMessage());
    }

    @Test
    void eliminarTest(){
        turnoService.guardar(turno2);
        turnoService.eliminar(turno2.getId());
        assertFalse(turnoService.listarTodos().contains(turno2));
    }

    @Test
    void actualizarTest(){
        turnoService.guardar(turno1);
        Turno turnoModificado = new Turno(1L, fechaLocalDate, odontologo1, paciente2);
        turnoService.actualizar(turnoModificado);
        assertNotEquals(turno1, turnoModificado);
    }

}