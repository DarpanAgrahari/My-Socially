package com.msh.frontend;

public class Users {

    String firstName, lastName, age,phoneNumber,email;
    public Users(){

    }




    public Users(String firstName, String lastName, String phoneNumber, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }



    // Getters and Setters
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGmail() {
        return email;
    }

    public void setGmail(String gmail) {
        this.email = gmail;
    }

}


