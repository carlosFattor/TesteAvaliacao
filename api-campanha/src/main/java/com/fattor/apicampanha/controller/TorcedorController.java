package com.fattor.apicampanha.controller;

import com.fattor.apicampanha.controller.utils.ValidateAndCreateTorcedor;
import com.fattor.apicampanha.dto.CampanhaInfo;
import com.fattor.apicampanha.dto.NewTorcedor;
import com.fattor.apicampanha.models.Campanha;
import com.fattor.apicampanha.models.TimeFutebol;
import com.fattor.apicampanha.models.Torcedor;
import com.fattor.apicampanha.service.CampanhaService;
import com.fattor.apicampanha.service.TimeFutebolService;
import com.fattor.apicampanha.service.TorcedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by carlos on 23/07/17.
 */
@RestController
@RequestMapping("/api/v1")
public class TorcedorController {

    String uri = "http://localhost:9000/api/v1/campanhas/time/";

    @Autowired
    TorcedorService torcedorService;

    @Autowired
    private TimeFutebolService timeFutebolService;

    @Autowired
    private CampanhaService campanhaService;

    @Autowired
    private ValidateAndCreateTorcedor validateAndCreateTorcedor;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/torcedores/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> createTorcedor(@RequestBody NewTorcedor newTorcedor) {
        Optional<Torcedor> torcedorCadastrado = Optional.ofNullable(this.torcedorService.findByEmail(newTorcedor.getEmail()));
        if(torcedorCadastrado.isPresent()){
            if(torcedorCadastrado.get().getTimeCoracao() != null && torcedorCadastrado.get().getCampanhas().size() <= 0){
                return getCampanhasByTime(newTorcedor);
            }
            return new ResponseEntity(HttpStatus.ALREADY_REPORTED);
        } else {
            return createNewTorcedor(newTorcedor);
        }
    }

    @RequestMapping(value = "/torcedores/{idTorcedor}/time/{idTime}/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> updateTorcedor(@PathVariable("idTorcedor") Long idTorcedor, @PathVariable("idTime") Long idTime) {
        Optional<Torcedor> torcedor = Optional.ofNullable(this.torcedorService.findById(idTorcedor));
        Optional<TimeFutebol> timeFutebol = Optional.ofNullable(this.timeFutebolService.findById(idTime));
        if(!torcedor.isPresent() || !timeFutebol.isPresent()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        torcedor.get().setTimeCoracao(timeFutebol.get());
        this.torcedorService.save(torcedor.get());
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/torcedores/{idTorcedor}/campanha/{idCampanha}/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> updateTorcedorCampanha(@PathVariable("idTorcedor") Long idTorcedor, @PathVariable("idCampanha") Long idCampanha) {
        Optional<Torcedor> torcedor = Optional.ofNullable(this.torcedorService.findById(idTorcedor));
        Optional<Campanha> campanha = Optional.ofNullable(this.campanhaService.findById(idCampanha));
        if(!torcedor.isPresent() || !campanha.isPresent()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        torcedor.get().getCampanhas().add(campanha.get());
        this.torcedorService.save(torcedor.get());
        return new ResponseEntity(HttpStatus.OK);
    }

    private ResponseEntity<?> createNewTorcedor(@RequestBody NewTorcedor newTorcedor) {
        if(newTorcedor.getTimeFutebol() != null){
            Optional<TimeFutebol> time = Optional.ofNullable(this.timeFutebolService.findById(newTorcedor.getTimeFutebol()));
            if(!time.isPresent()){
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            Torcedor torcedor = this.validateAndCreateTorcedor.createTorcedor(newTorcedor, time.get());

            this.torcedorService.save(torcedor);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            Torcedor torcedor = this.validateAndCreateTorcedor.createTorcedorWithoutTime(newTorcedor);

            this.torcedorService.save(torcedor);
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }

    private ResponseEntity<?> getCampanhasByTime(@RequestBody NewTorcedor newTorcedor) {
        ResponseEntity<CampanhaInfo[]> responseEntity = restTemplate.getForEntity(uri+newTorcedor.getTimeFutebol(), CampanhaInfo[].class);
        CampanhaInfo[] campanhas = responseEntity.getBody();
        return new ResponseEntity(campanhas, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/torcedores/", method = RequestMethod.GET)
    public ResponseEntity<?> listAllTorcedores() {
        List<Torcedor> torcedores = this.torcedorService.findAll();

        return new ResponseEntity(torcedores, HttpStatus.OK);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
