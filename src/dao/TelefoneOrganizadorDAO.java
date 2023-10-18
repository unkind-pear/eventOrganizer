package dao;

import bean.TelefoneOrganizador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TelefoneOrganizadorDAO {
    private Connection connection;

    public TelefoneOrganizadorDAO() {
        connection = new FabricaConexoes().getConnection();
    }

    public int inserir(TelefoneOrganizador telefoneOrganizador) {
        int inseriu = 0;
        String sql = "INSERT INTO telefone_organizador(id_organizador, telefone) VALUES (?, ?);";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, telefoneOrganizador.getIdOrganizador());
            stmt.setLong(2, telefoneOrganizador.getTelefone());
            inseriu = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inseriu;
    }

    public ArrayList<TelefoneOrganizador> getLista() {
        String sql = "SELECT * FROM telefone_organizador;";
        PreparedStatement stmt;
        TelefoneOrganizador telefoneOrganizador;

        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<TelefoneOrganizador> telefones = new ArrayList<>();

            while (rs.next()) {
                telefoneOrganizador = new TelefoneOrganizador(
                        rs.getInt("id"),
                        rs.getInt("id_organizador"),
                        rs.getLong("telefone")
                );
                telefones.add(telefoneOrganizador);
            }

            rs.close();
            stmt.close();
            return telefones;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int remover(TelefoneOrganizador telefoneOrganizador) {
        int removeu = 0;
        String sql = "DELETE FROM telefone_organizador WHERE id=?;";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, telefoneOrganizador.getId());
            removeu = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return removeu;
    }

    public int alterar(TelefoneOrganizador telefoneOrganizador) {
        int alterou = 0;
        String sql = "UPDATE telefone_organizador SET id_organizador=?, telefone=? WHERE id=?;";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, telefoneOrganizador.getIdOrganizador());
            stmt.setLong(2, telefoneOrganizador.getTelefone());
            stmt.setInt(3, telefoneOrganizador.getId());
            alterou = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alterou;
    }
}
