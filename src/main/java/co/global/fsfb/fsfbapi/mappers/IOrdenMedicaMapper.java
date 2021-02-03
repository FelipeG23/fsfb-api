package co.global.fsfb.fsfbapi.mappers;

import co.global.fsfb.fsfbapi.dto.OrdenMedicaDto;
import co.global.fsfb.fsfbapi.models.CaOrdenesMedicas;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface IOrdenMedicaMapper extends IMapperGeneric<CaOrdenesMedicas, OrdenMedicaDto>{ 
}
