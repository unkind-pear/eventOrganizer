package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.Conta;
import bean.Evento;
import dao.ContaDAO;
import dao.EventoDAO;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.util.List;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable eventoTable;
    private DefaultTableModel modeloTabela;
    
    private static EventoDAO eventog = new EventoDAO();
    private static ContaDAO contag = new ContaDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeScreen frame = new HomeScreen(contag.getLista().get(1));
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
	public HomeScreen(Conta CONTA_LOGADA) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 526);
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
		
		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Depositar frame = new Depositar(CONTA_LOGADA);
					frame.setVisible(true);
				} catch (Exception ef) {
					ef.printStackTrace();
				}
			}
		});
		btnDepositar.setBounds(323, 7, 117, 25);
		contentPane.add(btnDepositar);
		
		JLabel lblEventos = new JLabel("Eventos");
		lblEventos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblEventos.setBounds(309, 63, 117, 37);
		contentPane.add(lblEventos);
		
        String[] nomesColunas = {"Nome", "Descrição", "Data", "Capacidade Máxima", "Organizador"};
        modeloTabela = new DefaultTableModel(nomesColunas, 0);
	
        List<Evento> eventos = eventog.getLista();
        
        JComboBox<Object> eventoComboBox = new JComboBox<>(); // Create a JComboBox for Evento selection
        eventoComboBox.setBounds(297, 454, 200, 30);
        contentPane.add(eventoComboBox);

        // Preencher a tabela com dados de eventos e botões de ação
        for (Evento evento : eventos) {
            Object[] rowData = {evento.getNome(), evento.getDescricao(), evento.getData(),
                    evento.getCapacidadeMaxima(), evento.getIdOrganizador()};
            modeloTabela.addRow(rowData);
            eventoComboBox.addItem(evento.getNome());
        }

        JButton btnComprarIngresso = new JButton("Comprar Ingresso"); // JButton to get the selected Evento
        btnComprarIngresso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedEvento = (String) eventoComboBox.getSelectedItem();
                if (selectedEvento != null) {
                	
    				try {
    					ComprarIngresso frame = new ComprarIngresso(eventog.getEvento(selectedEvento), CONTA_LOGADA);
    					frame.setVisible(true);
    				} catch (Exception ef) {
    					ef.printStackTrace();
    				}
                }
            }
        });
        btnComprarIngresso.setBounds(509, 454, 183, 30);
        contentPane.add(btnComprarIngresso);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(12, 112, 708, 330);
        contentPane.add(scrollPane_1);
        
        eventoTable = new JTable(modeloTabela);
        scrollPane_1.setViewportView(eventoTable);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(222, 221, 218));
		panel.setBounds(0, 0, 731, 37);
		contentPane.add(panel);
	}
}
