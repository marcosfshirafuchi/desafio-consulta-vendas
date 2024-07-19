package com.devsuperior.dsmeta.dto;


import com.devsuperior.dsmeta.projections.SummaryMinProjection;

/**
 * <h1> DEVSUPERIOR - Java Spring Professional - Capítulo: JPA, consultas SQL e JPQL</h1>
 * DESAFIO: Consulta vendas
 * <p>
 * <b>Note:</b> Desenvolvido na linguagem Java.
 *
 * @author  Marcos Ferreira Shirafuchi
 * @version 1.0
 * @since   18/07/2024
 */
public class SummaryMinDTO {
    private String sellerName;
    private Double total;

    public SummaryMinDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SummaryMinDTO(SummaryMinProjection projection){
        sellerName = projection.sellerName();
        total = projection.total();
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
