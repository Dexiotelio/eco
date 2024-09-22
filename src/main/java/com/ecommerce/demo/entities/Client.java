package com.ecommerce.demo.entities;

import com.ecommerce.demo.dto.request.ClientRequest;
import com.ecommerce.demo.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Clients",
        uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email"}),
        indexes = {
            @Index(name = "idx_firstname", columnList = "firstName"),
            @Index(name = "idx_lastname", columnList = "lastName"),
            @Index(name = "idx_age",  columnList = "age"),
            @Index(name = "idx_created_at", columnList = "createdAt")
        })
@EntityListeners(AuditingEntityListener.class)
public class Client implements Serializable {
    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String userName;
        private String email;
        private Integer age;
        private Gender gender;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder firstName(String firstName) { this.firstName = firstName; return this; }
        public Builder lastName(String lastName) { this.lastName = lastName; return this; }
        public Builder userName(String userName) { this.userName = userName; return this;}
        public Builder email(String email) { this.email = email; return this; }
        public Builder age(Integer age) { this.age = age; return this; }
        public Builder gender(Gender gender) { this.gender = gender; return this; }
        public Builder createdAt(ZonedDateTime createdAt) {this.createdAt = createdAt; return this; }
        public Builder updatedAt(ZonedDateTime updatedAt) {this.updatedAt = updatedAt; return this; }

        public Client build() {
            return new Client(this);
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username is required")
    @Size(max = 30, message = "Username cannot exceed 30 characters")
    @Pattern(regexp = "^\\w+$", message = "Username can only contain alphanumeric characters and underscores")
    private String userName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Column(nullable = false)
    @NotNull(message = "Age is required")
    @Min(value = 19, message = "Age must be greater than 18")
    private Integer age;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender is required")
    private Gender gender;

    @Column(nullable = false)
    @NotBlank(message = "Phone is required")
    @Size(max = 20, message = "Phone cannot exceed 20 characters")
    private String phone;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("streetNumber ASC")
    private Set<Address> addresses;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdAt")
    private List<Cart> carts = new ArrayList<>();

    public Client(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.userName = builder.userName;
        this.email = builder.email;
        this.age = builder.age;
        this.gender = builder.gender;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static Client toClientResponse(ClientRequest request) {
        if (request == null) {
            return null;
        }
        return new Client.Builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .userName(request.getUsername())
                .email(request.getEmail())
                .age(request.getAge())
                .gender(request.getGender())
                .build();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

