
public class PagamentoEmDinheiro extends FormaPagamento {
    @Override
    public void processarPagamento(double valor) {
        // Simulação de processamento de pagamento em dinheiro
        System.out.println("Processando pagamento de R$ " + valor + " em dinheiro.");
        // Aqui você adicionaria a lógica real de processamento de pagamento
        System.out.println("Pagamento em dinheiro recebido com sucesso!");
    }
}
