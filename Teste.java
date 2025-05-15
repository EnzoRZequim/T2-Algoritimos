import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);     

        FilaDeImpressao filaImpressao = new FilaDeImpressao();
        PilhaDeEmergencia pilhaEmergencial = new PilhaDeEmergencia();
        int condicao;
    do {
            System.out.println("Escolha uma opção (0 fecha o programa): ");
            System.out.println("1 - enfilera documento");
            System.out.println("2 - empilhar documento EMERGENCIAL");
            System.out.println("3 - desenfileirar documento (Doc emergencial desempilha primeiro)");
            System.out.println("4 - mostrar fila");
            System.out.println("5 - Capacidade da fila. \n");
            condicao = sc.nextInt();

            switch (condicao){
                case 1:
                    if (filaImpressao.filaCheia()) // fila Doc
                        System.out.println("Fila esta cheia.\n");
                    else{                
                        System.out.println("Digite o nome do arquivo:");
                        String nomeArquivo = sc.next();
                        System.out.println("Digite o nome da pessoa:");
                        String nomePessoa = sc.next();
                        
                        LocalTime hora= LocalTime.now();
                        LocalTime horaFormatada = LocalTime.of(hora.getHour(), hora.getMinute(), hora.getSecond());
                        
                        filaImpressao.enfileira(new Item(nomeArquivo, nomePessoa, horaFormatada));                    
                        System.out.println("hora: " + horaFormatada + " - arquivo: " + nomeArquivo + " - pessoa: " + nomePessoa +"\n");
                    }
                    break;
                case 2://Pilha doc
                    if (pilhaEmergencial.estaCheio())
                    System.out.println("Pilha esta cheia");
                    else{
                        System.out.println("Digite o nome do arquivo:");
                        String nomeArquivo = sc.next();
                        System.out.println("Digite o nome da pessoa:");
                        String nomePessoa = sc.next();
                        
                        LocalTime hora= LocalTime.now();
                        LocalTime horaFormatada = LocalTime.of(hora.getHour(), hora.getMinute(), hora.getSecond());
                        
                        pilhaEmergencial.push(new Item(nomeArquivo, nomePessoa, horaFormatada));
                        System.out.println("hora: " + horaFormatada + " - arquivo: " + nomeArquivo + " - pessoa: " + nomePessoa +"\n");
                    }
                    break;
                case 3: // desenfileirar documento (Doc emergencial desempilha primeiro)
                    if (pilhaEmergencial.estaVazio()){
                        System.out.println("Pilha emergencial vazia");
                        if (filaImpressao.filaVazia())
                        System.out.println("Fila esta vazia");
                        else{
                            Item desinfilerado = filaImpressao.desenfileira();
                            Long diferenca = CalcularDiferenca(desinfilerado);
                            System.out.println("Diferença de tempo: " + diferenca + " segundos");
                            System.out.println("Fila de impressao:\n" + desinfilerado);
                        }
                    } else {
                        System.out.println("Pilha de emergencia:\n" + pilhaEmergencial.pop());
                    }
                    break;
                case 4: // mostrar fila
                    if (pilhaEmergencial.estaVazio())
                    System.out.println("Pilha Emergencial esta vazia");
                    else
                    System.out.println("Pilha Emergencial:\n" + pilhaEmergencial);
                    if (filaImpressao.filaVazia())
                    System.out.println("Fila de Impressao esta vazia");
                    else 
                    System.out.println("Fila de Impressao:\n" + filaImpressao);
                    break;
                case 5: //Capacidade da fila.
                    System.out.println("Pilha Emergencial: " + pilhaEmergencial.topo + "/" + pilhaEmergencial.TamanhoVetor());
                    System.out.println("Fila de Impressao: " + filaImpressao.ocupacao + "/" + filaImpressao.TamanhoVetor() );
                    break;
            }
        } while (condicao != 0);
        sc.close();
    }
    private static Long CalcularDiferenca(Item item)
    {
        LocalTime hora = LocalTime.now();
        LocalTime horaFormatada = LocalTime.of(hora.getHour(), hora.getMinute(), hora.getSecond());
        Duration diferencaHora = Duration.between(item.hora, horaFormatada);

        return diferencaHora.toSeconds();
    }
}