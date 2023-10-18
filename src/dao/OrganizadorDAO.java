package dao;

import bean.Organizador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrganizadorDAO {
    private Connection connection;

    public OrganizadorDAO() {
        connection = new FabricaConexoes().getConnection();
    }

    public int inserir(Organizador organizador) {
        int inseriu = 0;
        String sql = "INSERT INTO organizador(CNPJ, nome, email) VALUES (?, ?, ?);";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, organizador.getCnpj());
            stmt.setString(2, organizador.getNome());
            stmt.setString(3, organizador.getEmail());
            inseriu = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inseriu;
    }

    public ArrayList<Organizador> getLista() {
        String sql = "SELECT * FROM organizador;";
        PreparedStatement stmt;
        Organizador organizador;

        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Organizador> organizadores = new ArrayList<>();

            while (rs.next()) {
                organizador = new Organizador(
                        rs.getInt("id"),
                        rs.getString("CNPJ"),
                        rs.getString("nome"),
                        rs.getString("email")
                );
                organizadores.add(organizador);
            }

            rs.close();
            stmt.close();
            return organizadores;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int remover(Organizador organizador) {
        int removeu = 0;
        String sql = "DELETE FROM organizador WHERE id=?;";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, organizador.getId());
            removeu = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return removeu;
    }

    public int alterar(Organizador organizador) {
        int alterou = 0;
        String sql = "UPDATE organizador SET CNPJ=?, nome=?, email=? WHERE id=?;";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, organizador.getCnpj());
            stmt.setString(2, organizador.getNome());
            stmt.setString(3, organizador.getEmail());
            stmt.setInt(4, organizador.getId());
            alterou = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alterou;
    }
}
