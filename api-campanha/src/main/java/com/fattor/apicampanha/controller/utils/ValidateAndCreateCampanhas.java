package com.fattor.apicampanha.controller.utils;

import com.fattor.apicampanha.dto.NewCampanha;
import com.fattor.apicampanha.models.Campanha;
import com.fattor.apicampanha.models.Cliente;
import com.fattor.apicampanha.models.TimeFutebol;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

/**
 * Created by carlos on 22/07/17.
 */
@Component
public class ValidateAndCreateCampanhas {

    public Campanha createCampanha(NewCampanha newCampanha, Cliente cliente, TimeFutebol timeFutebol) {
        Campanha campanha = new Campanha(newCampanha.getNome(), newCampanha.getInicio(), newCampanha.getFim());
        campanha.setTimeFutebol(timeFutebol);
        campanha.setCliente(cliente);
        return campanha;
    }

    public Campanha validateCampanhas(NewCampanha newCampanha, List<Campanha> campanhas, Cliente cliente, TimeFutebol timeFutebol) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(newCampanha.getFim());
        instance.add(Calendar.DATE, campanhas.size());
        newCampanha.setFim(instance.getTime());
        return createCampanha(newCampanha, cliente, timeFutebol);
    }
}
