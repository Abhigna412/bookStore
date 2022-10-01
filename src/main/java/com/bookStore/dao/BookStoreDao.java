package com.bookStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookStore.entity.Product;
@Repository
public interface BookStoreDao extends JpaRepository<Product, Integer>{

}
