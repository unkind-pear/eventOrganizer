package bean;

import java.util.Date;

public class Compra {
    private int id;
    private Date data;
    private double valorTotal;
    private int idConta;
    private int idTipoIngresso;
    private int idEvento;

    public Compra(int id, Date data, double valorTotal, int idConta, int idTipoIngresso, int idEvento) {
        this.id = id;
        this.data = data;
        this.valorTotal = valorTotal;
        this.idConta = idConta;
        this.idTipoIngresso = idTipoIngresso;
        this.idEvento = idEvento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getIdTipoIngresso() {
        return idTipoIngresso;
    }

    public void setIdTipoIngresso(int idTipoIngresso) {
        this.idTipoIngresso = idTipoIngresso;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    @Override
    public String toString() {
        return "Compra [id=" + id + ", data=" + data + ", valorTotal=" + valorTotal + ", idConta=" + idConta
                + ", idTipoIngresso=" + idTipoIngresso + ", idEvento=" + idEvento + "]";
    }
}

