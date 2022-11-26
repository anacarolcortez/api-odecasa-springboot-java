package com.api.odecasa.inquilinos.controllers;

import com.api.odecasa.inquilinos.dtos.CadastrarInquilinosDTO;
import com.api.odecasa.inquilinos.dtos.ListarInquilinosDTO;
import com.api.odecasa.inquilinos.services.InquilinosService;
import com.api.odecasa.inquilinos.models.InquilinosModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/inquilinos")
public class InquilinosController {

    @Autowired
    InquilinosService inquilinosService;

    @PostMapping
    public ResponseEntity<Object> cadastrarInquilino(@RequestBody @Valid CadastrarInquilinosDTO novoInquilino){
        var inquilino = new InquilinosModel(novoInquilino);
        var cadastro = inquilinosService.save(inquilino);
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastro);
    }

    @GetMapping
    public ResponseEntity<Object> listarInquilinos(@PageableDefault(sort={"apto"}) Pageable page){
        var listagem = inquilinosService.getAll(page);
        return ResponseEntity.status(HttpStatus.OK).body(listagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultarUmInquilino(@PathVariable(value = "id") UUID id){
        Optional<ListarInquilinosDTO> inquilino = inquilinosService.getById(id);
        if (!inquilino.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inquilino não encontrado");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(inquilino);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarUmInquilino(@PathVariable(value = "id") UUID id){
        Optional<ListarInquilinosDTO> inquilino = inquilinosService.getById(id);
        if (!inquilino.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inquilino não encontrado");
        }
        inquilinosService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cadastro excluído com sucesso");
    }

}
