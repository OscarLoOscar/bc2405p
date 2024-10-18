package com.bootcamp.demo_restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.demo_restapi.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity,Long>{
  
}
