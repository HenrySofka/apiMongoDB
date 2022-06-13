package co.com.sura.sofkau.apiMongoDB.mappers;

import co.com.sura.sofkau.apiMongoDB.dto.RecursoDTO;
import co.com.sura.sofkau.apiMongoDB.models.Recurso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecursoMapper {

    //Convertir DTO a una Collection
    public Recurso fromDTO(RecursoDTO dto){
        Recurso recurso = new Recurso();
        recurso.setId(dto.getId());
        recurso.setTipoRecurso(dto.getTipoRecurso());
        recurso.setAreaTematica(dto.getAreaTematica());
        recurso.setFechaPrestamo(dto.getFechaPrestamo());
        recurso.setTitulo(dto.getTitulo());
        recurso.setIsPrestado(dto.getIsPrestado());
        return recurso;
    }

    //Convertir Collection a un DTO
    public RecursoDTO fromCollection(Recurso collection){
        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setTipoRecurso(collection.getTipoRecurso());
        recursoDTO.setAreaTematica(collection.getAreaTematica());
        recursoDTO.setId(collection.getId());
        recursoDTO.setTitulo(collection.getTitulo());
        recursoDTO.setFechaPrestamo(collection.getFechaPrestamo());
        recursoDTO.setIsPrestado(collection.getIsPrestado());
        return recursoDTO;
    }

    //Convertir una lista de Collection a una lista de DTO
    public List<RecursoDTO> fromCollectionList(List<Recurso> collections){
        if (collections == null){
            return null;
        }
        List<RecursoDTO> list = new ArrayList(collections.size());
        Iterator listTracks = collections.iterator();
        while (listTracks.hasNext()){
            Recurso recurso = (Recurso) listTracks.next();
            list.add(fromCollection(recurso));
        }
        return list;
    }

}
