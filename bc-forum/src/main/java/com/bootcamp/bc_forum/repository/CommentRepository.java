package com.bootcamp.bc_forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.bc_forum.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

}
