package view;
import controller.ProdutoDAO;
import model.*;
import java.awt.GridLayout;
import java.util.stream.Collectors;
import javax.swing.*;

public class BuscarProduto extends javax.swing.JPanel {
    private Usuario maybeUnusedUser;
    
    public BuscarProduto(Usuario u) {
        initComponents();
        
        this.maybeUnusedUser = u;
        mostrarProdutos(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtProductName = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        jButton1.setText("buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(txtProductName, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(21, 21, 21))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mostrarProdutos(String pname) {
        ProdutoDAO dao = new ProdutoDAO();        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));
        
        java.util.List<Produto> produtos = dao.getAllProducts();
        
        if (pname != null && !pname.isEmpty()) {
            produtos = produtos.stream()
                .filter(p -> p.getName().toLowerCase().contains(pname.toLowerCase()))
                .collect(Collectors.toList());
        }
            
        produtos.forEach(p -> panel.add(new ProductPanel(maybeUnusedUser, p)));    
        jScrollPane1.setViewportView(panel);
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String pname = txtProductName.getText();
        mostrarProdutos(pname);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtProductName;
    // End of variables declaration//GEN-END:variables
}
