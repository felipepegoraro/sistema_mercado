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
    
    // TODO: refazer essa função, pois há erro quando usuario esta sendo cadastrado, de resto parece ok!
    public void saveCarrinho(CarrinhoDeCompra carrinho) {
        String selectCarrinhoSQL = "SELECT id FROM " + carrinhoTable + " WHERE usuario_id = ?";
        String insertCarrinhoSQL = "INSERT INTO " + carrinhoTable + " (id, usuario_id, total_price, quantity) VALUES (?, ?, ?, ?)";
        String updateCarrinhoSQL = "UPDATE " + carrinhoTable + " SET total_price = ?, quantity = ? WHERE id = ?";
        String selectItemSQL = "SELECT id FROM " + itemCarrinhoTable + " WHERE carrinho_id = ? AND produto_id = ?";
        String insertItemCarrinhoSQL = "INSERT INTO " + itemCarrinhoTable + " (carrinho_id, produto_id, quantidade) VALUES (?, ?, ?)";
        String updateItemCarrinhoSQL = "UPDATE " + itemCarrinhoTable + " SET quantidade = ? WHERE id = ?";

        try {
            con.setAutoCommit(false);

            // se o carrinho já existe
            PreparedStatement cmd = con.prepareStatement(selectCarrinhoSQL);
            cmd.setInt(1, carrinho.getId());
            ResultSet rs = cmd.executeQuery();

            int carrinhoId = carrinho.getId();
            if (rs.next()) { // existe
                cmd = con.prepareStatement(updateCarrinhoSQL);
                cmd.setFloat(1, carrinho.getTotalPrice());
                cmd.setInt(2, carrinho.getQuantity());
                cmd.setInt(3, carrinhoId);
                cmd.executeUpdate();
            } else { // não existe
                cmd = con.prepareStatement(insertCarrinhoSQL);
                cmd.setInt(1, carrinhoId);
                cmd.setInt(2, carrinho.getId());
                cmd.setFloat(3, carrinho.getTotalPrice());
                cmd.setInt(4, carrinho.getQuantity());
                cmd.executeUpdate();
            }

            for (ItemCarrinho item : carrinho.getItems()) {
                cmd = con.prepareStatement(selectItemSQL);
                cmd.setInt(1, carrinhoId);
                cmd.setInt(2, item.getProduto().getId());
                ResultSet itemRS = cmd.executeQuery();

                if (itemRS.next()) { // atualizar quantidade
                    int itemId = itemRS.getInt(1);
                    cmd = con.prepareStatement(updateItemCarrinhoSQL);
                    cmd.setInt(1, item.getQuantidade());
                    cmd.setInt(2, itemId);
                    cmd.executeUpdate();
                } else { // inserir novo registro
                    cmd = con.prepareStatement(insertItemCarrinhoSQL);
                    cmd.setInt(1, carrinhoId);
                    cmd.setInt(2, item.getProduto().getId());
                    cmd.setInt(3, item.getQuantidade());
                    cmd.executeUpdate();
                }
            }

            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException autoCommitException) {
                autoCommitException.printStackTrace();
            }
        }
    }
 
    public CarrinhoDeCompra getCarrinhoFromId(int id) {
        String selectCarSQL = "SELECT * FROM " + carrinhoTable + " WHERE usuario_id = ?";
        String selectItemsSQL = "SELECT * FROM " + itemCarrinhoTable + " WHERE carrinho_id = ?";
        CarrinhoDeCompra car = null;

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