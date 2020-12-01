package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CredentialMapper {

    @Options(useGeneratedKeys = true, keyProperty = "credential.credentialId")
    @Insert("INSERT INTO CREDENTIALS (userid, url, username, password, key) " +
            "VALUES (#{userId}, #{credential.url}, #{credential.username}, #{credential.password}, #{credential.key} ) ")
    int save(Credential credential, Integer userId);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> retrieveAllCredentialsByUserId(Integer userId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    int deleteCredentialById(int credentialId);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{username}, password=#{password}, key=#{key} " +
            "WHERE credentialid = #{credentialId}")
    int update(Credential credential);
}
