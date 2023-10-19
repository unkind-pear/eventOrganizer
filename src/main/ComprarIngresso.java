package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.Conta;
import bean.Evento;
import bean.Sala;
import bean.TipoIngresso;
import dao.EventoDAO;
import dao.SalaDAO;
import dao.TipoIngressoDAO;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.TableModel;

public class ComprarIngresso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable eventosTable;
    private DefaultTableModel modeloTabela;
    
    private TipoIngressoDAO eventog = new TipoIngressoDAO();
    private SalaDAO salag = new SalaDAO();
    private JTable salasTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComprarIngresso frame = new ComprarIngresso();
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
	public ComprarIngresso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbUsuario = new JLabel("[user]");
		lbUsuario.setBounds(12, 12, 132, 15);
		contentPane.add(lbUsuario);
		
		JLabel lblSaldoTexto = new JLabel("Saldo:");
		lblSaldoTexto.setBounds(156, 12, 51, 15);
		contentPane.add(lblSaldoTexto);
		
		JLabel lblSaldoQtd = new JLabel("[saldo]");
		lblSaldoQtd.setBounds(206, 12, 99, 15);
		contentPane.add(lblSaldoQtd);
		
		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDepositar.setBounds(323, 7, 117, 25);
		contentPane.add(btnDepositar);
		
		JLabel lblIngressos = new JLabel("Ingressos");
		lblIngressos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblIngressos.setBounds(471, 63, 157, 37);
		contentPane.add(lblIngressos);
		
        String[] nomesColunas = {"Tipo", "Preço"};
        modeloTabela = new DefaultTableModel(nomesColunas, 0);
	
        JScrollPane scrollPane = new JScrollPane(eventosTable);

        List<TipoIngresso> eventos = eventog.getLista();
        
        JComboBox eventoComboBox = new JComboBox<>(); // Create a JComboBox for Evento selection
        eventoComboBox.setBounds(297, 454, 200, 30);
        contentPane.add(eventoComboBox);

        // Preencher a tabela com dados de eventos e botões de ação
        for (TipoIngresso evento : eventos) {
            Object[] rowData = {evento.getTipo(), evento.getPreco()};
            modeloTabela.addRow(rowData);
            eventoComboBox.addItem(evento.getTipo());
        }

        JButton btnComprarIngresso = new JButton("Comprar Ingresso"); // JButton to get the selected Evento
        btnComprarIngresso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedEvento = (String) eventoComboBox.getSelectedItem();
                if (selectedEvento != null) {
                    // Handle the selected Evento here
                    System.out.println("Selected Ingresso: " + selectedEvento);
                }
            }
        });
        btnComprarIngresso.setBounds(509, 454, 183, 30);
        contentPane.add(btnComprarIngresso);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(374, 112, 346, 330);
        contentPane.add(scrollPane_1);
        
        eventosTable = new JTable(modeloTabela);
		scrollPane_1.setViewportView(eventosTable);
       
        
        String[] nomesColunas2 = {"Número", "Andar", "Capacidade Máxima"};
        DefaultTableModel modeloTabela2 = new DefaultTableModel(nomesColunas2, 0);

        List<Sala> salas = salag.getLista();

        for (Sala evento : salas) {
            Object[] rowData = {evento.getNumero(), evento.getAndar(), evento.getCapacidadeMaxima()};
            modeloTabela2.addRow(rowData);
        }
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(222, 221, 218));
		panel.setBounds(0, 0, 731, 37);
		contentPane.add(panel);
		
		JLabel lblSalas = new JLabel("Salas");
		lblSalas.setFont(new Font("Dialog", Font.BOLD, 25));
		lblSalas.setBounds(142, 63, 76, 37);
		contentPane.add(lblSalas);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 112, 350, 330);
		contentPane.add(scrollPane_2);
		
		salasTable = new JTable(modeloTabela2);
		scrollPane_2.setViewportView(salasTable);
	}
}
