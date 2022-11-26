package com.api.odecasa.inquilinos.services;

import com.api.odecasa.inquilinos.dtos.ListarInquilinosDTO;
import com.api.odecasa.inquilinos.models.InquilinosModel;
import com.api.odecasa.inquilinos.repositories.IInquilinosRepository;
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
    public InquilinosModel save(InquilinosModel inquilino) {
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
}
