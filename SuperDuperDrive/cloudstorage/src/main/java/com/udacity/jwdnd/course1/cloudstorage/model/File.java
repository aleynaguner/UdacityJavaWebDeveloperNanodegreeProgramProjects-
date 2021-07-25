package com.udacity.jwdnd.course1.cloudstorage.model;

import java.sql.Blob;

public class File {
/*  schema.sql

    fileId INT PRIMARY KEY auto_increment,
    filename VARCHAR,
    contenttype VARCHAR,
    filesize VARCHAR,
    userid INT,
    filedata BLOB,
    foreign key (userid) references USERS(userid)

*/

    private Integer fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Integer userId;
    private Blob fileDate;

    public File(Integer fileId, String fileName, String contentType, String fileSize, Integer userId, Blob fileDate) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.fileDate = fileDate;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Blob getFileDate() {
        return fileDate;
    }

    public void setFileDate(Blob fileDate) {
        this.fileDate = fileDate;
    }
}
