package bean;

public class TelefoneOrganizador {
    private int id;
    private int idOrganizador;
    private long telefone;

    public TelefoneOrganizador(int id, int idOrganizador, long telefone) {
        this.id = id;
        this.idOrganizador = idOrganizador;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(int idOrganizador) {
        this.idOrganizador = idOrganizador;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "TelefoneOrganizador [id=" + id + ", idOrganizador=" + idOrganizador + ", telefone=" + telefone + "]";
    }
}

