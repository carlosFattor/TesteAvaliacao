package com.fattor.apicampanha.dto;

import com.fattor.apicampanha.models.Campanha;
import com.fattor.apicampanha.models.Torcedor;

import java.util.Date;
import java.util.List;

/**
 * Created by carlos on 23/07/17.
 */
public class TorcedorInfo {

    private String nome;
    private String email;
    private Date nascimento;
    private String meuTime;
    private List<Campanha> campanhas;

    public TorcedorInfo(Torcedor torcedor) {
        this.nome = torcedor.getNome();
        this.email = torcedor.getEmail();
        this.nascimento = torcedor.getNascimento();
        this.meuTime = torcedor.getTimeCoracao().getNome();
        this.campanhas = torcedor.getCampanhas();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getMeuTime() {
        return meuTime;
    }

    public void setMeuTime(String meuTime) {
        this.meuTime = meuTime;
    }

    public List<Campanha> getCampanhas() {
        return campanhas;
    }

    public void setCampanhas(List<Campanha> campanhas) {
        this.campanhas = campanhas;
    }
}
