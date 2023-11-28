package com.aditya.BlogPost.dao;

import com.aditya.BlogPost.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityDao extends JpaRepository<Authority, Integer> {
}
