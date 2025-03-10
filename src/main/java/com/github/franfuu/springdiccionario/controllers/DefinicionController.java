package com.github.franfuu.springdiccionario.controllers;

import com.github.franfuu.springdiccionario.exceptions.RecordNotFoundException;
import com.github.franfuu.springdiccionario.models.Definicion;
import com.github.franfuu.springdiccionario.services.DefinicionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/definicion")
public class DefinicionController {

    @Autowired
    private DefinicionService definicionService;

    @CrossOrigin
    @GetMapping("/{id}/definiciones")
    @Operation(summary = "Obtener todas las definiciones de una palabra")
    public ResponseEntity<List<Definicion>> getDefinicionesByPalabraId(@PathVariable Integer id) {
        List<Definicion> definiciones = definicionService.getDefinicionesByPalabraId(id);
        return ResponseEntity.ok(definiciones);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una definición por su id")
    public HttpStatus deleteDefinicionById(@PathVariable Integer id) throws RecordNotFoundException {
        definicionService.deleteDefinicion(id);
        return HttpStatus.ACCEPTED;
    }

}