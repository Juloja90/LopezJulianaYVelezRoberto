package dh.backend.clinica.service.impl;

import dh.backend.clinica.entity.Odontologo;
import dh.backend.clinica.entity.Paciente;
import dh.backend.clinica.exception.ResourceNotFoundException;
import dh.backend.clinica.repository.IPacienteRepository;
import dh.backend.clinica.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    private final IPacienteRepository pacienteRepository;
    private static final Logger logger = LoggerFactory.getLogger(PacienteService.class);

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        logger.info("Guardando paciente: {}", paciente);
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        logger.info("Buscando paciente por ID: {}", id);
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(id);
        if (pacienteEncontrado.isPresent()) {
            logger.info("Paciente encontrado: {}", pacienteEncontrado.get());
            return pacienteEncontrado;
        } else {
            logger.warn("Paciente no encontrado con ID: {}", id);
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }

    @Override
    public List<Paciente> buscarTodos() {
        logger.info("Buscando todos los pacientes");
        return pacienteRepository.findAll();
    }

    @Override
    public void modificarPaciente(Paciente paciente) {
        logger.info("Modificando paciente: {}", paciente);
        pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Integer id) {
        logger.info("Eliminando paciente con ID: {}", id);
        Optional<Paciente> pacienteEcontrado = buscarPorId(id);
        if (pacienteEcontrado.isPresent()) {
            pacienteRepository.deleteById(id);
            logger.info("Paciente con ID {} eliminado correctamente", id);
        } else {
            logger.warn("Paciente no encontrado con ID: {}", id);
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }

    @Override
    public List<Paciente> buscarPorApellidoyNombre(String apellido, String nombre) {
        logger.info("Buscando pacientes por apellido: {} y nombre: {}", apellido, nombre);
        return pacienteRepository.findByApellidoAndNombre(apellido, nombre);
    }

    @Override
    public List<Paciente> buscarPorUnaParteApellido(String parte) {
        logger.info("Buscando pacientes por parte del apellido: {}", parte);
        return pacienteRepository.buscarPorParteApellido(parte);
    }
}
