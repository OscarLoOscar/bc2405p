package com.bootcamp.demo_restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.demo_restapi.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
