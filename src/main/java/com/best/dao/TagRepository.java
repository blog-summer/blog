package com.best.dao;

import com.best.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag getByName(String name);
}
