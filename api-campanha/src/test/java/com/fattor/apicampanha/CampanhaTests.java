package com.fattor.apicampanha;

import com.fattor.apicampanha.models.Campanha;
import com.fattor.apicampanha.models.Cliente;
import com.fattor.apicampanha.models.TimeFutebol;
import com.fattor.apicampanha.repository.CampanhaRep;
import com.fattor.apicampanha.repository.ClienteRep;
import com.fattor.apicampanha.repository.TimeFutebolRep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Created by carlos on 21/07/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class CampanhaTests {

    Cliente cliente = new Cliente("Cliente 1");
    TimeFutebol time = new TimeFutebol("Palmeiras");

    Campanha campanha = new Campanha("Nova Campanha teste", new Date(), new Date(new Date().getTime() + (1000 * 60 * 60 * 24)));

    @Autowired
    CampanhaRep campanhaRep;

    @Autowired
    ClienteRep clienteRep;

    @Autowired
    TimeFutebolRep timeFutebolRep;

    @Test
    public void deveInserirNovaCampanhaCom1DiaVigencia() {
        Cliente cliente = clienteRep.save(this.cliente);
        System.out.println(this.campanha);
        TimeFutebol timeFutebol = timeFutebolRep.save(this.time);

        campanha.setCliente(cliente);
        campanha.setTimeFutebol(timeFutebol);
        Campanha campanha = campanhaRep.save(this.campanha);
        assertEquals(1, campanha.getVigencia().longValue());
    }

    @Test
    public void deveListarCampanhasComMesmoNome() {
        Cliente cliente = clienteRep.save(this.cliente);
        System.out.println(this.campanha);
        TimeFutebol timeFutebol = timeFutebolRep.save(this.time);

        campanha.setCliente(cliente);
        campanha.setTimeFutebol(timeFutebol);
        campanhaRep.save(this.campanha);
        List<Campanha> byNome = campanhaRep.findByNome("Nova Campanha teste");
        assertEquals(1, byNome.size());
    }
}
