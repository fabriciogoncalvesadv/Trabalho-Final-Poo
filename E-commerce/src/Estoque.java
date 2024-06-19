import java.util.HashMap;

public class Estoque {
    private HashMap<Produto, Integer> produtos;

    public Estoque() {
        this.produtos = new HashMap<>();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        this.produtos.put(produto, quantidade);
    }

    public void removerProduto(Produto produto, int quantidade) {
        if (this.produtos.containsKey(produto)) {
            int novaQuantidade = this.produtos.get(produto) - quantidade;
            if (novaQuantidade <= 0) {
                this.produtos.remove(produto);
            } else {
                this.produtos.put(produto, novaQuantidade);
            }
        }
    }

    public int verificarEstoque(Produto produto) {
        return this.produtos.getOrDefault(produto, 0);
    }
}
