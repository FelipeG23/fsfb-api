package co.global.fsfb.fsfbapi.mappers;

import java.util.List;

/**
 * @param <T> Entity
 * @param <D> DTO
 * @author salajorg
 */
public interface IMapperGeneric<T, D> {

    T dtoToEntity(D dto);

    D entityToDto(T entity);

    List<D> entityToDto(List<T> entity);
    
    List<T> dtoToEntity(List<D> dto);

}
