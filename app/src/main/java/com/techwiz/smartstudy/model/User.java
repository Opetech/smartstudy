package com.techwiz.smartstudy.model;

public class User {
    private int UserId;
    private String firstname;
    private String lastname;
    private String contact;
    private String email;
    private String password;
    private String category;

    public User() {
    }

    public User(String firstname, String lastname, String contact, String email, String password, String category) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.category = category;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
