package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bean.Conta;
import dao.ContaDAO;

public class criarConta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;

    private ContaDAO contag = new ContaDAO();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    criarConta frame = new criarConta();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public criarConta() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 549, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(70, 82, 114, 19);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(70, 111, 114, 19);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblCriarConta = new JLabel("Criar Conta");
        lblCriarConta.setFont(new Font("Dialog", Font.BOLD, 25));
        lblCriarConta.setBounds(210, 12, 159, 30);
        contentPane.add(lblCriarConta);

        JLabel lblNome_1 = new JLabel("Nome:");
        lblNome_1.setBounds(12, 84, 70, 15);
        contentPane.add(lblNome_1);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(12, 111, 70, 15);
        contentPane.add(lblSenha);

        JLabel lblSaldo = new JLabel("Saldo:");
        lblSaldo.setBounds(12, 138, 70, 15);
        contentPane.add(lblSaldo);

        JLabel lblDataNascimento = new JLabel("Data Nascimento:");
        lblDataNascimento.setBounds(12, 165, 134, 15);
        contentPane.add(lblDataNascimento);

        JLabel lblIdade = new JLabel("Idade:");
        lblIdade.setBounds(12, 192, 70, 15);
        contentPane.add(lblIdade);

        JLabel lblNomeNoCarto = new JLabel("Nome no Cartão:");
        lblNomeNoCarto.setBounds(12, 219, 119, 15);
        contentPane.add(lblNomeNoCarto);

        JLabel lblNomeNoCarto_1 = new JLabel("Número do Cartão:");
        lblNomeNoCarto_1.setBounds(12, 246, 134, 15);
        contentPane.add(lblNomeNoCarto_1);

        JLabel lblNomeNoCarto_2 = new JLabel("Número de Segurança do Cartão:");
        lblNomeNoCarto_2.setBounds(12, 273, 236, 15);
        contentPane.add(lblNomeNoCarto_2);

        JLabel lblNomeNoCarto_3 = new JLabel("Data de Validade do Cartão:");
        lblNomeNoCarto_3.setBounds(12, 300, 201, 15);
        contentPane.add(lblNomeNoCarto_3);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(70, 138, 114, 19);
        contentPane.add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(70, 190, 114, 19);
        contentPane.add(textField_3);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(136, 217, 144, 19);
        contentPane.add(textField_4);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(157, 244, 165, 19);
        contentPane.add(textField_5);

        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(255, 271, 114, 19);
        contentPane.add(textField_6);

        JButton btnCriar = new JButton("Criar");
        btnCriar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = textField.getText();
                    String senha = textField_1.getText();
                    double saldo = Double.parseDouble(textField_2.getText());

                    java.sql.Date dataNascimento = null;
                    java.sql.Date dataValidadeCartao = null;

                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                        java.util.Date parsedDataNascimento = dateFormat.parse(textField_8.getText());
                        dataNascimento = new java.sql.Date(parsedDataNascimento.getTime());

                        java.util.Date parsedDataValidadeCartao = dateFormat.parse(textField_7.getText());
                        dataValidadeCartao = new java.sql.Date(parsedDataValidadeCartao.getTime());
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                    int idade = Integer.parseInt(textField_3.getText());
                    String nomeCartao = textField_4.getText();
                    long numeroCartao = Long.parseLong(textField_5.getText());
                    int numeroSegurancaCartao = Integer.parseInt(textField_6.getText());

                    Conta c = new Conta(0, nome, senha, saldo, dataNascimento, idade, nomeCartao, numeroCartao, numeroSegurancaCartao, dataValidadeCartao);
                    contag.inserir(c);
                    
                    JOptionPane.showMessageDialog(null, "Conta criada com sucesso!", "Aviso", JOptionPane.PLAIN_MESSAGE);
             
                    dispose();
                }
                catch (Exception r) {
                    r.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Você não preencheu o formulário incorretamente. Use apenas números onde indicado, digite a data no formato: dia/mes/ano e escolha um nome que ainda não exsita", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnCriar.setBounds(410, 383, 117, 25);
        contentPane.add(btnCriar);

        textField_7 = new JTextField();
        textField_7.setColumns(10);
        textField_7.setBounds(220, 298, 102, 19);
        contentPane.add(textField_7);

        textField_8 = new JTextField();
        textField_8.setColumns(10);
        textField_8.setBounds(144, 163, 104, 19);
        contentPane.add(textField_8);
        
        JLabel lblNome_1_1 = new JLabel("(até 60 caracteres e único)");
        lblNome_1_1.setFont(new Font("Dialog", Font.ITALIC, 10));
        lblNome_1_1.setBounds(189, 84, 133, 15);
        contentPane.add(lblNome_1_1);
        
        JLabel lblNome_1_1_1 = new JLabel("(até 10 caracteres)");
        lblNome_1_1_1.setFont(new Font("Dialog", Font.ITALIC, 10));
        lblNome_1_1_1.setBounds(189, 113, 106, 15);
        contentPane.add(lblNome_1_1_1);
        
        JLabel lblNome_1_1_1_1 = new JLabel("(até 60 caracteres)");
        lblNome_1_1_1_1.setFont(new Font("Dialog", Font.ITALIC, 10));
        lblNome_1_1_1_1.setBounds(298, 220, 106, 15);
        contentPane.add(lblNome_1_1_1_1);
        
        JLabel lblNome_1_1_1_2 = new JLabel("(no formato dd/mm/yyyy)");
        lblNome_1_1_1_2.setFont(new Font("Dialog", Font.ITALIC, 10));
        lblNome_1_1_1_2.setBounds(255, 165, 133, 15);
        contentPane.add(lblNome_1_1_1_2);
        
        JLabel lblNome_1_1_1_1_1 = new JLabel("(use \".\" para números decimais)");
        lblNome_1_1_1_1_1.setFont(new Font("Dialog", Font.ITALIC, 10));
        lblNome_1_1_1_1_1.setBounds(189, 139, 165, 15);
        contentPane.add(lblNome_1_1_1_1_1);
        
        JLabel lblNome_1_1_1_2_1 = new JLabel("(no formato dd/mm/yyyy)");
        lblNome_1_1_1_2_1.setFont(new Font("Dialog", Font.ITALIC, 10));
        lblNome_1_1_1_2_1.setBounds(340, 301, 133, 15);
        contentPane.add(lblNome_1_1_1_2_1);
        
        JLabel lblNome_1_1_1_1_2 = new JLabel("(até 3 números)");
        lblNome_1_1_1_1_2.setFont(new Font("Dialog", Font.ITALIC, 10));
        lblNome_1_1_1_1_2.setBounds(381, 273, 106, 15);
        contentPane.add(lblNome_1_1_1_1_2);
    }
}
