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
import model.bean.Despesas;
import model.dao.DespesasDAO;

/**
 *
 * @author Dell
 */
public class mostrarDetalhesDespesas {

    public mostrarDetalhesDespesas(String email) {
        JFrame detalhesFrame = new JFrame("Detalhes das Despesas");
        detalhesFrame.setSize(600, 400);
        detalhesFrame.setLayout(new BorderLayout());

        String[] colunas = {"IDDesignacao", "Designação", "Quantia Total", "Prazo Pagamento"};
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        JTable tabelaDetalhes = new JTable(modeloTabela);

        
        DespesasDAO despesasDAO = new DespesasDAO();
        List<Despesas> listaDespesas = despesasDAO.readDespesaUsuario(email);
      
        for (Despesas desp : listaDespesas) {
            Object[] linha = {
                desp.getIdDespesas(),
                desp.getDesignacao(),
                desp.getQuantiatotal(),
                desp.getPrazopagamento()
            };
            modeloTabela.addRow(linha);
        }

   
        JScrollPane scrollPane = new JScrollPane(tabelaDetalhes);
        detalhesFrame.add(scrollPane, BorderLayout.CENTER);

       
        detalhesFrame.setLocationRelativeTo(null);
        detalhesFrame.setVisible(true);
        
    }
    public static void main(String[] args) {
        new mostrarDetalhesDespesas("yuminaeliascossa@gmail.com");
    }
}
