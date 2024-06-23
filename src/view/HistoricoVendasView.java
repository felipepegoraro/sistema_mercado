package view;

import controller.CompraDAO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Compra;

public class HistoricoVendasView extends javax.swing.JPanel {

    public HistoricoVendasView() {
        initComponents();
        fillTable();
    }
    
    private void fillTable(){
        CompraDAO d = new CompraDAO();
        ArrayList<Compra> compras =  d.listarTodasCompras();
        
        DefaultTableModel model = (DefaultTableModel) tableHistory.getModel();
        model.setRowCount(0);
        
        // preencher a tabela pegando os valores
        compras.forEach(compra -> {
           model.addRow(new Object[] { 
                compra.getId(),
                compra.getUser().getName(),
                compra.getTotalPrice(),
                compra.getPurchaseDate(),
                compra.getType()
            });
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableHistory = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(552, 411));

        tableHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Usuário", "Preço", "Data", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableHistory);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel2.setText("Histórico de vendas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableHistory;
    // End of variables declaration//GEN-END:variables
}
