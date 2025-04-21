import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaBancario {
    private static Banco banco = new Banco();
    private static Scanner scanner = new Scanner(System.in);
    private static List<Conta> contas = new ArrayList<>();

    public static void main (String[] args) {
        banco.setNome("Banco Digital Java");
        banco.setContas(contas);

        int opcao;
        do {
            System.out.println("\n=== MENU BANCO DIGITAL"); 
            System.out.println("1 Criar Conta Corrente");
            System.out.println("2 Criar Conta Poupança");
            System.out.println("3 Depositar");
            System.out.println("4 Sacar");
            System.out.println("5 Transferir");
            System.out.println("6 Listar Contas");
            System.out.println("0 Sair");
            System.out.println("Escolha uma opção");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> criarConta("corrente");
                case 2 -> criarConta("poupança");
                case 3 -> realizarDeposito();
                case 4 -> realizarSaque();
                case 5 -> realizarTransferencia();
                case 6 -> listarContas();
                case 0 -> System.out.println("Saindo...");
                    
            }
        }while (opcao != 0);
                
        }

    private static void criarConta(String tipo) {
           System.out.println("Nome do Cliente: "); 
           String nome = scanner.nextLine();
           
           
           System.out.println("Idade do Cliente: "); 
           int idade = scanner.nextInt();

           System.out.println("Digite o valor inicial para depósito: "); 
           double depositoInicial = scanner.nextDouble();
           scanner.nextLine();
           
           
           Cliente cliente = new Cliente ();
           cliente.setNome(nome); 
           cliente.setIdade(idade); 

           Conta novaConta = tipo.equalsIgnoreCase("corrente") ? new ContaCorrente(cliente) : new ContaPoupanca(cliente); 
           contas.add(novaConta);
           novaConta.depositar(depositoInicial);
           System.out.println( "Conta criada com sucesso! Número: " + novaConta.getNumero());
           System.out.println("Cliente: " + cliente.getNome());
           System.out.println("Idade: " + cliente.getIdade());
           System.out.printf("Saldo inicial: R$ %.2f%n", novaConta.getSaldo());

          
           }

    private static Conta encontrarContaPorNumero(){
        System.out.println("Por favor , informe o número da conta: ");
        int numero = scanner.nextInt();
        for (Conta conta : contas) {
            if (conta.getNumero() == numero) return conta; 
            }
        System.out.println("Conta não encontrada ");
        return null;
        
        }

    private static void realizarDeposito(){
        Conta conta = encontrarContaPorNumero();
        if (conta != null) {
        System.out.println("Digite o valor desejado para Depósito: ");
        Double valor = scanner.nextDouble();
        conta.depositar(valor);
        System.out.println("Depósito realizado com sucesso.");
            
        }
    }
    
    private static void realizarSaque() {
        Conta conta = encontrarContaPorNumero();
        if (conta != null) {
            System.out.println("Digite o valor desejado para Saque: ");
            double valor = scanner.nextDouble();
            conta.sacar(valor);
            System.out.println("Saque realizado com sucesso.");
            
        }
    }

    private static void realizarTransferencia(){
        System.out.println("Conta de origem ");
        Conta origem = encontrarContaPorNumero();
        if  (origem == null) return;

        System.out.println("Conta de Destino");
        Conta destino = encontrarContaPorNumero();
        if (destino == null) return;

        System.out.println("Digite o valor desejado para a transferência: ");
        double valor = scanner.nextDouble();
        origem.transferir(valor, destino);
        System.out.println("Transferência realizada com sucesso.");

    
        }

    private static void listarContas() {
        for (Conta conta: contas) {
            conta.imprimirExtrato();
            System.out.println("------------------");
        }

    }

    }       
