package com.dh.entrega.ClinicaEntrega.controller;

import com.dh.entrega.ClinicaEntrega.entity.Odontologo;
import com.dh.entrega.ClinicaEntrega.entity.Paciente;
import com.dh.entrega.ClinicaEntrega.entity.Turno;
import com.dh.entrega.ClinicaEntrega.service.IOdontologoService;
import com.dh.entrega.ClinicaEntrega.service.IPacienteService;
import com.dh.entrega.ClinicaEntrega.service.ITurnoService;
import com.dh.entrega.ClinicaEntrega.service.implementation.OdontologoService;
import com.dh.entrega.ClinicaEntrega.service.implementation.PacienteService;
import com.dh.entrega.ClinicaEntrega.service.implementation.TurnoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {


    private ITurnoService turnoService;
    private IOdontologoService odontologoService;
    private IPacienteService pacienteService;

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno){
        ResponseEntity<Turno> response;
        if (odontologoService.buscarPorId(turno.getOdontologo().getId()) != null &&
        pacienteService.buscarPorId(turno.getPaciente().getId()) != null){
            response = ResponseEntity.ok(turnoService.guardar(turno));
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos(){
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable("id") Long id){
        Optional<Turno> turno = turnoService.buscarPorId(id);
        if (turno.isPresent()) {
            return ResponseEntity.ok(turno.get());
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping
    public ResponseEntity<Turno> actualizar(@RequestBody Turno turno){
        Optional<Turno> turnoAActualizar = turnoService.buscarPorId(turno.getId());
        if (turnoAActualizar.isPresent() && odontologoService.buscarPorId(turno.getOdontologo().getId()) != null &&
                pacienteService.buscarPorId(turno.getPaciente().getId()) != null) {
            turnoService.actualizar(turno);
            return ResponseEntity.ok(turno);
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Turno> eliminar(@PathVariable("id") Long id){
        ResponseEntity<Turno> response;
        Optional<Turno> turnoAEliminar = turnoService.buscarPorId(id);
        if (turnoAEliminar.isPresent()) {
            response = new ResponseEntity("se elimino el turno", HttpStatus.OK);
            turnoService.eliminar(id);
        } else {
            response = new ResponseEntity("no se pudo eliminar el turno", HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
