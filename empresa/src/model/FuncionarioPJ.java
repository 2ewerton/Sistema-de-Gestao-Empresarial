package model;

public class FuncionarioPJ extends Funcionario {
    public FuncionarioPJ(String nome, String cpf, double salarioBase) {
        super(nome, cpf, salarioBase);
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase(); // Apenas base
    }
}