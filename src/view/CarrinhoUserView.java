package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import model.*;
import controller.*;

public class CarrinhoUserView extends javax.swing.JPanel {
    private CarrinhoDeCompra car;
    private CarrinhoDeCompraDAO car_dao;
    private Usuario u;

    public CarrinhoUserView(Usuario u) {
        this.u = u;
        initComponents();
        fillTableWithUserCar();
        adicionarBotoesRemove();
    }

    private void fillTableWithUserCar(){
        if (u == null) return;

        DefaultTableModel model = (DefaultTableModel) jtbCarrinho.getModel();
        
        car_dao = new CarrinhoDeCompraDAO();
        car = car_dao.getCarrinhoFromId(u.getId());
        
        if (car != null){
            car.getItems().forEach((i) -> {
                model.addRow(new Object[]{i.getId(), i.getProduto().getName(), i.getQuantidade()});
            });
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
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Produto produto = new Produto(id);

                        System.out.println(car.getItems());
                        car.removerProduto(produto);
                        System.out.println(car.getItems());

                        model.removeRow(x);
                        adicionarBotoesRemove();

                        car_dao = new CarrinhoDeCompraDAO();
                        car_dao.removeItemFromCarrinho(car.getId(), produto.getId());
                    }
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

        jtbCarrinho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "name", "quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtbCarrinho);
        if (jtbCarrinho.getColumnModel().getColumnCount() > 0) {
            jtbCarrinho.getColumnModel().getColumn(0).setPreferredWidth(10);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(removePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbCarrinho;
    private javax.swing.JPanel removePanel;
    // End of variables declaration//GEN-END:variables
}
