package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import model.bean.User;
import model.bean.Usuario;
import model.bean.Validacao;
import model.dao.UserDAO;
import model.dao.UsuarioDAO;

class telaCadastro {

    static JButton confirmarButton;
   // Validacao valid = new Validacao();
    static JTextField nomeField, apelidoField, emailField, senhaField, confirmarSenhaField, numerodependentes;
    static JFormattedTextField dataNascimentoField; // Declara o campo de data de nascimento como JFormattedTextField

    public void abrirTelaCadastro(JFrame parent) {
        JDialog dialogCadastro = new JDialog(parent, "HOUSEHOLDER - Cadastro", true);

        JPanel panelPrincipal = new JPanel(null);
        panelPrincipal.setBounds(150, 100, 420, 340);
        panelPrincipal.setBackground(new Color(192, 192, 192, 150));

        JLabel titulo = new JLabel("CADASTRO");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(150, 20, 200, 30);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(20, 60, 100, 25);
        nomeField = new JTextField("p");
        nomeField.setBounds(100, 60, 120, 25);

        JLabel apelidoLabel = new JLabel("Apelido:");
        apelidoLabel.setBounds(230, 60, 100, 25);
        apelidoField = new JTextField("p");
        apelidoField.setBounds(290, 60, 100, 25);

        JLabel dataNascimentoLabel = new JLabel("Data de nascimento:");
        dataNascimentoLabel.setBounds(20, 100, 140, 25);

        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            dataNascimentoField = new JFormattedTextField(dateMask); // Inicializa como JFormattedTextField
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dataNascimentoField.setBounds(160, 100, 230, 25);

        JLabel Numero_Dependentes = new JLabel("Numero de dependentes: ");
        Numero_Dependentes.setBounds(30, 260, 100, 25);
        numerodependentes = new JTextField("1");
        numerodependentes.setBounds(140, 260, 100, 25);

        JLabel emailLabel = new JLabel("E-mail:");
        emailLabel.setBounds(20, 140, 100, 25);
        emailField = new JTextField("p@gmail.com");
        emailField.setBounds(100, 140, 290, 25);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(20, 180, 100, 25);
        senhaField = new JPasswordField();
        senhaField.setBounds(100, 180, 290, 25);

        JLabel confirmarSenhaLabel = new JLabel("Confirmar Senha:");
        confirmarSenhaLabel.setBounds(20, 220, 130, 25);
        confirmarSenhaField = new JPasswordField();
        confirmarSenhaField.setBounds(150, 220, 240, 25);

        confirmarButton = new JButton("Confirmar");
        confirmarButton.setBounds(180, 290, 100, 30);
        confirmarButton.setBackground(Color.LIGHT_GRAY);

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = senhaField.getText();
                String passwordConfirm = confirmarSenhaField.getText();
                String nome = nomeField.getText();
                String apelido = apelidoField.getText();
                String dataNascimento = dataNascimentoField.getText();
                String numDependentes = numerodependentes.getText();

                if (email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty() || nome.isEmpty() || dataNascimento.contains("_") || numDependentes.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.INFORMATION_MESSAGE);
                }
                int idade = Validacao.calcularIdade(dataNascimento);
                if (idade == -1 || idade < 19 || idade > 100) {
                    JOptionPane.showMessageDialog(null, "Data de nascimento incompleta ou inválida. Use o formato dd/MM/yyyy.");
                    return;
                }
                boolean vi = Validacao.validarEmail(email);
                while (vi == false) {
                    JOptionPane.showMessageDialog(null, "O email deve ser do formato nome@gmail.com", "Erro", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (Validacao.verificarSeEmailExiste(email)==true) {
                    JOptionPane.showMessageDialog(null, "O email ja foi cadastrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if(Validacao.validarSenha(password, passwordConfirm) == false){
                    JOptionPane.showMessageDialog(null, "A senha deve conter 6 caracteres e pelo menos 1 digito numero \n ambas senhas devem ser igauis", "Erro", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if(Validacao.validarDependentes(Integer.parseInt(numDependentes))==false){
                    JOptionPane.showMessageDialog(null, "Numero de dependentes Invalido", "Erro", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                Usuario us = new Usuario();
                User user = new User();
                us.setEmail(email);
                us.setPass(password);
                us.setConfirmarpass(passwordConfirm);
                us.setNome(nome);
                us.setApelido(apelido);
                us.setNumerodependentes(Integer.parseInt(numDependentes));
                us.setIdade(idade);
                user.setEmail(email);
                user.setPass(password);

                UsuarioDAO users = new UsuarioDAO();
                users.create(us);

                UserDAO userD = new UserDAO();

                userD.create(user);
            }

        });

        panelPrincipal.add(titulo);
        panelPrincipal.add(nomeLabel);
        panelPrincipal.add(nomeField);
        panelPrincipal.add(apelidoLabel);
        panelPrincipal.add(apelidoField);
        panelPrincipal.add(dataNascimentoLabel);
        panelPrincipal.add(dataNascimentoField);
        panelPrincipal.add(Numero_Dependentes);
        panelPrincipal.add(numerodependentes);
        panelPrincipal.add(emailLabel);
        panelPrincipal.add(emailField);
        panelPrincipal.add(senhaLabel);
        panelPrincipal.add(senhaField);
        panelPrincipal.add(confirmarSenhaLabel);
        panelPrincipal.add(confirmarSenhaField);
        panelPrincipal.add(confirmarButton);

        JPanel panelFundo = new JPanel() {
            ImageIcon image = new ImageIcon("C:\\Users\\LENOVO\\Desktop\\HouseHolder\\src\\cadastroP.JPG");

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        panelFundo.setLayout(null);
        panelFundo.add(panelPrincipal);
        dialogCadastro.setContentPane(panelFundo);
        dialogCadastro.setSize(720, 580);
        dialogCadastro.setLocationRelativeTo(null);
        dialogCadastro.setResizable(false);
        dialogCadastro.setVisible(true);
    }}

   