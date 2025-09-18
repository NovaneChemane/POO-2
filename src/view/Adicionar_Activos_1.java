package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import model.bean.Activos;
import model.dao.ActivosDAO;

public class Adicionar_Activos_1 implements ActionListener {
    private JFrame janela;
    private JPanel painel;
    private JTextField id_activos, tipo_activos, valor_activos;
    private JFormattedTextField data_activos;
    private JButton botao_adicionar;

    public Adicionar_Activos_1() throws ParseException {
        janela = new JFrame("Adicionar Activos");
        janela.setSize(300, 215);

        painel = new JPanel();
        painel.setBackground(new Color(192, 192, 192, 150));
        painel.setLayout(null);

        JLabel label_tipo = new JLabel("Activo (Nome): ");
        label_tipo.setBounds(10, 20, 100, 25);
        painel.add(label_tipo);

        tipo_activos = new JTextField();
        tipo_activos.setBounds(110, 20, 165, 25);
        painel.add(tipo_activos);

        JLabel label_valor = new JLabel("Valor (MNZ): ");
        label_valor.setBounds(10, 60, 100, 25);
        painel.add(label_valor);

        valor_activos = new JTextField();
        valor_activos.setBounds(110, 60, 165, 25);
        painel.add(valor_activos);

        JLabel label_data = new JLabel("Data (dd/mm/aaaa): "); 
        label_data.setBounds(10, 100, 120, 25);
        painel.add(label_data);

        MaskFormatter dateMask = new MaskFormatter("##/##/####");
        dateMask.setPlaceholderCharacter('_');
        data_activos = new JFormattedTextField(dateMask);
        data_activos.setBounds(130, 100, 145, 25);
        painel.add(data_activos);

        botao_adicionar = new JButton("Adicionar");
        botao_adicionar.setBounds(85, 140, 120, 25);
        painel.add(botao_adicionar);
        botao_adicionar.addActionListener(this);

        janela.add(painel);
        janela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        janela.setVisible(true);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);
    }
    
    //Acção do botão adicionar

    @Override
    public void actionPerformed(ActionEvent e) {
        String nome = tipo_activos.getText();
        String valor = valor_activos.getText();
        String data = data_activos.getText();

        if (e.getSource() == botao_adicionar) {
            if (nome.isEmpty() || valor.isEmpty() || data.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha os campos vazios, por favor!");
            } else {
                adicionarActivo();
            }
        }
    }
    
    //Método adicionarActivo

    private void adicionarActivo() {
        Activos a = new Activos();
        ActivosDAO adao = new ActivosDAO();

        a.setTipoActivos(tipo_activos.getText());
        a.setValorActivos(Double.parseDouble(valor_activos.getText()));

        try {
            // Converter data de String (dd/mm/aaaa) para java.sql.Date (yyyy-MM-dd)
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataFormatada = formato.parse(data_activos.getText());
            java.sql.Date dataSql = new java.sql.Date(dataFormatada.getTime());
            a.setDataActivos(dataSql);

            adao.create(a);
            JOptionPane.showMessageDialog(null, "Activo adicionado com sucesso!");

            // Limpar campos após o cadastro
            tipo_activos.setText("");
            valor_activos.setText("");
            data_activos.setText("");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro no formato da data! Use dd/mm/aaaa.");
        }
    }
}
