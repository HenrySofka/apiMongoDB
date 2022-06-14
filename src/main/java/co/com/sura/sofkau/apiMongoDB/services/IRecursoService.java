package co.com.sura.sofkau.apiMongoDB.services;

import co.com.sura.sofkau.apiMongoDB.dto.RecursoDTO;

import java.util.List;

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


    /*
    * Recomendar un listado de recursos al usuario a partir del tipo de recurso, del área temática o
    * de los dos. Los recursos están clasificados por tipo de recurso (libros, revistas, fichas, etc)
    * pero también por área temática (ciencias, naturaleza, historia, etc).
    * */
    public List<RecursoDTO> recomendarPorTipoRecurso(String tipoRecurso);
    public List<RecursoDTO> recomendarPorAreatematica(String areaTematica);
    public List<RecursoDTO> recomendarPorTipoYArea(String tipoRecurso, String areaTematica);

}
