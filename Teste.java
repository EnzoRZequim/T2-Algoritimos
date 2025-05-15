import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

public class Teste { //Esta redimencionando os veteros, é para deixar assim?
    public static void main(String[] args) {
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
            System.out.println("4 - mostrar fila");
            System.out.println("5 - Capacidade da fila. \n");
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
                            Long diferenca = calcularDiferenca(desinfilerado);
                            System.out.println("Fila de impressao:\n" + desinfilerado);
                            System.out.println("Diferença de tempo: " + diferenca + " segundos.\n");
                        }
                    } else {
                        Item desenpilhado = pilhaEmergencial.pop();
                        long diferenca = calcularDiferenca(desenpilhado);
                        System.out.println("Pilha de emergencia:\n" + desenpilhado);
                        System.out.println("Diferença de tempo: " + diferenca + " segundos.\n");
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
                    System.out.println("Pilha Emergencial: " + pilhaEmergencial.topo + "/" + pilhaEmergencial.getTamanho());
                    System.out.println("Fila de Impressao: " + filaImpressao.ocupacao + "/" + filaImpressao.TamanhoVetor() );
                    break;
                default:
                    break;
            }
        } while (condicao != 0);
        sc.close();
    }
    private static Long calcularDiferenca(Item item)
    {
        LocalTime hora = LocalTime.now();
        LocalTime horaFormatada = LocalTime.of(hora.getHour(), hora.getMinute(), hora.getSecond());
        Duration diferencaHora = Duration.between(item.hora, horaFormatada);

        return diferencaHora.toSeconds();
    }
        private static Item lerItem(Scanner sc) {
        System.out.println("Digite o nome do arquivo:");
        String nomeArquivo = sc.next();
        System.out.println("Digite o nome da pessoa:");
        String nomePessoa = sc.next();
        LocalTime hora = LocalTime.now();
        LocalTime horaFormatada = LocalTime.of(hora.getHour(), hora.getMinute(), hora.getSecond());
        return new Item(nomeArquivo, nomePessoa, horaFormatada);
    }
}