package com.jamais404.auth.repository;

import com.jamais404.model.Comment;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

}