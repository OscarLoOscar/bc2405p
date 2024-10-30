package com.bootcamp.bc_forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.bc_forum.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity,Long>{
  
}
