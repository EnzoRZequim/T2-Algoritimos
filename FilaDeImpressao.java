public class FilaDeImpressao {
    Item[] dados;
    int primeiro, ultimo, ocupacao;

    public FilaDeImpressao(int capacidade) {
        primeiro = 0;
        ultimo = 0;
        ocupacao = 0;
        dados = new Item[capacidade];
    }

    public FilaDeImpressao() {
        this(5);
    }

    public boolean filaVazia() {
        return ocupacao == 0;
    }

    public boolean filaCheia() {
        return ocupacao == dados.length;
    }

    private int proximaPosicao(int posicao) {
        return (posicao + 1) % dados.length;
    }

    public void enfileira(Item item) { 
        if (filaCheia())
            throw new RuntimeException("falha na insercao");
        dados[ultimo] = item;
        ultimo = proximaPosicao(ultimo);
        ocupacao++;
    }

    public Item desenfileira() { 
        if (filaVazia())
            throw new RuntimeException("falha na remocao");
        Item aux = dados[primeiro];
        primeiro = proximaPosicao(primeiro);
        ocupacao--;
        return aux;
    }

    public int posicaoDoc (String nomeDoc){
        for (int i = primeiro, cont = 0; cont < ocupacao; i = proximaPosicao(i), cont++){
            if (nomeDoc.equals(dados[i].getNomeArquivo()))
                return cont;            
        }
        return -1;
    }

    @Override
    public String toString() {
        if (filaVazia())
            return "fila vazia";
        String s = "-----------\n";
        for (int i = primeiro, cont = 0; cont < ocupacao; i = proximaPosicao(i), cont++) {
            s = s + dados[i] + "\n";
        }
        return s + "-----------\n";
    }

    public String stringVetor() {
        String s = "";
        for (int i = 0; i < dados.length; i++) {
            if (dados[i] == null)
                s += "_ ";
            else
                s += dados[i] + " ";
        }
        return s;
    }

    public int TamanhoVetor() {
        return this.dados.length;
    }
}