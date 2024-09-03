package dh.backend.clinica.controller;

import dh.backend.clinica.entity.Odontologo;
import dh.backend.clinica.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //POST
    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    //PUT
    @PutMapping("/modificar")
    public ResponseEntity<String> modificarOdontologo(@PathVariable Integer id, @RequestBody Odontologo odontologo){
        odontologoService.modificarOdontologo(odontologo);
        return ResponseEntity.ok("{\"mensaje\": \"El odontologo fue modificado\"}");
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id){
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("{\"mensaje\": \"El odontologo fue eliminado\"}");
    }

    //GET
    @GetMapping("/buscar/{id}")
    public ResponseEntity<String>  buscarPorId(@PathVariable Integer id){
        odontologoService.buscarPorId(id);
        return ResponseEntity.ok("{\"mensaje\": \"El odontologo fue encontrado\"}");
    }

    //GET
    @GetMapping("/buscartodos")
    public ResponseEntity<List<Odontologo>>  buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
}

