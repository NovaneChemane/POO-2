package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.bean.Despesas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.text.MaskFormatter;
import model.dao.DespesasDAO;

public class Adicionar_Despesas {

    private JFrame janela;
    private JPanel painel;
    private JTextField designacao, quantiatotal;
    private JFormattedTextField prazopagamento;
    private JButton botao_adicionar;

    public Adicionar_Despesas(String email) throws ParseException {
        janela = new JFrame("Adicionar Despesas");
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

        JLabel label_quantia = new JLabel("Quantia Total (MNZ): ");
        label_quantia.setBounds(10, 60, 130, 25);
        painel.add(label_quantia);

        quantiatotal = new JTextField();
        quantiatotal.setBounds(140, 60, 135, 25);
        painel.add(quantiatotal);

        JLabel label_prazo = new JLabel("Prazo de Pagamento: ");
        label_prazo.setBounds(10, 100, 130, 25);
        painel.add(label_prazo);

        MaskFormatter dateMask = new MaskFormatter("##/##/####");
        dateMask.setPlaceholderCharacter('_');
        prazopagamento = new JFormattedTextField(dateMask);
        prazopagamento.setBounds(140, 100, 135, 25);
        painel.add(prazopagamento);

        botao_adicionar = new JButton("Adicionar");
        botao_adicionar.setBounds(85, 140, 120, 25);
        painel.add(botao_adicionar);

        botao_adicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == botao_adicionar) {
                    if (designacao.getText().isEmpty() || quantiatotal.getText().isEmpty() || prazopagamento.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos vazios, por favor!");
                    } else {
                        adicionarDespesa(email);

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

    //Método adicionarDespesa
    private void adicionarDespesa(String email) {
        Despesas des = new Despesas();
        DespesasDAO desdao = new DespesasDAO();

        des.setDesignacao(designacao.getText());
        des.setQuantiatotal(Double.parseDouble(quantiatotal.getText()));

        try {
            // Converter prazopagamento de String (dd/mm/aaaa) para java.sql.Date (yyyy-MM-dd)
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataFormatada = formato.parse(prazopagamento.getText());
            java.sql.Date dataSql = new java.sql.Date(dataFormatada.getTime());
            des.setPrazopagamento(dataSql);
            des.setEmailUsuario(email);

            desdao.create(des);
            JOptionPane.showMessageDialog(null, "Despesa adicionado com sucesso!");

            // Limpar campos após o cadastro
            designacao.setText("");
            quantiatotal.setText("");
            prazopagamento.setText("");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro no formato da data! Use dd/mm/aaaa.");
        }
    }
}

//Acção do botão adicionar 

