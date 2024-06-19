
import java.util.Date;

public class Venda {
    private Cliente cliente;
    private Vendedor vendedor;
    private Produto produto;
    private int quantidade;
    private Date dataVenda;
    private FormaPagamento formaPagamento;

    public Venda(Cliente cliente, Vendedor vendedor, Produto produto, int quantidade, Date dataVenda, FormaPagamento formaPagamento) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.produto = produto;
        this.quantidade = quantidade;
        this.dataVenda = dataVenda;
        this.formaPagamento = formaPagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double calcularValorTotal() {
        return this.quantidade * this.produto.getPreco();
    }

    public void processarVenda() {
        double valorTotal = calcularValorTotal();
        this.formaPagamento.processarPagamento(valorTotal);
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
