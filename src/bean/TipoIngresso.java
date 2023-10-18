package bean;

public class TipoIngresso {
    private int id;
    private String tipo;
    private double preco;
    private int idEvento;

    public TipoIngresso(int id, String tipo, double preco, int idEvento) {
        this.id = id;
        this.tipo = tipo;
        this.preco = preco;
        this.idEvento = idEvento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    @Override
    public String toString() {
        return "TipoIngresso [id=" + id + ", tipo=" + tipo + ", preco=" + preco + ", idEvento=" + idEvento + "]";
    }
}

