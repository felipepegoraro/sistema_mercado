package view;

import model.User;
import model.Produto;
import controller.ProdutoDAO;
import java.util.List;
import javax.swing.*;
import java.awt.GridLayout;

public class FavoritosView extends javax.swing.JPanel {
    // alterar para JList ou JTable.
    public FavoritosView(User user) {
        initComponents();
        
        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> l = dao.getFavoriteItemsListFromUser(user);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(l.size() < 2 ? 1 : 0, 2));
        
        l.forEach((p) -> panel.add(new ProductPanel(user, p)));
        pPanel.setViewportView(panel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pPanel = new javax.swing.JScrollPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane pPanel;
    // End of variables declaration//GEN-END:variables
}
