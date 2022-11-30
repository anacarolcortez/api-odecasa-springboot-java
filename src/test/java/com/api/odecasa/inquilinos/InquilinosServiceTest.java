package com.api.odecasa.inquilinos;

import com.api.odecasa.inquilinos.models.InquilinosModel;
import com.api.odecasa.inquilinos.services.InquilinosService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InquilinosServiceTest {

    @Autowired
    private InquilinosService inquilinosService;

    @Test
    public void deveriaRetornarUmInquilinoComDadosCorretos(){
        InquilinosModel inquilinoTeste = new InquilinosModel();
        inquilinoTeste.setApto("71A");
        inquilinoTeste.setBio("Bruxa do 71");
        inquilinoTeste.setNome("Dona Clotilde");
        inquilinoTeste.setTelefone("(22) 98787-1223");
        inquilinoTeste.setFoto("http://urldafoto.com");

        var inquilinoSalvo = inquilinosService.save(inquilinoTeste);
        Assert.assertNotNull(inquilinoSalvo.getId());
    }

    @Test
    public void deveriaListarClientesAoInformarPageable(){
        var inquilinos = inquilinosService.getAll(Pageable.ofSize(10));
        Assert.assertTrue(inquilinos.stream().collect(Collectors.toList()).size() > 0);
    }

    @Test
    public void deveriaRetornarUsuarioExistenteNaConsulta(){
        var inquilino = inquilinosService.getById(UUID.fromString("32ce35fd-49d9-47f6-854f-4779966c552a"));
        System.out.println(inquilino);
        Assert.assertTrue(inquilino.isPresent());
        Assert.assertTrue(inquilino.get().getApto().equals("71A"));
    }
}
