package model;

public class ItemCarrinho {
    private Produto produto;
    private int quantidade;
    private int id;
    
    public ItemCarrinho(Produto produto, int quantidade) {
        this.id = produto.getId();
        this.produto = produto;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "[" + quantidade + "]: " + produto.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void adicionarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }
}

