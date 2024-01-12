package com.toogether.repo;

import com.toogether.mapper.BoardUpdateMapping;
import com.toogether.vo.BoardVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepo extends JpaRepository<BoardVO,Integer> {
    int save(BoardUpdateMapping vo);
}
