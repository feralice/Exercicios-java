import  java.util.Random;
import java. util. concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class Matriz implements Runnable  {

    int linha;
    int coluna;
    int matriz1[][];
    int matriz2[][];
    int matriz3[][];

    public Matriz(int tam) {
        this.linha = tam;
        this.coluna = tam;
        this.matriz1 = new int[tam][tam];
        this.matriz2 = new int[tam][tam];
        this.matriz3 = new int[tam][tam];
    }

    public int getDimensao (){
        return coluna;
    }
    public void setDimensao (int linhas){
        this.linha = linhas;
    }

    public void run() {
        System.out.println("Starting");
        try{
            multiplicaMatriz();
            Thread.sleep(2000);
        } catch ( InterruptedException ignored) {}
        System.out.println("Completed");
    }

    public int[][] criaMatriz(int n) {
        int[][] matriz = new int[n][n];

         Random gera = new Random();

        for (int i = 0 ; i < n ; i++) {     
            for (int j = 0 ; j < n ; j++) {
                Integer r = gera.nextInt()% 20; 
                matriz[i][j] = Math.abs(r);
            }
         }
         return matriz;
    }

    public void copiaMatriz(int[][] matrix1, int[][] matrix2, int n) {
        for (int i = 0 ; i < n ; i++) {     
            for (int j = 0 ; j < n ; j++) {
                matriz1[i][j] = matrix1[i][j];
                matriz2[i][j] = matrix2[i][j];
            }
         }
    }

    public void multiplicaMatriz() {

        int n = getDimensao();

            for(int i = 0 ; i < n ; i++) {
                for(int j = 0 ; j < n ; j++) {
                    for(int k = 0 ; k < n ; k++) {
                        matriz3[i][j] += matriz1[i][k] * matriz2[k][j];
                    }
                }
            }
    }


    public void imprimeMatrizes() {
        System.out.println("Matriz A criada: ");
        for(int i=0; i<getDimensao(); i++) {
            for(int j=0; j<getDimensao();j++) {
                System.out.print(matriz1[i][j]+" ");
            }
            System.out.print("\n");
        }

        System.out.print("\nMatriz B criada: \n");
        for(int i=0; i<getDimensao(); i++) {
            for(int j=0; j<getDimensao();j++) {
                System.out.print(matriz2[i][j]+" ");
            }
            System.out.print("\n");
        }

        System.out.println("\nMatriz C criada da multiplicacao da A com B: \n");
        for(int i=0; i<getDimensao(); i++) {
            for(int j=0; j<getDimensao();j++) {
                System.out.print(matriz3[i][j]+" ");
            }
            System.out.print("\n");
        }

        
    }


    public static void main(String args[]) {

        Random gerador = new Random();

        int dimensao = gerador.nextInt(10);

        Matriz matriz = new Matriz(dimensao);
        int[][] matrix1 = matriz.criaMatriz(dimensao);
        int[][] matrix2 = matriz.criaMatriz(dimensao);
        matriz.copiaMatriz(matrix1,matrix2,dimensao);
        
        int mult = dimensao * dimensao;

        ExecutorService executor = Executors.newFixedThreadPool(mult);

        for(int i=0; i<mult; i++){
            executor.submit(new Matriz(i));
        }

        matriz.multiplicaMatriz();
        
        executor.shutdown();
        
        System.out.println("threads submetidas");
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
            
        } catch (InterruptedException ignored) {}

        matriz.imprimeMatrizes();
        System.out.println("tasks concluidas");

    }
}



