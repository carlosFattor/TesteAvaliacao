package com.fattor.apicampanha.dto;

import java.util.Date;

/**
 * Created by carlos on 23/07/17.
 */
public class NewTorcedor {

    private String nome;
    private String email;
    private Date nascimento;
    private Long timeFutebol;

    public NewTorcedor() {
    }

    public NewTorcedor(String nome, String email, Date nascimento, Long timeFutebol) {
        this.nome = nome;
        this.email = email;
        this.nascimento = nascimento;
        this.timeFutebol = timeFutebol;
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

    public Long getTimeFutebol() {
        return timeFutebol;
    }

    public void setTimeFutebol(Long timeFutebol) {
        this.timeFutebol = timeFutebol;
    }
}
