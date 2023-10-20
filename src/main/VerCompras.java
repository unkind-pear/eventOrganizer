package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.Compra;
import bean.Conta;
import bean.Evento;
import dao.CompraDAO;
import dao.ContaDAO;
import dao.EventoDAO;
import dao.TipoIngressoDAO;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerCompras extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable eventoTable;
    private DefaultTableModel modeloTabela;
    
    private static ContaDAO contag = new ContaDAO();
    private CompraDAO comprag = new CompraDAO();
    private EventoDAO eventog = new EventoDAO();
    private TipoIngressoDAO ingressog = new TipoIngressoDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerCompras frame = new VerCompras(contag.getLista().get(1));
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
	public VerCompras(Conta CONTA_LOGADA) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JLabel lblEventos = new JLabel("Suas Compras");
		lblEventos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblEventos.setBounds(271, 63, 200, 37);
		contentPane.add(lblEventos);
		
		// int id, Date data, double valorTotal, int idConta, int idTipoIngresso, int idEvento
        String[] nomesColunas = {"Data", "Valor", "Tipo do Ingresso", "Evento"};
        modeloTabela = new DefaultTableModel(nomesColunas, 0);
        
        List<Compra> compras = comprag.getListaConta(CONTA_LOGADA.getId());

        // Preencher a tabela com dados de eventos e botões de ação
        for (Compra compra : compras) {
            Object[] rowData = {compra.getData(), compra.getValorTotal(), ingressog.getIngressoId(compra.getIdTipoIngresso()).getTipo(),
            		eventog.getEventoId(compra.getIdEvento()).getNome()};
            modeloTabela.addRow(rowData);
        }
        
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
