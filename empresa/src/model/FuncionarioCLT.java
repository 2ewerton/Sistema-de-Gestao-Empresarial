package model;
import interfaces.Bonificacao;

public class FuncionarioCLT extends Funcionario implements Bonificacao {
    public FuncionarioCLT(String nome, String cpf, double salarioBase) {
        super(nome, cpf, salarioBase);
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() * 1.10; // Base + 10%
    }

    @Override
    public double calcularBonus() {
        return getSalarioBase() * 0.05; // 5% da base
    }
}