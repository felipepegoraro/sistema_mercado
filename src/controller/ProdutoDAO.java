package controller;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.awt.*; // para o Font
import java.util.Arrays;
import javax.swing.*;
import model.Produto;

public class ProdutoDAO {
    private Connection con;
    private PreparedStatement cmd;
    private static final String tablename = "tb_mercado_produtos";

    public ProdutoDAO(){
        this.con = Conexao.conectar();
    }
    
    public void saveProduto(Produto produto) {
        String SQL 
                = "INSERT INTO " + tablename 
                + " (id, stock_quantity, name, description, supplier, category, price, rating, tags, image) " 
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            cmd = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            cmd.setInt(1, produto.getId());
            cmd.setInt(2, produto.getStockQuantity());
            cmd.setString(3, produto.getName());
            cmd.setString(4, produto.getDescription());
            cmd.setString(5, produto.getSupplier());
            cmd.setString(6, produto.getCategory());
            cmd.setFloat(7, produto.getPrice());
            cmd.setFloat(8, produto.getRating());
            cmd.setArray(9, con.createArrayOf("text", produto.getTags().toArray()));
            cmd.setBytes(10, produto.getImage());

            cmd.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Produto> getAllProducts(){
        String SQL = "select * from " + tablename;
        List<Produto> list = new ArrayList<>();
        
        try {
            cmd = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()){
                Array tagsArray = rs.getArray("tags");
                String[] tags = (String[]) tagsArray.getArray();
                ArrayList<String> tagsList = new ArrayList<>();
                tagsList.addAll(Arrays.asList(tags));
                
                list.add(new Produto(
                    rs.getInt("id"),
                    rs.getInt("stock_quantity"),
                    rs.getString("name"),
                    rs.getString("description"),
                        rs.getString("supplier"),
                        rs.getString("category"),
                        rs.getFloat("price"),
                        rs.getFloat("rating"),
                        tagsList
                )); 
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        return list;
    }
    
    // TESTE!
    public void showProductImageById(int id){
        String SQL
                = "select name, image from " + tablename
                + " where id = ?";
        try {
           cmd = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
           cmd.setInt(1, id);
           ResultSet rs = cmd.executeQuery();
           
           if (rs.next()){
                String name = rs.getString("name");
                byte[] image = rs.getBytes("image");
               
                JFrame frame = new JFrame("Produto");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(300, 400);
               
                JLabel nomeLabel = new JLabel(name, SwingConstants.CENTER);
                nomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
               
                ImageIcon imageIcon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(imageIcon, SwingConstants.CENTER);
               
                frame.getContentPane().setLayout(new BorderLayout());
                frame.getContentPane().add(nomeLabel, BorderLayout.NORTH);
                frame.getContentPane().add(imageLabel, BorderLayout.CENTER);
             
                frame.setVisible(true);
           } else {
                JOptionPane.showMessageDialog(null, "Produto nao encontrado", "Erro",JOptionPane.ERROR_MESSAGE);
           }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    // TESTE!  
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        /*
        List<String> tags = new ArrayList<>(); 
        tags.add("tag2");
        tags.add("tag6");
        
        Produto produto = new Produto(
            49, 100, "Produto 11", "Descrição do Produto 11",
            "Fornecedor 11", "Categoria 11", 99.99f, 4.5f, tags
        );
        produto.setImageFromFile("/home/felipe/Mercado/src/assets/logo.25.png");
        produtoDAO.saveProduto(produto);*/
        
        produtoDAO.showProductImageById(49);
        List<Produto> list = produtoDAO.getAllProducts();
        for (Produto p : list){
            System.out.println(p);
        }
    }
}