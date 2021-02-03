package co.global.fsfb.fsfbapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.global.fsfb.fsfbapi.models.CaPrestacionesOrdMed;

public interface ICaPrestacionesOrdMed extends JpaRepository<CaPrestacionesOrdMed, Long>{
	
	List<CaPrestacionesOrdMed> findByOrmIdOrmdNumero(long ormIdOrmdNumero);

}
