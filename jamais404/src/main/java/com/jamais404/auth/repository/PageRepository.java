package com.jamais404.auth.repository;

import com.jamais404.model.Page;
import com.jamais404.model.User;

import org.hibernate.mapping.Set;
import org.springframework.data.repository.CrudRepository;

public interface PageRepository extends CrudRepository<Page, Integer> {

    Page findByName(String name);

}