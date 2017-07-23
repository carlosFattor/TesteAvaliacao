package com.fattor.apicampanha.service;

import com.fattor.apicampanha.models.TimeFutebol;
import com.fattor.apicampanha.repository.TimeFutebolRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by carlos on 21/07/17.
 */
@Service
public class TimeFutebolService {

    @Autowired
    private TimeFutebolRep timeFutebolRep;

    public TimeFutebol findById(Long id) {
        return this.timeFutebolRep.findById(id);
    }
}
