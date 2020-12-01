package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.util.List;

@Service
@Data
public class FileService {

    @Autowired
    private FileMapper fileMapper;

    public int save(MultipartFile multipartFile, int userId) throws Exception{
        File file = File.builder()
                .userId(userId)
                .fileName(multipartFile.getOriginalFilename())
                .contentType(multipartFile.getContentType())
                .fileData(multipartFile.getBytes())
                .fileSize(String.valueOf(multipartFile.getSize()))
                .build();
         return fileMapper.saveFile(file, userId);
    }

    public File getFile(final int fileId) {
        return fileMapper.retrieveFileById(fileId);
    }

    public List<File> getAllFiles(final int userId) {
        return fileMapper.retrieveFilesByUserId(userId);
    }

    public int deleteFile(final int noteId) {
        return fileMapper.deleteFile(noteId);
    }

    public boolean isFileNameAvailable(String fileName) {
        return fileMapper.retrieveFileByName(fileName).isEmpty();
    }
}
