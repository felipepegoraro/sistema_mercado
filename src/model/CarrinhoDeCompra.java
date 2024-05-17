package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class CarrinhoDeCompra {
    private List<ItemCarrinho> items;
    private float totalPrice;
    private int quantity;

    public CarrinhoDeCompra(){
        this.items = new ArrayList<>();
        this.quantity = 0;
        this.totalPrice = 0.0f;
    }
    
    public List<ItemCarrinho> getItems() {
        return items;
    }

    public void adicionarProduto(Produto produto, int quantidade){
        for (ItemCarrinho item : this.items){
            if (item.getProduto().getId() == produto.getId()){
                item.adicionarQuantidade(quantidade);
                this.quantity += quantidade;
                totalPrice = calcularTotal();
                return;
            }
        }
        items.add(new ItemCarrinho(produto, quantidade));
        this.quantity += quantidade;
        totalPrice = calcularTotal();
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
