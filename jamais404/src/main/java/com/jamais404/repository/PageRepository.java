package com.jamais404.repository;

import com.jamais404.model.Page;
import org.springframework.data.repository.CrudRepository;

public interface PageRepository extends CrudRepository<Page, Integer> {

    Page findByName(String name);

}