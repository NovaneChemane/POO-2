/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import model.bean.User;
import model.bean.Usuario;
import model.dao.UserDAO;
import model.dao.UsuarioDAO;

class TelaLogin {

    private  JLabel[] label = new JLabel[8];
    private  JPanel panelfundo;
    private  JPanel panelprincipal;
    private  JPasswordField password = new JPasswordField("1");
    private  JTextField email = new JTextField("p@gmail.com");
    private  JButton button = new JButton("LOGIN");

    public void abrirTelaLogin() {
        JPanel panelfundo = new JPanel() {
            ImageIcon image = new ImageIcon("C:\\Users\\LENOVO\\Desktop\\HouseHolder\\src\\grande.JPG");

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        JFrame janela = new JFrame("HOUSE HOLDER");
        for (int i = 0; i < label.length; i++) {
            label[i] = new JLabel();
        }
        panelfundo.setLayout(null);
        //criacao do painel que constarao os dados de login
        panelprincipal = new JPanel();
        panelprincipal.setLayout(null);
        panelprincipal.setBounds(150, 100, 420, 340);
        panelprincipal.setBackground(new Color(192, 192, 192, 150));
        panelprincipal.setOpaque(true);
        label[5].setText("LOGIN");
        label[5].setFont(new Font("TimesNewRoman", Font.BOLD, 25));
        label[5].setBounds(171, 15, 77, 25);
        label[1].setText("E-mail");
        label[1].setFont(new Font("TimesNewRoman", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        label[1].setBounds(35, 35, 100, 20);
        label[2].setText("Senha");
        email.setBounds(35, 60, 350, 40);
        email.setFont(new Font("TimesNewRoman", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        label[2].setFont(new Font("TimesNewRoman", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        label[2].setBounds(35, 110, 70, 20);
        password.setBounds(35, 135, 350, 40);
        password.setFont(new Font("TimesNewRoman", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        label[3].setText("Esqueceu a senha?");
        label[3].setBounds(35, 230, 113, 20);
        label[4].setText("recuperar senha");
        label[4].setForeground(Color.blue);
        label[4].setBounds(150, 230, 100, 20);
        label[4].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBounds(35, 190, 350, 30);
        button.setBackground(Color.LIGHT_GRAY);
        label[6].setText("Ainda não tem conta?");
        label[6].setBounds(150, 290, 155, 20);
        label[6].setFont(new Font("TimesNewRoman", Font.LAYOUT_RIGHT_TO_LEFT, 14));
        label[7].setText("<HTML><U>Criar conta</U><HTML>");
        label[7].setForeground(Color.blue);
        label[7].setBounds(310, 290, 65, 20);
        label[7].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailT = email.getText();
                char[] pass = password.getPassword();
                String senhaT = new String(pass);
                User user = new User();
                user.setEmail(emailT);
                user.setPass(senhaT);
                UserDAO ud = new UserDAO();

                if (ud.selectUser(user)) {
                   // JOptionPane.showMessageDialog(null, "Login feito com sucesso", "Confirmado", JOptionPane.INFORMATION_MESSAGE);
                    Tela_Principal tp = new Tela_Principal(emailT);
     

                } else {
                    JOptionPane.showMessageDialog(null, "Email ou senha invalidos", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        
        
        label[7].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                telaCadastro tc = new telaCadastro();
                tc.abrirTelaCadastro(janela);  // Passa a própria instância do JFrame atual
            }
        });
        
        label[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                telaEsqueceuSenha te = new telaEsqueceuSenha();
                te.abrirtelaEsqueceuSenha();  // Passa a própria instância do JFrame atual
            }
        });

        panelprincipal.add(button);
        panelprincipal.add(label[3]);
        panelprincipal.add(label[4]);
        panelprincipal.add(email);
        panelprincipal.add(password);
        panelprincipal.add(label[1]);
        panelprincipal.add(label[2]);
        panelprincipal.add(label[5]);
        panelprincipal.add(label[6]);
        panelprincipal.add(label[7]);
        //label SLOGAN
        label[0].setBounds(150, 50, 420, 25);
        label[0].setText("  HOUSEHOLDER A TUA FERRAMENTA DE POUPANÇA");
        label[0].setFont(new Font("TimesNewRoman", Font.BOLD, 16));
        panelfundo.add(label[0]);
        panelfundo.add(panelprincipal);
        janela.add(panelfundo);

        janela.setSize(720, 580);
        //pack();
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        janela.setResizable(false);
        janela.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    

   
}