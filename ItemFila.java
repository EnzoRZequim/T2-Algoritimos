import java.time.LocalTime;

public class ItemFila {
    String nomeArquivo;
    String nomePessoa;
    LocalTime hora;

    public ItemFila(String nomeArquivo, String nomePessoa, LocalTime hora) {
        this.nomeArquivo = nomeArquivo;
        this.nomePessoa = nomePessoa;
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Arquivo: " + nomeArquivo + ", Pessoa: " + nomePessoa + ", Hora: " + hora;
    }
}