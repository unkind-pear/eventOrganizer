package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.Conta;
import dao.ContaDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	
	ContaDAO contag = new ContaDAO();
	Conta CONTA_LOGADA;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Nome;
	private JTextField textField_Senha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bem Vindo ao [NOME]!");
		lblNewLabel.setBounds(142, 10, 156, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Preencha os dados abaixo para entrar na sua conta:");
		lblNewLabel_1.setBounds(33, 30, 374, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(142, 113, 45, 15);
		contentPane.add(lblNome);
		
		textField_Nome = new JTextField();
		textField_Nome.setBounds(197, 111, 114, 19);
		contentPane.add(textField_Nome);
		textField_Nome.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(142, 140, 50, 15);
		contentPane.add(lblSenha);
		
		textField_Senha = new JTextField();
		textField_Senha.setBounds(197, 138, 114, 19);
		contentPane.add(textField_Senha);
		textField_Senha.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CONTA_LOGADA = contag.pegar(textField_Nome.getText(), textField_Senha.getText());
				if (CONTA_LOGADA == null) {
		            JOptionPane.showMessageDialog(null, "Conta não encontrada. Verifique o nome de usuário e senha.", "Aviso", JOptionPane.WARNING_MESSAGE);
		        } else {
		            JFrame novaJanela = new JFrame("Janela da Conta");
		            novaJanela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		            
		            JLabel label = new JLabel("Bem-vindo, " + CONTA_LOGADA.getNome());
		            novaJanela.add(label);
		            
		            novaJanela.pack();
		            novaJanela.setVisible(true);
		        }
			}
		});
		btnLogin.setBounds(197, 169, 73, 25);
		contentPane.add(btnLogin);
		
		JButton btnCriarConta = new JButton("Não tem uma conta? Criar conta");
		btnCriarConta.setBounds(101, 233, 260, 25);
		contentPane.add(btnCriarConta);
	}

}
