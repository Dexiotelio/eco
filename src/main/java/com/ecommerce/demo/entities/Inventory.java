package com.ecommerce.demo.entities;

import com.ecommerce.demo.enums.InventoryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long inventoryId;

    @Column(name = "quantity", nullable = false)
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Integer quantity;

    @Column(name = "cost")
    @DecimalMin(value = "0.01", inclusive = false, message = "Cost must be greater than 0")
    private BigDecimal cost;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InventoryStatus status;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private ZonedDateTime updatedAt;

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public @Min(value = 0, message = "Quantity must be greater than or equal to 0") Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(value = 0, message = "Quantity must be greater than or equal to 0") Integer quantity) {
        this.quantity = quantity;
    }

    public @DecimalMin(value = "0.01", inclusive = false, message = "Cost must be greater than 0") BigDecimal getCost() {
        return cost;
    }

    public void setCost(@DecimalMin(value = "0.01", inclusive = false, message = "Cost must be greater than 0") BigDecimal cost) {
        this.cost = cost;
    }

    public InventoryStatus getStatus() {
        return status;
    }

    public void setStatus(InventoryStatus status) {
        this.status = status;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", quantity=" + quantity +
                ", cost=" + cost +
                ", status=" + status +
                ", version=" + version +
                ", supplier='" + supplier + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
