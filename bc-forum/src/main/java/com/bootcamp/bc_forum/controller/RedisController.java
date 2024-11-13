package com.bootcamp.bc_forum.controller;

import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_forum.model.UserDTO;
import com.bootcamp.bc_forum.redis.RedisHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class RedisController {

  @Autowired
  private RedisHelper redisHelper;

  @GetMapping("/get")
  public String get(@RequestParam String param) {
    System.out.println("Success get data from Redis");
    return (String) redisHelper.get(param);
  }

  @PostMapping("/add")
  public boolean postMethodName(@RequestParam String param,
      @RequestBody String data) {
    System.out.println("Success save in Redis");
    return redisHelper.set(param, data);
  }


  @GetMapping("/getuser")
  public UserDTO getuser(@RequestParam String param) throws JsonProcessingException {
    // return (UserDTO) redisHelper.get("User"+param);
    /**
     * Resolved [java.lang.ClassCastException: class java.util.LinkedHashMap cannot be cast to class com.bootcamp.bc_forum.model.UserDTO (java.util.LinkedHashMap is in module java.base of loader
     * 'bootstrap'; com.bootcamp.bc_forum.model.UserDTO is in unnamed module of loader org.springframework.boot.devtools.restart.classloader.RestartClassLoader @c754917)]
     */
  return redisHelper.get("User" + param,UserDTO.class);
  
  }

}
