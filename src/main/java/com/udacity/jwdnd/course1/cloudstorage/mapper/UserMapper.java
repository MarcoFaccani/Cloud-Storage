package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Mapper
@Component
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE username = #{username} ")
    User getUserByUsername(final String username);

    @Insert("INSERT INTO USERS (userId, firstName, lastName, userName, salt, password) VALUES (#{userId}, #{firstName}, #{lastName}, #{username}, #{salt}, #{password}) ")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int saveNewUser(User user);
}
