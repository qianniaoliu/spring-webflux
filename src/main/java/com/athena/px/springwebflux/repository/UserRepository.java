package com.athena.px.springwebflux.repository;

import com.athena.px.springwebflux.model.User;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/6/11 16:17
 */
@Repository
public class UserRepository {

    public User getUser(Integer id){
        User user = new User();
        user.setId(1);
        user.setName("athena");
        return user;
    }

    public boolean saveUser(User user){
        return true;
    }
}
