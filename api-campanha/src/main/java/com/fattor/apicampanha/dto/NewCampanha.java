package com.fattor.apicampanha.dto;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by carlos on 21/07/17.
 */
public class NewCampanha {
    private String nome;
    private Date inicio;
    private Date fim;
    private Long timeFutebol;
    private Long cliente;

    public NewCampanha() {
    }

    public NewCampanha(String nome, Date inicio, Date fim, Long timeFutebol, Long cliente) {
        this.inicio = inicio;
        this.fim = fim;
        this.timeFutebol = timeFutebol;
        this.cliente = cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Long getTimeFutebol() {
        return timeFutebol;
    }

    public void setTimeFutebol(Long timeFutebol) {
        this.timeFutebol = timeFutebol;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "NewCampanha{" +
                "nome='" + nome + '\'' +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", timeFutebol=" + timeFutebol +
                ", cliente=" + cliente +
                '}';
    }
}
