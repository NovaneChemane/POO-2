package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.bean.Dependentes;
import model.dao.DependentesDAO;

public class Adicionar_Dependentes {

    private JFrame janela;
    private JPanel painel;
    private JTextField nome, tipo;
    private JFormattedTextField data_activos;
    private JButton botao_adicionar;

    public Adicionar_Dependentes(String email) {
        janela = new JFrame("Adicionar Dependentes");
        janela.setSize(300, 215);

        painel = new JPanel();
        painel.setBackground(new Color(192, 192, 192, 150));
        painel.setLayout(null);

        JLabel label_nome = new JLabel("Nome: ");
        label_nome.setBounds(10, 40, 100, 25);
        painel.add(label_nome);

        nome = new JTextField();
        nome.setBounds(110, 40, 165, 25);
        painel.add(nome);

        JLabel label_tipo = new JLabel("Tipo: ");
        label_tipo.setBounds(10, 80, 100, 25);
        painel.add(label_tipo);

        tipo = new JTextField();
        tipo.setBounds(110, 80, 165, 25);
        painel.add(tipo);

        botao_adicionar = new JButton("Adicionar");
        botao_adicionar.setBounds(85, 140, 120, 25);
        painel.add(botao_adicionar);

        botao_adicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == botao_adicionar) {
                    if (nome.getText().isEmpty() || tipo.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos vazios, por favor!");
                    } else {
                        adicionarDependente(email);
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

    //Acção do botão adicionar
    //método adicionarDependente
    private void adicionarDependente(String email) {
        Dependentes d = new Dependentes();
        DependentesDAO depdao = new DependentesDAO();

        d.setNome(nome.getText());
        d.setTipo(tipo.getText());
        d.setEmailUsuario(email);

        depdao.create(d);
        JOptionPane.showMessageDialog(null, "Dependente adicionado com sucesso!");

        //Limpar campos após o cadastro
        nome.setText("");
        tipo.setText("");

    }

}
