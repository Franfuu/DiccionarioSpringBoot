package com.github.franfuu.springdiccionario.services;

import com.github.franfuu.springdiccionario.repositories.DefinicionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DefinicionService {
    @Autowired
    private DefinicionRepository definicionRepository;
}
