package co.com.sura.sofkau.apiMongoDB.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document
public class Recurso {
    @Id
    private String id;
    private String titulo;
    private Boolean isPrestado;
    private LocalDate fechaPrestamo;
    private String tipoRecurso;
    private String areaTematica;
}
