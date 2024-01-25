package com.toogether.repo.querydsl;

import com.toogether.vo.BoardVO;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepoCustom{
    int findByMax();
}
