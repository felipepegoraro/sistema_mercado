package controller;

import java.sql.*;
import model.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class CompraDAO {
    private final Connection con;
    private PreparedStatement cmd;    
    private static final String TABLENAME = "tb_mercado_historico_compras";
    
    public CompraDAO(){
        this.con = Conexao.conectar();
    }
    
    public void registrarCompra(model.Compra compra) throws ParseException {
        String SQL = "insert into " + TABLENAME
                + " (usuario_id, preco_total, data_compra, tipo_pagamento)"
                + " values (?, ?, ?, ?)";
        
        // TODO: formatacao da DATA nao ta funcionando
        DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

        if (compra != null){
            try {
                Date utilDate = dt.parse(compra.getPurchaseDate());
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                
                int user_id = compra.getUser().getId();
                float total = compra.getTotalPrice();
                String tipo = compra.getType();
                
                cmd = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                cmd.setInt(1, user_id);
                cmd.setFloat(2, total);
                cmd.setDate(3, sqlDate);
                cmd.setString(4, tipo);
                cmd.executeUpdate();
                
                CarteiraDAO d = new CarteiraDAO();
                
                Carteira carteira = d.getCarteiraByUserId(user_id);
                carteira.pagar(total, tipo);
                d.updateCarteira(carteira, user_id);

            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    
    public ArrayList<Compra> listarTodasCompras() {
        ArrayList<Compra> compras = new ArrayList<>();
        String SQL = "SELECT * FROM " + TABLENAME;

        try {
            cmd = con.prepareStatement(SQL);
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int usuarioId = rs.getInt("usuario_id");
                float precoTotal = rs.getFloat("preco_total");
                Date dataCompra = rs.getDate("data_compra");
                String tipoPagamento = rs.getString("tipo_pagamento");

                UsuarioDAO userdao = new UsuarioDAO();
                User u = userdao.getUserByGenericField("id", usuarioId);
                Compra compra = new Compra(id, u, precoTotal, dataCompra.toString(), tipoPagamento);
                compras.add(compra);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return compras;
    }
}
