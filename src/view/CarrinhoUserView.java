package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import model.*;
import controller.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
        
public class CarrinhoUserView extends javax.swing.JPanel {
    private CarrinhoDeCompra car;
    private CarrinhoDeCompraDAO car_dao;
    private User u;

    public CarrinhoUserView(User u) {
        this.u = u;
        initComponents();
        updateTable();
    }

    private void updateTable(){
        fillTableWithUserCar();
        adicionarBotoesRemove();
    }
    
    private void fillTableWithUserCar(){
        if (u == null) return;

        DefaultTableModel model = (DefaultTableModel) jtbCarrinho.getModel();
        model.setRowCount(0);
        
        car_dao = new CarrinhoDeCompraDAO();
        car = car_dao.getCarrinhoFromId(u.getId());
        
        if (car != null){
            car.getItems().forEach((i) -> {
                model.addRow(new Object[]{
                    i.getId(), i.getProduto().getName(), i.getQuantidade(),
                    "R$ " + i.getProduto().getPrice(),
                    "R$ " + i.getProduto().getPrice() * i.getQuantidade()
                });
            });
        }
        
        updateTotalPriceString();
    }
    
    private void updateTotalPriceString(){
        if (car != null) {
            txtTotalPrice.setText("R$ " + Float.toString(u.getCarrinho().getTotalPrice()));
        }
    }
    
    private void adicionarBotoesRemove() {
        removePanel.removeAll();
        removePanel.setLayout(new GridLayout(0, 1));

        DefaultTableModel model = (DefaultTableModel) jtbCarrinho.getModel();
        int rowCount = model.getRowCount();
        System.out.println(rowCount);
        jtbCarrinho.setRowHeight(26);

        for (int i = 0; i < rowCount; i++) {
            int id = (int) model.getValueAt(i, 0);
            String name = (String) model.getValueAt(i, 1);
            int qnt = (int) model.getValueAt(i, 2);
            
            int x = i;
            if (id != -1 && name !=  null && qnt != -1){
                JButton button = new JButton("Remove");
                button.addActionListener((ActionEvent e) -> {
                    Produto produto = new Produto(id);
                    
                    System.out.println(car.getItems());
                    car.removerProduto(produto);
                    System.out.println(car.getItems());
                    
                    model.removeRow(x);
                    adicionarBotoesRemove();
                    
                    car_dao = new CarrinhoDeCompraDAO();
                    car_dao.removeItemFromCarrinho(car.getId(), produto.getId());
                    updateTotalPriceString();
                });

                removePanel.add(button);
            }
        }

        revalidate(); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtbCarrinho = new javax.swing.JTable();
        removePanel = new javax.swing.JPanel();
        txtTotalPrice = new javax.swing.JLabel();
        btFinalizaCompra = new javax.swing.JButton();

        jtbCarrinho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "name", "quantity", "unit price", "total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtbCarrinho);
        if (jtbCarrinho.getColumnModel().getColumnCount() > 0) {
            jtbCarrinho.getColumnModel().getColumn(0).setPreferredWidth(2);
            jtbCarrinho.getColumnModel().getColumn(1).setPreferredWidth(7);
            jtbCarrinho.getColumnModel().getColumn(2).setPreferredWidth(3);
            jtbCarrinho.getColumnModel().getColumn(3).setPreferredWidth(7);
            jtbCarrinho.getColumnModel().getColumn(4).setPreferredWidth(8);
        }

        javax.swing.GroupLayout removePanelLayout = new javax.swing.GroupLayout(removePanel);
        removePanel.setLayout(removePanelLayout);
        removePanelLayout.setHorizontalGroup(
            removePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );
        removePanelLayout.setVerticalGroup(
            removePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );

        txtTotalPrice.setText("a");

        btFinalizaCompra.setText("Finalizar Compra");
        btFinalizaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizaCompraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(btFinalizaCompra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotalPrice)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(removePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btFinalizaCompra)
                    .addComponent(txtTotalPrice))
                .addContainerGap(9, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btFinalizaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizaCompraActionPerformed
        // TODO: criar tela de finalizar compra, selecionar credito, debito, dinheiro.
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        Compra c = new Compra(u, car.getTotalPrice(), dateFormat.format(new Date()), "debito");
        try {
            c.finalizaCompra();
            CarrinhoDeCompraDAO dao = new CarrinhoDeCompraDAO();
            dao.limparCarrinhoDoBanco(u.getCarrinho());
            updateTable();
        } catch (ParseException ex) {
            Logger.getLogger(CarrinhoUserView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btFinalizaCompraActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFinalizaCompra;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbCarrinho;
    private javax.swing.JPanel removePanel;
    private javax.swing.JLabel txtTotalPrice;
    // End of variables declaration//GEN-END:variables
}
