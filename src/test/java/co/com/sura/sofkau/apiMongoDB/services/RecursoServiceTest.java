package co.com.sura.sofkau.apiMongoDB.services;

import co.com.sura.sofkau.apiMongoDB.mappers.RecursoMapper;
import co.com.sura.sofkau.apiMongoDB.models.Recurso;
import co.com.sura.sofkau.apiMongoDB.repositories.IRecursoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class RecursoServiceTest {

    @MockBean
    private IRecursoRepository repository;

    @Autowired
    private RecursoService service;


    private RecursoMapper mapper = new RecursoMapper();

    @Test
    @DisplayName("Recurso Disponible")
    void isDisponibleTest() {
        //Arrange
        Recurso recurso = this.getRecursoDisponible();

        //Act
        Mockito.when(repository.findById("1111")).thenReturn(Optional.of(recurso));
        var resultado = service.isDisponible("1111");
        //Assert

        Assertions.assertEquals("El Recurso esta DISPONIBLE!!", resultado);
    }

    @Test
    @DisplayName("Prestar un Recurso")
    void prestarLibroTest() {
        //Arrange
        Recurso recurso = this.getRecursoDisponible();

        //Act
        Mockito.when(repository.findById("2222")).thenReturn(Optional.of(recurso));
        Mockito.when(repository.save(any())).thenReturn(recurso);

        var resultado = service.prestarLibro("2222");
        //Assert

        Assertions.assertEquals(
                "Recurso DISPONIBLE --> Fecha de prestamo[" + recurso.getFechaPrestamo() + "]",
                resultado
        );
    }

    @Test
    @DisplayName("Recomendar Recursos por Tipo de Recurso")
    void recomendarPorTipoRecursoTest() {
        //Arrange
        List<Recurso> lista = this.getRecursoList();

        //Act
        Mockito.when(repository.findByTipoRecurso("Manga")).thenReturn(lista);

        var resultado = service.recomendarPorTipoRecurso("Manga");

        //Assert
        Assertions.assertEquals(lista.get(0).getTitulo(), resultado.get(0).getTitulo());
        Assertions.assertEquals(lista.get(0).getId(), resultado.get(0).getId());
        Assertions.assertEquals(lista.get(0).getTipoRecurso(), resultado.get(0).getTipoRecurso());
        Assertions.assertEquals(lista.get(1).getTitulo(), resultado.get(1).getTitulo());
        Assertions.assertEquals(lista.get(1).getId(), resultado.get(1).getId());
        Assertions.assertEquals(lista.get(1).getId(), resultado.get(1).getId());
    }

    @Test
    @DisplayName("Recomendar Recursos por Área Temática")
    void recomendarPorAreatematica() {
        //Arrange
        List<Recurso> lista = this.getRecursoList();

        //Act
        Mockito.when(repository.findByAreaTematica("Aventura")).thenReturn(lista);

        var resultado = service.recomendarPorAreatematica("Aventura");

        //Assert
        Assertions.assertEquals(lista.get(0).getTitulo(), resultado.get(0).getTitulo());
        Assertions.assertEquals(lista.get(0).getId(), resultado.get(0).getId());
        Assertions.assertEquals(lista.get(0).getAreaTematica(), resultado.get(0).getAreaTematica());
        Assertions.assertEquals(lista.get(1).getTitulo(), resultado.get(1).getTitulo());
        Assertions.assertEquals(lista.get(1).getId(), resultado.get(1).getId());
        Assertions.assertEquals(lista.get(1).getAreaTematica(), resultado.get(1).getAreaTematica());
    }

    @Test
    @DisplayName("Recomendar Recursos por Tipo de Recurso y Área Temática")
    void recomendarPorTipoYArea() {
        //Arrange
        List<Recurso> lista = this.getRecursoList();

        //Act
        Mockito.when(repository.findByTipoRecursoAndAreaTematica("Manga", "Aventura")).thenReturn(lista);

        var resultado = service.recomendarPorTipoYArea("Manga", "Aventura");

        //Assert
        Assertions.assertEquals(lista.get(0).getTitulo(), resultado.get(0).getTitulo());
        Assertions.assertEquals(lista.get(0).getId(), resultado.get(0).getId());
        Assertions.assertEquals(lista.get(0).getAreaTematica(), resultado.get(0).getAreaTematica());
        Assertions.assertEquals(lista.get(1).getTitulo(), resultado.get(1).getTitulo());
        Assertions.assertEquals(lista.get(1).getId(), resultado.get(1).getId());
        Assertions.assertEquals(lista.get(1).getAreaTematica(), resultado.get(1).getAreaTematica());
    }

    @Test
    @DisplayName("Devolver un Recurso")
    void devolverRecurso() {
        //Arrange
        Recurso recurso = this.getRecursoPrestado();

        //Act
        Mockito.when(repository.findById("1111")).thenReturn(Optional.of(recurso));
        Mockito.when(repository.save(any())).thenReturn(recurso);

        var resultado = service.devolverRecurso("1111");
        //Assert

        Assertions.assertEquals("Recurso DEVUELTO correctamente", resultado);
    }

    private Recurso getRecursoPrestado() {
        Recurso recurso = new Recurso();
        recurso.setId("1111");
        recurso.setIsPrestado(true);
        recurso.setFechaPrestamo(LocalDate.of(2022, 06, 14));
        recurso.setTitulo("Konosuba");
        recurso.setTipoRecurso("Manga");
        recurso.setAreaTematica("Aventura");
        return recurso;
    }

    private Recurso getRecursoDisponible() {
        Recurso recurso = new Recurso();
        recurso.setId("2222");
        recurso.setIsPrestado(false);
        recurso.setFechaPrestamo(LocalDate.of(2022, 06, 14));
        recurso.setTitulo("Tate no yuusha");
        recurso.setTipoRecurso("Manga");
        recurso.setAreaTematica("Aventura");
        return recurso;
    }

    private List<Recurso> getRecursoList() {
        List<Recurso> lista = new ArrayList<>();
        lista.add(getRecursoDisponible());
        lista.add(getRecursoPrestado());
        return lista;
    }

}