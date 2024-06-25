package br.com.iniflex.modelo;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void aumentarSalario(double percentualAumento) {
        salario = salario.multiply(BigDecimal.valueOf(1 + percentualAumento / 100));
    }

    public String getFuncao() {
        return funcao;
    }

    @Override
    public String toString() {
        return super.toString() + ", Salário: " + String.format("%,.2f", salario) + ", Função: " + funcao;
    }
}
