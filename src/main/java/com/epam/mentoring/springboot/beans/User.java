package com.epam.mentoring.springboot.beans;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

public class User {


    private int id;

    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-z-A-Z]*")
    private String name;

    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-z-A-Z]*")
    private String surname;

    @Past
    @NotNull
    private Date birth;

    public User() {
    }

    public User(int id, String name, String surname, Date birth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth=" + birth +
                '}';
    }
}
