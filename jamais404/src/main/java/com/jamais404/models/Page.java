package com.jamais404.models;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pages")
public class Page{

    // FILEDS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(
        name = "pageId",
        nullable = false
    )
    private User owner;

    @Column(
        name = "name",
        nullable = false
    )
    private String name;

    @Column(
        name = "date",
        nullable = false
    )
    private Timestamp date;

    @OneToMany(mappedBy = "page")
    private Set<Comment> comments;


    // METHODS
    public Page() {}

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public User getOwner(){
        return owner;
    }

    public void setOwner(User owner){
        this.owner = owner;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Timestamp getDate(){
        return date;
    }

    public void setDate(Timestamp date){
        this.date = date;
    }

    public Set<Comment> getComments(){
        return comments;
    }

    public void setComments(Set<Comment> comments){
        this.comments = comments;
    }
}