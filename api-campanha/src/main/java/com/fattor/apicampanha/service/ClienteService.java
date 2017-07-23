package com.fattor.apicampanha.service;

import com.fattor.apicampanha.models.Cliente;
import com.fattor.apicampanha.repository.ClienteRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by carlos on 21/07/17.
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRep clienteRep;

    public Cliente findById (Long id) {
        return this.clienteRep.findById(id);
    }
}
