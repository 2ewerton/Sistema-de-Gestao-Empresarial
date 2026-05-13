package model;
import interfaces.Autenticavel;
import interfaces.Bonificacao;

public class Gerente extends Funcionario implements Bonificacao, Autenticavel {
    private String senha;

    public Gerente(String nome, String cpf, double salarioBase, String senha) {
        super(nome, cpf, salarioBase);
        this.senha = senha;
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() * 1.30; // Base + 30%
    }

    @Override
    public double calcularBonus() {
        return getSalarioBase() * 0.20; // 20% da base
    }

    @Override
    public boolean login(String senhaFornecida) {
        return this.senha.equals(senhaFornecida);
    }
}