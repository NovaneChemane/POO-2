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
import model.bean.Dividas;
import model.dao.DividasDAO;

/**
 *
 * @author Dell
 */
public class mostrarDetalhesDividas {

    public mostrarDetalhesDividas(String email) {
        
          JFrame detalhesFrame = new JFrame("Detalhes de Dívidas");
        detalhesFrame.setSize(600, 400);
        detalhesFrame.setLayout(new BorderLayout());

        String[] colunas = {"IDDívida", "Designação", "Valor","DataDívidas"};
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        JTable tabelaDetalhes = new JTable(modeloTabela);

        
        DividasDAO dividaDAO = new DividasDAO();
        List<Dividas> listaDividas = dividaDAO.readDividasUsuario(email);
      
        for (Dividas divida: listaDividas) {
            Object[] linha = {
                divida.getIdDividas(),
                divida.getDesigna(),
                divida.getValor(),
                divida.getDatadividas()
            };
            modeloTabela.addRow(linha);
        }
        JScrollPane scrollPane = new JScrollPane(tabelaDetalhes);
        detalhesFrame.add(scrollPane, BorderLayout.CENTER);

       
        detalhesFrame.setLocationRelativeTo(null);
        detalhesFrame.setVisible(true);
    }
    
}
