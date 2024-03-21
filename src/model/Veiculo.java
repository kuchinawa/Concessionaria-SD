package model;

import java.io.Serializable;

public class Veiculo implements Serializable {
    private String renavam;
    private String nome;
    private String categoria;
    private int anoFabricacao;
    private double preco;

    public Veiculo(String renavam, String nome, String categoria, int anoFabricacao, double preco) {
        this.renavam = renavam;
        this.nome = nome;
        this.categoria = categoria;
        this.anoFabricacao = anoFabricacao;
        this.preco = preco;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }


    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Veículo{" +
                "renavam='" + renavam + '\'' +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", anoFabricacao=" + anoFabricacao +
                ", preco=" + preco +
                '}';
    }
}
