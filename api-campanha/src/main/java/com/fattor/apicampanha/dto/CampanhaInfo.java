package com.fattor.apicampanha.dto;

import com.fattor.apicampanha.models.Campanha;

import java.util.Date;

/**
 * Created by carlos on 22/07/17.
 */
public class CampanhaInfo {

    private String nome;
    private Long idTime;
    private Long vigencia;
    private Date inicio;
    private Date fim;

    public CampanhaInfo() {
    }

    public CampanhaInfo(Campanha campanha) {
        this.nome = campanha.getNome();
        this.idTime = campanha.getTimeFutebol().getId();
        this.vigencia = campanha.getVigencia();
        this.inicio = campanha.getInicio();
        this.fim = campanha.getFim();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public Long getVigencia() {
        return vigencia;
    }

    public void setVigencia(Long vigencia) {
        this.vigencia = vigencia;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }
}
