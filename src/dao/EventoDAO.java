package dao;

import bean.Evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventoDAO {
    private Connection connection;

    public EventoDAO() {
        connection = new FabricaConexoes().getConnection();
    }

    public int inserir(Evento evento) {
        int inseriu = 0;
        String sql = "INSERT INTO evento(nome, descricao, data, capacidade_maxima, id_organizador) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, evento.getNome());
            stmt.setString(2, evento.getDescricao());
            stmt.setDate(3, evento.getData());
            stmt.setInt(4, evento.getCapacidadeMaxima());
            stmt.setInt(5, evento.getIdOrganizador());
            inseriu = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inseriu;
    }
    
    public Evento getEvento(String n) {
        String sql = "SELECT * FROM evento WHERE nome=?;";
        PreparedStatement stmt;
        Evento evento = null;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, n);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                evento = new Evento(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDate("data"),
                        rs.getInt("capacidade_maxima"),
                        rs.getInt("id_organizador")
                );
            }

            rs.close();
            stmt.close();
            return evento;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Evento> getLista() {
        String sql = "SELECT * FROM evento;";
        PreparedStatement stmt;
        Evento evento;

        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Evento> eventos = new ArrayList<>();

            while (rs.next()) {
                evento = new Evento(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDate("data"),
                        rs.getInt("capacidade_maxima"),
                        rs.getInt("id_organizador")
                );
                eventos.add(evento);
            }

            rs.close();
            stmt.close();
            return eventos;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int remover(Evento evento) {
        int removeu = 0;
        String sql = "DELETE FROM evento WHERE id=?;";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, evento.getId());
            removeu = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return removeu;
    }

    public int alterar(Evento evento) {
        int alterou = 0;
        String sql = "UPDATE evento SET nome=?, descricao=?, data=?, capacidade_maxima=?, id_organizador=? WHERE id=?;";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, evento.getNome());
            stmt.setString(2, evento.getDescricao());
            stmt.setDate(3, evento.getData());
            stmt.setInt(4, evento.getCapacidadeMaxima());
            stmt.setInt(5, evento.getIdOrganizador());
            stmt.setInt(6, evento.getId());
            alterou = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alterou;
    }
}
