package com.best.dao;

import com.best.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type,Long> {
    Type getByName(String name);
}
