package view;

import model.Produto;
import model.Usuario;
import model.ItemCarrinho;
import model.CarrinhoDeCompra;
import controller.UsuarioDAO;

import javax.swing.JPanel;
import javax.swing.JComboBox;

public class ProductPanel extends javax.swing.JPanel {
    Produto produto;
    Usuario user;
    
    public ProductPanel(Usuario u, Produto p) {
        this.user = u;
        this.produto = p;
        byte[] img;
        
        initComponents();
        
        txtPname.setText(p.getName());
        txtPprice.setText("R$ " + String.valueOf(p.getPrice()));
        txtPstars.setText(String.valueOf(p.getRating()));
        txtPsupplier.setText(p.getSupplier());
        txtDesc.setText(p.getDescription());
        
        if ((img = p.getImage()) != null){
            imgImageLabel.setIcon(new javax.swing.ImageIcon(img));
        }
        
        changeHeartIcon();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgImageLabel = new javax.swing.JLabel();
        txtPname = new javax.swing.JLabel();
        txtPsupplier = new javax.swing.JLabel();
        btAddCart = new javax.swing.JButton();
        txtPprice = new javax.swing.JLabel();
        txtPstars = new javax.swing.JLabel();
        cbQuantidade = new javax.swing.JComboBox<>();
        txtDesc = new javax.swing.JLabel();
        imgFav = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        imgImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/default.25.png.png"))); // NOI18N
        imgImageLabel.setText("imagelabel");

        txtPname.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        txtPname.setText("name");

        txtPsupplier.setText("supplier");

        btAddCart.setText("add");
        btAddCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddCartActionPerformed(evt);
            }
        });

        txtPprice.setText("price");

        txtPstars.setText("stars");

        cbQuantidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        txtDesc.setText("description");

        imgFav.setText("fav");
        imgFav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                imgFavMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imgImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPname)
                            .addComponent(txtPprice)
                            .addComponent(txtPsupplier)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPstars)
                            .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addComponent(btAddCart, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(imgFav)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPname)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPprice)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPsupplier))
                    .addComponent(imgImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPstars)
                            .addComponent(imgFav))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(cbQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAddCart, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btAddCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddCartActionPerformed
        this.user.getCarrinho().adicionarProduto(this.produto, Integer.parseInt(cbQuantidade.getSelectedItem().toString()));
        System.out.println(this.user.getCarrinho());
    }//GEN-LAST:event_btAddCartActionPerformed

    private void changeHeartIcon(){
        boolean fav = user.getFavItems().contains(produto.getId());
        String heartImg = "/assets/" + (fav ? "fullheart.25.png" : "emptyheart.25.png");
        imgFav.setIcon(new javax.swing.ImageIcon(getClass().getResource(heartImg)));
    }
    
    private void imgFavMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgFavMouseReleased
        this.produto.setFavorito(!this.produto.isFavorito());
        int id = produto.getId();
        if (produto.isFavorito()){
            if (!user.getFavItems().contains(id)){
                user.setFavItems(id);
            }
        } else {
            user.getFavItems().removeIf((i) -> i == id);
        }
        System.out.println(user.getFavItems());
        
        UsuarioDAO dao = new UsuarioDAO();
        dao.updateFavList(user);
        changeHeartIcon();
    }//GEN-LAST:event_imgFavMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddCart;
    private javax.swing.JComboBox<String> cbQuantidade;
    private javax.swing.JLabel imgFav;
    private javax.swing.JLabel imgImageLabel;
    private javax.swing.JLabel txtDesc;
    private javax.swing.JLabel txtPname;
    private javax.swing.JLabel txtPprice;
    private javax.swing.JLabel txtPstars;
    private javax.swing.JLabel txtPsupplier;
    // End of variables declaration//GEN-END:variables
}
