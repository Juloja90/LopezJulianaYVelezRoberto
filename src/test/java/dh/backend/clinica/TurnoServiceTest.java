package dh.backend.clinica;

import dh.backend.clinica.dto.response.TurnoResponseDto;
import dh.backend.clinica.entity.Domicilio;
import dh.backend.clinica.entity.Odontologo;
import dh.backend.clinica.entity.Paciente;
import dh.backend.clinica.entity.Turno;
import dh.backend.clinica.repository.ITurnoRepository;
import dh.backend.clinica.service.impl.OdontologoService;
import dh.backend.clinica.service.impl.PacienteService;
import dh.backend.clinica.service.impl.TurnoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class TurnoServiceTest {
    static final Logger logger = LoggerFactory.getLogger(TurnoServiceTest.class);

    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;

    @Autowired
    TurnoService turnoService;

    @Autowired
    ITurnoRepository turnoRepository;

    Paciente paciente;
    Paciente pacienteDesdeDb;
    Odontologo odontologo;
    Odontologo odontologoDesdeDb;
    Turno turno;
    Turno turnoDesdeDb;

    @BeforeEach
    void cargarDatos(){
        Domicilio domicilio = new Domicilio(null,"Falsa",145,"CABA","Buenos Aires");
        paciente = new Paciente();
        paciente.setApellido("Castro");
        paciente.setNombre("Maria");
        paciente.setDni("48974646");
        paciente.setFechaIngreso(LocalDate.of(2024,7,15));
        paciente.setDomicilio(domicilio);
        pacienteDesdeDb = pacienteService.guardarPaciente(paciente);

        odontologo = new Odontologo();
        odontologo.setApellido("Castro");
        odontologo.setNombre("Maria");
        odontologo.setNroMatricula("48974646");
        odontologoDesdeDb = odontologoService.guardarOdontologo(odontologo);

        turno = new Turno();
        turno.setPaciente(pacienteDesdeDb);
        turno.setOdontologo(odontologoDesdeDb);
        turno.setFecha(LocalDate.of(2024,7,15));
        turnoDesdeDb = turnoRepository.save(turno);  // Usar el servicio en lugar del repositorio directamente
    }

    @Test
    @DisplayName("Testear que un turno fue creado correctamente")
    void caso1(){
        assertNotNull(turnoDesdeDb.getId());
    }

    @Test
    @DisplayName("Testear que un turno pueda acceder por id")
    void caso2(){
        //Dado
        Integer id = turnoDesdeDb.getId();
        //cuando
        TurnoResponseDto turnoRecuperado = turnoService.buscarPorId(id).get();
        // entonces
        assertEquals(id, turnoRecuperado.getId());
    }

    @Test
    @DisplayName("Listar todos los turnos")
    void caso3(){
        //Dado
        List<TurnoResponseDto> turnos;
        // cuando
        turnos = turnoService.buscarTodos();
        // entonces
        assertFalse(turnos.isEmpty());
    }
}
