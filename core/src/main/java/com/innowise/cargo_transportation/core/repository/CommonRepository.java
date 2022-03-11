package com.innowise.cargo_transportation.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface CommonRepository<T, ID> extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T>{
}
