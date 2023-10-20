package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bean.Conta;
import dao.ContaDAO;

public class Depositar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private static ContaDAO cg = new ContaDAO();
    private JTextField textFieldValor;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Depositar frame = new Depositar(cg.getLista().get(1));
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
	public Depositar(Conta CONTA_LOGADA) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 299, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbUsuario = new JLabel(CONTA_LOGADA.getNome());
		lbUsuario.setBounds(12, 12, 132, 15);
		contentPane.add(lbUsuario);
		
		JLabel lblSaldoTexto = new JLabel("Saldo:");
		lblSaldoTexto.setBounds(156, 12, 51, 15);
		contentPane.add(lblSaldoTexto);
		
		JLabel lblSaldoQtd = new JLabel(Double.toString(CONTA_LOGADA.getSaldo()));
		lblSaldoQtd.setBounds(206, 12, 99, 15);
		contentPane.add(lblSaldoQtd);
		
		JLabel lblIngressos = new JLabel("Depositar");
		lblIngressos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblIngressos.setBounds(80, 49, 144, 37);
		contentPane.add(lblIngressos);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(222, 221, 218));
		panel.setBounds(0, 0, 731, 37);
		contentPane.add(panel);
		
		textFieldValor = new JTextField();
		textFieldValor.setBounds(130, 128, 114, 19);
		contentPane.add(textFieldValor);
		textFieldValor.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(61, 130, 51, 15);
		contentPane.add(lblValor);
		
		JButton btnNewButton = new JButton("Depositar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double valor = Double.parseDouble(textFieldValor.getText());
					CONTA_LOGADA.setSaldo(CONTA_LOGADA.getSaldo() + valor);
					cg.alterar(CONTA_LOGADA);
					lblSaldoQtd.setText(Double.toString(CONTA_LOGADA.getSaldo()));
				}
				catch (Exception r) {
					JOptionPane.showMessageDialog(null, "Você não preencheu o valor corretamente. Use apenas números", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(93, 159, 117, 25);
		contentPane.add(btnNewButton);
	}
}
