package com.ecommerce.demo.dto;

import com.ecommerce.demo.enums.Gender;

public class PostDto {
    public static class Builder {
        private Long id;
        private String firstname;
        private String lastname;
        private String username;
        private String email;
        private String password;
        private Integer age;
        private Gender gender;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder firstname(String firstname) { this.firstname = firstname; return this; }
        public Builder lastname(String lastname) { this.lastname = lastname; return this; }
        public Builder username(String username) { this.username = username; return this;}
        public Builder email(String email) { this.email = email; return this; }
        public Builder password(String password) {this.password = password; return this; }
        public Builder age(Integer age) { this.age = age; return this; }
        public Builder gender(Gender gender) { this.gender = gender; return this; }

        public PostDto build() {
            return new PostDto(this);
        }
    }

    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private Integer age;
    private Gender gender;

    private PostDto(Builder builder) {
        this.id = builder.id;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.age = builder.age;
        this.gender = builder.gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
