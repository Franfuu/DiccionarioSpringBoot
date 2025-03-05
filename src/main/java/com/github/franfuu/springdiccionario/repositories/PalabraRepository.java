package com.github.franfuu.springdiccionario.repositories;

import com.github.franfuu.springdiccionario.models.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, Long> {
    @Query("SELECT p FROM Palabra p WHERE p.termino LIKE ?1%")
    List<Palabra> findByInicial(String inicial);

}
