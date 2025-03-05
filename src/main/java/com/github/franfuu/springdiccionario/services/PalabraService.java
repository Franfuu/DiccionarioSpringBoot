package com.github.franfuu.springdiccionario.services;

import com.github.franfuu.springdiccionario.exceptions.RecordNotFoundException;
import com.github.franfuu.springdiccionario.models.Definicion;
import com.github.franfuu.springdiccionario.models.Palabra;
import com.github.franfuu.springdiccionario.repositories.DefinicionRepository;
import com.github.franfuu.springdiccionario.repositories.PalabraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PalabraService {

    @Autowired
    private PalabraRepository palabraRepository;
    @Autowired
    private DefinicionRepository definicionRepository;

    public List<Palabra> getAllPalabras() {
        List<Palabra> palabrasList = palabraRepository.findAll();
        if (palabrasList.size() > 0) {
            return palabrasList;
        } else {
            return new ArrayList<>();
        }
    }

    public Palabra getPalabraById(Long id) throws RecordNotFoundException {
        Optional<Palabra> palabra = palabraRepository.findById(id);
        if (palabra.isPresent()) {
            return palabra.get();
        } else {
            throw new RecordNotFoundException("No existe la palabra para el id: ", id);
        }
    }

    public Palabra savePalabra(Palabra palabra) {
        palabra = palabraRepository.save(palabra);
        return palabra;
    }

    public Palabra updatePalabra(Long id, Palabra palabra) throws RecordNotFoundException {
        if (palabra.getId() != null) {
            Optional<Palabra> palabraOptional = palabraRepository.findById(id);
            if (palabraOptional.isPresent()) {
                Palabra newPalabra = palabraOptional.get();
                newPalabra.setTermino(palabra.getTermino());
                newPalabra.setCategoriaGramatical(palabra.getCategoriaGramatical());
                newPalabra.setDefinicions(palabra.getDefinicions());
                newPalabra = palabraRepository.save(newPalabra);
                return newPalabra;
            } else {
                throw new RecordNotFoundException("No existe la palabra para el id: ", palabra.getId());
            }
        } else {
            throw new RecordNotFoundException("No hay id en la palabra a actualizar ", 0L);
        }
    }

    public void deletePalabra(Long id) throws RecordNotFoundException {
        Optional<Palabra> palabraOptional = palabraRepository.findById(id);
        if (palabraOptional.isPresent()) {
            palabraRepository.delete(palabraOptional.get());
        } else {
            throw new RecordNotFoundException("No existe la palabra para el id: ", id);
        }
    }

   public Definicion addDefinicionToPalabra(Long id, Definicion definicion) throws RecordNotFoundException {
        Palabra palabra = palabraRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Palabra no encontrada", id));
        definicion.setPalabra(palabra);
        return definicionRepository.save(definicion);
    }

    public List<Palabra> getPalabrasByCategoria(String categorias) {
        List<Palabra> palabras = palabraRepository.findAll();
        List<Palabra> filteredPalabras = new ArrayList<>();
        for (Palabra palabra : palabras) {
            if (palabra.getCategoriaGramatical().equalsIgnoreCase(categorias)) {
                filteredPalabras.add(palabra);
            }
        }
        return filteredPalabras;
    }


    public List<Palabra> getPalabrasByInicial(String inicial) {
        return palabraRepository.findByInicial(inicial);
    }

/*
    public Palabra savePalabraAndDefinicion(Palabra palabra) {
        palabra.getDefinicions().forEach(definicion -> definicion.setPalabra(palabra));
        return palabraRepository.save(palabra);
    }*/

    public Palabra savePalabraAndDefinicion(Palabra palabra) {
        Palabra savedPalabra = palabraRepository.save(palabra);
        for (Definicion definicion : palabra.getDefinicions()) {
            definicion.setPalabra(savedPalabra);
            definicionRepository.save(definicion);
        }
        return savedPalabra;
    }

}