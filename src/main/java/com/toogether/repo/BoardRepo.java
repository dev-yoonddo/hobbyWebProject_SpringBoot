package com.toogether.repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toogether.dto.BoardUpdateDTO;
import com.toogether.mapper.BoardUpdateMapping;
import com.toogether.vo.BoardVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepo extends JpaRepository<BoardVO,Integer>, BoardRepoCustom{
    Optional<BoardVO> save(BoardUpdateDTO vo);
    List<Integer> findByBoardID();
}
