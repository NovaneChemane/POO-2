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
import model.bean.User;
import model.dao.ActivosDAO;
import model.dao.DependentesDAO;
import model.dao.DespesasDAO;
import model.dao.DividasDAO;
import model.dao.UserDAO;
import model.dao.UsuarioDAO;

/**
 *
 * @author user
 */
public class ApagarConta {

    private JFrame janela;
    private JPasswordField senhaT;
    private JTextField emailT;
    private JButton botao_remover;

    public ApagarConta() {
        janela = new JFrame("Apagar Conta");
        janela.setSize(300, 200);

        JPanel painel = new JPanel();
        painel.setLayout(null);

        //Criando os ocmponentes da interface
        JLabel emailL = new JLabel("Email da conta: ");
        emailL.setBounds(25, 50, 100, 25);
        painel.add(emailL);

        emailT = new JTextField();
        emailT.setBounds(125, 50, 125, 25);
        painel.add(emailT);

        JLabel senhaL = new JLabel("Senha da conta: ");
        senhaL.setBounds(25, 80, 100, 25);
        painel.add(senhaL);

        senhaT = new JPasswordField();
        senhaT.setBounds(125, 80, 125, 25);
        painel.add(senhaT);

        botao_remover = new JButton("Apagar");
        botao_remover.setBounds(100, 115, 100, 35);
        painel.add(botao_remover);

        botao_remover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailT.getText();

                char[] arr = senhaT.getPassword();
                String senha = new String(arr);

                if (email.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha o campo vazio, por favor!");
                } else {
                    UserDAO ud = new UserDAO();
                    User user = new User();
                    user.setEmail(email);
                    user.setPass(senha);
                    if (ud.selectUser(user)) {
                        apagarConta(user);
                    }
                }

            }
        });

        janela.add(painel);
        janela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }

    //Método apagarConta
    public void apagarConta(User user) {

        UsuarioDAO uda = new UsuarioDAO();
        uda.apagarDadosUser(user);
        UserDAO ud = new UserDAO();

        boolean ac = ud.apagarDadosUser(user);
        ActivosDAO activos = new ActivosDAO();

        activos.apagarDadosUser(user);

        DependentesDAO dd = new DependentesDAO();

        dd.apagarDadosUser(user);

        DespesasDAO desp = new DespesasDAO();
        activos.apagarDadosUser(user);

        DividasDAO div = new DividasDAO();
        div.apagarDadosUser(user);

        if (ac == false) {

            JOptionPane.showMessageDialog(null, "Conta apagada com sucesso!", "Confirmado", JOptionPane.INFORMATION_MESSAGE);
            janela.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Conta nao encontrada: ", "Erro", JOptionPane.ERROR_MESSAGE);
            janela.dispose();
        }

    }

}
