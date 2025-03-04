package com.github.franfuu.springdiccionario.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "palabra")
public class Palabra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String termino;
    private String categoriaGramatical;

    @OneToMany(mappedBy = "palabra")
    @JsonManagedReference
    //@JsonIgnore
    private Set<Definicion> definicions = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @Size(max = 255) @NotNull String getTermino() {
        return termino;
    }

    public void setTermino(@Size(max = 255) @NotNull String termino) {
        this.termino = termino;
    }

    public @Size(max = 50) @NotNull String getCategoriaGramatical() {
        return categoriaGramatical;
    }

    public void setCategoriaGramatical(@Size(max = 50) @NotNull String categoriaGramatical) {
        this.categoriaGramatical = categoriaGramatical;
    }

    public void setDefinicions(Set<Definicion> definicions) {
        this.definicions = definicions;
    }

}