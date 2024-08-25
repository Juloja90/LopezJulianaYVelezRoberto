package dh.backend.clinica.controller;

import dh.backend.clinica.model.Turno;
import dh.backend.clinica.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        return ResponseEntity.ok(turnoService.guardarTurno(turno));
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

//    @GetMapping("/buscar/{id}")
//    public ResponseEntity<Turno>  buscarPorId(@PathVariable Integer id){
//        Turno turnoEncontrado = turnoService.buscarPorId(id);
//        if(turnoEncontrado!= null) {
//            return ResponseEntity.ok(turnoEncontrado);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


    @GetMapping("/buscarTurno/{id}")
    public ResponseEntity<Turno>  buscarPorId(@PathVariable Integer id){
        Turno turnoEncontrado = turnoService.buscarPorId(id);
        if(turnoEncontrado!= null) {
            return ResponseEntity.ok(turnoEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/modificar")
    public ResponseEntity<String> modificarTurno(@RequestBody Turno turno){
        Turno turnoEncontrado = turnoService.buscarPorId(turno.getId());
        if(turnoEncontrado!= null){
            turnoService.modificarTurno(turno);
            String jsonResponse = "{\"mensaje\": \"El turno fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id){
        Turno turnoEncontrado = turnoService.buscarPorId(id);
        if(turnoEncontrado!= null) {
            turnoService.eliminarTurno(id);
            String jsonResponse = "{\"mensaje\": \"El turno fue eliminado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
