package produto.api.application.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class ReservaItemDomain {

    private Long id;
    private Long reserva_id;
    private Long produto_id;
    private String nomeProduto;
    private String tipoProduto;
    private Integer quantidade;
    private BigDecimal preco_unitario;
    private BigDecimal valor_total_item;

    public ReservaItemDomain(Long id, Long reserva_id, Long produto_id, String nomeProduto, String tipoProduto, Integer quantidade, BigDecimal preco_unitario, BigDecimal valor_total_item) {
        this.id = id;
        this.reserva_id = reserva_id;
        this.produto_id = produto_id;
        this.nomeProduto = nomeProduto;
        this.tipoProduto = tipoProduto;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
        this.valor_total_item = valor_total_item;
    }

    public ReservaItemDomain() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(Long reserva_id) {
        this.reserva_id = reserva_id;
    }

    public Long getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Long produto_id) {
        this.produto_id = produto_id;
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(BigDecimal preco_unitario) {
        this.preco_unitario = preco_unitario;
    }

    public BigDecimal getValor_total_item() {
        return valor_total_item;
    }

    public void setValor_total_item(BigDecimal valor_total_item) {
        this.valor_total_item = valor_total_item;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ReservaItemDomain that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
