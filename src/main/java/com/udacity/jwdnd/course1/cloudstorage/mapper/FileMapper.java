package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Mapper
@Component
public interface FileMapper {

    @Options(useGeneratedKeys = true, keyProperty = "file.fileId")
    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) " +
            "VALUES ( #{file.fileName}, #{file.contentType}, #{file.fileSize}, #{file.fileData}, #{userId} ) ")
    int saveFile(File file, int userId);

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<File> retrieveFilesByUserId(int userId);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int deleteFile(int noteId);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File retrieveFileById(int fileId);

    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    Optional<File> retrieveFileByName(String fileName);
}
