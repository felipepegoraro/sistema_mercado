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
import model.Carteira;

import model.User;
import model.Produto;

public class UsuarioDAO {
    private Connection con;
    private PreparedStatement cmd;
    private static final String TABLENAME = "tb_mercado_usuarios";         
    private static final String WALLET_TABLENAME = "tb_mercado_carteira_usuarios";
    
    public UsuarioDAO(){
        this.con = Conexao.conectar();
    }
    
    private List<Integer> getFavItems(ResultSet rs) throws SQLException {
        Array arr = rs.getArray("fav_items");
        List<Integer> favlist = new ArrayList<>();
        if (arr != null){
            Integer[] favItemsArray = (Integer[])arr.getArray();
            favlist.addAll(Arrays.asList(favItemsArray));
        }
        return favlist;
    }
    
    private User extractUser(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        
        CarteiraDAO d = new CarteiraDAO();
        
        return new User(
            id,
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("password"),
            (ArrayList<Integer>) getFavItems(rs),
            rs.getBoolean("is_admin"),
            d.getCarteiraByUserId(id)
        );
    }
    
    // TODO    
    public int insertUser(User user) {
        String checkSQL = "SELECT COUNT(*) FROM " + TABLENAME + " WHERE email = ?";
        String insertSQLUser = "INSERT INTO " + TABLENAME + "(name, email, password, is_admin) VALUES (?, ?, MD5(?), ?)";
        String insertSQLCarteira = "INSERT INTO " + WALLET_TABLENAME + "(usuario_id, debito, credito, dinheiro, limite) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement checkStmt = con.prepareStatement(checkSQL)) {
            checkStmt.setString(1, user.getEmail());
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return 2; // usuario já existe
                }
            }

            con.setAutoCommit(false);

            try (PreparedStatement insertStmtUser = con.prepareStatement(insertSQLUser, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement insertStmtCarteira = con.prepareStatement(insertSQLCarteira)) {

                insertStmtUser.setString(1, user.getName());
                insertStmtUser.setString(2, user.getEmail());
                insertStmtUser.setString(3, user.getPassword());
                insertStmtUser.setBoolean(4, user.isAdmin());

                int affectedRowsUser = insertStmtUser.executeUpdate();
                if (affectedRowsUser > 0) {
                    try (ResultSet generatedKeys = insertStmtUser.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            user.setId(generatedKeys.getInt(1));
                        }
                    }

                    // inserir carteira para o usuario
                    insertStmtCarteira.setInt(1, user.getId());
                    insertStmtCarteira.setFloat(2, 1000); // Valor inicial para débito
                    insertStmtCarteira.setFloat(3, 0); // ............. para crédito
                    insertStmtCarteira.setFloat(4, 0); // ............. para dinheiro
                    insertStmtCarteira.setFloat(5, 0); // ............. para limite

                    int affectedRowsCarteira = insertStmtCarteira.executeUpdate();
                    if (affectedRowsCarteira > 0) {
                        con.commit();
                        System.out.println("Usuário e carteira inseridos com sucesso!");
                        return 0;
                    } else {
                        con.rollback(); return 1;
                    }
                } else {
                    con.rollback();
                    return 1;
                }
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
                return 1;
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    
    
    
    // antes era 2 fn getUserByEmail e (...)byId
    public User getUserByGenericField(String field, Object value){
        String SQL = "SELECT * FROM " + TABLENAME + " WHERE " + field + " = ?";
        User user = null;
        
        try {
            cmd = con.prepareStatement(SQL);
            
            if (value instanceof Integer id){
                cmd.setInt(1, id);
            }
            else if (value instanceof String str){
                cmd.setString(1, str);
            } else {
                throw new IllegalArgumentException("Tipo nao suportado de campo.");
            }
            
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
    
    public void updateFavList(User user){
        String SQL = "update " + TABLENAME + " set fav_items = ? where id = " + user.getId();
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
        String SQL = "select * from " + TABLENAME + " where email = ? and password = MD5(?)";
        User user = null;
       
        try {
            cmd = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            cmd.setString(1, email);
            cmd.setString(2, password);
            
            ResultSet rs = cmd.executeQuery();
            if (rs.next()){  
                user = extractUser(rs);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return user;
    }
    
    public List<User> getAllUsers(){
        List users = new ArrayList<User>();
        String SQL = "select * from " + TABLENAME + " order by id";
        
        try {
            cmd = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()){
                users.add(extractUser(rs));
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