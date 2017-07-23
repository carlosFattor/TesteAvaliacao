package com.fattor.apicampanha.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fattor.apicampanha.dto.NewCampanha;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by carlos on 21/07/17.
 */
@Entity
@Table(name="campanha", catalog = "apicampanhadb")
public class Campanha {

    private Long id;
    private String nome;
    private Long vigencia;
    private Date inicio;
    private Date fim;
    private Date atualizada;
    private TimeFutebol timeFutebol;
    private Cliente cliente;
    private List<Torcedor> torcedores;

    public Campanha() {
    }

    public Campanha(String nome, Date inicio, Date fim) {
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;

        //diferença em dias
        this.vigencia = calcVigencia();
        this.atualizada = new Date();
    }

    public Campanha updateCampanha(NewCampanha newCampanha) {
        this.nome = newCampanha.getNome();
        this.inicio = newCampanha.getInicio();
        this.fim = newCampanha.getFim();
        this.vigencia = calcVigencia();
        this.atualizada = new Date();
        return this;
    }

    private Long calcVigencia() {
        return (this.fim.getTime() - this.inicio.getTime())/(24 * 60 * 60 * 1000);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NOME")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "VIGENCIA")
    public Long getVigencia() {
        return vigencia;
    }

    public void setVigencia(Long vigencia) {
        this.vigencia = vigencia;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INICIO", nullable = false)
    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FIM", nullable = false)
    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ATUALIZADA", nullable = false)
    public Date getAtualizada() {
        return atualizada;
    }

    public void setAtualizada(Date atualizada) {
        this.atualizada = atualizada;
    }

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    public TimeFutebol getTimeFutebol() {
        return timeFutebol;
    }

    public void setTimeFutebol(TimeFutebol timeFutebol) {
        this.timeFutebol = timeFutebol;
    }

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @ManyToMany(mappedBy="campanhas", cascade = CascadeType.ALL)
    public List<Torcedor> getTorcedores() {
        return torcedores;
    }

    public void setTorcedores(List<Torcedor> torcedores) {
        this.torcedores = torcedores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campanha campanha = (Campanha) o;

        if (id != null ? !id.equals(campanha.id) : campanha.id != null) return false;
        if (vigencia != null ? !vigencia.equals(campanha.vigencia) : campanha.vigencia != null) return false;
        if (inicio != null ? !inicio.equals(campanha.inicio) : campanha.inicio != null) return false;
        if (fim != null ? !fim.equals(campanha.fim) : campanha.fim != null) return false;
        if (atualizada != null ? !atualizada.equals(campanha.atualizada) : campanha.atualizada != null) return false;
        if (timeFutebol != null ? !timeFutebol.equals(campanha.timeFutebol) : campanha.timeFutebol != null) return false;
        return cliente != null ? cliente.equals(campanha.cliente) : campanha.cliente == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (vigencia != null ? vigencia.hashCode() : 0);
        result = 31 * result + (inicio != null ? inicio.hashCode() : 0);
        result = 31 * result + (fim != null ? fim.hashCode() : 0);
        result = 31 * result + (atualizada != null ? atualizada.hashCode() : 0);
        result = 31 * result + (timeFutebol != null ? timeFutebol.hashCode() : 0);
        result = 31 * result + (cliente != null ? cliente.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Campanha{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", vigencia=" + vigencia +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", atualizada=" + atualizada +
                ", timeFutebol=" + timeFutebol +
                ", cliente=" + cliente +
                '}';
    }
}
