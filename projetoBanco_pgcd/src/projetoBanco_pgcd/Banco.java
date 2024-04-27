package projetoBanco_pgcd;

public class Banco {
    public void transferir(Conta origem, Conta destino, double valor) {
        boolean sucesso = false;
        while (!sucesso) {
            if (origem.getLock().tryLock()) {
                try {
                    if (destino.getLock().tryLock()) {
                        try {
                            if (origem.getSaldo() >= valor) {
                                origem.sacar(valor);
                                destino.depositar(valor);
                                sucesso = true;
                            }
                        } finally {
                            destino.getLock().unlock();
                        }
                    }
                } finally {
                    origem.getLock().unlock();
                }
            }
        }
    }
}