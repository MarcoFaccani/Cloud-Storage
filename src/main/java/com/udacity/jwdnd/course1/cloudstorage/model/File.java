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

    //TODO: delete class

    private int fileId;
    private int userId;
    private String name;
    private String contentType;
    private String size;
    private Blob data;

}
