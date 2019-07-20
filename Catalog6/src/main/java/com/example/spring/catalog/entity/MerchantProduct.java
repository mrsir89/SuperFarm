package com.example.spring.catalog.entity;

import com.example.spring.catalog.model.ImageUrl;
import com.example.spring.catalog.model.StockType;
import com.example.spring.catalog.model.VariantOption;
import com.example.spring.catalog.util.BooleanToStringConverter;
import com.example.spring.catalog.util.ImageUrlsToStringConverter;
import com.example.spring.catalog.util.VariantOptionsToStringConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "merchant_products")
@DynamicInsert
public class MerchantProduct implements Serializable {

    @Id
    @Column(columnDefinition = "CHAR(36)", name = "id", updatable = false, nullable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "name")
    private String name;

    @Column(name = "handle")
    private String handle;

    @Column(name = "supply_price")
    private Number supplyPrice;

    @Column(name = "markup")
    private Number markup;

    @Column(name = "price")
    private Number price;

    @Column(name = "price_incl_tax")
    private Number priceInclTax;

    @Column(name = "tax")
    private Number tax;

    @Column(name = "tax_rate")
    private Number taxRate;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_urls")
    @Convert(converter = ImageUrlsToStringConverter.class)
    private Collection<ImageUrl> imageUrls;

    @Column(name = "has_variants")
    @Convert(converter = BooleanToStringConverter.class)
    private Boolean hasVariants;

    @Column(name = "variant_options")
    @Convert(converter = VariantOptionsToStringConverter.class)
    private Collection<VariantOption> variantOptions;

    @Column(name = "sku")
    private String sku;

    @Column(name = "stock_type")
    @Enumerated(EnumType.STRING)
    private StockType stockType;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public Number getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(Number supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public Number getMarkup() {
        return markup;
    }

    public void setMarkup(Number markup) {
        this.markup = markup;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public Number getPriceInclTax() {
        return priceInclTax;
    }

    public void setPriceInclTax(Number priceInclTax) {
        this.priceInclTax = priceInclTax;
    }

    public Number getTax() {
        return tax;
    }

    public void setTax(Number tax) {
        this.tax = tax;
    }

    public Number getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Number taxRate) {
        this.taxRate = taxRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Collection<ImageUrl> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(Collection<ImageUrl> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Boolean getHasVariants() {
        return hasVariants;
    }

    public void setHasVariants(Boolean hasVariants) {
        this.hasVariants = hasVariants;
    }

    public Collection<VariantOption> getVariantOptions() {
        return variantOptions;
    }

    public void setVariantOptions(Collection<VariantOption> variantOptions) {
        this.variantOptions = variantOptions;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public StockType getStockType() {
        return stockType;
    }

    public void setStockType(StockType stockType) {
        this.stockType = stockType;
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
