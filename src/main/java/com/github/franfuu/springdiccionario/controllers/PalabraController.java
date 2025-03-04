package com.github.franfuu.springdiccionario.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.franfuu.springdiccionario.models.Palabra;
import com.github.franfuu.springdiccionario.services.PalabraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/palabras")
public class PalabraController {

    @Autowired
    private PalabraService palabraService;
    //Get todas las palabras
    @GetMapping
    public ResponseEntity<List<Palabra>> getPalabras() {
        return ResponseEntity.ok(palabraService.getAllPalabras());
    }

    //Get una palabra especifica sin sus definiciones



}
