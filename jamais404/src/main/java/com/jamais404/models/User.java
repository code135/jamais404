package com.jamais404.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    // FIELDS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",
    length = 32,
    nullable = false
    )
    private String name;

    @Column(name = "email",
    nullable = false)
    private String email;

    @Column(name = "password",
    length = 255,
    nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Comment> ownedComments;

    @OneToMany(mappedBy = "owner")
    private Set<Page> ownedPages;

    // METHODS
    public User() {}

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}