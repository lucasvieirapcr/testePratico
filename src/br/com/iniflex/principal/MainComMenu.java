package br.com.iniflex.principal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import br.com.iniflex.modelo.Funcionario;

public class MainComMenu {
    private static List<Funcionario> funcionarios = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        inicializarFuncionarios();

        // Exibir menu
        exibirMenu();
    }

    private static void inicializarFuncionarios() {
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 12), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1993, 3, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
    }

    private static void exibirMenu() {
        int opcao = 0;
        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Inserir funcionários");
            System.out.println("2. Remover funcionário por nome");
            System.out.println("3. Imprimir todos os funcionários");
            System.out.println("4. Aumentar salário dos funcionários");
            System.out.println("5. Agrupar funcionários por função");
            System.out.println("6. Imprimir funcionários por função");
            System.out.println("7. Funcionários que fazem aniversário nos meses 10 e 12");
            System.out.println("8. Funcionário mais velho");
            System.out.println("9. Funcionários ordenados por nome");
            System.out.println("10. Total dos salários dos funcionários");
            System.out.println("11. Quantidade de salários mínimos dos funcionários");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    System.out.println("Funcionários já inseridos.");
                    break;
                case 2:
                    removerFuncionario();
                    break;
                case 3:
                    imprimirFuncionarios();
                    break;
                case 4:
                    aumentarSalarios();
                    break;
                case 5:
                    agruparPorFuncao();
                    break;
                case 6:
                    imprimirFuncionariosPorFuncao();
                    break;
                case 7:
                    imprimirAniversariantes();
                    break;
                case 8:
                    imprimirFuncionarioMaisVelho();
                    break;
                case 9:
                    ordenarFuncionariosPorNome();
                    break;
                case 10:
                    imprimirTotalSalarios();
                    break;
                case 11:
                    imprimirSalariosMinimos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void removerFuncionario() {
        System.out.print("Digite o nome do funcionário que deseja remover: ");
        String nome = scanner.nextLine();

        boolean removido = funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
        if (removido) {
            System.out.println("Funcionário removido com sucesso.");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private static void imprimirFuncionarios() {
        System.out.println("Funcionários:");
        funcionarios.forEach(System.out::println);
    }

    private static void aumentarSalarios() {
        funcionarios.forEach(funcionario -> funcionario.aumentarSalario(10));
        System.out.println("Salários aumentados em 10%.");
    }

    private static void agruparPorFuncao() {
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        System.out.println("Funcionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(System.out::println);
            System.out.println();
        });
    }

    private static void imprimirFuncionariosPorFuncao() {
        agruparPorFuncao(); // Reutilizando o método anterior para imprimir os funcionários agrupados por função
    }

    private static void imprimirAniversariantes() {
        System.out.println("Funcionários que fazem aniversário nos meses 10 e 12:");
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 ||
                        funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);
    }

    private static void imprimirFuncionarioMaisVelho() {
        Comparator<Funcionario> idadeComparator = Comparator.comparingInt(funcionario ->
                LocalDate.now().getYear() - funcionario.getDataNascimento().getYear());
        Funcionario maisVelho = Collections.max(funcionarios, idadeComparator);
        int idadeMaisVelho = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
        System.out.println("Funcionário mais velho:");
        System.out.println("Nome: " + maisVelho.getNome() + ", Idade: " + idadeMaisVelho);
    }

    private static void ordenarFuncionariosPorNome() {
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("Funcionários ordenados por nome:");
        funcionarios.forEach(System.out::println);
    }

    private static void imprimirTotalSalarios() {
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários dos funcionários: R$ " + String.format("%,.2f", totalSalarios));
    }

    private static void imprimirSalariosMinimos() {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("Quantidade de salários mínimos dos funcionários:");
        funcionarios.forEach(funcionario -> {
            BigDecimal numSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
            System.out.println(funcionario.getNome() + ": " + numSalariosMinimos + " salários mínimos");
        });
    }
}