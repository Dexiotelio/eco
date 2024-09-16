package com.ecommerce.demo.dto.request;

import com.ecommerce.demo.enums.CartStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class CartRequest {
    @NotNull(message = "Client ID is required")
    private Long clientId;

    @NotNull(message = "Status is required")
    private CartStatus status;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.00", message = "Total amount must be positive")
    private BigDecimal totalAmount;

    private String couponCode;

    @NotNull(message = "Checked out status is required")
    private Boolean isCheckedOut;

    private Long shippingAddressId;

    @Size(max = 30, message = "Payment method cannot exceed 30 characters")
    private String paymentMethod;

    private ZonedDateTime expiresAt;

    public @NotNull(message = "Client ID is required") Long getClientId() {
        return clientId;
    }

    public void setClientId(@NotNull(message = "Client ID is required") Long clientId) {
        this.clientId = clientId;
    }

    public @NotNull(message = "Status is required") CartStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "Status is required") CartStatus status) {
        this.status = status;
    }

    public @NotNull(message = "Total amount is required") @DecimalMin(value = "0.00", message = "Total amount must be positive") BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(@NotNull(message = "Total amount is required") @DecimalMin(value = "0.00", message = "Total amount must be positive") BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public @NotNull(message = "Checked out status is required") Boolean getCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(@NotNull(message = "Checked out status is required") Boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public @Size(max = 30, message = "Payment method cannot exceed 30 characters") String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(@Size(max = 30, message = "Payment method cannot exceed 30 characters") String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ZonedDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(ZonedDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return "CartRequest{" +
                "clientId=" + clientId +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                ", couponCode='" + couponCode + '\'' +
                ", isCheckedOut=" + isCheckedOut +
                ", shippingAddressId=" + shippingAddressId +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", expiresAt=" + expiresAt +
                '}';
    }
}
