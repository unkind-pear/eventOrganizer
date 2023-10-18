package bean;

import java.sql.Date;

public class Conta {
    private int id;
    private String nome;
    private String senha;
    private double saldo;
    private Date dataNascimento;
    private int idade;
    private String nomeCartao;
    private long numeroCartao;
    private int numeroSegurancaCartao;
    private Date dataValidadeCartao;

    public Conta(int id, String nome, String senha, double saldo, Date dataNascimento, int idade, String nomeCartao, long numeroCartao, int numeroSegurancaCartao, Date dataValidadeCartao) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.saldo = saldo;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.nomeCartao = nomeCartao;
        this.numeroCartao = numeroCartao;
        this.numeroSegurancaCartao = numeroSegurancaCartao;
        this.dataValidadeCartao = dataValidadeCartao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public long getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(long numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public int getNumeroSegurancaCartao() {
        return numeroSegurancaCartao;
    }

    public void setNumeroSegurancaCartao(int numeroSegurancaCartao) {
        this.numeroSegurancaCartao = numeroSegurancaCartao;
    }

    public Date getDataValidadeCartao() {
        return dataValidadeCartao;
    }

    public void setDataValidadeCartao(Date dataValidadeCartao) {
        this.dataValidadeCartao = dataValidadeCartao;
    }

    @Override
    public String toString() {
        return "Conta [id=" + id + ", nome=" + nome + ", senha=" + senha
                + ", saldo=" + saldo + ", dataNascimento=" + dataNascimento
                + ", idade=" + idade + ", nomeCartao=" + nomeCartao
                + ", numeroCartao=" + numeroCartao + ", numeroSegurancaCartao=" + numeroSegurancaCartao
                + ", dataValidadeCartao=" + dataValidadeCartao + "]";
    }
}
