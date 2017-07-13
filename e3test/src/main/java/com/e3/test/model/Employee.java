package com.e3.test.model;

import javax.persistence.*;

/**
 * Created by e3learning dev on 14/07/2017.
 */
@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastNamel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNamel() {
        return lastNamel;
    }

    public void setLastNamel(String lastNamel) {
        this.lastNamel = lastNamel;
    }
}
