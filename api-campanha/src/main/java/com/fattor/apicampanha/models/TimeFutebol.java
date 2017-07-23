package com.fattor.apicampanha.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by carlos on 21/07/17.
 */
@Entity
@Table(name = "time_futebol", catalog = "apicampanhadb")
public class TimeFutebol implements java.io.Serializable {

    private Long id;
    private String nome;
    private Set<Campanha> campanhas = new HashSet<>(0);

    public TimeFutebol() {
    }

    public TimeFutebol(String nome) {
        this.nome = nome;
    }

    public TimeFutebol(Long id, String nome, Set<Campanha> campanhas) {
        this.id = id;
        this.nome = nome;
        this.campanhas = campanhas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NOME", nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "timeFutebol")
    public Set<Campanha> getCampanhas() {
        return campanhas;
    }

    public void setCampanhas(Set<Campanha> campanhas) {
        this.campanhas = campanhas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeFutebol time = (TimeFutebol) o;

        if (id != null ? !id.equals(time.id) : time.id != null) return false;
        return nome != null ? nome.equals(time.nome) : time.nome == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TimeFutebol{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
