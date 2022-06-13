package co.com.sura.sofkau.apiMongoDB.services;

public interface IRecursoService {
    /*
     * Consultar disponibilidad de un recurso indicando en un mensaje si esta disponible o no.
     * En caso de no estar disponible presentar la fecha del préstamo actual del ultimo ejemplar.
     */
    public String isDisponible(String id);
}
