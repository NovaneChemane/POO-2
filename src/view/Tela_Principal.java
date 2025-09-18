
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author user
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.bean.Activos;
import model.dao.ActivosDAO;
import java.util.List;
import model.dao.DespesasDAO;
import model.dao.DividasDAO;

import model.dao.UsuarioDAO;

public class Tela_Principal extends WindowAdapter implements ActionListener{

    private JFrame janela;
    private JPanel topPanel, tittleMenuPanel, dataUsuario, bottomPanel, resumoFinanceiro, transacoesRecentes, notificacoes;
    private JTextField data, totalActivos, totalDespesas, totalDividas, saldoDisponivel;
    private JLabel titulo, nomeUsuario;
    private JTable tabelaTransacoesRecentes, tabelaNotificacoes;
    private JMenuItem adicionarActivos, adicionarDespesas, adicionarDependentes, adicionarDividas, removerActivos, removerDependentes;
    private JMenuItem removerDespesas, removerDividas,detalhesActivos,detalhesDespesas,detalhesDependentes,detalhesDividas;

    public Tela_Principal(String email) {
        janela = new JFrame("HouseHolder - Gestão Financeira Familiar");
        janela.setSize(1000, 700);
        janela.setLayout(new BorderLayout());
        
        // Aplicando cor de fundo ao conteúdo principal
        janela.getContentPane().setBackground(Color.decode("#f2f2f2")); // Fundo cinza claro

        // Criando o painel Superior
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(1000, 150));
        topPanel.setBackground(Color.decode("#ffffff")); // Fundo branco

        // Criando painel contendo o título e a barra de menú
        tittleMenuPanel = new JPanel();
        tittleMenuPanel.setBackground(Color.decode("#ffffff")); // Fundo branco

