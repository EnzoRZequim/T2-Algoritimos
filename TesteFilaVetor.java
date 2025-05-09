import java.time.LocalTime;
import java.util.Scanner;

public class TesteFilaVetor {
    public static void main(String[] args) {
        int tamanho = 10;
        System.out.println("Qual o tamanho da fila? (0 a 50)");
        Scanner sc = new Scanner(System.in);
        
        try { //Usuario n escolhe
            tamanho = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Valor inválido. Tamanho padrão de 10 será utilizado."); //Repetindo outPut
            //tamanho = 10; // Repetindo Tamanho = 10; 
        }
        if (tamanho <= 0) {
            System.out.println("Valor inválido. Tamanho padrão de 10 será utilizado."); // Repetindo outPut
            //tamanho = 10; //Repetindo Tamanho = 10;
        } else if (tamanho > 50) {
            System.out.println("Valor muito grande. Tamanho padrão de 10 será utilizado.");
            //tamanho = 10; // Repetindo Tamanho = 10;
        }
        System.out.println("");

        FilaDeImpressao filaImpressao = new FilaDeImpressao(tamanho);
        FilaEmergencial filaEmergencial = new FilaEmergencial(tamanho);

        int condisao;
        do {
            System.out.println("Escolha uma opção (0 fecha o programa): ");
            System.out.println("1 - empilhar documento");
            System.out.println("2 - empilhar documento EMERGENCIAL");
            System.out.println("3 - desenfileirar documento (Doc emergencial desempilha primeiro)");
            System.out.println("4 - mostrar fila");
            System.out.println("5 - Capacidade da fila. \n");
            condisao = sc.nextInt();

            if (condisao == 1) {
                if (filaImpressao.filaCheia()){ // empilha Doc
                    System.out.println("Fila esta cheia.\n");
                }else{                
                    System.out.println("Digite o nome do arquivo:");
                    String nomeArquivo = sc.next();
                    System.out.println("Digite o nome da pessoa:");
                    String nomePessoa = sc.next();
                    
                    LocalTime hora= LocalTime.now();
                    LocalTime horaFormatada = LocalTime.of(hora.getHour(), hora.getMinute(), hora.getSecond());
                    
                    filaImpressao.enfileira(new ItemFila(nomeArquivo, nomePessoa, horaFormatada));
                    
                    System.out.println("hora: " + horaFormatada + " - arquivo: " + nomeArquivo + " - pessoa: " + nomePessoa +"\n");
                }
            } else if (condisao == 2) { //empilha Emergencia
                if (filaEmergencial.filaCheia()){
                    System.out.println("Fila esta cheia.\n");
                }else {
                    System.out.println("Digite o nome do arquivo:");
                    String nomeArquivo = sc.next();
                    System.out.println("Digite o nome da pessoa:");
                    String nomePessoa = sc.next();

                    LocalTime hora = LocalTime.now();
                    LocalTime horaFormatada = LocalTime.of(hora.getHour(), hora.getMinute(), hora.getSecond());

                    filaEmergencial.enfileira(new ItemFila(nomeArquivo, nomePessoa, horaFormatada));

                    System.out.println("hora: " + horaFormatada + " - arquivo: " + nomeArquivo + " - pessoa: " + nomePessoa + "\n");
                }

            } else if (condisao == 3) { //Desenpilha
                if (filaEmergencial.ocupacao != 0) 
                    filaEmergencial.desenfileira();                    
                else if (filaImpressao.ocupacao != 0)
                    filaImpressao.desenfileira();
                else
                    System.out.println("Não tem documentos na fila");

            } else if (condisao == 4) { //Mostra Pilha
                if (filaEmergencial.ocupacao != 0)
                    System.out.println("Fila Emergencial: " + filaEmergencial);
                else
                    System.out.println("Fila Emergencial vazia");
                if (filaImpressao.ocupacao != 0)   
                    System.out.println("Fila de Impressao" + filaImpressao);
                else
                    System.out.println("Fila de impressao vazia");

            } else if (condisao == 5) { //Capacidade Fila
                System.out.println("Fila de Impressao: \n" + filaImpressao.ocupacao + "/" + tamanho);
                System.out.println("Fila Emergencial: \n" + filaEmergencial.ocupacao + "/" + tamanho);

            } else
                System.out.println("Opção inválida. Tente novamente.");            
        } while (condisao != 0);
   }
}