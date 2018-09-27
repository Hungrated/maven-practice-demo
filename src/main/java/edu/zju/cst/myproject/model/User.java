package edu.zju.cst.myproject.model;

import org.hibernate.annotations.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "user")
public class User {
    private int id;
    private String name;

    User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public String sayHello(String guestName) {
        return "Hello" + guestName;
    }
}
