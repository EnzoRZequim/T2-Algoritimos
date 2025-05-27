import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);     

        FilaDeImpressao filaImpressao = new FilaDeImpressao();
        PilhaDeEmergencia pilhaEmergencial = new PilhaDeEmergencia();
        int condicao;
        do {
            while (true) { 
                System.out.println("Escolha uma opção (0 fecha o programa): ");
                System.out.println("1 - enfilera documento");
                System.out.println("2 - empilhar documento EMERGENCIAL");
                System.out.println("3 - desenfileirar documento (Doc emergencial desempilha primeiro)");
                System.out.println("4 - Buscar Doc na Fila");
                System.out.println("5 - Buscar Doc na Pilha ");
                System.out.println("6 - mostrar fila e pilha");
                System.out.println("7 - Capacidade da fila. \n");
                try {
                    condicao = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Entrada invalida. Tente novamente.");
                    sc.nextLine();
                }
            }

                switch (condicao){
                    case 1:
                        if (filaImpressao.filaCheia()) {
                            System.out.println("Fila esta cheia.\n");
                        } else {
                            Item item = lerItem(sc);
                            filaImpressao.enfileira(item);
                            System.out.println("hora: " + item.hora + " - arquivo: " + item.nomeArquivo + " - pessoa: " + item.nomePessoa + "\n");
                        }
                        break;
                    case 2://Pilha doc
                        if (pilhaEmergencial.estaCheio())
                        System.out.println("Pilha esta cheia");
                        else {
                            Item item = lerItem(sc);
                            pilhaEmergencial.push(item);
                            System.out.println("hora: " + item.hora + " - arquivo: " + item.nomeArquivo + " - pessoa: " + item.nomePessoa + "\n");
                        }
                        break;
                    case 3: // desenfileirar documento (Doc emergencial desempilha primeiro)
                        if (pilhaEmergencial.estaVazio()){
                            System.out.println("Pilha emergencial vazia.");
                            if (filaImpressao.filaVazia())
                                System.out.println("Fila esta vazia");
                            else{
                                Item desinfilerado = filaImpressao.desenfileira();
                                RemoveDados(desinfilerado, TipoDados.FilaDeImpressao);
                            }
                        } else {
                            Item desenpilhado = pilhaEmergencial.pop();                            
                            RemoveDados(desenpilhado, TipoDados.PilhaDeEmergencia);                            
                        }
                        break;
                    case 4: //Posição doc Fila
                        if (!filaImpressao.filaVazia()){
                            System.out.println("Digite o nome do documento que quer saber a posicao");
                            String buscado = sc.next();
                            int posicao = filaImpressao.posicaoDoc(buscado);                        
                            if (posicao == 0)
                                System.out.println("Seu arquivo é o proximo");
                            else
                                System.out.println(posicao+ " Arquivos na sua frente");
                        }
                        else
                            System.out.println("Fila esta vasia.");
                        break;
                    case 5: //Posição doc Pilha
                        if (!pilhaEmergencial.estaVazio()){
                            System.out.println("Digite o nome do documento que quer saber a posicao");
                            String buscado = sc.next();
                            int posicao = pilhaEmergencial.posicaoDoc(buscado);
                            if (posicao == 1)
                                System.out.println("Seu arquivo e o proximo");
                            else
                                System.out.println((posicao-1) + " Arquivos na sua frente");
                        }
                        else 
                            System.out.println("Pilha vazia");
                        break;
                    case 6: // mostrar fila
                        if (pilhaEmergencial.estaVazio())
                            System.out.println("Pilha Emergencial esta vazia");
                        else
                            System.out.println("Pilha Emergencial:\n" + pilhaEmergencial);
                        if (filaImpressao.filaVazia())
                            System.out.println("Fila de Impressao esta vazia");
                        else 
                            System.out.println("Fila de Impressao:\n" + filaImpressao);
                        break;
                    case 7: //Capacidade da fila.
                        System.out.println("Pilha Emergencial: " + pilhaEmergencial.topo + "/" + pilhaEmergencial.getTamanho());
                        System.out.println("Fila de Impressao: " + filaImpressao.ocupacao + "/" + filaImpressao.TamanhoVetor() );
                        break;
                    default:
                        break;
                }
            } while (condicao != 0);
        sc.close();
    }
    private static BigDecimal calcularDiferenca(Item item) {
        LocalTime hora = LocalTime.now();
        LocalTime horaFormatada = LocalTime.of(hora.getHour(), hora.getMinute(), hora.getSecond(), hora.getNano());
        Duration diferencaHora = Duration.between(item.hora, horaFormatada);

        return BigDecimal.valueOf(diferencaHora.toMillis());
    }
    private static Item lerItem(Scanner sc) {
        System.out.println("Digite o nome do Arquivo");
        String nomeArquivo = sc.next();
        System.out.println("Digite o nome da Pessoa:");
        String nomePessoa = sc.next();
        LocalTime hora = LocalTime.now();
        LocalTime horaFormatada = LocalTime.of(hora.getHour(), hora.getMinute(), hora.getSecond());
        return new Item(nomeArquivo, nomePessoa, horaFormatada);
    }
    private static void RemoveDados (Item removido, TipoDados tipo){
        String tipoItem = "";
        BigDecimal diferenca = calcularDiferenca(removido);
        long totalMs = diferenca.longValue();
        long ms = totalMs % 1000;
        long totalSec = totalMs / 1000;
        long s = totalSec % 60;
        long m = totalSec / 60;
        if (tipo == TipoDados.FilaDeImpressao)
            tipoItem = "Fila de Impressão:\n";
        else if (tipo == TipoDados.PilhaDeEmergencia)
            tipoItem = "Pilha de Emergencia:\n";

        System.out.println(tipoItem + removido);
        System.out.println("Diferença de tempo: " + m + " min, " + s + " s, " + ms + " ms.\n");
    }
    public static enum TipoDados{
        PilhaDeEmergencia, // 0
        FilaDeImpressao    // 1
    }
}