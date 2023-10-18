package bean;

import java.sql.Date;

public class Evento {
    private int id;
    private String nome;
    private String descricao;
    private Date data;
    private int capacidadeMaxima;
    private int idOrganizador;

    public Evento(int id, String nome, String descricao, Date data, int capacidadeMaxima, int idOrganizador) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.capacidadeMaxima = capacidadeMaxima;
        this.idOrganizador = idOrganizador;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public int getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(int idOrganizador) {
        this.idOrganizador = idOrganizador;
    }

    @Override
    public String toString() {
        return "Evento [id=" + id + ", nome=" + nome + ", descricao=" + descricao
                + ", data=" + data + ", capacidadeMaxima=" + capacidadeMaxima
                + ", idOrganizador=" + idOrganizador + "]";
    }
}
