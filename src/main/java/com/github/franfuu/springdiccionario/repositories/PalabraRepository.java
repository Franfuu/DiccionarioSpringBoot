package com.github.franfuu.springdiccionario.repositories;

import com.github.franfuu.springdiccionario.models.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, Long> {


}
