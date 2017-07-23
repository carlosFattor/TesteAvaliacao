package com.fattor.apicampanha.repository;

import com.fattor.apicampanha.models.Torcedor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by carlos on 23/07/17.
 */
public interface TorcedorRep extends CrudRepository<Torcedor, Long> {
    Torcedor findByEmail(String email);

    Torcedor findById(Long idTorcedor);
}
