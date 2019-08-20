package com.project.superfarm.service;


import com.project.superfarm.entity.util.ReviewFiles;
import com.project.superfarm.repository.file.FileRepository;
import com.project.superfarm.util.ExceptionList.FileStorageException;
import com.project.superfarm.util.ExceptionList.MyFileNotFoundException;
import com.project.superfarm.util.file.FileStorageProperties;
import com.project.superfarm.util.file.HashingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, HttpServletRequest request, Principal principal,Long reviewBoardNum) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Move file to the target location
            // 지금은 동시에 동일한 파일명을 가진 파일을 올릴 경우는 처리가 안 될듯??
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // 파일 명 변경 (동일한 이름을 가진 파일이 들어오더라도 중복되지 않게 하기 위함)
            HashingUtil hashingUtil = new HashingUtil();
            // 동일한 파일이라도 시간에 따라 파일명이 바뀌게 한다.
            String replaceFileName = hashingUtil.sha256Encoding(fileName + LocalDateTime.now());
            Files.move(targetLocation, targetLocation.resolveSibling(replaceFileName));

            // 파일 명 변경 후 DB 매핑작업 진행.
            //Member member = (Member) PrincipalUtil.from(principal);
            ReviewFiles reviewFilesEntity = new ReviewFiles();
            reviewFilesEntity.setFileOriginName(fileName);
            reviewFilesEntity.setFileName(replaceFileName);
            reviewFilesEntity.setFileSize(file.getSize());
            reviewFilesEntity.setFileUploadIp(request.getRemoteAddr());

            // 실제 운용 시 위 코드로 전환 (아래 10001은 테스트용)
            // reviewFilesEntity.setMemberId(member.getMemberId());
            // reviewBoardnum를 넣어 준다.
            reviewFilesEntity.setUserId(principal.getName());
            reviewFilesEntity.setReviewBoardNum(reviewBoardNum);
            reviewFilesEntity.setFileContentType(file.getContentType());

            fileRepository.save(reviewFilesEntity);

            return replaceFileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("ReviewFiles not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("ReviewFiles not found " + fileName, ex);
        }
    }

    public String retrieveFileContentType(String fileName) {
        return fileRepository.findByFileName(fileName).getFileContentType();
    }
}