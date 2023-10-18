package bean;

public class Sala {
    private int id;
    private int idEvento;
    private int numero;
    private int andar;
    private int capacidadeMaxima;

    public Sala(int id, int idEvento, int numero, int andar, int capacidadeMaxima) {
        this.id = id;
        this.idEvento = idEvento;
        this.numero = numero;
        this.andar = andar;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    @Override
    public String toString() {
        return "Sala [id=" + id + ", idEvento=" + idEvento + ", numero=" + numero + ", andar=" + andar
                + ", capacidadeMaxima=" + capacidadeMaxima + "]";
    }
}
