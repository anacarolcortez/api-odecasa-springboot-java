package com.api.odecasa.controllers.anuncios;

import com.api.odecasa.dtos.anuncios.CadastrarAnuncioDTO;
import com.api.odecasa.models.anuncios.Anuncio;
import com.api.odecasa.repositories.anuncios.IAnuncioRepository;
import com.api.odecasa.services.anuncios.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping("/{idInquilino}")
    public ResponseEntity cadastrarAnuncio(@PathVariable UUID idInquilino, @RequestBody @Valid CadastrarAnuncioDTO novoAnuncio) throws Exception {
        Anuncio anuncio = new Anuncio(novoAnuncio);
        Anuncio cadastro = anuncioService.save(anuncio, idInquilino);
        return ResponseEntity.status(201).body(cadastro);
    }

}
