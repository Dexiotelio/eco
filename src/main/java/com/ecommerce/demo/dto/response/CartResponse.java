package com.ecommerce.demo.dto.response;

import com.ecommerce.demo.enums.CartStatus;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class CartResponse {
    public static class Builder {
        private Long id;
        private Long clientId;
        private CartStatus status;
        private BigDecimal totalAmount;
        private String couponCode;
        private Boolean isCheckedOut;
        private Long shippingAddressId;
        private String paymentMethod;
        private ZonedDateTime expiresAt;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder clientId(Long clientId) { this.clientId = clientId; return this; }
        public Builder status(CartStatus status) { this.status = status; return this; }
        public Builder totalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; return this; }
        public Builder couponCode(String couponCode) { this.couponCode = couponCode; return this; }
        public Builder isCheckedOut(Boolean isCheckedOut) { this.isCheckedOut = isCheckedOut; return this; }
        public Builder shippingAddressId(Long shippingAddressId) { this.shippingAddressId = shippingAddressId; return this; }
        public Builder paymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; return this; }
        public Builder expiresAt(ZonedDateTime expiresAt) { this.expiresAt = expiresAt; return this; }
        public Builder createdAt(ZonedDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(ZonedDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public CartResponse build() {
            return new CartResponse(this);
        }
    }

    private final Long id;
    private final Long clientId;
    private final CartStatus status;
    private final BigDecimal totalAmount;
    private final String couponCode;
    private final Boolean isCheckedOut;
    private final Long shippingAddressId;
    private final String paymentMethod;
    private final ZonedDateTime expiresAt;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;

    private CartResponse(Builder builder) {
        this.id = builder.id;
        this.clientId = builder.clientId;
        this.status = builder.status;
        this.totalAmount = builder.totalAmount;
        this.couponCode = builder.couponCode;
        this.isCheckedOut = builder.isCheckedOut;
        this.shippingAddressId = builder.shippingAddressId;
        this.paymentMethod = builder.paymentMethod;
        this.expiresAt = builder.expiresAt;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getClientId() {
        return clientId;
    }

    public CartStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public Boolean getIsCheckedOut() {
        return isCheckedOut;
    }

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public ZonedDateTime getExpiresAt() {
        return expiresAt;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "CartResponse{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                ", couponCode='" + couponCode + '\'' +
                ", isCheckedOut=" + isCheckedOut +
                ", shippingAddressId=" + shippingAddressId +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", expiresAt=" + expiresAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
