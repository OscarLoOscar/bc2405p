package com.bootcamp.bc_forum.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import com.bootcamp.bc_forum.entity.AddressEntity;
import com.bootcamp.bc_forum.entity.CommentEntity;
import com.bootcamp.bc_forum.entity.CompanyEntity;
import com.bootcamp.bc_forum.entity.GeoEntity;
import com.bootcamp.bc_forum.entity.PostEntity;
import com.bootcamp.bc_forum.entity.UserEntity;
import com.bootcamp.bc_forum.model.CommentPlaceHolder;
import com.bootcamp.bc_forum.model.Mapper;
import com.bootcamp.bc_forum.model.PostPlaceHolder;
import com.bootcamp.bc_forum.model.UserPlaceHolder;
import com.bootcamp.bc_forum.service.CommentService;
import com.bootcamp.bc_forum.service.JPHService;
import com.bootcamp.bc_forum.service.PostService;
import com.bootcamp.bc_forum.service.UserService;

@Configuration
public class AppRunner implements CommandLineRunner {

  @Autowired
  private UserService userService;

  @Autowired
  private PostService postService;

  @Autowired
  private CommentService commentService;

  @Autowired
  private JPHService jphService;

  @Autowired
  private Mapper mapper;

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Start Server : ");

    System.out.println("Start saving  address, company ,geo, post and comment");
    List<UserPlaceHolder> userPlaceHolders = jphService.getUserRawdata();
    List<PostPlaceHolder> postPlaceHolders = jphService.getPostRawdata();
    List<CommentPlaceHolder> commentPlaceHolders =
        jphService.getCommentRawdata();

    // Convert and construct UserEntity
    List<UserEntity> userEntities = userPlaceHolders.stream().map(uDto -> {
      UserEntity userEntity = this.mapper.map(uDto);

      AddressEntity addressEntity = this.mapper.mapToAddressEntity(uDto);

      GeoEntity geoEntity = this.mapper.mapToGeoEntity(uDto);
      geoEntity.setAddress(addressEntity);

      addressEntity.setGeoEntity(geoEntity);
      addressEntity.setUser(userEntity);

      CompanyEntity companyEntity = this.mapper.mapToCompanyEntity(uDto);
      companyEntity.setUser(userEntity);

      List<PostEntity> postEntities = postPlaceHolders.stream() //
          .filter(pDto -> pDto.getUserId().equals(uDto.getId())) //
          .map(pDto -> {
            PostEntity postEntity = this.mapper.map(pDto);

            List<CommentEntity> commentEntities = commentPlaceHolders.stream() //
                .filter(cDto -> cDto.getPostId().equals(pDto.getId())) //
                .map(cDto -> {
                  CommentEntity commentEntity = this.mapper.map(cDto);
                  commentEntity.setPosts(postEntity);
                  return commentEntity;
                }).collect(Collectors.toList());

            postEntity.setComments(commentEntities);
            postEntity.setUser(userEntity);
            return postEntity;
          }) //
          .collect(Collectors.toList());

      userEntity.setAddressEntity(addressEntity);
      userEntity.setCompanyEntity(companyEntity);
      userEntity.setPosts(postEntities);
      return userEntity;
    }).collect(Collectors.toList());

      // Save all data to all Users, Posts, Comments, Addresses, Companies, Geos
      userService.saveAll(userEntities);
    // for-loop X 3
    // for (UserPlaceHolder user : userPlaceHolders) {
    //   for (PostPlaceHolder post : postPlaceHolders) {
    //     for (CommentPlaceHolder comment : commentPlaceHolders) {

    //     }
    //   }
    // }
  }

}
