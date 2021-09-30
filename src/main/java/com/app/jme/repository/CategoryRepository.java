package com.app.jme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.jme.model.exam.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
