/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.bean.Dependentes;
import model.bean.Despesas;
import model.dao.DependentesDAO;

/**
 *
 * @author Dell
 */
public class mostrarDetalhesDependentes {

    public mostrarDetalhesDependentes(String email) {
        
            JFrame detalhesFrame = new JFrame("Detalhes de Dependentes");
        detalhesFrame.setSize(600, 400);
        detalhesFrame.setLayout(new BorderLayout());

        String[] colunas = {"IDDependentes", "Nome", "Tipo"};
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        JTable tabelaDetalhes = new JTable(modeloTabela);

        
        DependentesDAO despesasDAO = new DependentesDAO();
        List<Dependentes> listaDependentes = despesasDAO.readDependenteUsuario(email);
      
        for (Dependentes dependente: listaDependentes) {
            Object[] linha = {
                dependente.getIdDependente(),
                dependente.getNome(),
                dependente.getTipo(),
              
            };
            modeloTabela.addRow(linha);
        }

   
        JScrollPane scrollPane = new JScrollPane(tabelaDetalhes);
        detalhesFrame.add(scrollPane, BorderLayout.CENTER);

       
        detalhesFrame.setLocationRelativeTo(null);
        detalhesFrame.setVisible(true);
        
    }
    
    public static void main(String[] args) {
        new mostrarDetalhesDependentes("yuminaeliascossa@gmail.com");
    }
    }
    

