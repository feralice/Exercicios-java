import java.util.Random;
import java.util.concurrent.Semaphore;

public class ProblemaFumantes {
  private static final int numFumantes = 3;
  private static final int tabaco = 0;
  private static final int papel = 1;
  private static final int fosforo = 2;

  private static int[] ingredientes = { tabaco, tabaco, fosforo };

  private static Semaphore[] smokerSemaphores = {
      new Semaphore(0), new Semaphore(0), new Semaphore(0)
  };
  private static Semaphore agentSemaphore = new Semaphore(1);

  private static class Fumante extends Thread {
    private int numIngredient;

    //construtor
    public Fumante(int numIngredient) {
      this.numIngredient = numIngredient;
      if(numIngredient==0) {
        System.out.println("tabaco");
      } else if(numIngredient==1){
        System.out.println("papel");
      } else {
        System.out.println("fósforo\n");
      }
    }

  
    public void run() {
      while (true) {
        try {
          smokerSemaphores[numIngredient].acquire();

          System.out.println("Fumante " + (numIngredient + 1) + " está fazendo um cigarro");
          Thread.sleep(1000);
          System.out.println("Fumante " + (numIngredient + 1) + " está fumando  ......\n");
          Thread.sleep(2000);
          agentSemaphore.release();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private static class Agente extends Thread {

    public void run() {
      while (true) {
        try {
          Random gerador = new Random();
          agentSemaphore.acquire();
          int primeiroIngred = gerador.nextInt(3);
          int segIngred = gerador.nextInt(3);
          while (segIngred == primeiroIngred) {
            segIngred = gerador.nextInt(3);
          }
          System.out.print("Agente colocou os ingredientes ");
          if(primeiroIngred==0) {
            System.out.print("tabaco e ");
          } else if(primeiroIngred==1){
            System.out.print("papel e ");
          } else if(primeiroIngred==2){
            System.out.print("fósforo e ");
          }

          if(segIngred==0) {
            System.out.println("tabaco na mesa");
          } else if(segIngred==1){
            System.out.println("papel na mesa");
          } else if(segIngred==2){
            System.out.println("fósforo na mesa");
          }

          int smoker = 0;

          for (int i = 0; i < numFumantes; i++) {
            if (i != primeiroIngred && i != segIngred) {
              smoker = i;
              break;
            }
          }
          smokerSemaphores[smoker].release();

        } catch (InterruptedException e) {}
      }
    }
  }

  public static void main(String[] args) {

    System.out.println("Iniciando....\n");

    for (int i = 0; i < numFumantes; i++) {
      System.out.print("Fumante "+(i+1)+" possui: ");
      Fumante smoker = new Fumante(i);
      
      smoker.start();
    }

    Agente agente = new Agente();
    agente.start();
  }
}
