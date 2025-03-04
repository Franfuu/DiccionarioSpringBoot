package com.github.franfuu.springdiccionario.services;

import com.github.franfuu.springdiccionario.models.Palabra;
import com.github.franfuu.springdiccionario.repositories.PalabraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PalabraService {
    @Autowired
    private PalabraRepository palabraRepository;

    public List<Palabra> getAllPalabras() {
        List<Palabra> palabras = palabraRepository.findAll();
        if (palabras.size() > 0) {
            return palabras;
        } else {
            return new ArrayList<>();
        }
    }

}
