package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import model.Usuario;

public class UsuarioDAO {
    private Connection con;
    private PreparedStatement cmd;
    private static final String tablename = "tb_mercado_usuarios";
    
    public UsuarioDAO(){
        this.con = Conexao.conectar();
    }
    
    // TODO    
    public int insertUser(Usuario user)
    {
        // sucesso: 0, erro: 1, ja_existe: 2
        String checkSQL = "select count(*) from " + tablename + " where email = ?";
        String insertSQL = "insert into " + tablename + "(name, email, password) values (?, ?, ?)";

        try (PreparedStatement checkStmt = con.prepareStatement(checkSQL)){
            checkStmt.setString(1, user.getEmail());
            try (ResultSet rs = checkStmt.executeQuery()){
                if (rs.next() && rs.getInt(1) > 0){
                    return 2;
                }
            }        

            try (PreparedStatement insertStmt = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)){
                insertStmt.setString(1, user.getName());
                insertStmt.setString(2, user.getEmail());
                insertStmt.setString(3, user.getPassword());
                
                int affectedRows = insertStmt.executeUpdate();
                if (affectedRows > 0){
                    try(ResultSet generatedKeys = insertStmt.getGeneratedKeys()){
                        if (generatedKeys.next()){
                            user.setId(generatedKeys.getInt(1));
                        }
                    }
                    System.out.println("usuario inserido com sucesso!");
                    return 0;
                } else {
                    return 1;
                }
            } 
        } catch(SQLException e){
            e.printStackTrace();
            return 1;
        }
    }
    
    public Usuario getUserByEmail(String email){
        String SQL = "select * from " + tablename + " where email = ?";
        Usuario user = null;
       
        try {
            cmd = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            cmd.setString(1, email);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()){
                user = new Usuario(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password")
                );
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return user;
    }
    
    public Usuario matchUserLogin(String email, String password){
        String SQL = "select * from " + tablename + " where email = ? and password = ?";
        Usuario user = null;
       
        try {
            cmd = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            cmd.setString(1, email);
            cmd.setString(2, password);
            
            ResultSet rs = cmd.executeQuery();
            if (rs.next()){
                user = new Usuario(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password")
                );
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return user;
    }
    
    public List<Usuario> getAllUsers(){
        List users = new ArrayList<Usuario>();
        String SQL = "select * from " + tablename + " order by id";
        
        try {
            cmd = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()){
                users.add(
                    new Usuario(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                    )
                );
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            return users;
        }
    }
    
    // TODO
    public boolean removeUser(Usuario user){
        return true;
    }
    
    // TODO
    public boolean removeUserById(int id){
        return true;
    }
    
    

}