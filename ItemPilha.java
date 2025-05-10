import java.time.LocalTime;

public class ItemPilha {
    String nomeArquivo;
    String nomeUsuario;
    LocalTime horarioAtual;

    public ItemPilha(String nomeArquivo, String nomeUsuario, LocalTime horarioAtual) {
        this.nomeArquivo = nomeArquivo;
        this.nomeUsuario = nomeUsuario;
        this.horarioAtual = horarioAtual;
    }

    @Override
    public String toString() {
        return "Arquivo: " + nomeArquivo + ", Usuario: " + nomeUsuario + ", Horario: " + horarioAtual;
    }
}
