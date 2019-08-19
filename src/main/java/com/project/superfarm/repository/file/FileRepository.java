package com.project.superfarm.repository.file;

import com.project.superfarm.entity.util.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
    File findByFileName(String fileName);
}
