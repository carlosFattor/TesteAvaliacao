package com.fattor.apicampanha.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by carlos on 23/07/17.
 */
@Entity
@Table(name = "torcedor", catalog = "apicampanhadb")
public class Torcedor {

    private Long id;
    private String nome;
    private String email;
    private Date nascimento;
    private boolean ativo;
    private Date atualizado;
    private TimeFutebol timeCoracao;
    private List<Campanha> campanhas;

    public Torcedor() {
    }

    public Torcedor(String nome, String email, Date nascimento) {
        this.nome = nome;
        this.email = email;
        this.nascimento = nascimento;
        this.ativo = true;
        this.atualizado = new Date();
    }

    public Torcedor(String nome, String email, Date nascimento, TimeFutebol timeCoracao) {
        this.nome = nome;
        this.email = email;
        this.nascimento = nascimento;
        this.timeCoracao = timeCoracao;
        this.ativo = true;
        this.atualizado = new Date();
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

    @Column(name = "EMAIL", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "NASCIMENTO", nullable = false)
    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    @Column(name = "ATIVO", nullable = false)
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ATUALIZADO", nullable = false)
    public Date getAtualizado() {
        return atualizado;
    }

    public void setAtualizado(Date atualizado) {
        this.atualizado = atualizado;
    }

    @OneToOne(fetch = FetchType.EAGER)
    public TimeFutebol getTimeCoracao() {
        return timeCoracao;
    }

    public void setTimeCoracao(TimeFutebol timeCoracao) {
        this.timeCoracao = timeCoracao;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="CAMPANHA_TORCEDOR",
            joinColumns={@JoinColumn(name="CAMPANHA_ID")},
            inverseJoinColumns={@JoinColumn(name="TORCEDOR_ID")})
    public List<Campanha> getCampanhas() {
        return campanhas;
    }

    public void setCampanhas(List<Campanha> campanhas) {
        this.campanhas = campanhas;
    }

    @Override
    public String toString() {
        return "Torcedor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", nascimento=" + nascimento +
                ", ativo=" + ativo +
                ", atualizado=" + atualizado +
                '}';
    }
}
