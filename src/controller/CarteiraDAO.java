package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Carteira;

public class CarteiraDAO {
    private final Connection con;
    private PreparedStatement cmd;    
    private static final String TABLENAME = "tb_mercado_carteira_usuarios";
    
    public CarteiraDAO(){
        con = Conexao.conectar();
    }
    
    public Carteira getCarteiraByUserId(int user_id) {
        String SQL = "SELECT * FROM " + TABLENAME + " WHERE usuario_id = ?";
        Carteira carteira = null;

        try {
            cmd = con.prepareStatement(SQL);
            cmd.setInt(1, user_id);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                float debito = rs.getFloat("debito");
                float credito = rs.getFloat("credito");
                float dinheiro = rs.getFloat("dinheiro");
                float limite = rs.getFloat("limite");

                carteira = new Carteira(debito, credito, dinheiro, limite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carteira;
    }    
    
    public void updateCarteira(Carteira carteira, int user_id) {
        String SQL = "UPDATE "+ TABLENAME + " SET debito = ?, credito = ?, dinheiro = ?, limite = ? WHERE usuario_id = ?";
        
        try {
            cmd = con.prepareStatement(SQL);
            cmd.setFloat(1, carteira.getDebito());
            cmd.setFloat(2, carteira.getCredito());
            cmd.setFloat(3, carteira.getDinheiro());
            cmd.setFloat(4, carteira.getLimite());
            cmd.setInt(5, user_id);
            cmd.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
