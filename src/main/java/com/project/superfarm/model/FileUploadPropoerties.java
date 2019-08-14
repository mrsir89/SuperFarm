package com.project.superfarm.model;


import org.springframework.boot.context.properties.ConfigurationProperties;


public class FileUploadPropoerties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
