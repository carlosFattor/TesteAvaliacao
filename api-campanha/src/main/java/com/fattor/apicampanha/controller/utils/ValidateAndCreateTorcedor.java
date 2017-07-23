package com.fattor.apicampanha.controller.utils;

import com.fattor.apicampanha.dto.NewTorcedor;
import com.fattor.apicampanha.models.TimeFutebol;
import com.fattor.apicampanha.models.Torcedor;
import org.springframework.stereotype.Component;

/**
 * Created by carlos on 23/07/17.
 */
@Component
public class ValidateAndCreateTorcedor {

    public Torcedor createTorcedor(NewTorcedor newTorcedor, TimeFutebol time) {

        Torcedor torcedor = new Torcedor(
                newTorcedor.getNome(),
                newTorcedor.getEmail(),
                newTorcedor.getNascimento(),
                time);

        return torcedor;
    }

    public Torcedor createTorcedorWithoutTime(NewTorcedor newTorcedor) {

        Torcedor torcedor = new Torcedor(
                newTorcedor.getNome(),
                newTorcedor.getEmail(),
                newTorcedor.getNascimento());

        return torcedor;
    }
}
