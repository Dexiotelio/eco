package com.ecommerce.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

import java.time.ZonedDateTime;

@Entity
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Long discountId;

    @Column(name = "min_quantity")
    @Min(value = 0, message = "Minimum quantity must be at least 0")
    private Double minQuantity;

    @Column(name = "discount_percentage")
    @DecimalMin(value = "0.1", inclusive = false, message = "Discount percentage must be greater than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "Discount percentage must be less than or equal to 100")
    private Float discountPercentage;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "expiry_date")
    private ZonedDateTime expiryDate;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private ZonedDateTime updatedAt;

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public @Min(value = 0, message = "Minimum quantity must be at least 0") Double getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(@Min(value = 0, message = "Minimum quantity must be at least 0") Double minQuantity) {
        this.minQuantity = minQuantity;
    }

    public @DecimalMin(value = "0.1", inclusive = false, message = "Discount percentage must be greater than 0") @DecimalMax(value = "100.0", inclusive = true, message = "Discount percentage must be less than or equal to 100") Float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(@DecimalMin(value = "0.1", inclusive = false, message = "Discount percentage must be greater than 0") @DecimalMax(value = "100.0", inclusive = true, message = "Discount percentage must be less than or equal to 100") Float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public ZonedDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(ZonedDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discountId=" + discountId +
                ", minQuantity=" + minQuantity +
                ", discountPercentage=" + discountPercentage +
                ", active=" + active +
                ", expiryDate=" + expiryDate +
                ", version=" + version +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
