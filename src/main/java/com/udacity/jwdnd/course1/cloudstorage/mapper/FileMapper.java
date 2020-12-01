package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
@Component
public interface FileMapper {

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES (  #{file.name}, #{file.contentType}, #{file.size}, #{file.bytes}, #{userId} ) ")
    int saveFile(MultipartFile file, int userId);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<MultipartFile> retrieveFiles(int userId);
}
