package co.com.sura.sofkau.apiMongoDB.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecursoDTO {
    private String id;
    private String titulo;
    private Boolean isPrestado;
    private LocalDate fechaPrestamo;
    private String tipoRecurso;
    private String areaTematica;
}
