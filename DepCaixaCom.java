import java.util.Scanner;

class Deposito {
    
    private int qtdDeposito;
    private int qtdMax;

    Deposito(int capacidade) {
        this.qtdMax = capacidade;
    }

    public int getQtdItens(){
        return this.qtdDeposito;
    }

    public int getQtdTotal(){
        return this.qtdMax;
    }

    void armazenar(){
        if(qtdDeposito < qtdMax) {
            qtdDeposito++;
            System.out.println("Caixa armazenada: Agora sao "+this.getQtdItens()+" caixas");
        }
    }

    void retirar(){
        if(qtdDeposito > 0) {
            qtdDeposito--;
            System.out.println("Caixa retirada: Agora sao "+this.getQtdItens()+" caixas");
        } else {
            System.out.println("numeros indisponiveis");
        }
    }
}

public class DepCaixaCom {

    public static final int MAX = 10;

    static class Produtor implements Runnable {

        Deposito deposito;
        int tempo;
        int MAX;

        Produtor(Deposito deposito, int tempo, int MAX) {
            this.deposito = deposito;
            this.tempo = tempo;
            this.MAX = MAX;
        }

        public void run(){
            
                System.out.println("Hello: Thread de producao: " +Thread.currentThread().getName());
                try {
                    for(int i=0; i<MAX; i++) {
                        deposito.armazenar();
                        Thread.sleep(tempo);
                    }
                } catch (InterruptedException e) {}
            
        }
        
    }


    static class Consumidor extends Thread {

        Deposito deposito;
        int tempo;
        int MAX;

        Consumidor(Deposito deposito, int tempo, int MAX) {
            this.deposito = deposito;
            this.tempo = tempo;
            this.MAX = MAX;
        }

        public void run(){
            
                System.out.println("Hello: Thread de consumo: " +Thread.currentThread().getName());
                try {
                    for(int i=0; i<MAX; i++) {
                    deposito.retirar();
                    Thread.sleep(tempo);
                }
            } catch (InterruptedException e) {}
            
        }
    }

    public static void main(String args[]) {

        Deposito dep = new Deposito(10);
        System.out.println("Qual a cons max de producoes? ");
        Scanner ler = new Scanner(System.in);
        Thread prod = new Thread(new Produtor(dep,100,ler.nextInt()));
        System.out.println("Qual a cons max de consumos? ");
        Consumidor cons = new Consumidor(dep,200,ler.nextInt());
        prod.start();
        cons.start();
    }
}
