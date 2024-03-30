package com.dh.entrega.ClinicaEntrega.service.implementation;

import com.dh.entrega.ClinicaEntrega.entity.Odontologo;
import com.dh.entrega.ClinicaEntrega.exception.BadRequestException;
import com.dh.entrega.ClinicaEntrega.repository.IOdontologoRepository;
import com.dh.entrega.ClinicaEntrega.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private IOdontologoRepository odontologoRepository;
    public static final Logger LOGGER = Logger.getLogger(PacienteService.class);


    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("estamos guardando un odontologo");
        return odontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("estamos listando todos los odontologos");
        return odontologoRepository.findAll();
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        Optional<Odontologo> odontologoABuscar = odontologoRepository.findById(id);
        if (odontologoABuscar.isPresent()){
            LOGGER.info("se encontró el odontólogo con id " + id);
            return odontologoABuscar.get();
        }else {
            LOGGER.error("no se encontró el odontólogo con id " + id);
            throw new BadRequestException("no se encontró el odontólogo con id " + id);
        }
    }

    @Override
    public void eliminar(Long id) {
        LOGGER.info("estamos eliminando el odontologo con id " + id);
        odontologoRepository.deleteById(id);
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        LOGGER.info("estamos actualizando el odontologo con id " + odontologo.getId());
        odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> findByMatricula(String matricula) {
        return odontologoRepository.findByMatricula(matricula);
    }


}
