import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Menu implements Gui {
    private final ArrayList<Cliente> clientes;
    private final ArrayList<Vendedor> vendedores;
    private final ArrayList<Produto> produtos;
    private final Estoque estoque;
    private final Scanner scanner;

    public Menu() {
        this.clientes = new ArrayList<>();
        this.vendedores = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.estoque = new Estoque();
        this.scanner = new Scanner(System.in);
    }


    @Override
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\nMenu do Sistema de Vendas:");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Adicionar Vendedor");
            System.out.println("3. Adicionar Produto");
            System.out.println("4. Registrar Venda");
            System.out.println("5. Verificar Estoque");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha

            switch (opcao) {
                case 1 -> adicionarCliente();
                case 2 -> adicionarVendedor();
                case 3 -> adicionarProduto();
                case 4 -> registrarVenda();
                case 5 -> verificarEstoque();
                case 6 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 6);
    }

    private void adicionarCliente() {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do Cliente: ");
        String cpf = scanner.nextLine();
        System.out.print("Endereço do Cliente: ");
        String endereco = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpf, endereco);
        clientes.add(cliente);
        System.out.println("Cliente adicionado com sucesso!");
    }

    private void adicionarVendedor() {
        System.out.print("Nome do Vendedor: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do Vendedor: ");
        String cpf = scanner.nextLine();
        System.out.print("Salário do Vendedor: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();  // Consumir a nova linha

        Vendedor vendedor = new Vendedor(nome, cpf, salario);
        vendedores.add(vendedor);
        System.out.println("Vendedor adicionado com sucesso!");
    }

    private void adicionarProduto() {
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();
        System.out.print("Preço do Produto: ");
        double preco = scanner.nextDouble();
        System.out.print("Quantidade do Produto: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha

        Produto produto = new Produto(nome, preco, quantidade);
        produtos.add(produto);
        estoque.adicionarProduto(produto, quantidade);
        System.out.println("Produto adicionado com sucesso!");
    }

    private void registrarVenda() {
        System.out.print("CPF do Cliente: ");
        String cpfCliente = scanner.nextLine();
        Cliente cliente = encontrarCliente(cpfCliente);

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        System.out.print("CPF do Vendedor: ");
        String cpfVendedor = scanner.nextLine();
        Vendedor vendedor = encontrarVendedor(cpfVendedor);

        if (vendedor == null) {
            System.out.println("Vendedor não encontrado!");
            return;
        }

        System.out.print("Nome do Produto: ");
        String nomeProduto = scanner.nextLine();
        Produto produto = encontrarProduto(nomeProduto);

        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha

        if (estoque.verificarEstoque(produto) < quantidade) {
            System.out.println("Quantidade em estoque insuficiente!");
            return;
        }

        System.out.println("Selecione a forma de pagamento:");
        System.out.println("1. Cartão de Crédito");
        System.out.println("2. Boleto");
        System.out.println("3. Dinheiro");
        int opcaoPagamento = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha

        FormaPagamento formaPagamento;
        switch (opcaoPagamento) {
            case 1 -> {
                System.out.print("Número do Cartão: ");
                String numeroCartao = scanner.nextLine();
                System.out.print("Nome do Titular: ");
                String nomeTitular = scanner.nextLine();
                System.out.print("Data de Validade (MM/AA): ");
                String dataValidade = scanner.nextLine();
                System.out.print("CVV: ");
                String cvv = scanner.nextLine();
                formaPagamento = new CartaoCredito(numeroCartao, nomeTitular, dataValidade, cvv);
            }
            case 2 -> {
                System.out.print("Código de Barras do Boleto: ");
                String codigoBarras = scanner.nextLine();
                System.out.print("Data de Vencimento (dd/MM/aaaa): ");
                String dataVencimento = scanner.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date date = sdf.parse(dataVencimento);
                    formaPagamento = new Boleto(codigoBarras, date);
                } catch (ParseException e) {
                    System.out.println("Data de vencimento inválida!");
                    return;
                }
            }
            case 3 -> formaPagamento = new PagamentoEmDinheiro();
            default -> {
                System.out.println("Opção de pagamento inválida!");
                return;
            }
        }

        Venda venda = new Venda(cliente, vendedor, produto, quantidade, new Date(), formaPagamento);
        estoque.removerProduto(produto, quantidade);
        venda.processarVenda();
        System.out.println("Venda registrada com sucesso! Valor total: R$ " + venda.calcularValorTotal());
    }

    private void verificarEstoque() {
        System.out.print("Nome do Produto: ");
        String nomeProduto = scanner.nextLine();
        Produto produto = encontrarProduto(nomeProduto);

        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        int quantidade = estoque.verificarEstoque(produto);
        System.out.println("Quantidade em estoque: " + quantidade);
    }

    private Cliente encontrarCliente(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    private Vendedor encontrarVendedor(String cpf) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getCpf().equals(cpf)) {
                return vendedor;
            }
        }
        return null;
    }

    private Produto encontrarProduto(String nome) {
        for (Produto produto : produtos) {
            if (produto.getNome().equals(nome)) {
                return produto;
            }
        }
        return null;
    }
}
