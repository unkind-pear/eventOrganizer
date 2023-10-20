package dao;

import bean.Evento;
import bean.Sala;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaDAO {
    private Connection connection;

    public SalaDAO() {
        connection = new FabricaConexoes().getConnection();
    }

    public int inserir(Sala sala) {
        int inseriu = 0;
        String sql = "INSERT INTO sala(id_evento, numero, andar, capacidade_maxima) VALUES (?, ?, ?, ?);";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, sala.getIdEvento());
            stmt.setInt(2, sala.getNumero());
            stmt.setInt(3, sala.getAndar());
            stmt.setInt(4, sala.getCapacidadeMaxima());
            inseriu = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inseriu;
    }
    
    public ArrayList<Sala> getSalasEvento(Evento ev) {
        String sql = "SELECT * FROM sala WHERE id_evento=?;";
        PreparedStatement stmt;
        Sala sala;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, ev.getId());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Sala> salas = new ArrayList<>();

            while (rs.next()) {
                sala = new Sala(
                        rs.getInt("id"),
                        rs.getInt("id_evento"),
                        rs.getInt("numero"),
                        rs.getInt("andar"),
                        rs.getInt("capacidade_maxima")
                );
                salas.add(sala);
            }

            rs.close();
            stmt.close();
            return salas;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Sala> getLista() {
        String sql = "SELECT * FROM sala;";
        PreparedStatement stmt;
        Sala sala;

        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Sala> salas = new ArrayList<>();

            while (rs.next()) {
                sala = new Sala(
                        rs.getInt("id"),
                        rs.getInt("id_evento"),
                        rs.getInt("numero"),
                        rs.getInt("andar"),
                        rs.getInt("capacidade_maxima")
                );
                salas.add(sala);
            }

            rs.close();
            stmt.close();
            return salas;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int remover(Sala sala) {
        int removeu = 0;
        String sql = "DELETE FROM sala WHERE id=?;";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, sala.getId());
            removeu = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return removeu;
    }

    public int alterar(Sala sala) {
        int alterou = 0;
        String sql = "UPDATE sala SET id_evento=?, numero=?, andar=?, capacidade_maxima=? WHERE id=?;";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, sala.getIdEvento());
            stmt.setInt(2, sala.getNumero());
            stmt.setInt(3, sala.getAndar());
            stmt.setInt(4, sala.getCapacidadeMaxima());
            stmt.setInt(5, sala.getId());
            alterou = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alterou;
    }
}
