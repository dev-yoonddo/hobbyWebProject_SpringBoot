package com.toogether.repo;

import com.toogether.vo.FileVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<FileVO, Integer> {
}
