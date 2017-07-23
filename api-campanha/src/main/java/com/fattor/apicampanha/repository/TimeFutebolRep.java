package com.fattor.apicampanha.repository;

import com.fattor.apicampanha.models.TimeFutebol;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by carlos on 21/07/17.
 */
public interface TimeFutebolRep extends CrudRepository<TimeFutebol, Long> {

    TimeFutebol findById(long id);
}
