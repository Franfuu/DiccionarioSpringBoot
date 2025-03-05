package com.github.franfuu.springdiccionario.services;

import com.github.franfuu.springdiccionario.models.Definicion;
import com.github.franfuu.springdiccionario.repositories.DefinicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.franfuu.springdiccionario.exceptions.RecordNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class DefinicionService {

    @Autowired
    private DefinicionRepository definicionRepository;

    public List<Definicion> getDefinicionesByPalabraId(Integer palabraId) {
        return definicionRepository.findByPalabraId(palabraId);
    }

    public void deleteDefinicion(Integer id) throws RecordNotFoundException {
        Optional<Definicion> definicionOptional = definicionRepository.findById(id);
        if (definicionOptional.isPresent()) {
            definicionRepository.delete(definicionOptional.get());
        } else {
            throw new RecordNotFoundException("No existe la definición para el id: ", id);
        }
    }
}