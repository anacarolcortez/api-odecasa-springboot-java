package com.api.odecasa.services.inquilinos;

import com.api.odecasa.dtos.inquilinos.AtualizarInquilinoDTO;
import com.api.odecasa.dtos.inquilinos.ListarInquilinoDTO;
import com.api.odecasa.models.inquilinos.Inquilino;
import com.api.odecasa.repositories.inquilinos.IInquilinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class InquilinoService {

    @Autowired
    IInquilinoRepository inquilinosRepository;

    @Transactional
    public Inquilino save(Inquilino inquilino) {
        return inquilinosRepository.save(inquilino);
    }

    public Page<ListarInquilinoDTO> getAll(Pageable page) {
        return inquilinosRepository.findAll(page).map(ListarInquilinoDTO::new);
    }

    public Optional<ListarInquilinoDTO> getById(UUID id) {
        return inquilinosRepository.findById(id).map(ListarInquilinoDTO::new);
    }

    @Transactional
    public void delete(UUID id) {
        inquilinosRepository.deleteById(id);
    }

    @Transactional
    public void desativarRegistro(UUID id) {
        Inquilino inquilino = inquilinosRepository.getReferenceById(id);
        inquilino.desativarRegistro();
    }

    @Transactional
    public void atualizarCadastroInquilino(AtualizarInquilinoDTO inquilinoDTO) {
        Inquilino inquilino = inquilinosRepository.getReferenceById(inquilinoDTO.getId());
        inquilino.atualizarPeloId(inquilinoDTO);
    }
}
