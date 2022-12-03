package com.api.odecasa.services.inquilinos;

import com.api.odecasa.dtos.inquilinos.AtualizarInquilinosDTO;
import com.api.odecasa.dtos.inquilinos.ListarInquilinosDTO;
import com.api.odecasa.models.inquilinos.Inquilinos;
import com.api.odecasa.repositories.inquilinos.IInquilinosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class InquilinosService {

    @Autowired
    IInquilinosRepository inquilinosRepository;

    @Transactional
    public Inquilinos save(Inquilinos inquilino) {
        return inquilinosRepository.save(inquilino);
    }

    public Page<ListarInquilinosDTO> getAll(Pageable page) {
        return inquilinosRepository.findAll(page).map(ListarInquilinosDTO::new);
    }

    public Optional<ListarInquilinosDTO> getById(UUID id) {
        return inquilinosRepository.findById(id).map(ListarInquilinosDTO::new);
    }

    @Transactional
    public void delete(UUID id) {
        inquilinosRepository.deleteById(id);
    }

    @Transactional
    public void desativarRegistro(UUID id) {
        Inquilinos inquilino = inquilinosRepository.getReferenceById(id);
        inquilino.desativarRegistro();
    }

    @Transactional
    public void atualizarCadastroInquilino(AtualizarInquilinosDTO inquilinoDTO) {
        Inquilinos inquilino = inquilinosRepository.getReferenceById(inquilinoDTO.getId());
        inquilino.atualizarPeloId(inquilinoDTO);
    }
}
