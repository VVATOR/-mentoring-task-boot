package com.epam.mentoring.springboot.beans;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @NotBlank
  @Pattern(regexp = "[a-z-A-Z]*")
  @Column(name = "name")
  private String name;

  @NotNull
  @NotBlank
  @Pattern(regexp = "[a-z-A-Z]*")
  @Column(name = "surname")
  private String surname;

  @Past
  @NotNull
  @Column(name = "birth")
  private Date birth;

  public User() {
  }

  public User(Long id, String name, String surname, Date birth) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.birth = birth;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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
