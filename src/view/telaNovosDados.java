/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author LENOVO
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.dao.UsuarioDAO;

public class telaNovosDados {

    public void abrirTelaNovosDados(String email, JDialog janela) {
        janela.getContentPane().removeAll();
        janela.setSize(450, 240);
        janela.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(null);

        JLabel es = new JLabel("Repor senha");
        es.setFont(new Font("Arial", Font.BOLD, 24));
        es.setBounds(130, 20, 200, 30);

        JLabel senhaL = new JLabel("Digite a nova senha");
        JPasswordField senhaT = new JPasswordField();
        senhaL.setBounds(20, 60, 120, 25);
        senhaT.setBounds(190, 60, 190, 25);

        JLabel confsenhaL = new JLabel("Confirme a nova senha");
        JPasswordField confsenhaT = new JPasswordField();

        confsenhaL.setBounds(20, 100, 130, 25);
        confsenhaT.setBounds(190, 100, 190, 25);

        JButton confirmar = new JButton("Repor");

        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                char[] passwordChars = senhaT.getPassword();
                String password = new String(passwordChars);

                char[] passwordChars2 = confsenhaT.getPassword();
                String confpassword = new String(passwordChars2);

                if (password.isEmpty() || confpassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else if (!password.equals(confpassword)) {
                    JOptionPane.showMessageDialog(null, "As senhas devem ser iguais", "Erro", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    UsuarioDAO ud = new UsuarioDAO();
                    ud.updateSenha(email, password, confpassword);
                    JOptionPane.showMessageDialog(null, "Senha alterada com sucesso", "Aprovado", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        confirmar.setBounds(130, 140, 150, 35);
        mainPanel.add(es);
        mainPanel.add(senhaL);
        mainPanel.add(senhaT);
        mainPanel.add(confsenhaL);
        mainPanel.add(confsenhaT);
        mainPanel.add(confirmar);

        //janela.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        janela.add(mainPanel);
        janela.setResizable(true);
        janela.setVisible(true);

    }

    public static void novosDadosConta() {
        JDialog janela = new JDialog();

    }
}