        titulo = new JLabel("HouseHolder");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.decode("#1a237e")); // Cor do título Azul Escuro

        // Criando a barra de menú
        JMenuBar barraMenu = new JMenuBar();
        barraMenu.setBackground(Color.decode("#ffffff")); // Fundo branco da barra de menu
        barraMenu.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // Espaço entre o título e o menu

        // Menu "Activos"
        JMenu menuActivos = new JMenu("Activos");

        adicionarActivos = new JMenuItem("Adicionar");
        adicionarActivos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Adicionar_Activos(email);
                } catch (ParseException ex) {
                    Logger.getLogger(Tela_Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        removerActivos = new JMenuItem("Remover");
        removerActivos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Remover_Activos(email);

            }

        });

         detalhesActivos = new JMenuItem("Detalhes");

        menuActivos.add(adicionarActivos);
        menuActivos.add(removerActivos);
        menuActivos.add(detalhesActivos);
        barraMenu.add(menuActivos);

        // Menu "Despesas"
        JMenu menuDespesas = new JMenu("Despesas");

        adicionarDespesas = new JMenuItem("Adicionar");
        adicionarDespesas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Adicionar_Despesas(email);
                } catch (ParseException ex) {
                    Logger.getLogger(Tela_Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        removerDespesas = new JMenuItem("Remover");
        removerDespesas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Remover_Despesas(email);
            }

        });

         detalhesDespesas = new JMenuItem("Detalhes");

        menuDespesas.add(adicionarDespesas);
        menuDespesas.add(removerDespesas);
        menuDespesas.add(detalhesDespesas);
        barraMenu.add(menuDespesas);

        // Menu "Dependentes"
        JMenu menuDependentes = new JMenu("Dependentes");

        adicionarDependentes = new JMenuItem("Adicionar");
        adicionarDependentes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Adicionar_Dependentes(email);
            }

        });

        removerDependentes = new JMenuItem("Remover");
        removerDependentes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Remover_Dependentes(email);
            }

        });

         detalhesDependentes = new JMenuItem("Detalhes");

        menuDependentes.add(adicionarDependentes);
        menuDependentes.add(removerDependentes);
        menuDependentes.add(detalhesDependentes);
        barraMenu.add(menuDependentes);

        // Menu "Dívidas"
        JMenu menuDividas = new JMenu("Dívidas");

        adicionarDividas = new JMenuItem("Adicionar");
        adicionarDividas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Adicionar_Dividas(email);
                } catch (ParseException ex) {
                    Logger.getLogger(Tela_Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        removerDividas = new JMenuItem("Remover");
        removerDividas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Remover_Dividas(email);
            }

        });

        detalhesDividas = new JMenuItem("Detalhes");

        menuDividas.add(adicionarDividas);
        menuDividas.add(removerDividas);
        menuDividas.add(detalhesDividas);
        barraMenu.add(menuDividas);

        // Menu de Configurações
        JMenu menuConfiguracoes = new JMenu("\u2630");
        JMenuItem dataHora = new JMenuItem("Data e Hora");
        JMenuItem dadosPessoais = new JMenuItem("Dados Pessoais");
        JMenuItem apagarConta = new JMenuItem("Apagar Conta");

        menuConfiguracoes.add(dataHora);
        menuConfiguracoes.add(dadosPessoais);
        menuConfiguracoes.add(apagarConta);
        barraMenu.add(menuConfiguracoes);
        
        apagarConta.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new ApagarConta();
                
                        
                 }
            
        });
        
       
        
        tittleMenuPanel.add(titulo);
        tittleMenuPanel.add(barraMenu);

        // Criando o painel contendo a data e o nome do usuário
        dataUsuario = new JPanel();
        dataUsuario.setBackground(Color.decode("#ffffff")); // Fundo branco do painel superior

        data = new JTextField(getDataActual(), 15);
        data.setEditable(false); // Assim, o TextField não será editável
        data.setBackground(Color.decode("#e0e0e0")); // Cor de fundo do campo de data (Cinza Claro)

        nomeUsuario = new JLabel();
        UsuarioDAO userdao=new UsuarioDAO(); 
        nomeUsuario.setText(userdao.selectEmail(email));
           
        

        nomeUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        nomeUsuario.setForeground(Color.decode("#1a287e")); // Cor do nome do usuário (Azul Escuro)

        dataUsuario.add(data);
        dataUsuario.add(nomeUsuario);

        // Adicionando os dois painéis criados ao painel "topPanel"
        topPanel.add(tittleMenuPanel, BorderLayout.WEST);
        topPanel.add(dataUsuario, BorderLayout.EAST);

        janela.add(topPanel, BorderLayout.NORTH);

        // Criando o painel inferior
        bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setBackground(Color.decode("#f2f2f2")); // Fundo cinza claro
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Ajustando as proporções
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        // Painel de resumo financeiro
        resumoFinanceiro = new JPanel();
        resumoFinanceiro.setLayout(new GridLayout(4, 2));
        resumoFinanceiro.setBorder(BorderFactory.createTitledBorder("Resumo Financeiro"));
        resumoFinanceiro.setBackground(Color.decode("#ffffff")); // Fundo branco
 
        ActivosDAO actdao=new ActivosDAO();
        totalActivos = new JTextField();
        try {
            totalActivos.setText(actdao.getTotalActivos()+"MZN");
        } catch (SQLException ex) {
            Logger.getLogger(Tela_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        totalActivos.setEditable(false);
        resumoFinanceiro.add(new JLabel("Total Activos: "));
        resumoFinanceiro.add(totalActivos);

        DespesasDAO desdao=new DespesasDAO();
        totalDespesas = new JTextField();
        try {
            totalDespesas.setText(desdao.getTotalDespesas()+"MZN");
        } catch (SQLException ex) {
            Logger.getLogger(Tela_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        totalDespesas.setEditable(false);
        resumoFinanceiro.add(new JLabel("Total Despesas: "));
        resumoFinanceiro.add(totalDespesas);

        
        DividasDAO divdao=new DividasDAO();
        totalDividas = new JTextField();
        try {
            totalDividas.setText(divdao.getTotalDividas()+"MZN");
        } catch (SQLException ex) {
            Logger.getLogger(Tela_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        totalDividas.setEditable(false);
        resumoFinanceiro.add(new JLabel("Total Dívidas: "));
        resumoFinanceiro.add(totalDividas);

        saldoDisponivel = new JTextField("0.00MZN");
        saldoDisponivel.setEditable(false);
        resumoFinanceiro.add(new JLabel("Saldo disponível: "));
        resumoFinanceiro.add(saldoDisponivel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;

        bottomPanel.add(resumoFinanceiro, gbc);
        
        detalhesActivos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new mostrarDetalhesActivos(email);
            }
           
            
        
        
        });
       detalhesDespesas.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new mostrarDetalhesDespesas(email);
            }
       
       
       
       
       });
       detalhesDependentes.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               new mostrarDetalhesDependentes(email);
            }
       
       
       
       
       });
       
      detalhesDividas.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new mostrarDetalhesDividas(email);
            }
      
      
      });
       
          
        
        
        
        
                
        
        // Painel da tabela de transações recentes
        transacoesRecentes = new JPanel();
        transacoesRecentes.setLayout(new BorderLayout());
    //    transacoesRecentes.setBorder(BorderFactory.createTitledBorder("Transações Recentes"));
        transacoesRecentes.setBackground(Color.decode("#ffffff")); // Fundo branco

        String[] colunasCabecalho = {"Data", "Tipo", "Descrição", "Dependente", "Valor"};
        Object[][] dados = {
            {"15/10/2024", "Despesa", "Transporte", "-", "5,000.00Mnz"},
            {"13/10/2024", "Despesa", "Mensalidade", "Júnior", "3,000.00Mnz"},
            {"11/10/2024", "Dívida", "Agiota Rungo", "-", "5,000.00Mnz"}
        };
        
        // Painel para notificações
        notificacoes = new JPanel(new BorderLayout());
        notificacoes.setBorder(BorderFactory.createTitledBorder("Notificações"));
        notificacoes.setBackground(Color.decode("#ffffff")); // Fundo branco

        String[] colunasNotificacoes = {"Descrição", "Dias Restantes"};
        Object[][] dadosNotificacoes = {
            {"Electricidade", "2 Dias"},
            {"Subscrição", "10 Dias"},
            {"Água", "11 Dias"}
        };

        tabelaNotificacoes = new JTable(new DefaultTableModel(dadosNotificacoes, colunasNotificacoes));
        JScrollPane scrollNotificacoes = new JScrollPane(tabelaNotificacoes);
        notificacoes.add(scrollNotificacoes, BorderLayout.CENTER);
        notificacoes.setPreferredSize(new Dimension(250, 200));

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        bottomPanel.add(notificacoes, gbc);

        janela.add(bottomPanel, BorderLayout.CENTER);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    // Método para obter a data actual
    private String getDataActual() {
        SimpleDateFormat formatador = new SimpleDateFormat("EEE dd/MM/yyyy");
        return formatador.format(new Date());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    
 

    private static class mostrarDetalhesActivos {

        public mostrarDetalhesActivos(String email) {
            JFrame detalhesFrame = new JFrame("Detalhes dos Ativos");
        detalhesFrame.setSize(600, 400);
        detalhesFrame.setLayout(new BorderLayout());

        String[] colunas = {"ID Ativo", "Tipo", "Data", "Valor"};
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        JTable tabelaDetalhes = new JTable(modeloTabela);

        
        ActivosDAO activosDAO = new ActivosDAO();
        List<Activos> listaAtivos = activosDAO.readActivosUsuario(email);
      
        for (Activos ativo : listaAtivos) {
            Object[] linha = {
                ativo.getIdActivos(),
                ativo.getTipoActivos(),
                ativo.getDataActivos(),
                ativo.getValorActivos()
            };
            modeloTabela.addRow(linha);
        }

        // Adiciona a tabela com rolagem na janela de detalhes
        JScrollPane scrollPane = new JScrollPane(tabelaDetalhes);
        detalhesFrame.add(scrollPane, BorderLayout.CENTER);

        // Configura e exibe a janela
        detalhesFrame.setLocationRelativeTo(null);
        detalhesFrame.setVisible(true);
        }
        
    }
}


   
