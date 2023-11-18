package main;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView.TableRow;

import bean.Compra;
import bean.Conta;
import bean.Evento;
import bean.Organizador;
import bean.Sala;
import bean.TelefoneOrganizador;
import bean.TipoIngresso;
import dao.CompraDAO;
import dao.ContaDAO;
import dao.EventoDAO;
import dao.OrganizadorDAO;
import dao.SalaDAO;
import dao.TelefoneOrganizadorDAO;
import dao.TipoIngressoDAO;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHomeScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableOrganizadores;
	private JTable tableTelefonesOrganizador;
	private JTable tableCompras;
	private JTable tableEventos;
	private JTable tableSalas;
	private JTable tableTipoIngresso;
	private JTable tableContas;
	
	private OrganizadorDAO organizadorg = new OrganizadorDAO();
	private EventoDAO eventog = new EventoDAO();
	private SalaDAO salag = new SalaDAO();
	private ContaDAO contag = new ContaDAO();
	private TipoIngressoDAO tipoIngressog = new TipoIngressoDAO();
	private CompraDAO comprag = new CompraDAO();
	private TelefoneOrganizadorDAO telefoneOrganizadorg = new TelefoneOrganizadorDAO();
	
	// AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

    private void editOrganizador() {
		int tr = tableOrganizadores.getSelectedRow();
		Organizador organizador = new Organizador((int) tableOrganizadores.getValueAt(tr, 0), (String) tableOrganizadores.getValueAt(tr, 1), (String) tableOrganizadores.getValueAt(tr, 2), (String) tableOrganizadores.getValueAt(tr, 3));
		if (organizadorg.alterar(organizador) != 0) {
			JOptionPane.showMessageDialog(null, "Editado com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Houve um erro ao editar o Banco de Dados. Verifique se os dados estão corretos: "+organizador, "Erro", JOptionPane.ERROR_MESSAGE);
		}
    }

    private boolean removeOrganizador() {
		int tr = tableTelefonesOrganizador.getSelectedRow();
		Organizador organizador = new Organizador((int) tableTelefonesOrganizador.getValueAt(tr, 0), (String) tableTelefonesOrganizador.getValueAt(tr, 1), (String) tableOrganizadores.getValueAt(tr, 2), (String) tableOrganizadores.getValueAt(tr, 3));
		if (organizadorg.remover(organizador) != 0) {
			JOptionPane.showMessageDialog(null, "Removido com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Houve um erro ao editar o Banco de Dados. Verifique se os dados estão corretos: "+organizador, "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHomeScreen frame = new AdminHomeScreen();
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
	public AdminHomeScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1263, 933);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 39, 345, 211);
		contentPane.add(scrollPane);
		
		// Preencher a tabela com dados
        String[] nomesColunas = {"ID", "CNPJ", "Nome", "Email"};
        DefaultTableModel modeloTabela = new DefaultTableModel(nomesColunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make the "Age" column uneditable (column index 1)
                return column != 0;
            }
        };
	
        List<Organizador> lista = organizadorg.getLista();

        for (Organizador e : lista) {
            Object[] rowData = {e.getId(), e.getCnpj(), e.getNome(),
                    e.getEmail()};
            modeloTabela.addRow(rowData);
        }
		
		tableOrganizadores = new JTable(modeloTabela);
		scrollPane.setViewportView(tableOrganizadores);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editOrganizador();
			}
		});
		btnEditar.setBounds(178, 262, 76, 25);
		contentPane.add(btnEditar);
		
		JLabel lblOrganizadores = new JLabel("Organizadores");
		lblOrganizadores.setBounds(127, 12, 117, 15);
		contentPane.add(lblOrganizadores);
		
		JLabel lblSalas = new JLabel("Salas");
		lblSalas.setBounds(647, 311, 70, 15);
		contentPane.add(lblSalas);
		
		JLabel lblEventos = new JLabel("Eventos");
		lblEventos.setBounds(184, 311, 70, 15);
		contentPane.add(lblEventos);
		
		JLabel lblCompras = new JLabel("Compras");
		lblCompras.setBounds(952, 12, 70, 15);
		contentPane.add(lblCompras);
		
		JLabel lblContas = new JLabel("Contas");
		lblContas.setBounds(599, 598, 70, 15);
		contentPane.add(lblContas);
		
		JLabel lblTipoIngresso = new JLabel("Tipo Ingresso");
		lblTipoIngresso.setBounds(1025, 311, 96, 15);
		contentPane.add(lblTipoIngresso);
		
		JLabel lblTelefonesOrganizador = new JLabel("Telefones Organizador");
		lblTelefonesOrganizador.setBounds(476, 12, 182, 15);
		contentPane.add(lblTelefonesOrganizador);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(450, 39, 229, 211);
		contentPane.add(scrollPane_1);
		
		// Preencher a tabela com dados
		
        String[] nomesColunas2 = {"ID", "ID Organizador", "Telefone"};
        DefaultTableModel modeloTabela2 = new DefaultTableModel(nomesColunas2, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make the "Age" column uneditable (column index 1)
                return column != 0;
            }};
	
        List<TelefoneOrganizador> lista2 = telefoneOrganizadorg.getLista();

        for (TelefoneOrganizador e : lista2) {
            Object[] rowData = {e.getId(), e.getIdOrganizador(), e.getTelefone()};
            modeloTabela2.addRow(rowData);
        }
		
		tableTelefonesOrganizador = new JTable(modeloTabela2);
		scrollPane_1.setViewportView(tableTelefonesOrganizador);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(760, 39, 447, 211);
		contentPane.add(scrollPane_2);
		
		// Preencher a tabela com dados
		
        String[] nomesColunas3 = {"ID", "Data", "Valor Total", "ID Conta", "ID Tipo Ingresso", "ID Evento"};
        DefaultTableModel modeloTabela3 = new DefaultTableModel(nomesColunas3, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make the "Age" column uneditable (column index 1)
                return column != 0;
            }
        };
	
        List<Compra> lista3 = comprag.getLista();

        for (Compra e : lista3) {
            Object[] rowData = {e.getId(), e.getData(), e.getValorTotal(), e.getIdConta(), e.getIdTipoIngresso(), e.getIdEvento()};
            modeloTabela3.addRow(rowData);
        }
		
		tableCompras = new JTable(modeloTabela3);
		scrollPane_2.setViewportView(tableCompras);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(12, 338, 409, 211);
		contentPane.add(scrollPane_3);
		
		// Preencher a tabela com dados da tabela "evento"
		String[] nomesColunasEvento = {"ID", "Nome", "Descrição", "Data", "Capacidade Máxima", "ID Organizador"};
		DefaultTableModel modeloTabelaEvento = new DefaultTableModel(nomesColunasEvento, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make the "Age" column uneditable (column index 1)
                return column != 0;
            }};

		List<Evento> listaEventos = eventog.getLista(); // Substitua "Evento" e "eventoDAO" pelos nomes apropriados

		for (Evento evento : listaEventos) {
		    Object[] rowData = {evento.getId(), evento.getNome(), evento.getDescricao(), evento.getData(),
		                        evento.getCapacidadeMaxima(), evento.getIdOrganizador()};
		    modeloTabelaEvento.addRow(rowData);
		}

		tableEventos = new JTable(modeloTabelaEvento);
		scrollPane_3.setViewportView(tableEventos);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(503, 338, 337, 211);
		contentPane.add(scrollPane_4);
		
		// Preencher a tabela com dados da tabela "sala"
		String[] nomesColunasSala = {"ID", "ID Evento", "Número", "Andar", "Capacidade Máxima"};
		DefaultTableModel modeloTabelaSala = new DefaultTableModel(nomesColunasSala, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make the "Age" column uneditable (column index 1)
                return column != 0;
            }};

		List<Sala> listaSalas = salag.getLista(); // Substitua "Sala" e "salaDAO" pelos nomes apropriados

		for (Sala sala : listaSalas) {
		    Object[] rowData = {sala.getId(), sala.getIdEvento(), sala.getNumero(), sala.getAndar(), sala.getCapacidadeMaxima()};
		    modeloTabelaSala.addRow(rowData);
		}

		tableSalas = new JTable(modeloTabelaSala);
		scrollPane_4.setViewportView(tableSalas);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(922, 338, 285, 211);
		contentPane.add(scrollPane_5);
		
		// Preencher a tabela com dados da tabela "tipo_ingresso"
		String[] nomesColunasTipoIngresso = {"ID", "Tipo", "Preço", "ID Evento"};
		DefaultTableModel modeloTabelaTipoIngresso = new DefaultTableModel(nomesColunasTipoIngresso, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make the "Age" column uneditable (column index 1)
                return column != 0;
            }};

		List<TipoIngresso> listaTiposIngresso = tipoIngressog.getLista(); // Substitua "TipoIngresso" e "tipoIngressoDAO" pelos nomes apropriados

		for (TipoIngresso tipoIngresso : listaTiposIngresso) {
		    Object[] rowData = {tipoIngresso.getId(), tipoIngresso.getTipo(), tipoIngresso.getPreco(), tipoIngresso.getIdEvento()};
		    modeloTabelaTipoIngresso.addRow(rowData);
		}

		tableTipoIngresso = new JTable(modeloTabelaTipoIngresso);
		scrollPane_5.setViewportView(tableTipoIngresso);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(374, 629, 508, 211);
		contentPane.add(scrollPane_6);
		
		// Preencher a tabela com dados da tabela "conta"
		String[] nomesColunasConta = {"ID", "Nome", "Saldo", "Data de Nascimento", "Idade", "Nome do Cartão", "Número do Cartão"};
		DefaultTableModel modeloTabelaConta = new DefaultTableModel(nomesColunasConta, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make the "Age" column uneditable (column index 1)
                return column != 0;
            }};

		List<Conta> listaContas = contag.getLista(); // Substitua "Conta" e "contaDAO" pelos nomes apropriados

		for (Conta conta : listaContas) {
		    Object[] rowData = {conta.getId(), conta.getNome(), conta.getSaldo(), conta.getDataNascimento(),
		                        conta.getIdade(), conta.getNomeCartao(), conta.getNumeroCartao()};
		    modeloTabelaConta.addRow(rowData);
		}

		tableContas = new JTable(modeloTabelaConta);
		scrollPane_6.setViewportView(tableContas);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (removeOrganizador()) {
					modeloTabela.removeRow(tableOrganizadores.getSelectedRow());
				}
			}
		});
		btnRemover.setBounds(261, 262, 96, 25);
		contentPane.add(btnRemover);
	}
}
