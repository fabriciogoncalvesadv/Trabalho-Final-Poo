import java.util.Date;

public class Boleto extends FormaPagamento {
    private String codigoBarras;
    private Date dataVencimento;

    public Boleto(String codigoBarras, Date dataVencimento) {
        this.codigoBarras = codigoBarras;
        this.dataVencimento = dataVencimento;
    }

    @Override
    public void processarPagamento(double valor) {
        // Simulação de processamento de pagamento com boleto
        System.out.println("Processando pagamento de R$ " + valor + " com boleto.");
        System.out.println("Código de Barras: " + this.codigoBarras);
        System.out.println("Data de Vencimento: " + this.dataVencimento);
        // Aqui você adicionaria a lógica real de processamento de pagamento
        System.out.println("Boleto gerado com sucesso!");
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
