package dao;

import bean.TipoIngresso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipoIngressoDAO {
    private Connection connection;

    public TipoIngressoDAO() {
        connection = new FabricaConexoes().getConnection();
    }

    public int inserir(TipoIngresso tipoIngresso) {
        int inseriu = 0;
        String sql = "INSERT INTO tipo_ingresso(tipo, preco, id_evento) VALUES (?, ?, ?);";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, tipoIngresso.getTipo());
            stmt.setDouble(2, tipoIngresso.getPreco());
            stmt.setInt(3, tipoIngresso.getIdEvento());
            inseriu = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inseriu;
    }

    public ArrayList<TipoIngresso> getLista() {
        String sql = "SELECT * FROM tipo_ingresso;";
        PreparedStatement stmt;
        TipoIngresso tipoIngresso;

        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<TipoIngresso> tiposIngresso = new ArrayList<>();

            while (rs.next()) {
                tipoIngresso = new TipoIngresso(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getDouble("preco"),
                        rs.getInt("id_evento")
                );
                tiposIngresso.add(tipoIngresso);
            }

            rs.close();
            stmt.close();
            return tiposIngresso;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int remover(TipoIngresso tipoIngresso) {
        int removeu = 0;
        String sql = "DELETE FROM tipo_ingresso WHERE id=?;";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, tipoIngresso.getId());
            removeu = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return removeu;
    }

    public int alterar(TipoIngresso tipoIngresso) {
        int alterou = 0;
        String sql = "UPDATE tipo_ingresso SET tipo=?, preco=?, id_evento=? WHERE id=?;";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, tipoIngresso.getTipo());
            stmt.setDouble(2, tipoIngresso.getPreco());
            stmt.setInt(3, tipoIngresso.getIdEvento());
            stmt.setInt(4, tipoIngresso.getId());
            alterou = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alterou;
    }
}
