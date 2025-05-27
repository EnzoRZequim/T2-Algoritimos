import java.time.LocalTime;

public class Item {
    String nomeArquivo;
    String nomePessoa;
    LocalTime hora;

    public Item(String nomeArquivo, String nomePessoa, LocalTime hora) {
        this.nomeArquivo = nomeArquivo;
        this.nomePessoa = nomePessoa;
        this.hora = hora;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    @Override
    public String toString() {
        return "Arquivo: " + nomeArquivo + ", Pessoa: " + nomePessoa + ", Hora: " + hora;
    }
}