package projetoBanco_pgcd;

public class sistemaBancario{
    public static void main(String[] args) {
        // Criação das entidades
        Banco banco = new Banco();
        Conta contaLoja1 = new Conta(0);
        Conta contaLoja2 = new Conta(0);
        Loja loja1 = new Loja(contaLoja1, new Funcionario[2]);
        Loja loja2 = new Loja(contaLoja2, new Funcionario[2]);
        Loja[] lojas = {loja1, loja2};
        Cliente[] clientes = new Cliente[5];
        Funcionario[] funcionarios = new Funcionario[4];

        // Inicialização das contas dos clientes e das threads dos clientes
        Thread[] threadsClientes = new Thread[clientes.length];
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente(new Conta(1000.0), lojas);
            threadsClientes[i] = new Thread(clientes[i]);
        }

        // Inicialização das contas dos funcionários e das threads dos funcionários
        Thread[] threadsFuncionarios = new Thread[funcionarios.length];
        for (int i = 0; i < funcionarios.length; i++) {
            funcionarios[i] = new Funcionario(new Conta(0), new Conta(0), lojas[i / 2]);
            threadsFuncionarios[i] = new Thread(funcionarios[i]);
            lojas[i / 2].setFuncionario(i % 2, funcionarios[i]);
        }

        // Início das threads
        for (Thread thread : threadsClientes) {
            thread.start();
        }
        for (Thread thread : threadsFuncionarios) {
            thread.start();
        }

        // Aguardar o término das threads
        try {
            for (Thread thread : threadsClientes) {
                thread.join();
            }
            for (Thread thread : threadsFuncionarios) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Exibir o saldo final de cada conta
        for (Cliente cliente : clientes) {
            System.out.println("Saldo final do cliente: " + cliente.getConta().getSaldo());
        }
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Saldo final do funcionário (conta salário): " + funcionario.getContaSalario().getSaldo());
            System.out.println("Saldo final do funcionário (conta investimentos): " + funcionario.getContaInvestimentos().getSaldo());
        }
        System.out.println("Saldo final da loja 1: " + loja1.getConta().getSaldo());
        System.out.println("Saldo final da loja 2: " + loja2.getConta().getSaldo());
    }
}