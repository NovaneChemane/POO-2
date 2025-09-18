import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HouseHolderGUI {

    public static void main(String[] args) {
        // Criando o frame principal
        JFrame frame = new JFrame("HouseHolder - Gestão Financeira Familiar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);  // Ajuste do tamanho da janela principal
        frame.setLayout(new BorderLayout(10, 10));

        // Painel superior (título, menu ao lado, data, nome do usuário)
        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setPreferredSize(new Dimension(1000, 150));  // Ajuste do tamanho

        // Título "HouseHolder"
        JPanel painelTituloMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));  // Título e Menu no mesmo painel
        JLabel titulo = new JLabel("HouseHolder", JLabel.LEFT);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        // Criando a barra de menus
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));  // Espaço entre o título e o menu

        // Espaçamento entre os menus
        int espacamento = 15;  // Espaçamento personalizado

        // Menu "Activos"
        JMenu menuActivos = new JMenu("Activos");
        menuActivos.setMargin(new Insets(0, espacamento, 0, espacamento));  // Espaçamento entre os itens do menu
        JMenuItem addActivos = new JMenuItem("Adicionar");
        JMenuItem detalhesActivos = new JMenuItem("Detalhes");
        menuActivos.add(addActivos);
        menuActivos.add(detalhesActivos);
        menuBar.add(menuActivos);

        // Menu "Despesas"
        JMenu menuDespesas = new JMenu("Despesas");
        menuDespesas.setMargin(new Insets(0, espacamento, 0, espacamento));
        JMenuItem addDespesas = new JMenuItem("Adicionar");
        JMenuItem detalhesDespesas = new JMenuItem("Detalhes");
        menuDespesas.add(addDespesas);
        menuDespesas.add(detalhesDespesas);
        menuBar.add(menuDespesas);

        // Menu "Dependentes"
        JMenu menuDependentes = new JMenu("Dependentes");
        menuDependentes.setMargin(new Insets(0, espacamento, 0, espacamento));
        JMenuItem addDependentes = new JMenuItem("Adicionar");
        JMenuItem detalhesDependentes = new JMenuItem("Detalhes");
        menuDependentes.add(addDependentes);
        menuDependentes.add(detalhesDependentes);
        menuBar.add(menuDependentes);

        // Menu "Dívidas"
        JMenu menuDividas = new JMenu("Dívidas");
        menuDividas.setMargin(new Insets(0, espacamento, 0, espacamento));
        JMenuItem addDividas = new JMenuItem("Adicionar");
        JMenuItem detalhesDividas = new JMenuItem("Detalhes");
        menuDividas.add(addDividas);
        menuDividas.add(detalhesDividas);
        menuBar.add(menuDividas);

        painelTituloMenu.add(titulo);
        painelTituloMenu.add(menuBar);  // Adiciona o menu ao lado do título

        painelSuperior.add(painelTituloMenu, BorderLayout.WEST);  // Coloca o painel título+menu à esquerda

        // Painel de informações do usuário à direita
        JPanel painelInfoUsuario = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel dataLabel = new JLabel("Wen 15/10/2024");
        JLabel nomeUsuario = new JLabel("Mirione Mafalacusser");
        nomeUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        painelInfoUsuario.add(dataLabel);
        painelInfoUsuario.add(nomeUsuario);

        painelSuperior.add(painelInfoUsuario, BorderLayout.EAST);  // Informações do usuário à direita

        // Adicionando o painel superior ao frame
        frame.add(painelSuperior, BorderLayout.NORTH);

        // Painel central com layout manual (usando GridBagLayout para maior controle de tamanho)
        JPanel painelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Margens internas entre os componentes

        // Ajuste de proporções
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        // Resumo Financeiro
        JPanel painelResumoFinanceiro = new JPanel(new GridLayout(4, 1));
        painelResumoFinanceiro.setBorder(BorderFactory.createTitledBorder("Resumo Financeiro"));
        painelResumoFinanceiro.add(new JLabel("Total Activos: 50,000.00 Mnz"));
        painelResumoFinanceiro.add(new JLabel("Total Despesas: 50,000.00 Mnz"));
        painelResumoFinanceiro.add(new JLabel("Total Dívidas: 50,000.00 Mnz"));
        painelResumoFinanceiro.add(new JLabel("Saldo Disponível: 50,000.00 Mnz"));
        painelResumoFinanceiro.setPreferredSize(new Dimension(250, 200));  // Tamanho ajustado

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        painelCentral.add(painelResumoFinanceiro, gbc);

        // Transações Recentes
        JPanel painelTransacoes = new JPanel(new BorderLayout());
        painelTransacoes.setBorder(BorderFactory.createTitledBorder("Transações Recentes"));

        // Criando a tabela de transações
        String[] colunas = {"Data", "Tipo", "Descrição", "Dependente", "Valor"};
        Object[][] dados = {
                {"15/10/2024", "Despesa", "Transporte", "-", "5,000.00Mnz"},
                {"13/10/2024", "Despesa", "Mensalidade", "Amante 2", "3,000.00Mnz"},
                {"11/10/2024", "Dívida", "Ágiota Rungo", "-", "5,000.00Mnz"}
        };
        JTable tabelaTransacoes = new JTable(new DefaultTableModel(dados, colunas));
        JScrollPane scrollTransacoes = new JScrollPane(tabelaTransacoes);
        painelTransacoes.add(scrollTransacoes, BorderLayout.CENTER);
        painelTransacoes.setPreferredSize(new Dimension(400, 200));  // Tamanho maior para o central

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        painelCentral.add(painelTransacoes, gbc);

        // Notificações
        JPanel painelNotificacoes = new JPanel(new GridLayout(4, 1));
        painelNotificacoes.setBorder(BorderFactory.createTitledBorder("Notificações"));
        painelNotificacoes.add(new JLabel("1. Electricidade: 2 Dias"));
        painelNotificacoes.add(new JLabel("2. Subscrição: 10 Dias"));
        painelNotificacoes.add(new JLabel("3. Água: 11 Dias"));
        painelNotificacoes.setPreferredSize(new Dimension(250, 200));  // Tamanho ajustado

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        painelCentral.add(painelNotificacoes, gbc);

        frame.add(painelCentral, BorderLayout.CENTER);

        // Exibindo a interface
        frame.setVisible(true);
    }
}