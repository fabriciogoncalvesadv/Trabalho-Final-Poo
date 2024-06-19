public class CartaoCredito extends FormaPagamento {
    private String numeroCartao;
    private String nomeTitular;
    private String dataValidade;
    private String cvv;

    public CartaoCredito(String numeroCartao, String nomeTitular, String dataValidade, String cvv) {
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.dataValidade = dataValidade;
        this.cvv = cvv;
    }

    @Override
    public void processarPagamento(double valor) {
        // Simulação de processamento de pagamento com cartão de crédito
        System.out.println("Processando pagamento de R$ " + valor + " com cartão de crédito.");
        System.out.println("Número do Cartão: " + this.numeroCartao);
        // Aqui você adicionaria a lógica real de processamento de pagamento
        System.out.println("Pagamento com cartão de crédito processado com sucesso!");
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
