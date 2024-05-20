package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import controller.CarrinhoDeCompraDAO;

public class CarrinhoDeCompra {
    private int id;
    private ArrayList<ItemCarrinho> items;
    private float totalPrice;
    private int quantity;

    public CarrinhoDeCompra(int id){
        this.id = id;
        this.quantity = 0;
        this.totalPrice = 0.0f;
        this.items = new ArrayList<>();
    }
    
    public CarrinhoDeCompra(int id, int quantity, float price){
        this.id = id;
        this.quantity = quantity;
        this.totalPrice = price;
        this.items = new ArrayList<>();
    }
    
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    
    public ArrayList<ItemCarrinho> getItems() {
        return items;
    }
    
    public void setItems(ArrayList<ItemCarrinho> items){
        this.items = items;
    }

    public void adicionarProduto(Produto produto, int quantidade){
        CarrinhoDeCompraDAO dao = new CarrinhoDeCompraDAO();

        for (ItemCarrinho item : this.items){
            if (item.getProduto().getId() == produto.getId()){
                item.adicionarQuantidade(quantidade);
                this.quantity += quantidade;
                totalPrice = calcularTotal();
                dao.saveCarrinho(this);
                return;
            }
        }
        items.add(new ItemCarrinho(produto, quantidade));
        this.quantity += quantidade;
        totalPrice = calcularTotal();
        dao.saveCarrinho(this);
    }
    
    public void removerProduto(Produto produto){
        Iterator<ItemCarrinho> iter = items.iterator();
        while (iter.hasNext()){
           ItemCarrinho i = iter.next();
            if (i.getProduto().getId() == produto.getId()){
                int qnt = i.getQuantidade();
                iter.remove();
                quantity -= qnt;
                totalPrice = calcularTotal();
                return;
            }
        }
    }
    
    public void atualizarQuantidade(Produto produto, int quantidade) {
        for (ItemCarrinho item : items) {
            if (item.getProduto().getId() == produto.getId()) {
                if (quantidade <= 0) {
                    removerProduto(produto);
                } else {
                    item.setQuantidade(quantidade);
                    totalPrice = calcularTotal();
                }
                quantity = items.size();
                return;
            }
        }
    }
    
    public void removeOneOrMore(Produto p, int n) {
        for (ItemCarrinho i : getItems()) {
            if (i.getProduto().getId() == p.getId()) {
                int qntRemovida = Math.min(n, i.getQuantidade());
                int novaQuantidade = i.getQuantidade() - qntRemovida;
                atualizarQuantidade(p, novaQuantidade);
                return;
            }
        }
    }
    
    public float calcularTotal() {
        return (float) items.stream()
            .mapToDouble(item -> item.getProduto().getPrice() * item.getQuantidade())
            .sum();
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }
    
    @Override
    public String toString(){
        return "CARRINHO[" + quantity + "]: (" + totalPrice + ")"  
        + items;
    }

    public static void main(String[] args) {
        System.out.println("testando carrinho");
    }

}
