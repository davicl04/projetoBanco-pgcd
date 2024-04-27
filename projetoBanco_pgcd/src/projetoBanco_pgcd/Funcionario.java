package projetoBanco_pgcd;

public class Funcionario implements Runnable {
    private Conta contaSalario;
    private Conta contaInvestimentos;
    private Loja loja;

    public Funcionario(Conta contaSalario, Conta contaInvestimentos, Loja loja) {
        this.contaSalario = contaSalario;
        this.contaInvestimentos = contaInvestimentos;
        this.loja = loja;
    }

    public void run() {
        while (true) {
            if (loja.pagarSalario(contaSalario)) {
                double valorInvestimento = contaSalario.getSaldo() * 0.2;
                if (contaSalario.sacar(valorInvestimento)) {
                    contaInvestimentos.depositar(valorInvestimento);
                }
            }
        }
    }

	public Conta getContaSalario() {
		return contaSalario;
	}

	public Conta getContaInvestimentos() {
		return contaInvestimentos;
	}
}