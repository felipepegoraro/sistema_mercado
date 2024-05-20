package model;

import java.util.List;
import java.util.ArrayList;
import controller.CarrinhoDeCompraDAO;

public class Usuario {
    private int id;
    private String name;
    private String email;
    private String password;
    private CarrinhoDeCompra carrinho;
    private ArrayList<Integer> favItems;
    
    public Usuario(int id, String name, String email, String passwd, ArrayList<Integer> fav){
        CarrinhoDeCompraDAO dao = new CarrinhoDeCompraDAO();
        
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = passwd;
        this.favItems = fav;
        
        this.carrinho = new CarrinhoDeCompra(id);
        CarrinhoDeCompra c = dao.getCarrinhoFromId(id);
        this.carrinho.setItems(c != null ? c.getItems() : new ArrayList<>());
        System.out.println(this.carrinho.getItems());
    }
    
    public Usuario(String name, String email, String passwd, ArrayList<Integer> fav){
        CarrinhoDeCompraDAO dao = new CarrinhoDeCompraDAO();
        
        this.name = name;
        this.email = email;
        this.password = passwd;
        this.favItems = fav;
        
        this.carrinho = new CarrinhoDeCompra(id);
        this.carrinho.setItems(carrinho.getItems() != null ? carrinho.getItems() : new ArrayList<>());
        System.out.println(this.carrinho.getItems());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public CarrinhoDeCompra getCarrinho(){
        return carrinho;
    }
    
    public void setFavItems(int id){
        this.favItems.add(id);
    }
    
    public void setFavItems(ArrayList<Integer> list){
        this.favItems = list;
    }
    
    public ArrayList<Integer> getFavItems(){
        return this.favItems;
    }
    
    @Override
    public String toString(){
        return "("+ this.id + "): " + this.email;
    }
}