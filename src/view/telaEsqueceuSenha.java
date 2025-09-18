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

public class telaEsqueceuSenha {
    public void abrirtelaEsqueceuSenha(){
        JDialog janela = new JDialog();
        janela.setSize(400, 240);
        janela.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(null);
        
        JLabel es = new JLabel("Recuperar Senha");
        es.setFont(new Font("Arial", Font.BOLD, 24));
        es.setBounds(100, 20, 200, 30);
        
        JLabel nomeL = new JLabel("Nome");
        JTextField nomeT = new JTextField();
        nomeL.setBounds(20, 60, 100, 25);
        nomeT.setBounds(160, 60, 190, 25);
        
        
        JLabel emailL = new JLabel("Email");
        JTextField emailT = new JTextField();
        
        emailL.setBounds(20, 100, 100, 25);
        emailT.setBounds(160, 100, 190, 25);
        
        JButton confirmar = new JButton("Recuperar");
        
        confirmar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            
                String nome = nomeT.getText();
                String email = emailT.getText();
                
                if(nome.isEmpty() || email.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.INFORMATION_MESSAGE);
                
                } else{
                    UsuarioDAO user = new UsuarioDAO();
                    boolean verificado = user.selectEmail(email, nome);
                    if(verificado == false){
                        JOptionPane.showMessageDialog(null, "Nome ou email invalidos", "Erro", JOptionPane.INFORMATION_MESSAGE);
                    } else{
                        telaNovosDados tnd = new telaNovosDados();
                        tnd.abrirTelaNovosDados(email, janela);
                        
                    }
                }
                
            }
        
    });
        
        confirmar.setBounds(130, 140, 150, 35);
        mainPanel.add(es);
        mainPanel.add(nomeL);
        mainPanel.add(nomeT);
        mainPanel.add(emailL);
        mainPanel.add(emailT);
        mainPanel.add(confirmar);
        
        
        //janela.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        janela.add(mainPanel);
        janela.setResizable(true);
        janela.setVisible(true);
        
    }
    

}
