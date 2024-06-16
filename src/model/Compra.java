package model;

public class Compra {
    private int id;
    private User user; // (id, carrinho)
    private float totalPrice;
    private String purchaseDate;
    
    public Compra(int id, User u, float price, String date){
        this.id = id;
        this.user = u;
        this.totalPrice = price;
        this.purchaseDate = date;
    }
    
//    private void finalizaCompra(){} 
}
