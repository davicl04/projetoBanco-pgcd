package projetoBanco_pgcd;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Conta {
    private double saldo;
    private final Lock lock = new ReentrantLock();

    public Conta(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void depositar(double valor) {
        lock.lock();
        try {
            saldo += valor;
        } finally {
            lock.unlock();
        }
    }

    public boolean sacar(double valor) {
        lock.lock();
        try {
            if (saldo >= valor) {
                saldo -= valor;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public Lock getLock() {
        return lock;
    }
}