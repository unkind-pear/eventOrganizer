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
import bean.Sala;
import bean.TipoIngresso;
import dao.CompraDAO;
import dao.ContaDAO;
import dao.EventoDAO;
import dao.SalaDAO;
import dao.TipoIngressoDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.util.List;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ComprarIngresso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable eventosTable;
    private DefaultTableModel modeloTabela;
    
    private TipoIngressoDAO eventog = new TipoIngressoDAO();
    private SalaDAO salag = new SalaDAO();
    private CompraDAO comprag = new CompraDAO();
    private JTable salasTable;
    
    private static EventoDAO evg = new EventoDAO();
    private static ContaDAO cg = new ContaDAO();
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComprarIngresso frame = new ComprarIngresso(evg.getLista().get(1), cg.getLista().get(1));
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
	public ComprarIngresso(Evento EVENTO_SELECIONADO, Conta CONTA_LOGADA) {
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
		
		JLabel lblIngressos = new JLabel("Ingressos");
		lblIngressos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblIngressos.setBounds(471, 63, 157, 37);
		contentPane.add(lblIngressos);
		
        String[] nomesColunas = {"Tipo", "Preço"};
        modeloTabela = new DefaultTableModel(nomesColunas, 0);
	
        List<TipoIngresso> eventos = eventog.getIngressosEvento(EVENTO_SELECIONADO);
        
        JComboBox<Object> eventoComboBox = new JComboBox<>(); // Create a JComboBox for Evento selection
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
                    double novoSaldo = CONTA_LOGADA.getSaldo() - eventog.getIngresso(selectedEvento, EVENTO_SELECIONADO.getId()).getPreco();
                    if (novoSaldo >= 0) {
                    	Date data = new Date();
                    	Compra compra = new Compra(0, data, eventog.getIngresso(selectedEvento, EVENTO_SELECIONADO.getId()).getPreco(), CONTA_LOGADA.getId(), eventog.getIngresso(selectedEvento, EVENTO_SELECIONADO.getId()).getId(), EVENTO_SELECIONADO.getId());
                        comprag.inserir(compra);
                    	CONTA_LOGADA.setSaldo(novoSaldo);
                    	cg.alterar(CONTA_LOGADA);
                    } else {
                    	JOptionPane.showMessageDialog(null, "Você não possi dinheiro suficiente", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
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

        List<Sala> salas = salag.getSalasEvento(EVENTO_SELECIONADO);

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
