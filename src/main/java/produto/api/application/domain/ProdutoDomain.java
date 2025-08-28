package produto.api.application.domain;

import java.math.BigDecimal;

public class ProdutoDomain {
    private Long id;
    private String nomeProduto;
    private String tipoProduto;
    private BigDecimal preco;
    private Integer quantidadeEstoque;

    public ProdutoDomain(Long id, String nomeProduto, String tipoProduto, BigDecimal preco, Integer quantidadeEstoque) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.tipoProduto = tipoProduto;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public ProdutoDomain() {
    }

    public ProdutoDomain(ProdutoDomain data){
        this.id = data.getId();
        this.nomeProduto = data.getNomeProduto();
        this.tipoProduto = data.getTipoProduto();
        this.preco = data.getPreco();
        this.quantidadeEstoque = data.getQuantidadeEstoque();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}
