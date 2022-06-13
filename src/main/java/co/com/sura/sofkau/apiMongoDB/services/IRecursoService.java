package co.com.sura.sofkau.apiMongoDB.services;

import co.com.sura.sofkau.apiMongoDB.dto.RecursoDTO;

public interface IRecursoService {
    /*
    * Consultar disponibilidad de un recurso indicando en un mensaje si esta disponible o no.
    * En caso de no estar disponible presentar la fecha del préstamo actual del ultimo ejemplar.
    * */
    public String isDisponible(String id);

    /*
    * Prestar un recurso, se debe comprobar si esta prestado o no, indicarlo mediante un mensaje.
    * Si se encuentra disponible debemos marcarlo como prestado y registrar la fecha del
    * préstamo (no es necesario llevar el historia de prestamos).
    * */
    public String prestarLibro(String id);

}
