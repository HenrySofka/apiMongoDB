package co.com.sura.sofkau.apiMongoDB.repositories;

import co.com.sura.sofkau.apiMongoDB.models.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IRecursoRepository extends MongoRepository<Recurso, String> {
    //CRUD por defecto
    public List<Recurso> findByTipoRecurso(String tipoRecurso);
    public List<Recurso> findByAreaTematica(String areaTematica);
    public List<Recurso> findByTipoRecursoAndAreaTematica(String tipoRecurso, String areaTematica);



}
