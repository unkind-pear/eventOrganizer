package dao;

import bean.Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContaDAO {
    private Connection connection;

    public ContaDAO() {
        connection = new FabricaConexoes().getConnection();
    }

    public int inserir(Conta conta) {
        int inseriu = 0;
        String sql = "INSERT INTO conta(nome, senha, saldo, data_nascimento, idade, nome_cartao, numero_cartao, numero_seguranca_cartao, data_validade_cartao) " +
                     "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, conta.getNome());
            stmt.setString(2, conta.getSenha());
            stmt.setDouble(3, conta.getSaldo());
            stmt.setDate(4, new java.sql.Date(conta.getDataNascimento().getTime()));
            stmt.setInt(5, conta.getIdade());
            stmt.setString(6, conta.getNomeCartao());
            stmt.setLong(7, conta.getNumeroCartao());
            stmt.setInt(8, conta.getNumeroSegurancaCartao());
            stmt.setDate(9, new java.sql.Date(conta.getDataValidadeCartao().getTime()));
            inseriu = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inseriu;
    }

    public ArrayList<Conta> getLista() {
        String sql = "SELECT * FROM conta;";
        PreparedStatement stmt;
        Conta conta;
        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Conta> contas = new ArrayList<>();
            while (rs.next()) {
                conta = new Conta(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("senha"),
                    rs.getDouble("saldo"),
                    rs.getDate("data_nascimento"),
                    rs.getInt("idade"),
                    rs.getString("nome_cartao"),
                    rs.getLong("numero_cartao"),
                    rs.getInt("numero_seguranca_cartao"),
                    rs.getDate("data_validade_cartao")
                );
                contas.add(conta);
            }
            rs.close();
            stmt.close();
            return contas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int remover(Conta conta) {
        int removeu = 0;
        String sql = "DELETE FROM conta WHERE id = ?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, conta.getId());
            removeu = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return removeu;
    }

    public int alterar(Conta conta) {
        int alterou = 0;
        String sql = "UPDATE conta SET nome=?, senha=?, saldo=?, data_nascimento=?, idade=?, nome_cartao=?, " +
                     "numero_cartao=?, numero_seguranca_cartao=?, data_validade_cartao=? WHERE id = ?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, conta.getNome());
            stmt.setString(2, conta.getSenha());
            stmt.setDouble(3, conta.getSaldo());
            stmt.setDate(4, new java.sql.Date(conta.getDataNascimento().getTime()));
            stmt.setInt(5, conta.getIdade());
            stmt.setString(6, conta.getNomeCartao());
            stmt.setLong(7, conta.getNumeroCartao());
            stmt.setInt(8, conta.getNumeroSegurancaCartao());
            stmt.setDate(9, new java.sql.Date(conta.getDataValidadeCartao().getTime()));
            stmt.setInt(10, conta.getId());
            alterou = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alterou;
    }
    
    public Conta pegar(String nome, String senha) {
        String sql = "SELECT * FROM conta WHERE nome=? AND senha=?;";
        PreparedStatement stmt;
        Conta conta = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                conta = new Conta(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("senha"),
                    rs.getDouble("saldo"),
                    rs.getDate("data_nascimento"),
                    rs.getInt("idade"),
                    rs.getString("nome_cartao"),
                    rs.getLong("numero_cartao"),
                    rs.getInt("numero_seguranca_cartao"),
                    rs.getDate("data_validade_cartao")
                );
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conta;
    }

}
