package com.ecommerce.demo.entities;

import com.ecommerce.demo.enums.CartStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CartStatus status;

    @Column(name = "total_amount", nullable = false)
    @NotNull(message = "Total amount is required")
    private BigDecimal totalAmount;

    @Column(name = "coupon_code")
    private String couponCode;

    @Column(name = "is_checked_out", nullable = false)
    private Boolean isCheckedOut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "expires_at")
    private ZonedDateTime expiresAt;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private ZonedDateTime updatedAt;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CartStatus getStatus() {
        return status;
    }

    public void setStatus(CartStatus status) {
        this.status = status;
    }

    public @NotNull(message = "Total amount is required") BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(@NotNull(message = "Total amount is required") BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Boolean getCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(Boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ZonedDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(ZonedDateTime expiresAt) {
        this.expiresAt = expiresAt;
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

    public Set<CartItem> getItems() {
        return items;
    }

    public void setItems(Set<CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", client=" + client +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                ", couponCode='" + couponCode + '\'' +
                ", isCheckedOut=" + isCheckedOut +
                ", shippingAddress=" + shippingAddress +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", expiresAt=" + expiresAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", items=" + items +
                '}';
    }
}
