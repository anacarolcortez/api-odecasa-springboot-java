package com.api.odecasa.inquilinos;

import com.api.odecasa.models.inquilinos.Inquilino;
import com.api.odecasa.services.inquilinos.InquilinoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InquilinoServiceTest {

    @Autowired
    private InquilinoService inquilinoService;

    @Test
    public void deveriaRetornarUmInquilinoComDadosCorretos(){
        Inquilino inquilinoTeste = new Inquilino();
        inquilinoTeste.setApto("71A");
        inquilinoTeste.setBio("Bruxa do 71");
        inquilinoTeste.setNome("Dona Clotilde");
        inquilinoTeste.setTelefone("(22) 98787-1223");
        inquilinoTeste.setFoto("http://urldafoto.com");

        var inquilinoSalvo = inquilinoService.save(inquilinoTeste);
        Assert.assertNotNull(inquilinoSalvo.getId());
    }

    @Test
    public void deveriaListarClientesAoInformarPageable(){
        var inquilinos = inquilinoService.getAll(Pageable.ofSize(10));
        Assert.assertTrue(inquilinos.stream().collect(Collectors.toList()).size() > 0);
    }

    @Test
    public void deveriaRetornarUsuarioExistenteNaConsulta(){
        var inquilino = inquilinoService.getById(UUID.fromString("32ce35fd-49d9-47f6-854f-4779966c552a"));
        System.out.println(inquilino);
        Assert.assertTrue(inquilino.isPresent());
        Assert.assertTrue(inquilino.get().getApto().equals("71A"));
    }
}
