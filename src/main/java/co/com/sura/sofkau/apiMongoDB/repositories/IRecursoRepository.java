package co.com.sura.sofkau.apiMongoDB.repositories;

import co.com.sura.sofkau.apiMongoDB.models.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IRecursoRepository extends MongoRepository<Recurso, String> {
    //CRUD por defecto
}
