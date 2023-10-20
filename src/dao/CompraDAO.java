package dao;

import bean.Compra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CompraDAO {
    private Connection connection;

    public CompraDAO() {
        connection = new FabricaConexoes().getConnection();
    }

    public int inserir(Compra compra) {
        int inseriu = 0;
        String sql = "INSERT INTO compra(data, valor_total, id_conta, id_tipo_ingresso, id_evento) " +
                     "VALUES (?, ?, ?, ?, ?);";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setTimestamp(1, new java.sql.Timestamp(compra.getData().getTime()));
            stmt.setDouble(2, compra.getValorTotal());
            stmt.setInt(3, compra.getIdConta());
            stmt.setInt(4, compra.getIdTipoIngresso());
            stmt.setInt(5, compra.getIdEvento());
            inseriu = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inseriu;
    }

    public ArrayList<Compra> getLista() {
        String sql = "SELECT * FROM compra;";
        PreparedStatement stmt;
        Compra compra;

        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Compra> compras = new ArrayList<>();

            while (rs.next()) {
                compra = new Compra(
                        rs.getInt("id"),
                        new Date(rs.getTimestamp("data").getTime()),
                        rs.getDouble("valor_total"),
                        rs.getInt("id_conta"),
                        rs.getInt("id_tipo_ingresso"),
                        rs.getInt("id_evento")
                );
                compras.add(compra);
            }

            rs.close();
            stmt.close();
            return compras;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public ArrayList<Compra> getListaConta(int id) {
        String sql = "SELECT * FROM compra WHERE id_conta=?;";
        PreparedStatement stmt;
        Compra compra;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Compra> compras = new ArrayList<>();

            while (rs.next()) {
                compra = new Compra(
                        rs.getInt("id"),
                        new Date(rs.getTimestamp("data").getTime()),
                        rs.getDouble("valor_total"),
                        rs.getInt("id_conta"),
                        rs.getInt("id_tipo_ingresso"),
                        rs.getInt("id_evento")
                );
                compras.add(compra);
            }

            rs.close();
            stmt.close();
            return compras;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int remover(Compra compra) {
        int removeu = 0;
        String sql = "DELETE FROM compra WHERE id=?;";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, compra.getId());
            removeu = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return removeu;
    }

    public int alterar(Compra compra) {
        int alterou = 0;
        String sql = "UPDATE compra SET data=?, valor_total=?, id_conta=?, id_tipo_ingresso=?, id_evento=? WHERE id=?;";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setTimestamp(1, new java.sql.Timestamp(compra.getData().getTime()));
            stmt.setDouble(2, compra.getValorTotal());
            stmt.setInt(3, compra.getIdConta());
            stmt.setInt(4, compra.getIdTipoIngresso());
            stmt.setInt(5, compra.getIdEvento());
            stmt.setInt(6, compra.getId());
            alterou = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alterou;
    }
}

