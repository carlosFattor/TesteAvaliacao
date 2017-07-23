package com.fattor.apicampanha.repository;

import com.fattor.apicampanha.models.Cliente;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by carlos on 21/07/17.
 */
public interface ClienteRep extends CrudRepository<Cliente, Long> {

    Cliente findById(long id);
}
