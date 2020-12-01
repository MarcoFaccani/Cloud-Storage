package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class File {

    private int fileId;
    private int userId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private byte[] fileData;

}
