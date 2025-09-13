package produto.api.application.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class ProdutoReservaDomain {
    private String emailUsuario;
    private Long idProduto;
    private String tipoProduto;
    private BigDecimal precoUnitario;
    private BigDecimal valorTotal;
    private Integer quantidade;

    public ProdutoReservaDomain(String emailUsuario, Long idProduto, String tipoProduto, BigDecimal precoUnitario, BigDecimal valorTotal, Integer quantidade) {
        this.emailUsuario = emailUsuario;
        this.idProduto = idProduto;
        this.tipoProduto = tipoProduto;
        this.precoUnitario = precoUnitario;
        this.valorTotal = valorTotal;
        this.quantidade = quantidade;
    }

    public ProdutoReservaDomain() {
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProdutoReservaDomain that)) return false;
        return Objects.equals(getEmailUsuario(), that.getEmailUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getEmailUsuario());
    }
}
