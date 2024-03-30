package com.dh.entrega.ClinicaEntrega.controller;

import com.dh.entrega.ClinicaEntrega.entity.Odontologo;
import com.dh.entrega.ClinicaEntrega.entity.Paciente;
import com.dh.entrega.ClinicaEntrega.service.IOdontologoService;
import com.dh.entrega.ClinicaEntrega.service.implementation.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private  IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardar(odontologo)) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable("id") Long id){

        Odontologo odontologoBuscado = odontologoService.buscarPorId(id);
        if (odontologoBuscado != null){
            return ResponseEntity.ok(odontologoBuscado);
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos(){
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo){

        ResponseEntity<Odontologo> response;
        Odontologo odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());

        if (odontologoBuscado != null){
            odontologoService.actualizar(odontologo);
            response = new ResponseEntity(odontologo, HttpStatus.OK);
        }else {
            response = new ResponseEntity("no se pudo actualizar el odontologo", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Odontologo> eliminar(@PathVariable("id") Long id){
        ResponseEntity<Odontologo> response;
        Odontologo odontologoBuscado = odontologoService.buscarPorId(id);

        if (odontologoBuscado.getId().equals(id)){
            odontologoService.eliminar(id);
            response = new ResponseEntity("se elimino el odontologo", HttpStatus.OK);
        }else {
            response = new ResponseEntity("no se pudo eliminar el odontologo", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/buscarMatricula/{matricula}")
    public ResponseEntity<Odontologo> findByMatricula(@PathVariable("matricula") String matricula) throws Exception {
        Optional<Odontologo> odontologoOptional = odontologoService.findByMatricula(matricula);

        if (odontologoOptional.isPresent()){
            return ResponseEntity.ok(odontologoOptional.get());
        } else {
            throw new Exception("no se encontó al odontologo con matricula: " + matricula);
        }

    }

}
