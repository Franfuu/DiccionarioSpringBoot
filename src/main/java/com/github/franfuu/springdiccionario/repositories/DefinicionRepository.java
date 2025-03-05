package com.github.franfuu.springdiccionario.repositories;

import com.github.franfuu.springdiccionario.models.Definicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DefinicionRepository extends JpaRepository<Definicion, Integer> {

    @Query("SELECT d FROM Definicion d WHERE d.palabra.id = ?1")
    List<Definicion> findByPalabraId(int id);

}