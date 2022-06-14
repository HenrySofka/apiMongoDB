package co.com.sura.sofkau.apiMongoDB.controllers;

import co.com.sura.sofkau.apiMongoDB.dto.RecursoDTO;
import co.com.sura.sofkau.apiMongoDB.models.Recurso;
import co.com.sura.sofkau.apiMongoDB.services.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recursos")
public class RecursoController {

    @Autowired
    RecursoService service;

    @GetMapping()
    public ResponseEntity<List<RecursoDTO>> findAll(){
        List<RecursoDTO> lista = service.findAll();
        return new ResponseEntity(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")//PathVariable para obtener los valores de la URL
    public ResponseEntity<RecursoDTO> finById(@PathVariable("id") String id){
        RecursoDTO dto = service.findById(id);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @PostMapping("/crear")//RequestBody indica el objeto que viene en el Body
    public ResponseEntity<RecursoDTO> create(@RequestBody RecursoDTO dto){
        return new ResponseEntity(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/modificar")
    public ResponseEntity<RecursoDTO> update(@RequestBody RecursoDTO dto){
        if(dto.getId() != null){
            return new ResponseEntity(service.update(dto), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RecursoDTO> deleteById(@PathVariable("id") String id){
        try {
            service.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    //MÉTODOS DEL TALLER
    @GetMapping("disponibilidad/{id}")
    public ResponseEntity<String> isDisponible(@PathVariable("id") String id){
        return new ResponseEntity(service.isDisponible(id), HttpStatus.OK);
    }

    @PutMapping("prestar/{id}")
    public ResponseEntity<String> prestarLibro(@PathVariable("id") String id){
        return new ResponseEntity(service.prestarLibro(id), HttpStatus.OK);
    }

    @GetMapping("tipo/{tipoRecurso}")
    public ResponseEntity<List<RecursoDTO>> recomendarPorTipoRecurso(@PathVariable("tipoRecurso") String tipoRecurso){
        List<RecursoDTO> lista = service.recomendarPorTipoRecurso(tipoRecurso);
        if (lista.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(lista, HttpStatus.OK);
    }

    @GetMapping("tematica/{areaTematica}")
    public ResponseEntity<List<RecursoDTO>> recomendarPorAreatematica(@PathVariable("areaTematica") String areaTematica){
        List<RecursoDTO> lista = service.recomendarPorAreatematica(areaTematica);
        if (lista.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(lista, HttpStatus.OK);
    }

    @GetMapping("tipo_tematica/{tipoRecurso}/{areaTematica}")
    public ResponseEntity<List<RecursoDTO>> recomendarPorTipoYArea(
            @PathVariable("tipoRecurso") String tipoRecurso,
            @PathVariable("areaTematica") String areaTematica
    ){
        List<RecursoDTO> lista = service.recomendarPorTipoYArea(tipoRecurso,areaTematica);
        if (lista.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(lista, HttpStatus.OK);
    }

    @PutMapping("devolver/{id}")
    public ResponseEntity<String> devolverRecurso(@PathVariable("id") String id){
        return new ResponseEntity(service.devolverRecurso(id), HttpStatus.OK);
    }

}
