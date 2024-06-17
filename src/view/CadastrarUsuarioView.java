package view;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import model.User;
import controller.UsuarioDAO;
import java.util.ArrayList;

public class CadastrarUsuarioView extends javax.swing.JPanel {

    private src.Main mainFrame;

    public CadastrarUsuarioView(src.Main main) {
        this.mainFrame = main;

        setSize(600, 530);
        setVisible(true);

        initComponents();

        System.out.println("ok");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgLogo = new javax.swing.JLabel();
        btVoltar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        txtConfirmarSenha = new javax.swing.JPasswordField();
        btCadastrarUsuario = new javax.swing.JButton();
        txtErrorLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(600, 530));

        imgLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo.5.png"))); // NOI18N

        btVoltar.setText("voltar");
        btVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });

        jLabel2.setText("Nome");

        jLabel3.setText("Email");

        jLabel4.setText("Senha");

        jLabel5.setText("Confirmar senha");

        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });

        btCadastrarUsuario.setText("Cadastrar-se");
        btCadastrarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCadastrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(btCadastrarUsuario)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(imgLogo)
                        .addGap(62, 62, 62)
                        .addComponent(btVoltar)
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(95, 95, 95)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5)))))
                            .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(88, 88, 88))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btVoltar)
                    .addComponent(imgLogo))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(btCadastrarUsuario)
                .addContainerGap(110, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private boolean isValidSignIn() {
        System.out.println("validando...");
        JTextField[] textFields = {txtName, txtEmail, txtSenha, txtConfirmarSenha};
        boolean isEmpty = false;

        // 1. verificar se campos sao vazios
        for (JTextField textField : textFields) {
            if (textField.getText().isEmpty()) {
                textField.setBorder(BorderFactory.createLineBorder(Color.RED));
                isEmpty = true;
            } else {
                textField.setBorder(null);
            }
        }

        if (isEmpty) {
            txtErrorLabel.setText("preencha todos os campos.");
            return false;
        }
        
        String passwd = txtSenha.getText();
        String passwd2 = txtConfirmarSenha.getText();

        // 2. verificar se senhas sao diferentes
        if (!passwd.equals(passwd2)){
            txtSenha.setBorder(BorderFactory.createLineBorder(Color.RED));
            txtConfirmarSenha.setBorder(BorderFactory.createLineBorder(Color.RED));
            txtSenha.setText("");
            txtConfirmarSenha.setText("");
            txtErrorLabel.setText("senhas invalidas");
            return false;
        }
        
        // 3. verificar se usuario existe baseado no email!
        UsuarioDAO dao = new UsuarioDAO();
        User u = dao.getUserByEmail(txtEmail.getText());
        if (u != null){
            // usuario com email ja existe.
            txtErrorLabel.setText("usuario ja existe.");
            txtEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
            return false;
        }
        
        System.out.println("ok!");
        return true;
    }

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        mainFrame.changePanel(mainFrame.oldPanel);
    }//GEN-LAST:event_btVoltarActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void btCadastrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarUsuarioActionPerformed
        JTextField[] textFields = {txtName, txtEmail, txtSenha, txtConfirmarSenha};
        if (isValidSignIn()){
            User newUser = new User(
                txtName.getText(),
                txtEmail.getText(),
                txtSenha.getText(),
                new ArrayList<>()
            );
            
            UsuarioDAO dao = new UsuarioDAO();
    
            if (dao.insertUser(newUser) == 0){
                mainFrame.setCurrentUser(dao.getUserByEmail(txtEmail.getText()));
                for (JTextField textField : textFields)
                    textField.setText("");
                
                mainFrame.showTelaUsuarioInicial();
            }
        }
    }//GEN-LAST:event_btCadastrarUsuarioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadastrarUsuario;
    private javax.swing.JButton btVoltar;
    private javax.swing.JLabel imgLogo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField txtConfirmarSenha;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JLabel txtErrorLabel;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
