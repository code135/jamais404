package com.jamais404.model;

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

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "pages")
public class Page{

    // FILEDS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(
        name = "userId",
        nullable = false
    )
    private User owner;

    @Column(
        name = "name",
        nullable = false
    )
    private String name;

    @Column(
        name = "datetime",
        nullable = false
    )
    @CreationTimestamp
    private Timestamp datetime;

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

    public Timestamp getDatetime(){
        return datetime;
    }

    public void setDatetime(Timestamp datetime){
        this.datetime = datetime;
    }

    public Set<Comment> getComments(){
        return comments;
    }

    public void setComments(Set<Comment> comments){
        this.comments = comments;
    }
}