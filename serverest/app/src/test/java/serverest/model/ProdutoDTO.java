package serverest.model;

import com.github.javafaker.Faker;

public class ProdutoDTO {
    private String nome;
    private Integer preco;
    private String descricao;
    private Integer quantidade;

    public ProdutoDTO() {
        Faker faker = new Faker();
        this.nome = faker.name().firstName();
        this.preco = faker.number().randomDigitNotZero();
        this.descricao = faker.book().title();
        this.quantidade = faker.number().randomDigitNotZero();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPreco() {
        return preco;
    }

    public void setPreco(Integer preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
