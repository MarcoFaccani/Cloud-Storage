package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;

@Service
@Data
public class UploadFileService {

    @Autowired
    private FileMapper fileMapper;

    public int upload(MultipartFile file, int userId) {
         int i = fileMapper.saveFile(file, userId);
         return i;

    }

}
