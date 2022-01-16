package com.oyaerdayi.dto;

public class UserDto {

    //    private Long id;
    private String name;
    private String surname;
    private String email;
    private String userName;
    private String phoneNumber;

    public UserDto(String name, String surname, String email, String userName, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}