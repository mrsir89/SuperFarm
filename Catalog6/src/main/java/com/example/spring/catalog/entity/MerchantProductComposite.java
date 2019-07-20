package com.example.spring.catalog.entity;

import com.example.spring.catalog.util.BooleanToStringConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "merchant_product_composites")
@DynamicInsert
public class MerchantProductComposite implements Serializable {

    @Id
    @Column(columnDefinition = "BIGINT", name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private MerchantProduct parent;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private MerchantProduct item;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "is_active")
    @Convert(converter = BooleanToStringConverter.class)
    private Boolean isActive;

    @Column(name = "is_deleted")
    @Convert(converter = BooleanToStringConverter.class)
    private Boolean isDeleted;

    @Column(name = "created")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "updated")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public MerchantProduct getParent() {
        return parent;
    }

    public void setParent(MerchantProduct parent) {
        this.parent = parent;
    }

    public MerchantProduct getItem() {
        return item;
    }

    public void setItem(MerchantProduct item) {
        this.item = item;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
