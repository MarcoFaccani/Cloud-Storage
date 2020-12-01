package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
@Component
public interface FileMapper {

    //@Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES ( #{file.name}, #{file.contentType}, #{file.size}, #{file.bytes}, #{userId} ) ")
    @Insert("INSERT INTO FILES (filename) VALUES ( #{file.name} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int saveFile(MultipartFile file, int userId);

    @Select("SELECT * FROM FILES")
    List<File> retrieveFiles(int userId);
}
