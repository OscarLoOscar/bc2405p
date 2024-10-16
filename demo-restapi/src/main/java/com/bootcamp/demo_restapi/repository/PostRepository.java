package com.bootcamp.demo_restapi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bootcamp.demo_restapi.entity.PostEntity;
import com.bootcamp.demo_restapi.entity.UserEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

  // 1.JPQL
  // 2.defauld case , use the name of java Entity class
  // 3.when you use nativeQuery , use the field name of database

  // select * from Users where name = ? and website = ?
  List<PostEntity> findByTitleAndBody(String title, String body);

  // select * from Users where name = ? or website = ?
  // List<PostEntity> findByTitleAndBody(String title, String body);

  // @Query("SELECT '*' FROM PostEntity WHERE PostEntity.postId > 2")
  // // defauld case , use the name of java Entity class
  // List<PostEntity> findPostIdBiggerThanTwo();

  @Query(
      value = "SELECT t_posts.userId , t_posts.title FROM t_posts WHERE t_posts.db_postID > 2",
      nativeQuery = true)
  // when you use nativeQuery , use the field name of database
  List<PostEntity> findPostIDBiggerThanTwoVersionTwo();
}
