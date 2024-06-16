package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Array;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import model.User;
import model.Produto;

public class UsuarioDAO {
    private Connection con;
    private PreparedStatement cmd;
    private static final String tablename = "tb_mercado_usuarios";
    
    public UsuarioDAO(){
        this.con = Conexao.conectar();
    }
    
    // TODO    
    public int insertUser(User user)
    {
        // sucesso: 0, erro: 1, ja_existe: 2
        String checkSQL = "select count(*) from " + tablename + " where email = ?";
        String insertSQL = "insert into " + tablename + "(name, email, password, is_admin) values (?, ?, MD5(?), ?)";

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
                insertStmt.setBoolean(4, user.isAdmin());
                
                int affectedRows = insertStmt.executeUpdate();
                if (affectedRows > 0){
                    try(ResultSet generatedKeys = insertStmt.getGeneratedKeys()){
                        if (generatedKeys.next()){
                            user.setId(generatedKeys.getInt(1));
                            System.out.println("current user id: " + user.getId());
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
    
    public User getUserByEmail(String email){
        String SQL = "select * from " + tablename + " where email = ?";
        User user = null;
       
        try {
            cmd = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            cmd.setString(1, email);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()){
                Array arr = rs.getArray("fav_items");
                List<Integer> favlist = new ArrayList<>();
                if (arr != null){
                    Integer[] favItemsArray = (Integer[])arr.getArray();
                    favlist.addAll(Arrays.asList(favItemsArray));
                }
                 
                user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    (ArrayList<Integer>)favlist,
                    rs.getBoolean("is_admin")
                );
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return user;
    }
    
    public void updateFavList(User user){
        String SQL = "update " + tablename + " set fav_items = ? where id = " + user.getId();
        try { 
            cmd = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            cmd.setArray(1, con.createArrayOf("INTEGER", user.getFavItems().toArray(new Integer[0])));
            int affectedRows = cmd.executeUpdate();
            if (affectedRows > 0){
                System.out.println("ok");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
       
    public User matchUserLogin(String email, String password){
        String SQL = "select * from " + tablename + " where email = ? and password = MD5(?)";
        User user = null;
       
        try {
            cmd = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            cmd.setString(1, email);
            cmd.setString(2, password);
            
            ResultSet rs = cmd.executeQuery();
            if (rs.next()){  
                Array arr = rs.getArray("fav_items");
                List<Integer> favlist = new ArrayList<>();
                if (arr != null){
                    Integer[] favItemsArray = (Integer[])arr.getArray();
                    favlist.addAll(Arrays.asList(favItemsArray));
                }
                
                user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    (ArrayList<Integer>) favlist,
                    rs.getBoolean("is_admin")
                );
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return user;
    }
    
    public List<User> getAllUsers(){
        List users = new ArrayList<User>();
        String SQL = "select * from " + tablename + " order by id";
        
        try {
            cmd = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()){
                Array arr = rs.getArray("fav_items");
                List<Integer> favlist = new ArrayList<>();
                if (arr != null){
                    Integer[] favItemsArray = (Integer[])arr.getArray();
                    favlist.addAll(Arrays.asList(favItemsArray));
                }
                
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        (ArrayList<Integer>) favlist,
                        rs.getBoolean("is_admin")
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
    public boolean removeUser(User user){
        return true;
    }
    
    // TODO
    public boolean removeUserById(int id){
        return true;
    }
    
    

}