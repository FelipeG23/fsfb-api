package co.global.fsfb.fsfbapi.repositories;

import co.global.fsfb.fsfbapi.models.CaOrdenesMedicas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author POLLO
 */
@Repository
public interface IOrdenMedicaRepository extends JpaRepository<CaOrdenesMedicas, Long>{
    
    @Query("select om from CaOrdenesMedicas om where om.pacPacRut = :pacPacRut order by om.ormIdOrdmNumero desc")
    List<CaOrdenesMedicas> getOrdenesMedica(@Param("pacPacRut") String pacPacRut);

}
