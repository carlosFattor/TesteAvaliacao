package com.fattor.apicampanha.repository;

import com.fattor.apicampanha.models.Campanha;
import com.fattor.apicampanha.models.TimeFutebol;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by carlos on 21/07/17.
 */
public interface CampanhaRep extends CrudRepository<Campanha, Long> {

    Campanha findById(long id);

    List<Campanha> findByFimGreaterThanEqual(Date date);
    List<Campanha> findByNome(String nome);

    List<Campanha> findByTimeFutebol(TimeFutebol time);
}
