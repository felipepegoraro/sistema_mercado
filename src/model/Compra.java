package model;

import controller.CompraDAO;
import java.text.ParseException;

public class Compra {
    private int id;
    private User user; // (id, carrinho)
    private float totalPrice;
    private String purchaseDate;
    private String type; // (credito, debito, dinheiro)
    
    public Compra(User u, float price, String date, String type){
        this.user = u;
        this.totalPrice = price;
        this.purchaseDate = date;
        this.type = type;
    }
    
    public Compra(int id, User u, float price, String date, String type){
        this.id = id;
        this.user = u;
        this.totalPrice = price;
        this.purchaseDate = date;
        this.type = type;
    }
    
    public void finalizaCompra() throws ParseException {
        if (user.getCarteira().pagar(totalPrice, type)){
            CompraDAO dao = new CompraDAO();
            dao.registrarCompra(this);
        }
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getType() {
        return type;
    }
    
    
}
