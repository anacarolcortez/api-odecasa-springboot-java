package com.api.odecasa.controllers.inquilinos;

import com.api.odecasa.dtos.inquilinos.AtualizarInquilinoDTO;
import com.api.odecasa.dtos.inquilinos.CadastrarInquilinoDTO;
import com.api.odecasa.dtos.inquilinos.ListarInquilinoDTO;
import com.api.odecasa.services.inquilinos.InquilinoService;
import com.api.odecasa.models.inquilinos.Inquilino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/inquilinos")
public class InquilinoController {

    @Autowired
    InquilinoService inquilinoService;

    @PostMapping
    public ResponseEntity<Object> cadastrarInquilino(@RequestBody @Valid CadastrarInquilinoDTO novoInquilino) {
        Inquilino inquilino = new Inquilino(novoInquilino);
        Inquilino cadastro = inquilinoService.save(inquilino);
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastro);
    }

    @GetMapping
    public ResponseEntity<Object> listarInquilinos(@PageableDefault(sort={"nome"}) Pageable page){
        Page<ListarInquilinoDTO> listagem = inquilinoService.getAll(page);
        return ResponseEntity.status(HttpStatus.OK).body(listagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultarUmInquilino(@PathVariable(value = "id") UUID id){
        Optional<ListarInquilinoDTO> inquilino = inquilinoService.getById(id);
        if (!inquilino.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inquilino não encontrado");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(inquilino);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarUmInquilino(@PathVariable(value = "id") UUID id){
        Optional<ListarInquilinoDTO> inquilino = inquilinoService.getById(id);
        if (!inquilino.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inquilino não encontrado");
        }
        inquilinoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cadastro excluído com sucesso");
    }

    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<Object> desativarRegistro(@PathVariable(value = "id") UUID id){
        Optional<ListarInquilinoDTO> inquilino = inquilinoService.getById(id);
        if (!inquilino.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inquilino não encontrado");
        }
        inquilinoService.desativarRegistro(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cadastro desativado com sucesso");
    }

    @PatchMapping()
    public ResponseEntity<Object> atualizarInquilino(@RequestBody @Valid AtualizarInquilinoDTO inquilinosDTO){
        Optional<ListarInquilinoDTO> inquilino = inquilinoService.getById(inquilinosDTO.getId());
        if (!inquilino.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inquilino não encontrado");
        }
        inquilinoService.atualizarCadastroInquilino(inquilinosDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Cadastro atualizado com sucesso");
    }

}
