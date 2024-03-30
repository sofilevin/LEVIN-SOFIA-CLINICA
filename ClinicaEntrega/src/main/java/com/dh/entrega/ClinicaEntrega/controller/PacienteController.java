package com.dh.entrega.ClinicaEntrega.controller;

import com.dh.entrega.ClinicaEntrega.entity.Odontologo;
import com.dh.entrega.ClinicaEntrega.entity.Paciente;
import com.dh.entrega.ClinicaEntrega.service.IPacienteService;
import com.dh.entrega.ClinicaEntrega.service.implementation.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private IPacienteService pacienteService;


    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public Paciente guardar(@RequestBody Paciente paciente){
        return pacienteService.guardar(paciente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable("id") Long id){
        Paciente pacienteBuscado = pacienteService.buscarPorId(id);
        if (pacienteBuscado != null){
            return ResponseEntity.ok(pacienteBuscado);
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos(){
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente){

        ResponseEntity<Paciente> response;
        Paciente pacienteBuscado = pacienteService.buscarPorId(paciente.getId());

        if (pacienteBuscado != null){
            pacienteService.actualizar(paciente);
            response = new ResponseEntity(paciente, HttpStatus.OK);
        }else {
            response = new ResponseEntity("no se pudo actualizar el paciente", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> eliminar(@PathVariable("id") Long id){
        ResponseEntity<Paciente> response;
        Paciente pacienteBuscado = pacienteService.buscarPorId(id);

        if (pacienteBuscado.getId().equals(id)){
            pacienteService.eliminar(id);
            response = new ResponseEntity("se elimino el paciente", HttpStatus.OK);
        }else {
            response = new ResponseEntity("no se pudo eliminar el paciente", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

}
