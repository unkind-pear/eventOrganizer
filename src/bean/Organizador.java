package bean;

public class Organizador {
    private int id;
    private String cnpj;
    private String nome;
    private String email;

    public Organizador(int id, String cnpj, String nome, String email) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    @Override
    public String toString() {
        return "Organizador [id=" + id + ", CNPJ=" + cnpj + ", nome=" + nome + ", email=" + email + "]";
    }
}

