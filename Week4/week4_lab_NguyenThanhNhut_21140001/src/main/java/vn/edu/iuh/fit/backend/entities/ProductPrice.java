/*
 * @ (#) ProductPriceEntity.java       1.0     28/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.entities;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 28/09/2024
 * @version:    1.0
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "product_price")
@NamedQueries({
        @NamedQuery(name = "ProductPrice.findAll", query = "select p from ProductPrice p"),
        @NamedQuery(name = "ProductPrice.findById", query = "select p from ProductPrice p where p.priceId = :priceId"),
        @NamedQuery(name = "ProductPrice.findActivePriceByProduct", query = "select p from ProductPrice p where p.product.productId = :productId and p.status = 1"),
        @NamedQuery(name = "ProductPrice.findAllProductPriceByProductId", query = "select p from ProductPrice p where p.product.productId = :productId")
})
public class ProductPrice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "price_id", nullable = false)
    private Integer priceId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @Column(name = "apply_date", nullable = false)
    private Timestamp applyDate;

    @NotNull
    @Column(name = "value", nullable = false)
    private double value;

    @Lob
    @Column(name = "note")
    private String note;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    public @NotNull Product getProduct() {
        return product;
    }

    public void setProduct(@NotNull Product product) {
        this.product = product;
    }

    public @NotNull Timestamp getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(@NotNull Timestamp applyDate) {
        this.applyDate = applyDate;
    }

    @NotNull
    public double getValue() {
        return value;
    }

    public void setValue(@NotNull double value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public @NotNull Integer getStatus() {
        return status;
    }

    public void setStatus(@NotNull Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPrice that = (ProductPrice) o;
        return Objects.equals(priceId, that.priceId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(priceId);
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "priceId=" + priceId +
                ", product=" + product +
                ", applyDate=" + applyDate +
                ", value=" + value +
                ", note='" + note + '\'' +
                ", status=" + status +
                '}';
    }
}
