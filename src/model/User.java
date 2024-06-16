package model;

import java.util.List;
import java.util.ArrayList;
import controller.CarrinhoDeCompraDAO;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class User extends Person {
    private CarrinhoDeCompra carrinho;
    private ArrayList<Integer> favItems;
    
    public User(int id, String name, String email, String passwd, ArrayList<Integer> fav, boolean isAdmin){
        super(id, name, email, passwd, isAdmin);

        this.favItems = fav;
        this.carrinho = new CarrinhoDeCompra(id);

        CarrinhoDeCompraDAO dao = new CarrinhoDeCompraDAO();        
        
        ExecutorService exs = Executors.newSingleThreadExecutor();
        exs.submit(() -> {
            CarrinhoDeCompra c = dao.getCarrinhoFromId(id);
            this.carrinho.setItems(c != null ? c.getItems() : new ArrayList<>());
        });
        exs.shutdown();
    }
    
    public User(String name, String email, String passwd, ArrayList<Integer> fav){
        super(name, email, passwd);
        super.setAdmin(false);

        this.favItems = fav;
        this.carrinho = new CarrinhoDeCompra(getId());
        this.carrinho.setItems(carrinho.getItems() != null ? carrinho.getItems() : new ArrayList<>());
        System.out.println(this.carrinho.getItems());
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
        return "("+ getId() + "): " + getEmail();
    }
}