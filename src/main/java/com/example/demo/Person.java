package com.example.demo;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pid;

/*    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "p_id", referencedColumnName = "c_id")
    Courier courier;*/

/*    @OneToMany()
    @JoinColumn(name = "o_id",referencedColumnName = "p_id")
    private Set<Order> p_order;*/

    private String firstName;
    private String lastName;
    private String fatherName;
    private String login;
    private String password;
    private String email;
    private Boolean is_courier;
    private String telephone;


    protected Person (){}

    public long getPid() {
        return pid;
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

    public String getFatherName() {
        return fatherName;
    }
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIs_courier() {
        return is_courier;
    }
    public void setIs_courier(Boolean is_courier) {
        this.is_courier = is_courier;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
