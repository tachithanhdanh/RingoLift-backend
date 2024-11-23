package com.gorgeous.ringolift.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_gender")
public class Gender {

    @Id
    @Column(name = "gender", columnDefinition = "ENUM('MALE', 'FEMALE')", nullable = false, unique = true)
    private String gender;

    // Constructors

    public Gender() {
    }

    public Gender(String gender) {
        this.gender = gender;
    }

    // Getters and Setters

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
