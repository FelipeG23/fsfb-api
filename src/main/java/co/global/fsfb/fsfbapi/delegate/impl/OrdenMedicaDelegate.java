package co.global.fsfb.fsfbapi.delegate.impl;

import co.global.fsfb.fsfbapi.delegate.IOrdenMedicaDelegate;
import co.global.fsfb.fsfbapi.dto.ConsultaOrdenMedicaDto;
import co.global.fsfb.fsfbapi.dto.OrdenMedicaDto;
import co.global.fsfb.fsfbapi.dto.ResultadoOrdenMedicaDto;
import co.global.fsfb.fsfbapi.services.IOrdenMedicaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author POLLO
 */
@Service
@Slf4j
public class OrdenMedicaDelegate implements IOrdenMedicaDelegate{
    
    @Autowired
    private IOrdenMedicaService ordenMedicaService;
    
    @Override
    public OrdenMedicaDto getOrdenMedica(String pacPacRut) {
        return ordenMedicaService.getOrdenMedica(pacPacRut);
    }

    @Override
    public OrdenMedicaDto createOrdenMedica(OrdenMedicaDto ordenMedicaDto) {
        
        ordenMedicaDto = ordenMedicaService.createOrdenMedica(ordenMedicaDto);
        
        ordenMedicaService.createTrazaOrdenMedica(ordenMedicaDto);
        
        return ordenMedicaDto;
    }

    @Override
    public List<ResultadoOrdenMedicaDto> getOrdenesMedicas(ConsultaOrdenMedicaDto consultaOrdenMedicaDto) {
        return ordenMedicaService.getOrdenesMedicas(consultaOrdenMedicaDto);
    }

    @Override
    public boolean validarGestionContinuidad(long ormIdOrdmNumero) {
        return ordenMedicaService.validarOrdenContinuidad(ormIdOrdmNumero);
    }

}
