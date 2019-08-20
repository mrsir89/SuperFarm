package com.project.superfarm.repository.file;

import com.project.superfarm.entity.util.ReviewFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<ReviewFiles, Integer> {
    ReviewFiles findByFileName(String fileName);
}
