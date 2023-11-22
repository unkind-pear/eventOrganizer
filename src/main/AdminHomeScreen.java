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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

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

	private void InserirOrganizador() {
		String id_organizador = JOptionPane.showInputDialog("Id do organizador:");
		String cnpj_organizador = JOptionPane.showInputDialog("CNPJ do organizador:");
		String nome_organizador = JOptionPane.showInputDialog("Nome do organizador:");
		String email_organizador = JOptionPane.showInputDialog("Email do organizador:");
		
		int id = Integer.parseInt(id_organizador);
		
		Organizador org = new Organizador(id, cnpj_organizador,nome_organizador,email_organizador);
		
		if(organizadorg.inserir(org) != 0) {
			JOptionPane.showMessageDialog(null,"Organizador inserido com sucesso!");
		}else {
			JOptionPane.showMessageDialog(null, "Houve um erro ao insrir o organizador!");
		}
	};
	
	private void editOrganizador() {
	    int tr = tableOrganizadores.getSelectedRow();
	    Organizador organizador = new Organizador(
	        (int) tableOrganizadores.getValueAt(tr, 0),
	        (String) tableOrganizadores.getValueAt(tr, 1),
	        (String) tableOrganizadores.getValueAt(tr, 2),
	        (String) tableOrganizadores.getValueAt(tr, 3)
	    );

	    if (organizadorg.alterar(organizador) != 0) {
	        JOptionPane.showMessageDialog(null, "Editado com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(null, "Houve um erro ao editar o Banco de Dados. Verifique se os dados estão corretos: " + organizador, "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private boolean removeOrganizador() {
	    int tr = tableOrganizadores.getSelectedRow();  // Corrected from tableTelefonesOrganizador
	    Organizador organizador = new Organizador(
	        (int) tableOrganizadores.getValueAt(tr, 0),
	        (String) tableOrganizadores.getValueAt(tr, 1),
	        (String) tableOrganizadores.getValueAt(tr, 2),
	        (String) tableOrganizadores.getValueAt(tr, 3)
	    );

	    if (organizadorg.remover(organizador) != 0) {
	        JOptionPane.showMessageDialog(null, "Removido com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
	        return true;
	    } else {
	        JOptionPane.showMessageDialog(null, "Houve um erro ao editar o Banco de Dados. Verifique se os dados estão corretos: " + organizador, "Erro", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	}
    
    private void InserirTelefoneOrganizador() {
		String id_telefone = JOptionPane.showInputDialog("Id do telefone:");
		String id_organizador = JOptionPane.showInputDialog("Id do organizador:");
		String telefone_organizador = JOptionPane.showInputDialog("Telefone do organizador:");
		
		int id_tel = Integer.parseInt(id_telefone);
		int id_org = Integer.parseInt(id_organizador);
		long telefone = Long.parseLong(telefone_organizador);
		
		TelefoneOrganizador tel = new TelefoneOrganizador(id_tel,id_org,telefone);
		
		if(telefoneOrganizadorg.inserir(tel) != 0) {
			JOptionPane.showMessageDialog(null,"Telefone inserido com sucesso!");
		}else {
			JOptionPane.showMessageDialog(null, "Houve um erro ao insrir o telefone!");
		}
	};
	
	private void editTelefoneOrganizador() {
	    int tr = tableTelefonesOrganizador.getSelectedRow();
	    
	    // Assuming that the value in the table is stored as a String
	    Object rawValue = tableTelefonesOrganizador.getValueAt(tr, 2);

	    // Convert the value to long
	    long telefone = Long.parseLong(String.valueOf(rawValue));

	    TelefoneOrganizador Tel = new TelefoneOrganizador(
	        (int) tableTelefonesOrganizador.getValueAt(tr, 0),
	        (int) tableTelefonesOrganizador.getValueAt(tr, 1),
	        telefone
	    );

	    if (telefoneOrganizadorg.alterar(Tel) != 0) {
	        JOptionPane.showMessageDialog(null, "Telefone editado com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(null, "Houve um erro ao editar o Banco de Dados. Verifique se os dados estão corretos: " + Tel, "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private boolean removeTelefoneOrganizador() {
	    int tr = tableTelefonesOrganizador.getSelectedRow();
	    
	    // Assuming that the value in the table is stored as a String
	    Object rawValue = tableTelefonesOrganizador.getValueAt(tr, 2);

	    // Convert the value to long
	    long telefone = Long.parseLong(String.valueOf(rawValue));

	    TelefoneOrganizador Tel = new TelefoneOrganizador(
	        (int) tableTelefonesOrganizador.getValueAt(tr, 0),
	        (int) tableTelefonesOrganizador.getValueAt(tr, 1),
	        telefone
	    );

	    if (telefoneOrganizadorg.remover(Tel) != 0) {
	        JOptionPane.showMessageDialog(null, "Removido com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
	        return true;
	    } else {
	        JOptionPane.showMessageDialog(null, "Houve um erro ao editar o Banco de Dados. Verifique se os dados estão corretos: " + Tel, "Erro", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	}
	 
	 private void InserirCompra() {
		    String id_compra = JOptionPane.showInputDialog("Id da compra:");
		    String data_compra = JOptionPane.showInputDialog("Data da compra:");
		    String valorTL_compra = JOptionPane.showInputDialog("Valor Total da compra:");
		    String id_conta = JOptionPane.showInputDialog("Id da conta que efetuou a compra:");
		    String id_tipo_ingresso = JOptionPane.showInputDialog("Id do tipo do ingresso comprado:");
		    String id_evento = JOptionPane.showInputDialog("Digite o id do evento:");

		    int id_com = Integer.parseInt(id_compra);

		    // Converte a string para o formato Date
		    java.sql.Date data_com = null;
		    try {
		        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		        java.util.Date parsedDate = dateFormat.parse(data_compra);
		        data_com = new java.sql.Date(parsedDate.getTime());
		    } catch (ParseException e) {
		        System.err.println(e.getMessage());
		    }

		    double valor_Total_compra = Double.parseDouble(valorTL_compra);
		    int idConta = Integer.parseInt(id_conta);
		    int idTipoIngresso = Integer.parseInt(id_tipo_ingresso);
		    int idEvento = Integer.parseInt(id_evento);

		    Compra comp = new Compra(id_com, data_com, valor_Total_compra, idConta, idTipoIngresso, idEvento);

		    if (comprag.inserir(comp) != 0) {
		        JOptionPane.showMessageDialog(null, "Compra inserida com sucesso!");
		    } else {
		        JOptionPane.showMessageDialog(null, "Houve um erro ao inserir a compra!");
		    }
		}
		
		private void editCompra() {
			int tr = tableCompras.getSelectedRow();
			java.util.Date utilDate = (java.util.Date) tableCompras.getValueAt(tr, 1);
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			String valorStr = tableCompras.getValueAt(tr, 2).toString();
			double valor = Double.parseDouble(valorStr);

			Compra Compra = new Compra(
			    (int) tableCompras.getValueAt(tr, 0),
			    sqlDate,
			    valor,
			    (int) tableCompras.getValueAt(tr, 3),
			    (int) tableCompras.getValueAt(tr, 4),
			    (int) tableCompras.getValueAt(tr, 5)
			);
			if (comprag.alterar(Compra) != 0) {
				JOptionPane.showMessageDialog(null, "Compra editada com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Houve um erro ao editar o Banco de Dados. Verifique se os dados estão corretos: "+Compra, "Erro", JOptionPane.ERROR_MESSAGE);
			}
	    }
		
		private boolean removeCompra() {
			int tr = tableCompras.getSelectedRow();
			java.util.Date utilDate = (java.util.Date) tableCompras.getValueAt(tr, 1);
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			Compra Compra = new Compra(
			    (int) tableCompras.getValueAt(tr, 0),
			    sqlDate,
			    (double) tableCompras.getValueAt(tr, 2),
			    (int) tableCompras.getValueAt(tr, 3),
			    (int) tableCompras.getValueAt(tr, 4),
			    (int) tableCompras.getValueAt(tr, 5)
			);
			if (comprag.remover(Compra) != 0) {
				JOptionPane.showMessageDialog(null, "Removido com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Houve um erro ao editar o Banco de Dados. Verifique se os dados estão corretos: "+Compra, "Erro", JOptionPane.ERROR_MESSAGE);
				return false;
			}
	    }
		
		private void InserirEvento() {
		    String id_evento = JOptionPane.showInputDialog("Id do evento:");
		    String nome_evento = JOptionPane.showInputDialog("Nome do evento:");
		    String descricao_evento = JOptionPane.showInputDialog("Descrição do evento:");
		    String data_evento = JOptionPane.showInputDialog("Data do evento (formato dd-MM-yyyy):");
		    String capacidade = JOptionPane.showInputDialog("Capacidade máxima do evento:");
		    String id_organizador = JOptionPane.showInputDialog("Id do organizador do evento");

		    try {
		        int id_event = Integer.parseInt(id_evento);
		        int id_org = Integer.parseInt(id_organizador);
		        int capacidade_maxima = Integer.parseInt(capacidade);

		        // Converte a string para o formato data
		        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		        java.util.Date parsedDate = dateFormat.parse(data_evento);

		        // Create a java.sql.Date instance from the parsed date
		        java.sql.Date data_event = new java.sql.Date(parsedDate.getTime());

		        Evento evento = new Evento(id_event, nome_evento, descricao_evento, data_event, capacidade_maxima, id_org);

		        if (eventog.inserir(evento) != 0) {
		            JOptionPane.showMessageDialog(null, "Evento inserido com sucesso!");
		        } else {
		            JOptionPane.showMessageDialog(null, "Houve um erro ao inserir o evento!");
		        }
		    } catch (NumberFormatException | ParseException e) {
		        JOptionPane.showMessageDialog(null, "Erro na entrada de dados. Verifique os valores inseridos.", "Erro", JOptionPane.ERROR_MESSAGE);
		        e.printStackTrace(); // Handle or log the exception appropriately
		    }
		}

		private void editEvento() {
			int tr = tableEventos.getSelectedRow();
			Evento evento = new Evento((int) tableEventos.getValueAt(tr, 0), (String) tableEventos.getValueAt(tr, 1), (String) tableEventos.getValueAt(tr, 2), (java.sql.Date) tableEventos.getValueAt(tr, 3), (int) tableEventos.getValueAt(tr, 4), (int) tableEventos.getValueAt(tr, 5));
			if (eventog.alterar(evento) != 0) {
				JOptionPane.showMessageDialog(null, "Editado com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Houve um erro ao editar o Banco de Dados. Verifique se os dados estão corretos: "+evento, "Erro", JOptionPane.ERROR_MESSAGE);
			}
	    }
		
		private boolean removeEvento() {
			int tr = tableEventos.getSelectedRow();
			Evento evento = new Evento((int) tableEventos.getValueAt(tr, 0), (String) tableEventos.getValueAt(tr, 1), (String) tableEventos.getValueAt(tr, 2), (java.sql.Date) tableEventos.getValueAt(tr, 3), (int) tableEventos.getValueAt(tr, 4), (int) tableEventos.getValueAt(tr, 5));
			if (eventog.remover(evento) != 0) {
				JOptionPane.showMessageDialog(null, "Removido com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Houve um erro ao editar o Banco de Dados. Verifique se os dados estão corretos: "+evento, "Erro", JOptionPane.ERROR_MESSAGE);
				return false;
			}
	    }
		
		private void InserirSala() {
			String id_sala = JOptionPane.showInputDialog("Id da sala:");
			String id_evento = JOptionPane.showInputDialog("Id do evento:");
			String numero_sala = JOptionPane.showInputDialog("Número da sala:");
			String andar_sala = JOptionPane.showInputDialog("Andar da sala:");
			String Capacidade = JOptionPane.showInputDialog("Capacidade da sala:");
			
			int id = Integer.parseInt(id_sala);
			int id_even = Integer.parseInt(id_evento);
			int num_sala = Integer.parseInt(numero_sala);
			int andar = Integer.parseInt(andar_sala);
			int capacidade_maxima = Integer.parseInt(Capacidade);
			
			Sala sala = new Sala(id, id_even, num_sala, andar, capacidade_maxima);
			
		    if (salag.inserir(sala) != 0) {
		        JOptionPane.showMessageDialog(null, "Sala inserida com sucesso!");
		    } else {
		        JOptionPane.showMessageDialog(null, "Houve um erro ao inserir a sala!");
		    }
		};
		
		private void editSala() {
			int tr = tableSalas.getSelectedRow();
			Sala sala = new Sala((int) tableSalas.getValueAt(tr, 0), (int) tableSalas.getValueAt(tr, 1), (int) tableSalas.getValueAt(tr, 2), (int) tableSalas.getValueAt(tr, 3), (int) tableSalas.getValueAt(tr, 4));
		    if (salag.alterar(sala) != 0) {
		        JOptionPane.showMessageDialog(null, "Sala editada com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
		    } else {
		        JOptionPane.showMessageDialog(null, "Houve um erro ao editar o Banco de Dados. Verifique se os dados estão corretos: " + sala, "Erro", JOptionPane.ERROR_MESSAGE);
		    }
	    }
		
		private boolean removeSala() {
			int tr = tableSalas.getSelectedRow();
			Sala sala = new Sala((int) tableSalas.getValueAt(tr, 0), (int) tableSalas.getValueAt(tr, 1), (int) tableSalas.getValueAt(tr, 2), (int) tableSalas.getValueAt(tr, 3), (int) tableSalas.getValueAt(tr, 4));
		    if (salag.remover(sala) != 0) {
		        JOptionPane.showMessageDialog(null, "Removido com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
		        return true;
		    } else {
		        JOptionPane.showMessageDialog(null, "Houve um erro ao editar o Banco de Dados. Verifique se os dados estão corretos: " + sala, "Erro", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }
	    }
		
		private void InserirTipoIngresso() {
			String id_tipo_ingresso = JOptionPane.showInputDialog("Id do tipo do ingresso:");
			String tipo_ingresso = JOptionPane.showInputDialog("Tipo do ingresso:");
			String preco_ingresso = JOptionPane.showInputDialog("Preço do ingresso:");
			String id_evento = JOptionPane.showInputDialog("Id do evento:");
			
			int id = Integer.parseInt(id_tipo_ingresso);
			double preco = Double.parseDouble(preco_ingresso);
			int id_event = Integer.parseInt(id_evento);
			
			TipoIngresso tpi = new TipoIngresso(id, tipo_ingresso,preco,id_event);
			
			if(tipoIngressog.inserir(tpi) != 0) {
				JOptionPane.showMessageDialog(null,"Tipo do ingresso inserido com sucesso!");
			}else {
				JOptionPane.showMessageDialog(null, "Houve um erro ao insrir o tipo do ingresso!");
			}
		};
		
		private void editTipoIngresso() {
			int tr = tableTipoIngresso.getSelectedRow();
			TipoIngresso Tpi = new TipoIngresso((int) tableTipoIngresso.getValueAt(tr, 0), (String) tableTipoIngresso.getValueAt(tr, 1), (int) tableTipoIngresso.getValueAt(tr, 2), (int) tableTipoIngresso.getValueAt(tr, 3));
			if (tipoIngressog.alterar(Tpi) != 0) {
				JOptionPane.showMessageDialog(null, "Editado com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Houve um erro ao editar o Banco de Dados. Verifique se os dados estão corretos: "+Tpi, "Erro", JOptionPane.ERROR_MESSAGE);
			}
	    }
		
		private boolean removeTipoIngresso() {
			int tr = tableTipoIngresso.getSelectedRow();
			TipoIngresso Tpi = new TipoIngresso((int) tableTipoIngresso.getValueAt(tr, 0), (String) tableTipoIngresso.getValueAt(tr, 1), (int) tableTipoIngresso.getValueAt(tr, 2), (int) tableTipoIngresso.getValueAt(tr, 3));
			if (tipoIngressog.remover(Tpi) != 0) {
				JOptionPane.showMessageDialog(null, "Removido com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Houve um erro ao editar o Banco de Dados. Verifique se os dados estão corretos: "+Tpi, "Erro", JOptionPane.ERROR_MESSAGE);
				return false;
			}
	    }
		
		 private void InserirConta() throws ParseException {
				String id_conta = JOptionPane.showInputDialog("Id da conta:");
				String nome_conta = JOptionPane.showInputDialog("Nome da conta:");
				String senha_conta = JOptionPane.showInputDialog("Senha da conta:");
				String saldo_conta = JOptionPane.showInputDialog("Saldo da conta:");
				String data_nascimento = JOptionPane.showInputDialog("Data de nascimento:");
				String idadeS = JOptionPane.showInputDialog("Idade:");
				String nome_Cartao = JOptionPane.showInputDialog("Nome que aparece no cartão:");
				String numero_cartao = JOptionPane.showInputDialog("Número do cartão:");
				String numero_seg_cartao = JOptionPane.showInputDialog("Número de segurança do cartão:");
				String data_validade_cartao = JOptionPane.showInputDialog("Data de validade do cartão:");
				
				int id = Integer.parseInt(id_conta);
				double saldo = Double.parseDouble(saldo_conta);
				int idade = Integer.parseInt(idadeS);
				long num_cartao = Long.parseLong(numero_cartao);
				int num_seg_cartao = Integer.parseInt(numero_seg_cartao);
				
				//Converte a string para o formato Date
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date utilDate = null;
				java.sql.Date data_nasc = null;
				try {
				    utilDate = dateFormat.parse(data_nascimento);
				} catch (ParseException e) {
				    System.err.println(e.getMessage());
				}
				data_nasc = new java.sql.Date(utilDate.getTime());
				
				SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date utilDateValid = null;
				java.sql.Date data_valid = null;
				try {
				    utilDateValid = date.parse(data_validade_cartao);
				} catch (ParseException e) {
				    System.err.println(e.getMessage());
				}

				// Convert java.util.Date to java.sql.Date
				data_valid = new java.sql.Date(utilDateValid.getTime());
				
				Conta conta = new Conta(id, nome_conta, senha_conta, saldo, data_nasc, idade, nome_Cartao, num_cartao, num_seg_cartao, data_valid);
				
				if(contag.inserir(conta) != 0) {
					JOptionPane.showMessageDialog(null,"Conta inserida com sucesso!");
				}else {
					JOptionPane.showMessageDialog(null, "Houve um erro ao insrir a conta!");
				}
			};
			
			private void editConta() {
				int tr = tableContas.getSelectedRow();
				Conta Conta = new Conta((int) tableContas.getValueAt(tr, 0), (String) tableContas.getValueAt(tr, 1), (String) tableContas.getValueAt(tr, 2), (double) tableContas.getValueAt(tr, 3), (java.sql.Date) tableContas.getValueAt(tr, 4), (int) tableContas.getValueAt(tr, 5), (String) tableContas.getValueAt(tr, 6), (long) tableContas.getValueAt(tr, 7), (int) tableContas.getValueAt(tr, 8), (java.sql.Date) tableContas.getValueAt(tr, 9));
				if (contag.alterar(Conta) != 0) {
					JOptionPane.showMessageDialog(null, "Conta editada com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Houve um erro ao editar o Banco de Dados. Verifique se os dados estão corretos: "+Conta, "Erro", JOptionPane.ERROR_MESSAGE);
				}
		    }
			
			private boolean removeConta() {
				int tr = tableContas.getSelectedRow();
				Conta Conta = new Conta((int) tableContas.getValueAt(tr, 0), (String) tableContas.getValueAt(tr, 1), (String) tableContas.getValueAt(tr, 2), (double) tableContas.getValueAt(tr, 3), (java.sql.Date) tableContas.getValueAt(tr, 4), (int) tableContas.getValueAt(tr, 5), (String) tableContas.getValueAt(tr, 6), (long) tableContas.getValueAt(tr, 7), (int) tableContas.getValueAt(tr, 8), (java.sql.Date) tableContas.getValueAt(tr, 9));
				if (contag.remover(Conta) != 0) {
					JOptionPane.showMessageDialog(null, "Removido com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "Houve um erro ao editar o Banco de Dados. Verifique se os dados estão corretos: "+Conta, "Erro", JOptionPane.ERROR_MESSAGE);
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
		
		JButton Editar_Organizador = new JButton("Editar");
		Editar_Organizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editOrganizador();
			}
		});
		Editar_Organizador.setBounds(178, 262, 76, 25);
		contentPane.add(Editar_Organizador);
		
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
		lblTelefonesOrganizador.setBounds(497, 12, 182, 15);
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
		scrollPane_6.setBounds(12, 629, 1195, 211);
		contentPane.add(scrollPane_6);
		
		// Preencher a tabela com dados da tabela "conta"
		String[] nomesColunasConta = {"ID", "Nome", "Saldo", "Data de Nascimento", "Idade", "Nome do Cartão", "Número do Cartão", "Data de validade cartão"};
		DefaultTableModel modeloTabelaConta = new DefaultTableModel(nomesColunasConta, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make the "Age" column uneditable (column index 1)
                return column != 0;
            }};

		List<Conta> listaContas = contag.getLista(); // Substitua "Conta" e "contaDAO" pelos nomes apropriados

		for (Conta conta : listaContas) {
		    Object[] rowData = {conta.getId(), conta.getNome(), conta.getSaldo(), conta.getDataNascimento(),
		                        conta.getIdade(), conta.getNomeCartao(), conta.getNumeroCartao(), conta.getDataValidadeCartao()};
		    modeloTabelaConta.addRow(rowData);
		}

		tableContas = new JTable(modeloTabelaConta);
		scrollPane_6.setViewportView(tableContas);
		
		JButton Remover_Organizador = new JButton("Remover");
		Remover_Organizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (removeOrganizador()) {
					modeloTabela.removeRow(tableOrganizadores.getSelectedRow());
				}
			}
		});
		Remover_Organizador.setBounds(261, 262, 96, 25);
		contentPane.add(Remover_Organizador);
		
		JButton Inserir_Organizador = new JButton("Inserir");
		Inserir_Organizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserirOrganizador();
			}
		});
		Inserir_Organizador.setBounds(92, 262, 76, 25);
		contentPane.add(Inserir_Organizador);
		
		JButton Remover_Telefone_Organizador = new JButton("Remover");
		Remover_Telefone_Organizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (removeTelefoneOrganizador()) {
					modeloTabela.removeRow(tableTelefonesOrganizador.getSelectedRow());
				}
			}
		});
		Remover_Telefone_Organizador.setBounds(520, 298, 96, 25);
		contentPane.add(Remover_Telefone_Organizador);
		
		JButton Editar_Telefone_Organizador = new JButton("Editar");
		Editar_Telefone_Organizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editTelefoneOrganizador();
			}
		});
		Editar_Telefone_Organizador.setBounds(450, 262, 96, 25);
		contentPane.add(Editar_Telefone_Organizador);
		
		JButton Inserir_Telefone_Organizador = new JButton("Inserir");
		Inserir_Telefone_Organizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserirTelefoneOrganizador();
			}
		});
		Inserir_Telefone_Organizador.setBounds(576, 262, 96, 25);
		contentPane.add(Inserir_Telefone_Organizador);
		
		JButton Remover_Compra = new JButton("Remover");
		Remover_Compra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (removeCompra()) {
					modeloTabela.removeRow(tableCompras.getSelectedRow());
				}
			}
		});
		Remover_Compra.setBounds(1105, 262, 96, 25);
		contentPane.add(Remover_Compra);
		
		JButton Editar_Compra = new JButton("Editar");
		Editar_Compra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editCompra();
			}
		});
		Editar_Compra.setBounds(999, 262, 96, 25);
		contentPane.add(Editar_Compra);
		
		JButton Inserir_Compra = new JButton("Inserir");
		Inserir_Compra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserirCompra();
			}
		});
		Inserir_Compra.setBounds(893, 262, 96, 25);
		contentPane.add(Inserir_Compra);
		
		JButton Remover_Evento = new JButton("Remover");
		Remover_Evento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (removeEvento()) {
					modeloTabela.removeRow(tableEventos.getSelectedRow());
				}
			}
		});
		Remover_Evento.setBounds(261, 560, 96, 25);
		contentPane.add(Remover_Evento);
		
		JButton Editar_Evento = new JButton("Editar");
		Editar_Evento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editEvento();
			}
		});
		Editar_Evento.setBounds(178, 560, 76, 25);
		contentPane.add(Editar_Evento);
		
		JButton Inserir_Evento = new JButton("Inserir");
		Inserir_Evento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserirEvento();
			}
		});
		Inserir_Evento.setBounds(92, 561, 76, 25);
		contentPane.add(Inserir_Evento);
		
		JButton Remover_Sala = new JButton("Remover");
		Remover_Sala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (removeSala()) {
					modeloTabela.removeRow(tableSalas.getSelectedRow());
				}
			}
		});
		Remover_Sala.setBounds(731, 561, 96, 25);
		contentPane.add(Remover_Sala);
		
		JButton Editar_Sala = new JButton("Editar");
		Editar_Sala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editSala();
			}
		});
		Editar_Sala.setBounds(647, 560, 76, 25);
		contentPane.add(Editar_Sala);
		
		JButton Inserir_Sala = new JButton("Inserir");
		Inserir_Sala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserirSala();
			}
		});
		Inserir_Sala.setBounds(560, 560, 76, 25);
		contentPane.add(Inserir_Sala);
		
		JButton Remover_Tipo_Ingresso = new JButton("Remover");
		Remover_Tipo_Ingresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (removeTipoIngresso()) {
					modeloTabela.removeRow(tableTipoIngresso.getSelectedRow());
				}
			}
		});
		Remover_Tipo_Ingresso.setBounds(1105, 560, 96, 25);
		contentPane.add(Remover_Tipo_Ingresso);
		
		JButton Editar_Tipo_Ingresso = new JButton("Editar");
		Editar_Tipo_Ingresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editTipoIngresso();
			}
		});
		Editar_Tipo_Ingresso.setBounds(1019, 561, 76, 25);
		contentPane.add(Editar_Tipo_Ingresso);
		
		JButton Inserir_Tipo_Ingresso = new JButton("Inserir");
		Inserir_Tipo_Ingresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserirTipoIngresso();
			}
		});
		Inserir_Tipo_Ingresso.setBounds(932, 561, 76, 25);
		contentPane.add(Inserir_Tipo_Ingresso);
		
		JButton Inserir_Conta = new JButton("Inserir");
		Inserir_Conta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					InserirConta();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Inserir_Conta.setBounds(560, 851, 76, 25);
		contentPane.add(Inserir_Conta);
		
		JButton Editar_Conta = new JButton("Editar");
		Editar_Conta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editConta();
			}
		});
		Editar_Conta.setBounds(647, 851, 76, 25);
		contentPane.add(Editar_Conta);
		
		JButton Remover_Conta = new JButton("Remover");
		Remover_Conta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (removeConta()) {
					modeloTabela.removeRow(tableContas.getSelectedRow());
				}
			}
		});
		Remover_Conta.setBounds(731, 851, 96, 25);
		contentPane.add(Remover_Conta);
	}
}
