package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.text.MaskFormatter;
import model.bean.Dividas;
import model.dao.DividasDAO;

public class Adicionar_Dividas {

    private JFrame janela;
    private JPanel painel;
    private JTextField designacao, valor;
    private JFormattedTextField data;
    private JButton botao_adicionar;

    public Adicionar_Dividas(String email) throws ParseException {
        janela = new JFrame("Adicionar Dívidas");
        janela.setSize(300, 215);

        painel = new JPanel();
        painel.setBackground(new Color(192, 192, 192, 150));
        painel.setLayout(null);

        JLabel label_designacao = new JLabel("Designação: ");
        label_designacao.setBounds(10, 20, 100, 25);
        painel.add(label_designacao);

        designacao = new JTextField();
        designacao.setBounds(110, 20, 165, 25);
        painel.add(designacao);

        JLabel label_valor = new JLabel("Valor (MNZ): ");
        label_valor.setBounds(10, 60, 130, 25);
        painel.add(label_valor);

        valor = new JTextField();
        valor.setBounds(110, 60, 165, 25);
        painel.add(valor);

        JLabel label_data = new JLabel("Prazo de Pagamento: ");
        label_data.setBounds(10, 100, 130, 25);
        painel.add(label_data);

        MaskFormatter dateMask = new MaskFormatter("##/##/####");
        dateMask.setPlaceholderCharacter('_');
        data = new JFormattedTextField(dateMask);
        data.setBounds(140, 100, 135, 25);
        painel.add(data);

        botao_adicionar = new JButton("Adicionar");
        botao_adicionar.setBounds(85, 140, 120, 25);
        painel.add(botao_adicionar);

        botao_adicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == botao_adicionar) {
                    if (designacao.getText().isEmpty() || valor.getText().isEmpty() || data.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos vazios, por favor");
                    } else {
                        adicionarDivida(email);
                    }
                }
            }
        });

        janela.add(painel);
        janela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        janela.setVisible(true);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);
    }

    //Método adicionarDividas
    public void adicionarDivida(String email) {
        Dividas div = new Dividas();
        DividasDAO divdao = new DividasDAO();

        div.setDesigna(designacao.getText());
        div.setValor(Double.parseDouble(valor.getText()));

        try {
            // Converter data de String (dd/mm/aaaa) para java.sql.Date (yyyy-MM-dd)
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataFormatada = formato.parse(data.getText());
            java.sql.Date dataSql = new java.sql.Date(dataFormatada.getTime());
            div.setDatadividas(dataSql);
            div.setEmailUsuario(email);

            divdao.create(div);
            JOptionPane.showMessageDialog(null, "Dívida adicionada com sucesso!");

            // Limpar campos após o cadastro
            designacao.setText("");
            valor.setText("");
            data.setText("");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro no formato da data! Use dd/mm/aaaa.");
        }
    }

    //Adicionando a acção do botão adicionar
}
