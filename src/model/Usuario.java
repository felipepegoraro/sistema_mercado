package model;

public class Usuario {
    private int id;
    private String name;
    private String email;
    private String password;
    private CarrinhoDeCompra carrinho;
    
    public Usuario(){
        this.id = -1;
        this.name = "";
        this.email = "";
        this.password = "";
        this.carrinho = null;
    }

    public Usuario(int id, String name, String email, String passwd){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = passwd;
        this.carrinho = new CarrinhoDeCompra();
    }
    
    public Usuario(String name, String email, String passwd){
        this.name = name;
        this.email = email;
        this.password = passwd;
        this.carrinho = new CarrinhoDeCompra();
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
    
    @Override
    public String toString(){
        return "("+ this.id + "): " + this.email;
    }
}