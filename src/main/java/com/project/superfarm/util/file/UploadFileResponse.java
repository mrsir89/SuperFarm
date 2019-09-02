package com.project.superfarm.util.file;

import lombok.Getter;
import lombok.Setter;

public class UploadFileResponse {
    @Getter
    @Setter
    private String replaceFileName;

    @Getter
    @Setter
    private String originFileName;

    @Getter
    @Setter
    private String fileDownloadUri;

    @Getter
    @Setter
    private String fileType;

    @Getter
    @Setter
    private long size;

    public UploadFileResponse(String originFileName, String replaceFileName, String fileDownloadUri, String fileType, long size) {
        this.originFileName = originFileName;
        this.replaceFileName = replaceFileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
}