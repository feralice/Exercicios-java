import java.util.concurrent.*;
import java.util.Random;

public class Banco {

    static int saldoReais = 2000; //Saldo inicial da conta conjunta

    static class Operacoes extends Thread {
        Semaphore semaforo;
        char pessoa;// M - marido e E - esposa

        public Operacoes(Semaphore semaforo, char pessoa) {
            this.semaforo = semaforo;
            this.pessoa = pessoa;
        }

        public void deposito(int valor) {
            saldoReais+=valor;
            System.out.println("O saldo esta: "+saldoReais);
        }

        public void retirada(int valor) {
            saldoReais-=valor;
            System.out.println("O saldo esta: "+saldoReais);
        }


        public void run() {

            System.out.println("Saldo inicial: "+saldoReais);
            try {

                semaforo.acquire();
                
                if(pessoa=='E'){
                    System.out.println("\n\n4 depositos estao sendo feitos pela esposa\n");
                    for(int i=0; i<4; i++) {
                        Random gerador = new Random();
                        int valor = gerador.nextInt(1000); //Coloquei o valor aleátorioa ate mil reais
                        deposito(valor);
                    }
                } else {
                    System.out.println(("\n\n4 retiradas estao sendo feitas pelo marido\n"));
                    for(int i=0; i<4; i++) {
                        Random gerador = new Random();
                        int valor = gerador.nextInt(1000); //Coloquei o valor aleátorioa ate mil reais
                        retirada(valor);
                    }
                }
                Random gerador = new Random();
                int tempoDormir = gerador.nextInt(500);
                Thread.sleep(tempoDormir);
            } catch(InterruptedException e) {}

            semaforo.release();

        }
    }

    public static void main(String args[]) throws InterruptedException {

		Semaphore sem = new Semaphore(1);
		Operacoes marido = new Operacoes(sem, 'M');
		Operacoes esposa = new Operacoes(sem, 'E');

		marido.start();
		esposa.start();

		marido.join();
		esposa.join();

        System.out.println("\nSaldo final:"+saldoReais);
	}
}


