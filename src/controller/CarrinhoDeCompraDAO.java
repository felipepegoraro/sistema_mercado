package controller;

import java.sql.*;
import model.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

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
    
    public void removeItemFromCarrinho(int carrinhoId, int produtoId) {
        String deleteItemQuery = "DELETE FROM " + itemCarrinhoTable + " WHERE carrinho_id = ? AND produto_id = ?";
        try {
            con.setAutoCommit(false);

            // deletar item do carrinho
            cmd = con.prepareStatement(deleteItemQuery);
            cmd.setInt(1, carrinhoId);
            cmd.setInt(2, produtoId);
            cmd.executeUpdate();

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
        String selectSQL = "SELECT c.*, ic.produto_id, ic.quantidade " +
                           "FROM " + carrinhoTable + " c " +
                           "JOIN " + itemCarrinhoTable + " ic ON c.id = ic.carrinho_id " +
                           "WHERE c.usuario_id = ?";
        CarrinhoDeCompra car = null;

        try {
            PreparedStatement cmd = con.prepareStatement(selectSQL);
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();

            if (rs.next()) {
                car = new CarrinhoDeCompra(
                    id,
                    rs.getInt("quantity"),
                    rs.getFloat("total_price")
                );

                ProdutoDAO prodDAO = new ProdutoDAO();
                do {
                    int productId = rs.getInt("produto_id");
                    int quantity = rs.getInt("quantidade");
                    Produto produto = prodDAO.getProductbyId(productId);
                    car.adicionarProduto(produto, quantity);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }

    
    public static void main(String[] args) {
        System.out.println("carrinho!!!!!!!!");
    }
}