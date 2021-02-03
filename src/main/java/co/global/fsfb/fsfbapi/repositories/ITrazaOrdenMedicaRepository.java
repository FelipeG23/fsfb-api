package co.global.fsfb.fsfbapi.repositories;

import co.global.fsfb.fsfbapi.models.CaTrazaOrdenMedicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author POLLO
 */
@Repository
public interface ITrazaOrdenMedicaRepository extends JpaRepository<CaTrazaOrdenMedicas, Long>{}
