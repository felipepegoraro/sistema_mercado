package model;

import java.util.List;
import java.util.ArrayList;
import controller.CarrinhoDeCompraDAO;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class User extends Person {
    private CarrinhoDeCompra carrinho;
    private ArrayList<Integer> favItems;
    private Carteira carteira;
    
    public User(int id, String name, String email, String passwd, ArrayList<Integer> fav, boolean isAdmin, Carteira wll){
        super(id, name, email, passwd, isAdmin);

        this.favItems = fav;
        this.carrinho = new CarrinhoDeCompra(id);
        this.carteira = wll;

        CarrinhoDeCompraDAO dao = new CarrinhoDeCompraDAO();        
        
        ExecutorService exs = Executors.newSingleThreadExecutor();
        exs.submit(() -> {
            CarrinhoDeCompra c = dao.getCarrinhoFromId(id);
            this.carrinho.setItems(c != null ? c.getItems() : new ArrayList<>());
        });
        exs.shutdown();
    }
    
    public User(String name, String email, String passwd){
        super(name, email, passwd);
        super.setAdmin(false);

        this.favItems = new ArrayList<>();
        this.carteira = new Carteira(100, 100, 100, 100); // valor inicial teste
        
        this.carrinho = new CarrinhoDeCompra(getId());
        this.carrinho.setItems(carrinho.getItems() != null ? carrinho.getItems() : new ArrayList<>());
        System.out.println(this.carrinho.getItems());
        // TODO inicializar carteira
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

    public Carteira getCarteira() {
        return carteira;
    }
    
    @Override
    public String toString(){
        return "("+ getId() + "): " + getEmail();
    }
}