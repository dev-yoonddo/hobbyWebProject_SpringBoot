package com.toogether.repo.querydsl.Impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toogether.controller.BoardController;
import com.toogether.repo.querydsl.BoardRepoCustom;
import com.toogether.vo.QBoardVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RequiredArgsConstructor
public class BoardRepoCustomImpl implements BoardRepoCustom {
    private final JPAQueryFactory queryFactory;
    private final QBoardVO board = QBoardVO.boardVO;
    private final Logger log = LoggerFactory.getLogger(BoardController.class);

    @Override
    public int findByMax(){
        log.debug("findByMax 메서드 실행");
        return queryFactory.select(board.boardID.max())
                .from(board)
                .fetchFirst();
    }


}
