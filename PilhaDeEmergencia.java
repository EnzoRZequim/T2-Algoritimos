public class PilhaDeEmergencia {
    Item[] vetor;
    int topo;

    public PilhaDeEmergencia(int tamanho) {
        topo = 0;
        vetor = new Item[tamanho];
    }

    public PilhaDeEmergencia() {
        this(5);
    }

    public void push(Item item) {
        if (estaCheio())
            throw new VetorException("vetor esta cheio");
        vetor[topo++] = item;
    }

    public int getTamanho() {
        return vetor.length;
    }

    public Item pop() {
        if (estaVazio())
            throw new VetorException("Pilha vazia, nao ha o que remover");
        Item aux = vetor[--topo];
        return aux;
    }

    public boolean estaCheio() {
        return topo == vetor.length;
    }

    public boolean estaVazio() {
        return topo == 0;
    }

    // private void redimensiona(int novoTamanho) {
    //     Item[] temp = new Item[novoTamanho];
    //     for (int i = 0; i < topo; i++) {
    //         temp[i] = vetor[i];
    //     }
    //     vetor = temp;
    // }

    public int posicaoDoc(String nomeDoc){
        for (int i = topo -1, pos=1; i >= 0; i--, pos++){
            if(nomeDoc.equals(vetor[i].getNomeArquivo())){
                return pos;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String s = "-----------\n";
        if (estaVazio())
            s += "esta vazia\n";
        else
            for (int i = topo - 1; i >= 0; i--) {
                s += vetor[i] + "\n";
            }
        return s + "-----------\n";
    }

    public boolean contem(Item i) {
        for (int j = 0; j < topo; j++)
            if (vetor[j] == i)
                return true;
        return false;
    }

    public int indiceDe(Item i) {
        for (int j = 0; j < topo; j++)
            if (vetor[j] == i)
                return j;
        return -1;
    }
}

class VetorException extends RuntimeException {
    public VetorException(String msg) {
        super(msg);
    }
}