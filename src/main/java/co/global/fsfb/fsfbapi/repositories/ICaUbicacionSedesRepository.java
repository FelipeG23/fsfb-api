/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.global.fsfb.fsfbapi.repositories;

import co.global.fsfb.fsfbapi.models.CaUbicacionSedes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author julio
 */
public interface ICaUbicacionSedesRepository extends JpaRepository<CaUbicacionSedes, String>{

    @Query("SELECT u FROM CaUbicacionSedes u WHERE TRIM(u.ubicacion) = :ubicacion")
    List<CaUbicacionSedes> findDistinctByUbicacion(@Param("ubicacion") String ubicacion);
}
