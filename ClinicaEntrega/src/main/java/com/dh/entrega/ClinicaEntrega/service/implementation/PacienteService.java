package com.dh.entrega.ClinicaEntrega.service.implementation;

import com.dh.entrega.ClinicaEntrega.entity.Paciente;
import com.dh.entrega.ClinicaEntrega.exception.BadRequestException;
import com.dh.entrega.ClinicaEntrega.repository.IPacienteRepository;
import com.dh.entrega.ClinicaEntrega.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    private IPacienteRepository pacienteRepository;
    public final static Logger LOGGER = Logger.getLogger(TurnoService.class);


    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        LOGGER.info("estamos guardando un paciente");
        return pacienteRepository.save(paciente);
    }

    @Override
    public List<Paciente> listarTodos() {
        LOGGER.info("estamos listando todos los pacientes");
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente buscarPorId(Long id) {
        Optional<Paciente> pacienteABuscar = pacienteRepository.findById(id);
        if (pacienteABuscar.isPresent()){
            LOGGER.info("se encontró el paciente con id " + id);
            return pacienteABuscar.get();
        }else {
            LOGGER.error("No se encontró el paciente con id " + id);
            throw new BadRequestException("no se encontró el paciente con id " + id);
        }
    }

    @Override
    public void eliminar(Long id) {
        LOGGER.info("estamos eliminando el paciente con id " + id);
        pacienteRepository.deleteById(id);
    }

    @Override
    public void actualizar(Paciente paciente) {
        LOGGER.info("estamos actualizando el paciente con id " + paciente.getId());
        pacienteRepository.save(paciente);
    }
}
