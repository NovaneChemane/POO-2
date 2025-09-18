/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import model.dao.DependentesDAO;

/**
 *
 * @author user
 */
public class Remover_Dependentes {

    private JFrame janela;
    private JTextField idDependente;
    private JButton botao_remover;

    public Remover_Dependentes(String email) {
        janela = new JFrame("Remover Dependentes");
        janela.setSize(300, 200);

        JPanel painel = new JPanel();
        painel.setLayout(null);

        //Criando os ocmponentes da interface
        JLabel label_id = new JLabel("ID do Dependente: ");
        label_id.setBounds(25, 50, 120, 25);
        painel.add(label_id);

        idDependente = new JTextField();
        idDependente.setBounds(150, 50, 120, 25);
        painel.add(idDependente);

        botao_remover = new JButton("Remover");
        botao_remover.setBounds(100, 105, 100, 35);
        painel.add(botao_remover);

        botao_remover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == botao_remover) {
                    if (idDependente.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Preencha o campo vazio, por favor!");
                    } else {
                        removerActivo(email);
                    }
                }

            }
        });

        janela.add(painel);
        janela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }

    //Método removerActivo
    public void removerActivo(String email) {
        int id = Integer.parseInt(idDependente.getText());
        DependentesDAO dedao = new DependentesDAO();
        boolean sucesso = dedao.delete(id, email);

        if (sucesso) {
            JOptionPane.showMessageDialog(janela, "Dependente removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(janela, "Remoção falhou! Dependente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    //Acção do botao_remover
}
