package com.jamais404.repositories;

import com.jamais404.models.Page;

import org.springframework.data.repository.CrudRepository;

public interface PageRepository extends CrudRepository<Page, Integer> {

    Page findByName(String name);

}