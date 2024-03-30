package com.dh.entrega.ClinicaEntrega.service.implementation;

import com.dh.entrega.ClinicaEntrega.entity.Turno;
import com.dh.entrega.ClinicaEntrega.exception.BadRequestException;
import com.dh.entrega.ClinicaEntrega.exception.NotFoundException;
import com.dh.entrega.ClinicaEntrega.repository.ITurnoRepository;
import com.dh.entrega.ClinicaEntrega.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    public final static Logger LOGGER = Logger.getLogger(TurnoService.class);

    private ITurnoRepository turnoRepository;

    @Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno guardar(Turno turno) {
        LOGGER.info("estamos guardando un turno");
        return turnoRepository.save(turno);
    }

    @Override
    public List<Turno> listarTodos() {
        LOGGER.info("estamos listando todos los turnos");
        return turnoRepository.findAll();
    }

    @Override
    public Optional<Turno> buscarPorId(Long id) {
        Optional<Turno> turnoABuscar = turnoRepository.findById(id);
        if (turnoABuscar.isPresent()){
            LOGGER.info("se encontró el turno con id " + id);
            return turnoABuscar;
        }else {
            LOGGER.error("No se encontró el turno con id " + id);
            throw new BadRequestException("No se encontró el turno con id " + id);
        }
    }

    @Override
    public void eliminar(Long id) {
        Optional<Turno> turnoAEliminar = turnoRepository.findById(id);
        if (turnoAEliminar.isPresent()){
            LOGGER.info("se eliminará el turno con id " + id);
            turnoRepository.deleteById(id);
        }else {
            LOGGER.error("No se encontró el turno con id " + id);
            throw new BadRequestException("No se encontró el turno con id " + id);
        }
    }

    @Override
    public void actualizar(Turno turno) {
        Optional<Turno> turnoAActualizar = turnoRepository.findById(turno.getId());
        if (turnoAActualizar.isPresent()){
            LOGGER.info("se actualizará el turno con id " + turno.getId());
            turnoRepository.save(turno);
        }else {
            LOGGER.error("No se encontró el turno con id " + turno.getId());
            throw new BadRequestException("No se encontró el turno con id " + turno.getId());
        }
    }
}
