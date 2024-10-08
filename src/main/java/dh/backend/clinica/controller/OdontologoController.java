package dh.backend.clinica.controller;

import dh.backend.clinica.entity.Odontologo;
import dh.backend.clinica.entity.Paciente;
import dh.backend.clinica.service.IOdontologoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Odontologo> guardarOdontologo(@Valid @RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    //PUT
    @PutMapping("/modificar")
    public ResponseEntity<String>  modificarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(odontologo.getId());
        if(odontologoEncontrado.isPresent()){
            odontologoService.modificarOdontologo(odontologo);
            String jsonResponse = "{\"mensaje\": \"El odontologo fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id){
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("{\"mensaje\": \"El odontologo fue eliminado\"}");
    }

    //GET
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo>  buscarPorId(@PathVariable Integer id){
        Optional<Odontologo>  odontologoEncontrado = odontologoService.buscarPorId(id);
        if(odontologoEncontrado.isPresent()) {
            return ResponseEntity.ok(odontologoEncontrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //GET
    @GetMapping("/buscartodos")
    public ResponseEntity<List<Odontologo>>  buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @GetMapping("/buscarApellidoNombre/{apellido}/{nombre}")
    public ResponseEntity<List<Odontologo>> buscarApellido(@PathVariable String apellido, @PathVariable String nombre){
        return ResponseEntity.ok(odontologoService.buscarPorApellidoyNombre(apellido, nombre));
    }

    @GetMapping("/buscarApellido/{parte}")
    public ResponseEntity<List<Odontologo>> buscarParteApellido(@PathVariable String parte){
        return ResponseEntity.ok(odontologoService.buscarPorUnaParteApellido(parte));
    }

    @GetMapping("/buscarNombre/{parte}")
    public ResponseEntity<List<Odontologo>> buscarParteNombre(@PathVariable String parte){
        return ResponseEntity.ok(odontologoService.buscarPorUnaParteNombre(parte));
    }

}

