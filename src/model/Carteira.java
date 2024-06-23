package model;

public class Carteira {
    private float debito;
    private float credito;
    private float dinheiro;
    private float limite;

    public Carteira(float debito, float credito, float dinheiro, float limite) {
        this.debito = debito;
        this.credito = credito;
        this.dinheiro = dinheiro;
        this.limite = limite;
    }
    
    public Carteira(){
        this.debito = 0.0f;
        this.credito = 0.0f;
        this.dinheiro = 0.0f;
        this.limite = 0.0f;
    }
   
    public boolean pagar(float quantia, String tipo){
        if (quantia > 0){
            switch(tipo){
                case "debito" -> {
                    if (this.debito >= quantia){
                        this.debito -= quantia;
                        System.out.println("pagamento finalizado com sucesso.");
                        return true;
                    }
                    System.out.println("saldo insuficiente na conta.");
                    return false;
                }
                case "credito" -> {
                    if (this.credito + quantia <= this.limite){
                        this.credito += quantia;
                        // aqui eu devo diminuir o limite?
                        System.out.println("pagamento finalizado com sucesso.");
                        return true;
                    }
                    System.out.println("não foi possivel finalizar o pagamento.");
                    return false;
                }
                case "dinheiro" -> {
                    if (this.dinheiro >= quantia){
                        this.dinheiro -= quantia;
                        System.out.println("pagamento finalizado com sucesso.");
                        return true;
                    }
                    System.out.println("dinheiro insuficiente.");
                    return false;
                }
                default -> {
                    System.out.println("forma inválida de pagamento.");
                    return false;
                }
            }
        }
        
        System.out.println("valor inválido.");
        return false;
    }
    
    public void simularDeposito(float quantia, String tipo){
        if (quantia >= 0){
            switch(tipo){
                case "debito" -> this.debito += quantia;
                case "dinheiro" -> this.dinheiro += quantia;
                default -> {
                    System.out.println("forma inválida.");
                    return;
                }
            }
            return;
        }
        System.out.println("quantia inválida");
    }
    
    public void pagarFaturaCartaoCredito(float valor, String tipo){
        if (valor >= this.credito && valor >= 0){
            switch(tipo){
                case "debito" -> { 
                    this.debito -= valor;
                    this.credito -= valor;
                    System.out.println("pagamento finalizado.");
                }
                case "dinheiro" -> {
                    transferirParaConta(valor);
                    this.debito -= valor;
                    this.credito -= valor;
                    System.out.println("pagamento finalizado.");
                }
                default -> {
                    return;
                }
            }
        }
    }
    
    private float getTotal(){
        return this.dinheiro + this.debito;
    }
    
    public void transferirParaConta(float valor){
        if (valor >= 0){
            if (this.dinheiro >= valor){
                this.dinheiro -= valor;
                this.debito += valor;
                System.out.println("(+" + valor + " R$) -> saldo final: " + getTotal());
                return;
            }
            System.out.println("dinheiro insuficiente.");
            return;
        }
        System.out.println("valor inválido");
    }
    
    public float getDebito() {
        return debito;
    }

    public float getCredito() {
        return credito;
    }

    public float getDinheiro() {
        return dinheiro;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    @Override
    public String toString() {
        return "Carteira{ \n debito=" + debito + ",\n credito=" + credito + ",\n dinheiro=" + dinheiro + ",\n limite=" + limite + "\n}";
    }
}
