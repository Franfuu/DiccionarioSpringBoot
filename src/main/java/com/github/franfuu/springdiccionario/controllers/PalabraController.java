package com.github.franfuu.springdiccionario.controllers;

import com.github.franfuu.springdiccionario.exceptions.RecordNotFoundException;
import com.github.franfuu.springdiccionario.models.Definicion;
import com.github.franfuu.springdiccionario.models.Palabra;
import com.github.franfuu.springdiccionario.services.PalabraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/palabras")
@Tag(name = "Palabras", description = "API para la gestión de palabras")
public class PalabraController {

    @Autowired
    private PalabraService palabraService;

    @CrossOrigin
    @GetMapping
    @Operation(summary = "Obtener todas las palabras sin las definiciones")
    public ResponseEntity<List<Palabra>> findAll() {
        List<Palabra> list = palabraService.getAllPalabras();
        list.forEach(palabra -> palabra.setDefinicions(null));
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una palabra por su id")
    public ResponseEntity<Palabra> getPalabraById(@PathVariable Integer id) throws RecordNotFoundException {
        Palabra palabra = palabraService.getPalabraById(Long.valueOf(id));
        return new ResponseEntity<>(palabra, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    @Operation(summary = "Crear una palabra sin definiciones")
    public ResponseEntity<Palabra> createPalabra(@RequestBody Palabra palabra) {
        Palabra createdPalabra = palabraService.savePalabra(palabra);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPalabra);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una palabra")
    public ResponseEntity<Palabra> updatePalabra(@RequestBody Palabra updatedPalabra) throws RecordNotFoundException {
        Palabra palabraUpdated = palabraService.updatePalabra(Long.valueOf(updatedPalabra.getId()), updatedPalabra);
        return ResponseEntity.status(HttpStatus.OK).body(palabraUpdated);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una palabra por su id")
    public HttpStatus deletePalabraById(@PathVariable Integer id) throws RecordNotFoundException {
        palabraService.deletePalabra(Long.valueOf(id));
        return HttpStatus.ACCEPTED;
    }

    @CrossOrigin
    @PostMapping("/{id}/definiciones")
    @Operation(summary = "Añadir una definición a una palabra")
    public ResponseEntity<Definicion> addDefinicionToPalabra(@PathVariable Long id, @RequestBody Definicion definicion) throws RecordNotFoundException {
        Definicion newDefinicion = palabraService.addDefinicionToPalabra(id, definicion);
        return new ResponseEntity<>(newDefinicion, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/categoria/{categorias}")
    @Operation(summary = "Obtener palabras por categoría")
    public ResponseEntity<List<Palabra>> getPalabrasByCategoria(@PathVariable String categorias) {
        List<Palabra> palabras = palabraService.getPalabrasByCategoria(categorias);
        palabras.forEach(palabra -> palabra.setDefinicions(null));
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/inicial/{inicial}")
    @Operation(summary = "Obtener palabras por inicial")
    public ResponseEntity<List<Palabra>> getPalabrasByInicial(@PathVariable String inicial) {
        List<Palabra> palabras = palabraService.getPalabrasByInicial(inicial);
        palabras.forEach(palabra -> palabra.setDefinicions(null));
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/definiciones")
    @Operation(summary = "Crear una palabra con definiciones")
    public ResponseEntity<Palabra> createPalabraWithDefiniciones(@Valid @RequestBody Palabra palabra) {
        Palabra createdPalabra = palabraService.savePalabraAndDefinicion(palabra);
        return new ResponseEntity<Palabra>(createdPalabra, new HttpHeaders(), HttpStatus.OK);
    }

}