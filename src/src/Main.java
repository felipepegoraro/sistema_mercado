package src;

import java.awt.BorderLayout;
import javax.swing.*;
import model.Usuario;
//import java.awt.*;
//import java.awt.event.*;

public class Main extends JFrame /*implements ActionListener*/ {
    public JPanel currentPanel;
    public JPanel oldPanel = null;
    public JFrame frame = null;
    private Usuario currentUser = null;
    
    // o painel inicial será o de *Login*
    private void configureInitialPanel(){
        setTitle("Mercado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        changePanel(new view.LoginView(this));

        setSize(600, 530);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void changePanel(JPanel p){
        oldPanel = this.currentPanel;
        getContentPane().removeAll();
        getContentPane().add(p, BorderLayout.CENTER);
        currentPanel = p;
        revalidate();
        repaint();
    }
    
    public void showTelaUsuarioInicial() {
        frame = this;
        frame.getContentPane().removeAll();
        frame.dispose();
        frame = new view.TelaUsuarioInicial(this);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void showLoginView() {
        frame.getContentPane().removeAll();
        frame.add(new view.LoginView(this));
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    public void setCurrentUser(Usuario user){
        this.currentUser = user;
    }
    public Usuario getCurrentUser(){
        return this.currentUser;
    }
    
    public Main() {    
        configureInitialPanel();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
