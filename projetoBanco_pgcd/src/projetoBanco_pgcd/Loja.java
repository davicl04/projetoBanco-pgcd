package projetoBanco_pgcd;

public class Loja {
    private Conta conta;
    private Funcionario[] funcionarios;

    public Loja(Conta conta, Funcionario[] funcionarios) {
        this.conta = conta;
        this.funcionarios = funcionarios;
    }

    public void realizarCompra(Conta contaCliente) {
        if (contaCliente.sacar(100)) {
            conta.depositar(100);
        }
    }

    public boolean pagarSalario(Conta contaFuncionario) {
        if (conta.getSaldo() >= 1400) {
            conta.sacar(1400);
            contaFuncionario.depositar(1400);
            return true;
        }
        return false;
    }

	public Conta getConta() {
		return conta;
	}
	
	public void setFuncionario(int index, Funcionario funcionario) {
        if (index >= 0 && index < funcionarios.length) {
            funcionarios[index] = funcionario;
        } else {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
        }
    }
}