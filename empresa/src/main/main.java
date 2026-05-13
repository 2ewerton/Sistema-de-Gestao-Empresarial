package main;

import model.*;
import services.GerenciadorEmpresa;
import interfaces.Bonificacao;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GerenciadorEmpresa service = new GerenciadorEmpresa();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n==============================");
            System.out.println(" SISTEMA EMPRESARIAL JAVA");
            System.out.println("==============================");
            System.out.println("1 - Cadastrar Funcionário CLT");
            System.out.println("2 - Cadastrar Funcionário PJ");
            System.out.println("3 - Cadastrar Gerente");
            System.out.println("4 - Listar Funcionários");
            System.out.println("5 - Calcular Folha Salarial");
            System.out.println("6 - Realizar Login de Gerente");
            System.out.println("7 - Buscar Funcionário por CPF");
            System.out.println("0 - Sair");
            System.out.print("\nEscolha uma opção: ");
            
            opcao = sc.nextInt();
            sc.nextLine(); // Limpa o buffer do teclado

            switch (opcao) {
                case 1, 2, 3 -> {
                    // Interface comum para os três cadastros
                    System.out.print("Digite o nome: "); 
                    String nome = sc.nextLine();
                    System.out.print("Digite o CPF: "); 
                    String cpf = sc.nextLine();
                    System.out.print("Digite o salário base: "); 
                    double sal = sc.nextDouble();
                    sc.nextLine(); // limpa buffer

                    if (opcao == 1) {
                        service.cadastrarFuncionario(new FuncionarioCLT(nome, cpf, sal));
                        System.out.println("✅ Funcionário CLT cadastrado!");
                    } else if (opcao == 2) {
                        service.cadastrarFuncionario(new FuncionarioPJ(nome, cpf, sal));
                        System.out.println("✅ Funcionário PJ cadastrado!");
                    } else {
                        System.out.print("Digite a senha do gerente: ");
                        String senha = sc.nextLine();
                        service.cadastrarFuncionario(new Gerente(nome, cpf, sal, senha));
                        System.out.println("✅ Gerente cadastrado!");
                    }
                }

                case 4 -> {
                    System.out.println("\n==============================");
                    System.out.println(" LISTA DE FUNCIONÁRIOS");
                    System.out.println("==============================");
                    if (service.getFuncionarios().isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado.");
                    } else {
                        for (Funcionario f : service.getFuncionarios()) {
                            // Polimorfismo: o toString usado depende do tipo real do objeto
                            System.out.println(f);
                            System.out.println("Salário Final: R$ " + f.calcularSalario());
                            
                            // Verifica se o funcionário tem bônus (CLT e Gerente)
                            if (f instanceof Bonificacao) {
                                Bonificacao funcComBonus = (Bonificacao) f;
                                System.out.println("Bônus: R$ " + funcComBonus.calcularBonus());
                            }
                            System.out.println("----------------------------");
                        }
                    }
                }

                case 5 -> {
                    double total = service.calcularFolhaSalarial();
                    System.out.printf("Folha salarial total da empresa: R$ %.2f%n", total);
                }

                case 6 -> {
                    System.out.print("Digite o CPF do gerente: ");
                    String cpfBusca = sc.nextLine();
                    Funcionario f = service.buscarPorCpf(cpfBusca);

                    if (f instanceof Gerente) {
                        Gerente g = (Gerente) f;
                        System.out.print("Digite a senha: ");
                        String senha = sc.nextLine();
                        if (g.login(senha)) {
                            System.out.println("🔓 Login realizado com sucesso!");
                        } else {
                            System.out.println("❌ Senha incorreta!");
                        }
                    } else {
                        System.out.println("⚠️ Este funcionário não é um Gerente ou não existe.");
                    }
                }

                case 7 -> {
                    System.out.print("Digite o CPF para busca: ");
                    String cpfBusca = sc.nextLine();
                    Funcionario f = service.buscarPorCpf(cpfBusca);
                    if (f != null) {
                        System.out.println("\n--- Dados do Funcionário ---");
                        System.out.println(f);
                        System.out.println("Salário Base: R$ " + f.getSalarioBase());
                    } else {
                        System.out.println("❌ CPF não encontrado.");
                    }
                }

                case 0 -> System.out.println("Saindo do sistema...");
                
                default -> System.out.println("Opção inválida!");
            }
        }
        sc.close();
    }
}