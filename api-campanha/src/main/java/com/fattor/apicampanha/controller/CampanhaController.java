package com.fattor.apicampanha.controller;

import com.fattor.apicampanha.controller.utils.ValidateAndCreateCampanhas;
import com.fattor.apicampanha.dto.CampanhaInfo;
import com.fattor.apicampanha.dto.NewCampanha;
import com.fattor.apicampanha.models.Campanha;
import com.fattor.apicampanha.models.Cliente;
import com.fattor.apicampanha.models.TimeFutebol;
import com.fattor.apicampanha.service.CampanhaService;
import com.fattor.apicampanha.service.ClienteService;
import com.fattor.apicampanha.service.TimeFutebolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by carlos on 21/07/17.
 */

@RestController
@RequestMapping("/api/v1")
public class CampanhaController {

    public static final Logger logger = LoggerFactory.getLogger(CampanhaController.class);

    @Autowired
    private ValidateAndCreateCampanhas validateAndCreateCampanhas;

    @Autowired
    private CampanhaService campanhaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TimeFutebolService timeFutebolService;

    @RequestMapping(value = "/campanhas/", method = RequestMethod.GET)
    public ResponseEntity<?> listAllCampanhas() {
        try {
            List<Campanha> campanhas = campanhaService.findCampanhaVigente();
            if(campanhas.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

            List<CampanhaInfo> campanhaInfos = campanhas.stream().parallel().map(campanha -> new CampanhaInfo(campanha)).collect(Collectors.toList());
            return new ResponseEntity<>(campanhaInfos, HttpStatus.OK);
        } catch (ParseException e) {
            System.out.println(e.getCause());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/campanhas/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> listAllCampanhas(@PathVariable("id") long id) {
        Optional<Campanha> campanha = Optional.ofNullable(this.campanhaService.findById(id));
        if(campanha.isPresent()){
            CampanhaInfo campanhaInfo = new CampanhaInfo(campanha.get());
            return new ResponseEntity<>(campanhaInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/campanhas/time/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> listAllCampanhasByTime(@PathVariable("id") long id) {
        Optional<TimeFutebol> time = Optional.ofNullable(this.timeFutebolService.findById(id));
        if(!time.isPresent()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        Optional<List<Campanha>> campanhas = Optional.ofNullable(this.campanhaService.findByIdTime(time.get()));
        if(campanhas.isPresent()){
            List<CampanhaInfo> campanhaInfo = campanhas.get().stream().parallel().map(campanha -> new CampanhaInfo(campanha)).collect(Collectors.toList());
            return new ResponseEntity<>(campanhaInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/campanhas/", method = RequestMethod.POST)
    public ResponseEntity<?> createCampanha(@RequestBody NewCampanha newCampanha) {

        Optional<Cliente> cliente = Optional.ofNullable(this.clienteService.findById(newCampanha.getCliente()));
        Optional<TimeFutebol> timeFutebol = Optional.ofNullable(this.timeFutebolService.findById(newCampanha.getTimeFutebol()));

        if(!timeFutebol.isPresent() || !cliente.isPresent()) {
            logger.error("Unable to create a new campanha");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        List<Campanha> campanhas = campanhaService.findByName(newCampanha.getNome());

        if(campanhas.isEmpty()){
            Campanha campanha = this.validateAndCreateCampanhas.createCampanha(newCampanha, cliente.get(), timeFutebol.get());
            this.campanhaService.save(campanha);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            Campanha campanha = this.validateAndCreateCampanhas.validateCampanhas(newCampanha, campanhas, cliente.get(), timeFutebol.get());
            this.campanhaService.save(campanha);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/campanhas/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCampanha(@PathVariable("id") long idCampanha) {

        boolean deleteById = this.campanhaService.deleteById(idCampanha);
        if(deleteById) {
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/campanhas/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCampanha(@PathVariable("id") long idCampanha, @RequestBody NewCampanha newCampanha) {
        Optional<Campanha> campanha = Optional.ofNullable(this.campanhaService.findById(idCampanha));
        if(campanha.isPresent()) {
            Campanha _campanha = campanha.get().updateCampanha(newCampanha);
            this.campanhaService.save(_campanha);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
