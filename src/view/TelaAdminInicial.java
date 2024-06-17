package view;
import model.User;
import java.awt.*;
import javax.swing.*;

public class TelaAdminInicial extends javax.swing.JFrame {
    static private src.Main mainFrame;
    
    public TelaAdminInicial(src.Main main) {
        initComponents();
        this.mainFrame = main;
        setTitle("Mercado: admin page");
        this.jScrollPane1.setViewportView(new GerenciarProdutosView());
    }

    private void updateAllButtons(JButton bt){
        JButton bts[] = {btGProd, btGUsers, btHisVenda, btSair};
        for (JButton b : bts) b.setEnabled(true);
        bt.setEnabled(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        btSair = new javax.swing.JButton();
        btGUsers = new javax.swing.JButton();
        btHisVenda = new javax.swing.JButton();
        btGProd = new javax.swing.JButton();
        bthome3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btSair.setText("x");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        btGUsers.setText("Usuarios");
        btGUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGUsersActionPerformed(evt);
            }
        });

        btHisVenda.setText("Historico de Vendas");
        btHisVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHisVendaActionPerformed(evt);
            }
        });

        btGProd.setText("Gerenciar Produtos");
        btGProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGProdActionPerformed(evt);
            }
        });

        bthome3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        bthome3.setForeground(new java.awt.Color(0, 153, 255));
        bthome3.setText("MERCADO");
        bthome3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bthome3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bthome3MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bthome3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(btGProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btHisVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btGUsers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSair)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSair)
                    .addComponent(btGUsers)
                    .addComponent(btHisVenda)
                    .addComponent(btGProd)
                    .addComponent(bthome3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        this.dispose();
        mainFrame = new src.Main();
    }//GEN-LAST:event_btSairActionPerformed

    private void btHisVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHisVendaActionPerformed
        updateAllButtons(btHisVenda);
        this.jScrollPane1.setViewportView(new HistoricoVendasView());
    }//GEN-LAST:event_btHisVendaActionPerformed

    private void btGProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGProdActionPerformed
        updateAllButtons(btGProd);
        this.jScrollPane1.setViewportView(new GerenciarProdutosView());
    }//GEN-LAST:event_btGProdActionPerformed

    private void bthome3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bthome3MouseReleased
        updateAllButtons(btGUsers); btGUsers.setEnabled(true);
        this.jScrollPane1.setViewportView(new GerenciarProdutosView());
    }//GEN-LAST:event_bthome3MouseReleased

    private void btGUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGUsersActionPerformed
        updateAllButtons(btGUsers);
        this.jScrollPane1.setViewportView(new GerenciarUsuariosView());
    }//GEN-LAST:event_btGUsersActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaAdminInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAdminInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAdminInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAdminInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAdminInicial(mainFrame).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btGProd;
    private javax.swing.JButton btGUsers;
    private javax.swing.JButton btHisVenda;
    private javax.swing.JButton btSair;
    private javax.swing.JLabel bthome3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
