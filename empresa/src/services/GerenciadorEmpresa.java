package services;

import model.Funcionario;
import java.util.ArrayList;

public class GerenciadorEmpresa {
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();

    // 6. SOBRECARGA
    public void cadastrarFuncionario(Funcionario f) {
        funcionarios.add(f);
    }

    public void cadastrarFuncionario(String nome) {
        System.out.println("Iniciando cadastro de: " + nome);
    }

    public void cadastrarFuncionario(String nome, String cpf) {
        System.out.println("Cadastro parcial de: " + nome + " CPF: " + cpf);
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public Funcionario buscarPorCpf(String cpf) {
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) return f;
        }
        return null;
    }

    public double calcularFolhaSalarial() {
        double total = 0;
        for (Funcionario f : funcionarios) {
            total += f.calcularSalario(); // Polimorfismo
        }
        return total;
    }
}