package projetoBanco_pgcd;

public class Cliente implements Runnable {
    private Conta conta;
    private Loja[] lojas;
    private int lojaAtual = 0;

    public Cliente(Conta conta, Loja[] lojas) {
        this.conta = conta;
        this.lojas = lojas;
    }

    public void run() {
        while (conta.getSaldo() > 0) {
            lojas[lojaAtual].realizarCompra(conta);
            lojaAtual = (lojaAtual + 1) % lojas.length;
        }
    }

    public Conta getConta() {
        return conta;
    }
}