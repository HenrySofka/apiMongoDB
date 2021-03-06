package co.com.sura.sofkau.apiMongoDB.services;

import co.com.sura.sofkau.apiMongoDB.dto.RecursoDTO;
import co.com.sura.sofkau.apiMongoDB.mappers.RecursoMapper;
import co.com.sura.sofkau.apiMongoDB.models.Recurso;
import co.com.sura.sofkau.apiMongoDB.repositories.IRecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RecursoService implements IRecursoService {
    @Autowired
    private IRecursoRepository repository;
    private RecursoMapper mapper = new RecursoMapper();

    public List<RecursoDTO> findAll(){
        List<Recurso> lista = repository.findAll();
        return mapper.fromCollectionList(lista);
    }

    public RecursoDTO findById(String id){
        Recurso recurso = repository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontro el Recurso: [" + id + "]")
        );
        return mapper.fromCollection(recurso);
    }

    public RecursoDTO create(RecursoDTO dto){
        Recurso recurso = mapper.fromDTO(dto);
        return mapper.fromCollection(repository.save(recurso));
    }

    public RecursoDTO update(RecursoDTO dto){
        Recurso recurso = mapper.fromDTO(dto);
        repository.findById(recurso.getId()).orElseThrow(
                () -> new RuntimeException("No se encontro el Recurso: [" + recurso.getId() + "]")
        );
        return mapper.fromCollection(repository.save(recurso));
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }

    @Override
    public String isDisponible(String id){
        Recurso recurso = repository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontro el Recurso: [" + id + "]")
        );

        if(recurso.getIsPrestado()){
            return "El Recurso ya esta prestado. Fecha de prestamo["+ recurso.getFechaPrestamo() +"]";
        }

        return "El Recurso esta DISPONIBLE!!";
    }

    @Override
    public String prestarLibro(String id) {
        Recurso recurso = repository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontro el Recurso: [" + id + "]")
        );

        if(recurso.getIsPrestado()){
            return "El Recurso ya esta prestado. Fecha de prestamo["+ recurso.getFechaPrestamo() +"]";
        }

        recurso.setFechaPrestamo(LocalDate.now());
        recurso.setIsPrestado(true);
        this.update(mapper.fromCollection(recurso));

        return "Recurso DISPONIBLE --> Fecha de prestamo["+ recurso.getFechaPrestamo() +"]";
    }

    @Override
    public List<RecursoDTO> recomendarPorTipoRecurso(String tipoRecurso) {
        List<RecursoDTO> lista = mapper.fromCollectionList(repository.findByTipoRecurso(tipoRecurso));
        return lista;
    }

    @Override
    public List<RecursoDTO> recomendarPorAreatematica(String areaTematica) {
        List<RecursoDTO> lista = mapper.fromCollectionList(repository.findByAreaTematica(areaTematica));
        return lista;
    }

    @Override
    public List<RecursoDTO> recomendarPorTipoYArea(String tipoRecurso, String areaTematica) {
        List<RecursoDTO> lista = mapper.fromCollectionList(
                repository.findByTipoRecursoAndAreaTematica(tipoRecurso, areaTematica)
        );
        return lista;
    }

    @Override
    public String devolverRecurso(String id) {
        Recurso recurso = repository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontro el Recurso: [" + id + "]")
        );

        if(recurso.getIsPrestado()){
            recurso.setFechaPrestamo(null);
            recurso.setIsPrestado(false);
            this.update(mapper.fromCollection(recurso));
            return "Recurso DEVUELTO correctamente";
        }

        return "El recurso no a sido prestado --> DISPONIBLE";
    }


}
