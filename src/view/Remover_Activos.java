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
import model.dao.ActivosDAO;

/**
 *
 * @author user
 */
public class Remover_Activos {

    private JFrame janela;
    private JTextField idActivo;
    private JButton botao_remover;

    public Remover_Activos(String email) {
        janela = new JFrame("Remover Activos");
        janela.setSize(300, 200);

        JPanel painel = new JPanel();
        painel.setLayout(null);

        //Criando os ocmponentes da interface
        JLabel label_id = new JLabel("ID do Activo: ");
        label_id.setBounds(25, 50, 100, 25);
        painel.add(label_id);

        idActivo = new JTextField();
        idActivo.setBounds(125, 50, 125, 25);
        painel.add(idActivo);

        botao_remover = new JButton("Remover");
        botao_remover.setBounds(100, 105, 100, 35);
        painel.add(botao_remover);

        botao_remover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == botao_remover) {
                    if (idActivo.getText().isEmpty()) {
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
        int id = Integer.parseInt(idActivo.getText());
        ActivosDAO adao = new ActivosDAO();
        boolean sucesso = adao.delete(id, email);

        if (sucesso) {
            JOptionPane.showMessageDialog(janela, "Activo removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(janela, "Remoção falhou! Activo não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    //Acção do botao_remover
}
