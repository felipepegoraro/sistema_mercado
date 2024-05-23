package view;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import model.Usuario;
import controller.UsuarioDAO;

public class LoginView extends javax.swing.JPanel {
    private src.Main mainFrame;
        
    public LoginView(src.Main main) {
        this.mainFrame = main;
        
        setSize(600, 530);
        setVisible(true);
        
        initComponents();
    }
    
    private boolean isLoginValid(){
        // 1. login vazio
        boolean isEmpty = false;
        JTextField f[] = {txtEmail, txtPassword};
        for (JTextField field : f){
            if (field.getText().isEmpty()){
                isEmpty = true;
                field.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
        
        if (isEmpty){
            txtLoginErrorMsg.setText("preencha todos os campos.");
            return false;
        }
        
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        boolean emailCadastrado = false;
        UsuarioDAO dao = new UsuarioDAO();
        
        // 2. verificar se usuario existe no banco.
        System.out.println("a.1");
        if (dao.getUserByEmail(email) != null) emailCadastrado = true;
        if (!emailCadastrado) txtEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
        
        // 3. ver se senha esta correta.
        System.out.println("a.2");
        Usuario currentUser = dao.matchUserLogin(email, password);
        if (currentUser != null){ // login ok
            mainFrame.setCurrentUser(currentUser);
            return true;
        }
        else {
            txtLoginErrorMsg.setText( 
                emailCadastrado 
                ? "senha incorreta. tente novamente"
                : "login inválido / não cadastrado."
            );
            
            if (emailCadastrado) txtPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
            return false;
        }   
    }
    
    private void goToInitialPage(){
        System.out.println("a");
        if (isLoginValid()){
            System.out.println("b");
            mainFrame.showTelaUsuarioInicial();
            System.out.println("c");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btLogin = new javax.swing.JButton();
        txtLoginErrorMsg = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("Cadastrar-se");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel1MouseReleased(evt);
            }
        });

        jLabel2.setText("Não tem conta?");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo.5.png"))); // NOI18N

        jLabel5.setText("Email");

        jLabel6.setText("Senha");

        btLogin.setText("login");
        btLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(btLogin)))
                .addContainerGap(198, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(txtEmail)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLoginErrorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel4)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addGap(34, 34, 34)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLoginErrorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(37, 37, 37))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseReleased
        mainFrame.oldPanel = this;
        mainFrame.changePanel(new CadastrarView(mainFrame));
    }//GEN-LAST:event_jLabel1MouseReleased

    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoginActionPerformed
        goToInitialPage();
    }//GEN-LAST:event_btLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JLabel txtLoginErrorMsg;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
