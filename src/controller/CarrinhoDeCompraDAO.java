package controller;

import java.sql.*;
import model.CarrinhoDeCompra;
import model.ItemCarrinho;
import model.Produto;
import java.util.Arrays;

public class CarrinhoDeCompraDAO {
    private Connection con;
    private PreparedStatement cmd;
    private static final String carrinhoTable = "tb_mercado_carrinho";
    private static final String itemCarrinhoTable = "tb_mercado_item_carrinho";

    public CarrinhoDeCompraDAO() {
        this.con = Conexao.conectar();
    }
    
    public void saveCarrinho(CarrinhoDeCompra carrinho) {
        String checkQuery = "SELECT COUNT(*) FROM " + carrinhoTable + " WHERE id = ?";
        String updateCarrinhoQuery = "UPDATE " + carrinhoTable + " SET total_price = ?, quantity = ? WHERE id = ?";
        String insertCarrinhoQuery = "INSERT INTO " + carrinhoTable + " (id, usuario_id, total_price, quantity) VALUES (?, ?, ?, ?)";
        
        String checkItemQuery = "SELECT COUNT(*) FROM " + itemCarrinhoTable + " WHERE carrinho_id = ? AND produto_id = ?";
        String updateItemQuery = "UPDATE " + itemCarrinhoTable + " SET quantidade = ? WHERE carrinho_id = ? AND produto_id = ?";
        String insertItemQuery = "INSERT INTO " + itemCarrinhoTable + " (carrinho_id, produto_id, quantidade) VALUES (?, ?, ?)";
                            
        try {
            con.setAutoCommit(false);
            int userid = carrinho.getId();

            // se o carrinho existe
            cmd = con.prepareStatement(checkQuery);
            cmd.setInt(1, userid);
            ResultSet rs = cmd.executeQuery();
            rs.next();
            boolean exists = rs.getInt(1) > 0;

            if (exists) {
                // atualizar o carrinho existente
                cmd = con.prepareStatement(updateCarrinhoQuery);
                cmd.setFloat(1, carrinho.getTotalPrice());
                cmd.setInt(2, carrinho.getQuantity());
                cmd.setInt(3, userid);
                cmd.executeUpdate();
            } else {
                // criar novo carrinho
                cmd = con.prepareStatement(insertCarrinhoQuery);
                cmd.setInt(1, carrinho.getId());
                cmd.setInt(2, carrinho.getId());
                cmd.setFloat(3, carrinho.getTotalPrice());
                cmd.setInt(4, carrinho.getQuantity());
                cmd.executeUpdate();
            }

            // atualizar itens do carrinho
            for (ItemCarrinho item : carrinho.getItems()) {
                // verificar se item existe
                cmd = con.prepareStatement(checkItemQuery);
                cmd.setInt(1, userid);
                cmd.setInt(2, item.getProduto().getId());
                ResultSet itemRs = cmd.executeQuery();
                itemRs.next();
                boolean itemExists = itemRs.getInt(1) > 0;

                if (itemExists) {
                    // atualizar o item existente (antes delete
                    cmd = con.prepareStatement(updateItemQuery);
                    cmd.setInt(1, item.getQuantidade());
                    cmd.setInt(2, userid);
                    cmd.setInt(3, item.getProduto().getId());
                    cmd.executeUpdate();
                } else {
                    // add novo item em itemcarrinhotable
                    cmd = con.prepareStatement(insertItemQuery);
                    cmd.setInt(1, userid);
                    cmd.setInt(2, item.getProduto().getId());
                    cmd.setInt(3, item.getQuantidade());
                    cmd.executeUpdate();
                }
            }

            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (cmd != null) cmd.close();
                if (con != null) con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

 
    public CarrinhoDeCompra getCarrinhoFromId(int id) {
        String selectCarSQL = "SELECT * FROM " + carrinhoTable + " WHERE usuario_id = ?";
        String selectItemsSQL = "SELECT * FROM " + itemCarrinhoTable + " WHERE carrinho_id = ?";
        CarrinhoDeCompra car = null;

        System.out.println("ok");
        
        try {
            PreparedStatement cmd = con.prepareStatement(selectCarSQL);
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                car = new CarrinhoDeCompra(
                    id,
                    rs.getInt("quantity"),
                    rs.getFloat("total_price")
                );

                PreparedStatement itemsCmd = con.prepareStatement(selectItemsSQL);
                itemsCmd.setInt(1, id);
                ResultSet itemsRs = itemsCmd.executeQuery();
                ProdutoDAO prodDAO = new ProdutoDAO();
                while (itemsRs.next()) {
                    int productId = itemsRs.getInt("produto_id");
                    int quantity = itemsRs.getInt("quantidade");
                    Produto produto = prodDAO.getProductbyId(productId);
                    car.adicionarProduto(produto, quantity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }
    
    public static void main(String[] args) {
        CarrinhoDeCompraDAO carrinhoDAO = new CarrinhoDeCompraDAO();
        
        Produto produto = new Produto(1, 100, "Produto Teste", "Descrição Teste", "Fornecedor Teste", "Categoria Teste", 50.0f, 4.5f, Arrays.asList("tag1", "tag2"), null);
        ItemCarrinho item = new ItemCarrinho(produto, 2);
        CarrinhoDeCompra carrinho = new CarrinhoDeCompra(1);
        carrinho.adicionarProduto(produto, 2);
        
        carrinhoDAO.saveCarrinho(carrinho);
    }
}