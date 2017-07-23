package com.fattor.apicampanha.service;

import com.fattor.apicampanha.models.Torcedor;
import com.fattor.apicampanha.repository.TorcedorRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by carlos on 23/07/17.
 */
@Service
public class TorcedorService {

    @Autowired
    private TorcedorRep torcedorRe;

    public Torcedor save(Torcedor torcedor) {
        return this.torcedorRe.save(torcedor);
    }

    public Torcedor findByEmail(String email) {
        return this.torcedorRe.findByEmail(email);
    }

    public List<Torcedor> findAll() {
        return (List<Torcedor>) this.torcedorRe.findAll();
    }

    public Torcedor findById(Long idTorcedor) {
        return this.torcedorRe.findById(idTorcedor);
    }
}
