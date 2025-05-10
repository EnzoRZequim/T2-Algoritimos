public class PilhaEmergencial {
    ItemFila[] vetor;
    int topo;

    public PilhaEmergencial(int tamanho) {
        vetor = new ItemFila[tamanho];
        topo = 0;
    }

    public PilhaEmergencial() {
        this(10);
    }

    public void push(ItemFila i) {
        if (estaCheio())
            redimensiona(vetor.length * 2);
        vetor[topo++] = i;
    }

    public int getTamanho() {
        return vetor.length;
    }

    public ItemFila pop() {
        if (estaVazio())
            throw new VetorVazioException("vetor vazio, nao ha o que remover");
            ItemFila aux = vetor[--topo];
        if (vetor.length >= 6 && topo <= vetor.length / 4)
            redimensiona(vetor.length / 2);
        return aux;
    }

    public boolean estaCheio() {
        return topo == vetor.length;
    }

    public boolean estaVazio() {
        return topo == 0;
    }

    private void redimensiona(int novoTamanho) {
        int[] temp = new int[novoTamanho];
        for (int i = 0; i < topo; i++) {
            temp[i] = vetor[i];
        }
        vetor = temp;
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

    public boolean contem(int i) {
        for (int j = 0; j < topo; j++)
            if (vetor[j] == i)
                return true;
        return false;
    }

    public int indiceDe(int i) {
        for (int j = 0; j < topo; j++)
            if (vetor[j] == i)
                return j;
        return -1;
    }
}

class VetorVazioException extends RuntimeException {
    public VetorVazioException(String msg) {
        super(msg);
    }
 }