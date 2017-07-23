package com.fattor.apicampanha.service;

import com.fattor.apicampanha.models.Campanha;
import com.fattor.apicampanha.models.TimeFutebol;
import com.fattor.apicampanha.repository.CampanhaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by carlos on 21/07/17.
 */
@Service
public class CampanhaService {

    @Autowired
    private CampanhaRep repository;

    public List<Campanha> findAll() {
        return (List<Campanha>) repository.findAll();
    }

    public Campanha findById(Long id) {
        return repository.findById(id);
    }

    public Campanha save (Campanha campanha) {
        return repository.save(campanha);
    }

    public List<Campanha> findCampanhaVigente() throws ParseException {
        Date vigencia = new Date();
        return repository.findByFimGreaterThanEqual(vigencia);
    }

    public List<Campanha> findByName(String nome) {
        return repository.findByNome(nome);
    }

    public boolean deleteById(Long id) {
        Campanha campanha = this.findById(id);
        if(campanha != null) {
            repository.delete(campanha);
            return true;
        }
        return false;
    }

    public List<Campanha> findByIdTime(TimeFutebol time) {
        return this.repository.findByTimeFutebol(time);
    }
}
