package view;

import java.util.List;
import controller.ProdutoDAO;
import java.awt.GridLayout;
import model.Produto;
import javax.swing.*;
import model.Usuario;

public class TelaUsuarioInicial extends JFrame {
    private static src.Main mainFrame;
//    private int selectedPanel = 1;
    
    public TelaUsuarioInicial(src.Main mainFrame) {
        this.mainFrame = mainFrame;
        this.defaultPanel = new javax.swing.JScrollPane();

        setTitle("Mercado: inicio");
        initComponents();

        Usuario u = mainFrame.getCurrentUser();
        txtLoggedUser.setText(u != null ? "("+u.getId()+"):"+u.getName() : "error");

        defaultPanel.requestFocusInWindow();
        bthomeMouseReleased(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        bt1perfil = new javax.swing.JButton();
        bt3fav = new javax.swing.JButton();
        bt4carrinho = new javax.swing.JButton();
        bt5compras = new javax.swing.JButton();
        bt6sair = new javax.swing.JButton();
        txtLoggedUser = new javax.swing.JLabel();
        defaultPanel = new javax.swing.JScrollPane();
        bthome = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bt1perfil.setText("meu perfil");
        bt1perfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt1perfilActionPerformed(evt);
            }
        });

        bt3fav.setText("favoritos");
        bt3fav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt3favActionPerformed(evt);
            }
        });

        bt4carrinho.setText("carrinho");

        bt5compras.setText("minhas compras");

        bt6sair.setText("sair");
        bt6sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt6sairActionPerformed(evt);
            }
        });

        txtLoggedUser.setText("...");

        bthome.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        bthome.setForeground(new java.awt.Color(0, 153, 255));
        bthome.setText("MERCADO");
        bthome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bthome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bthomeMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(bt6sair)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtLoggedUser, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(364, 364, 364))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(bthome)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt1perfil)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bt3fav)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bt4carrinho)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bt5compras)))
                    .addComponent(defaultPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt1perfil)
                    .addComponent(bt3fav)
                    .addComponent(bt4carrinho)
                    .addComponent(bt5compras)
                    .addComponent(bthome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(defaultPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLoggedUser, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt6sair))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt6sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt6sairActionPerformed
        System.out.println("dispose");
        this.dispose();
        mainFrame = new src.Main();
    }//GEN-LAST:event_bt6sairActionPerformed

    private void updateAllButton(){
        JButton bt[] = {bt1perfil, bt3fav, bt4carrinho, bt5compras};
        for (JButton b : bt) b.setEnabled(true);
    }
    
    private void bt1perfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt1perfilActionPerformed
        updateAllButton();
        defaultPanel.setViewportView(new UsuarioPerfil());
        bt1perfil.setEnabled(false);
    }//GEN-LAST:event_bt1perfilActionPerformed

    private void bthomeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bthomeMouseReleased
        updateAllButton();        
        defaultPanel.setViewportView(new BuscarProduto(mainFrame.getCurrentUser()));
    }//GEN-LAST:event_bthomeMouseReleased

    private void bt3favActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt3favActionPerformed
        updateAllButton();        
        defaultPanel.setViewportView(new FavoritosView(mainFrame.getCurrentUser()));
        bt3fav.setEnabled(false);
    }//GEN-LAST:event_bt3favActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(TelaUsuarioInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaUsuarioInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaUsuarioInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaUsuarioInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaUsuarioInicial(mainFrame).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt1perfil;
    private javax.swing.JButton bt3fav;
    private javax.swing.JButton bt4carrinho;
    private javax.swing.JButton bt5compras;
    private javax.swing.JButton bt6sair;
    private javax.swing.JLabel bthome;
    private javax.swing.JScrollPane defaultPanel;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel txtLoggedUser;
    // End of variables declaration//GEN-END:variables

}
