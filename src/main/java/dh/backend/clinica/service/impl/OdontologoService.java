package dh.backend.clinica.service.impl;

import dh.backend.clinica.entity.Odontologo;
import dh.backend.clinica.exception.ResourceNotFoundException;
import dh.backend.clinica.repository.IOdontologoRepository;
import dh.backend.clinica.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    @Autowired
    private IOdontologoRepository odontologoRepository;

    private static final Logger logger = LoggerFactory.getLogger(OdontologoService.class);

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        logger.info("Guardando odontólogo: {}", odontologo);
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        logger.info("Buscando odontólogo por ID: {}", id);
        Optional<Odontologo> odontologoEncontrado = odontologoRepository.findById(id);
        if (odontologoEncontrado.isEmpty()) {
            logger.warn("Odontólogo no encontrado con ID: {}", id);
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
        logger.info("Odontólogo encontrado: {}", odontologoEncontrado.get());
        return odontologoEncontrado;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("Buscando todos los odontólogos");
        return odontologoRepository.findAll();
    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) {
        logger.info("Modificando odontólogo: {}", odontologo);
        odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminarOdontologo(Integer id) {
        logger.info("Eliminando odontólogo con ID: {}", id);
        Optional<Odontologo> odontologoEncontrado = buscarPorId(id);
        if (odontologoEncontrado.isPresent()) {
            odontologoRepository.deleteById(id);
            logger.info("Odontólogo con ID {} eliminado correctamente", id);
        } else {
            logger.warn("Odontólogo no encontrado con ID: {}", id);
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
    }

    @Override
    public List<Odontologo> buscarPorApellidoyNombre(String apellido, String nombre) {
        logger.info("Buscando odontólogos por apellido: {} y nombre: {}", apellido, nombre);
        return odontologoRepository.findByApellidoAndNombre(apellido, nombre);
    }

    @Override
    public List<Odontologo> buscarPorUnaParteApellido(String parte) {
        logger.info("Buscando odontólogos por parte del apellido: {}", parte);
        return odontologoRepository.buscarPorParteApellido(parte);
    }

    @Override
    public List<Odontologo> buscarPorUnaParteNombre(String parte) {
        logger.info("Buscando odontólogos por parte del nombre: {}", parte);
        return odontologoRepository.buscarPorParteNombre(parte);
    }
}
