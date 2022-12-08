package com.api.odecasa.services.anuncios;

import com.api.odecasa.dtos.inquilinos.ListarInquilinoDTO;
import com.api.odecasa.models.anuncios.Anuncio;
import com.api.odecasa.models.inquilinos.Inquilino;
import com.api.odecasa.repositories.anuncios.IAnuncioRepository;
import com.api.odecasa.repositories.inquilinos.IInquilinoRepository;
import com.api.odecasa.services.inquilinos.InquilinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AnuncioService {

    @Autowired
    private IAnuncioRepository anuncioRepository;

    @Autowired
    private InquilinoService inquilinoService;

    public Anuncio save(Anuncio anuncio, UUID idInquilino) throws Exception {
        Optional<ListarInquilinoDTO> inquilino = inquilinoService.getById(idInquilino);
        if (inquilino != null){
            anuncio.setInquilino(new Inquilino(inquilino.get()));
            return anuncioRepository.save(anuncio);
        } else {
            throw new Exception("Id do inquilino inválido");
        }

    }
}
